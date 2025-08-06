package org.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.exception.ValorMinimoException;
import org.example.mapper.CaixinhaMapper;
import org.example.model.dto.SomaPontuacaoDTO;
import org.example.model.entity.Caixinha;
import org.example.model.entity.Usuario;
import org.example.service.validation.CaixinhaValidator;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.example.model.entity.Caixinha.VALOR_MINIMO;
import static org.example.service.utils.CaixinhaUtils.ordenaCaixinhas;
import static org.example.service.utils.CaixinhaUtils.ordenaPreRedistribuicao;

@Service
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RedistribuicaoService {

    private static final Logger logger = LogManager.getLogger(RedistribuicaoService.class);

    private final RedistribuicaoService self;
    private final UsuarioService usuarioService;

    public List<CaixinhaResponse> calculaDistribuicaoInvestimento(
            BigDecimal valorSobrou, List<CaixinhaRequest> caixinhas
    ) {

        List<Caixinha> caixinhasArray = caixinhas.stream().map(CaixinhaMapper::toEntity).toList();

        return self.doRedistribuicao(valorSobrou, caixinhasArray);
    }

    @Cacheable(
            value = "caixinha",
            key = "T(org.example.config.CacheKeyGenerator).gerarChave(#valorSobrou, #caixinhas)"
    )
    public List<CaixinhaResponse> doRedistribuicao(BigDecimal valorSobrou, List<Caixinha> caixinhas) {
        logger.info("Executando redistribuição com cache...");

        caixinhas.forEach(item -> item.adicionaInvestimento(VALOR_MINIMO));
        valorSobrou = valorSobrou.subtract(VALOR_MINIMO.multiply(BigDecimal.valueOf(caixinhas.size())));

        caixinhas = ordenaPreRedistribuicao(caixinhas);

        SomaPontuacaoDTO somaPontuacaoDTO = calculaSomaPontuacao(caixinhas);

        BigDecimal soma = somaPontuacaoDTO.getSoma();
        BigDecimal totalSomaPontuacao = somaPontuacaoDTO.getTotalSomaPontuacao();

        if (valorSobrou.add(VALOR_MINIMO.multiply(BigDecimal.valueOf(caixinhas.size())))
                .compareTo(soma) >= 0
        ) {

            for (Caixinha c : caixinhas) {
                CaixinhaMapper.toQuitada(c);
            }
            logger.warn("Valor que sobrou é suficiente para quitar todas as caixinhas");
            return caixinhas.stream().map(CaixinhaMapper::toResponse).toList();
        }

        redistribuir(valorSobrou, caixinhas, totalSomaPontuacao);

        colocaValorMinimoEReajustaCaixinhas(caixinhas);

        logger.info("Sucesso ao distribuir caixinhas!");
        return ordenaCaixinhas(caixinhas);
    }

    private void colocaValorMinimoEReajustaCaixinhas(List<Caixinha> caixinhas) {
        logger.info("Corrige valor minimo caixinhas");
        BigDecimal somaDiferencas = BigDecimal.ZERO;
        long contador = 0L;

        for (Caixinha caixinha : caixinhas) {
            if (caixinha.getInvestimento()
                    .compareTo(VALOR_MINIMO.add(BigDecimal.ONE)) < 0
            ) {

                somaDiferencas = somaDiferencas
                        .add(caixinha.getInvestimento()
                                .subtract(VALOR_MINIMO)
                                .abs());

                caixinha.setInvestimento(VALOR_MINIMO);
                contador++;
            } else if (caixinha.isQuitada()) {
                contador++;
            }
        }

        if (somaDiferencas.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        BigDecimal divisor = BigDecimal.valueOf(caixinhas.size() - contador);
        if (BigDecimal.ZERO.equals(divisor)) {
            divisor = BigDecimal.ONE;
        }

        BigDecimal valorARemover = somaDiferencas.divide(divisor, MathContext.DECIMAL128);
        logger.info("Valor a remover: {}", valorARemover.round(MathContext.DECIMAL32));
        if (BigDecimal.ONE.compareTo(valorARemover) < 0) {
            for (Caixinha caixinha : caixinhas) {
                if (validaPodeRemover(somaDiferencas, caixinha)) {
                    caixinha.adicionaInvestimento(valorARemover.negate());
                    somaDiferencas = somaDiferencas.subtract(valorARemover);
                }
            }
        }

        if (!BigDecimal.ZERO.equals(somaDiferencas)) {
            adicionaRestinhoFinal(caixinhas, somaDiferencas);
        }
    }

    private static void adicionaRestinhoFinal(List<Caixinha> caixinhas, BigDecimal somaDiferencas) {
        List<Caixinha> listFiltrada = caixinhas.stream()
                .filter(item -> item.getInvestimento()
                        .compareTo(VALOR_MINIMO.add(BigDecimal.ONE)) > 0
                )
                .toList();
        if (!listFiltrada.isEmpty())
            listFiltrada.get(0).adicionaInvestimento(somaDiferencas);
        else
            caixinhas.get(0).adicionaInvestimento(somaDiferencas);
    }

    private static boolean validaPodeRemover(BigDecimal somaDiferencas, Caixinha caixinha) {
        return !caixinha.isQuitada()
                && caixinha.getInvestimento().compareTo(VALOR_MINIMO.add(BigDecimal.ONE)) >= 0
                && BigDecimal.ZERO.equals(somaDiferencas);
    }

    private void redistribuir(
            BigDecimal valorSobrou, List<Caixinha> caixinhasArray, BigDecimal totalSomaPontuacao) {

        logger.info("Início distribuição");
        SomaPontuacaoDTO dto = SomaPontuacaoDTO.builder()
                .totalSomaPontuacao(totalSomaPontuacao)
                .soma(valorSobrou).build();
        int ck = 1;
        boolean primeiraVez = true;
        do {
            for (Caixinha c : caixinhasArray) {
                logger.debug("{} Distribuir {}", ck, c.getNome());
                distribuir(totalSomaPontuacao, dto, primeiraVez, c);
                logger.debug("Total pontuação: {} Soma: {}",
                        dto.getTotalSomaPontuacao().round(MathContext.DECIMAL32),
                        dto.getSoma().round(MathContext.DECIMAL32));
            }

            primeiraVez = false;
            ck++;
            totalSomaPontuacao = dto.getTotalSomaPontuacao();
        } while (isValorMinimoAndRedistribuiuDezVezes(caixinhasArray, dto.getSoma(), ck));
        logger.info("Foram necessárias {} distribuições", ck - 1);

        redistribuiRestinhos(caixinhasArray, dto.getSoma());
    }

    private void distribuir(
            BigDecimal totalSomaPontuacao, SomaPontuacaoDTO dto, boolean primeiraVez, Caixinha c
    ) {

        if (isQuitadaOrControleProgramadaJaRecebeu(c))
            return;

        BigDecimal investimentoCalculado = c.getPontuacao()
                .divide(totalSomaPontuacao, MathContext.DECIMAL128)
                .multiply(dto.getSoma());

        BigDecimal diferenca = c.getTotal()
                .subtract(c.getArrecadado())
                .subtract(c.getInvestimento());

        logger.debug("Investimento calculado: {}",
                investimentoCalculado.round(MathContext.DECIMAL32));

        if (isVencimentoProgramadoAndPrimeiraVez(primeiraVez, c)) {
            BigDecimal mesesDiferenca = getMesesDiferenca(c);

            CaixinhaValidator.validateMesesDiferenca(mesesDiferenca);

            BigDecimal parcelaMinima = diferenca.divide(mesesDiferenca, MathContext.DECIMAL128);

            logger.debug("Parcela mínima: {} Meses diferenca: {} Pontuação: {}",
                    parcelaMinima.round(MathContext.DECIMAL32), mesesDiferenca, c.getPontuacao());

            if (parcelaMinima.compareTo(dto.getSoma()) < 0) {
                c.adicionaInvestimento(parcelaMinima);
                dto.setSoma(dto.getSoma().subtract(parcelaMinima));
                c.setControleVencimentoProgramado(true);
                if (parcelaMinima.compareTo(diferenca) >= 0) {
                    logger.debug("quitou {}", c.getNome());
                    c.setQuitada(true);
                }
                dto.setTotalSomaPontuacao(dto.getTotalSomaPontuacao().subtract(c.getPontuacao()));
                return;
            }
        }

        if (investimentoCalculado.compareTo(diferenca) >= 0) {
            c.adicionaInvestimento(diferenca);
            dto.setSoma(dto.getSoma().subtract(diferenca));
            c.setQuitada(true);
            logger.debug("quitou {}", c.getNome());
            dto.setTotalSomaPontuacao(dto.getTotalSomaPontuacao().subtract(c.getPontuacao()));
        } else {
            c.adicionaInvestimento(investimentoCalculado);
            dto.setSoma(dto.getSoma().subtract(investimentoCalculado));
        }
    }

    private static BigDecimal getMesesDiferenca(Caixinha c) {
        BigDecimal mesesDiferenca = BigDecimal.valueOf(
                ChronoUnit.MONTHS.between(LocalDate.now(), c.getDataVencimento()));

        mesesDiferenca = mesesDiferenca.equals(BigDecimal.ZERO) ? BigDecimal.ONE : mesesDiferenca;

        return mesesDiferenca;
    }

    private boolean isValorMinimoAndRedistribuiuDezVezes(
            List<Caixinha> caixinhasArray, BigDecimal restoTemp, int ck
    ) {

        boolean repeticoesMaxima = ck < 10;
        if (!repeticoesMaxima) {
            logger.warn("Medida para evitar loop infinito aplicada!");
            return false;
        }
        return restoTemp.compareTo(
                Caixinha.VALOR_MINIMO
                        .multiply(BigDecimal.valueOf(caixinhasArray.size() / 2))
        ) >= 0;
    }

    private boolean isVencimentoProgramadoAndPrimeiraVez(boolean primeiraVez, Caixinha c) {
        return primeiraVez && c.getDataVencimento() != null;
    }

    private boolean isQuitadaOrControleProgramadaJaRecebeu(Caixinha c) {
        return c.isQuitada() || c.isControleVencimentoProgramado();
    }

    private void redistribuiRestinhos(List<Caixinha> caixinhasArray, BigDecimal restoTemp) {
        logger.info("Redistribuir 'restinho' tentando quitar {}",
                restoTemp.round(MathContext.DECIMAL32));

        for (Caixinha c: caixinhasArray) {
            if (!c.isQuitada()){
                BigDecimal valorQueFaltaParaQuitar = c.getTotal()
                        .subtract(c.getArrecadado())
                        .subtract(c.getInvestimento());

                if (valorQueFaltaParaQuitar.compareTo(restoTemp) < 0) {
                    logger.debug("Quitando com restinho: {}", c.getNome());
                    c.adicionaInvestimento(valorQueFaltaParaQuitar);
                    restoTemp = restoTemp.subtract(valorQueFaltaParaQuitar);
                    c.setQuitada(true);
                }
            }
        }

        logger.info("Redistribuir 'restinho' {}", restoTemp.round(MathContext.DECIMAL32));
        if (restoTemp.compareTo(BigDecimal.ZERO) > 0) {

            BigDecimal quantidadeNaoQuitada = BigDecimal.valueOf(
                    caixinhasArray.stream().filter(item -> !item.isQuitada()).toList().size()
            );

            if (BigDecimal.ZERO.equals(quantidadeNaoQuitada)) {
                quantidadeNaoQuitada = BigDecimal.ONE;
            }

            BigDecimal restinho = restoTemp.divide(quantidadeNaoQuitada, MathContext.DECIMAL128);
            logger.debug("Restinha adicionado: {}", restinho.round(MathContext.DECIMAL32));
            caixinhasArray.forEach(item -> {
                if (!item.isQuitada()){
                    item.adicionaInvestimento(restinho);
                }
            });
        }
    }

    private SomaPontuacaoDTO calculaSomaPontuacao(List<Caixinha> caixinhasArray) {
        logger.info("Cálculo soma total e pontuação");
        SomaPontuacaoDTO somaPontuacaoDTO = new SomaPontuacaoDTO();
        for (Caixinha c : caixinhasArray) {
            if (c.isQuitada()) continue;
            c.calculaPontuacao();
            somaPontuacaoDTO.setTotalSomaPontuacao(
                    somaPontuacaoDTO.getTotalSomaPontuacao().add(c.getPontuacao()));

            somaPontuacaoDTO.setSoma(
                    somaPontuacaoDTO.getSoma().add(c.getTotal().subtract(c.getArrecadado())));
        }
        return somaPontuacaoDTO;
    }

    public List<CaixinhaResponse> calculaDistribuicaoInvestimento(
            BigDecimal valorSobrou, long usuarioId) {

        Usuario usuario = usuarioService.getUsuario(usuarioId);

        List<Caixinha> caixinhas = usuario.getCaixinhas();

        if (valorSobrou.compareTo(BigDecimal.valueOf(caixinhas.size()).multiply(VALOR_MINIMO)) < 0) {
            throw new ValorMinimoException("Valor insuficiente para cálculos de distribuição");
        }

        return self.doRedistribuicao(valorSobrou, caixinhas);
    }
}
