package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.auth.repository.UsuarioRepository;
import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.mapper.CaixinhaMapper;
import org.example.model.dto.SomaPontuacaoDTO;
import org.example.model.entity.Caixinha;
import org.example.model.entity.Usuario;
import org.example.service.utils.CaixinhaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.example.model.entity.Caixinha.VALOR_MINIMO;
import static org.example.service.utils.CaixinhaUtils.ordenaCaixinhas;
import static org.example.service.utils.CaixinhaUtils.ordenaPreRedistribuicao;

@Service
public class RedistribuicaoService {

    private Logger logger = LogManager.getLogger(RedistribuicaoService.class);

    @Autowired
    private CaixinhaUtils caixinhaUtils;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Cacheable("caixinha")
    public List<CaixinhaResponse> calculaDitribuicaoInvestimento(BigDecimal valorSobrou) {
        Caixinha[] caixinhas = caixinhaUtils.montaCaixinhas();

        return doRedistribuicao(valorSobrou, caixinhas);
    }

    @Cacheable("caixinha")
    public List<CaixinhaResponse> calculaDitribuicaoInvestimento(BigDecimal valorSobrou, List<CaixinhaRequest> caixinhas) {
        Caixinha[] caixinhasArray = caixinhas.stream().map(CaixinhaMapper::toEntity).toList().toArray(Caixinha[]::new);

        return doRedistribuicao(valorSobrou, caixinhasArray);
    }

    @Cacheable("caixinha")
    private List<CaixinhaResponse> doRedistribuicao(BigDecimal valorSobrou, Caixinha[] caixinhasArray) {
        SomaPontuacaoDTO somaPontuacaoDTO = calculaSomaPontuacao(caixinhasArray);

        BigDecimal soma = somaPontuacaoDTO.getSoma();
        BigDecimal totalSomaPontuacao = somaPontuacaoDTO.getTotalSomaPontuacao();

        if (valorSobrou.compareTo(soma) >= 0) {
            Arrays.stream(caixinhasArray).toList().forEach(CaixinhaMapper::toQuitada);
            return Arrays.stream(caixinhasArray).toList().stream().map(CaixinhaMapper::toResponse).toList();
        }

        redistribuir(valorSobrou, caixinhasArray, totalSomaPontuacao);

        colocaValorMinimoEReajustaCaixinhas(caixinhasArray);

        return ordenaCaixinhas(caixinhasArray);
    }

    private void colocaValorMinimoEReajustaCaixinhas(Caixinha[] caixinhasArray) {
        BigDecimal somaDiferencas = BigDecimal.ZERO;
        long contador = 0L;
        BigDecimal valorMinimoPorCaixinha = VALOR_MINIMO.multiply(BigDecimal.valueOf(caixinhasArray.length / 2));
        for (Caixinha caixinha : caixinhasArray) {
            if (caixinha.getInvestimento().compareTo(VALOR_MINIMO.add(BigDecimal.ONE)) < 0) {
                somaDiferencas = somaDiferencas.add(VALOR_MINIMO.subtract(caixinha.getInvestimento()));
                caixinha.setInvestimento(VALOR_MINIMO);
                contador++;
            } else if (caixinha.isQuitada()) {
                contador++;
            } else if (caixinha.getInvestimento().compareTo(valorMinimoPorCaixinha) < 0) {
                contador++;
            }
        }
        BigDecimal valorARemover = somaDiferencas.divide(BigDecimal.valueOf(caixinhasArray.length - contador), MathContext.DECIMAL128);
        for (Caixinha caixinha : caixinhasArray) {
            if (!caixinha.isQuitada() && caixinha.getInvestimento().compareTo(VALOR_MINIMO) != 0
                    && caixinha.getInvestimento().compareTo(valorMinimoPorCaixinha) > 0) {
                caixinha.adicionaInvestimento(valorARemover.negate());
            }
        }
    }

    @Cacheable("caixinha")
    private void redistribuir(BigDecimal valorSobrou, Caixinha[] caixinhasArray, BigDecimal totalSomaPontuacao) {
        SomaPontuacaoDTO dto = SomaPontuacaoDTO.builder()
                .totalSomaPontuacao(totalSomaPontuacao)
                .soma(valorSobrou).build();
        int ck = 1;
        boolean primeiraVez = true;
        do {
            for (Caixinha c : caixinhasArray) {
                distribuir(totalSomaPontuacao, dto, primeiraVez, c);
            }

            primeiraVez = false;
            ck++;
            totalSomaPontuacao = dto.getTotalSomaPontuacao();
        } while (isValorMinimoAndRedistribuiuDezVezes(caixinhasArray, dto.getSoma(), ck));


        redistribuiRestinhos(caixinhasArray, dto.getSoma());
        logger.info(ck);
    }

    private static void distribuir(BigDecimal totalSomaPontuacao, SomaPontuacaoDTO dto, boolean primeiraVez, Caixinha c) {
        if (isQuitadaOrControleProgramadaJaRecebeu(c))
            return;

        BigDecimal investimentoCalculado = c.getPontuacao().divide(totalSomaPontuacao, MathContext.DECIMAL128).multiply(dto.getSoma());
        BigDecimal diferenca = c.getTotal().subtract(c.getArrecadado()).subtract(c.getInvestimento());

        if (isVencimentoProgramadoAndPrimeiraVez(primeiraVez, c)) {
            BigDecimal mesesDiferenca = BigDecimal.valueOf(ChronoUnit.MONTHS.between(LocalDate.now(), c.getDataVencimento())- 1);
            BigDecimal parcelaMinima = diferenca.divide(mesesDiferenca, MathContext.DECIMAL128);
            if (parcelaMinima.compareTo(dto.getSoma()) < 0) {
                c.adicionaInvestimento(parcelaMinima);
                dto.setSoma(dto.getSoma().subtract(parcelaMinima));
                c.setControleVencimentoProgramado(true);
                if (parcelaMinima.compareTo(diferenca) >= 0) {
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
            dto.setTotalSomaPontuacao(dto.getTotalSomaPontuacao().subtract(c.getPontuacao()));
        } else {
            c.adicionaInvestimento(investimentoCalculado);
            dto.setSoma(dto.getSoma().subtract(investimentoCalculado));
        }
    }

    private static boolean isValorMinimoAndRedistribuiuDezVezes(Caixinha[] caixinhasArray, BigDecimal restoTemp, int ck) {
        return restoTemp.compareTo(Caixinha.VALOR_MINIMO.multiply(BigDecimal.valueOf(caixinhasArray.length / 2))) >= 0 && ck < 10;
    }

    private static boolean isVencimentoProgramadoAndPrimeiraVez(boolean primeiraVez, Caixinha c) {
        return primeiraVez && c.isVencimentoProgramado();
    }

    private static boolean isQuitadaOrControleProgramadaJaRecebeu(Caixinha c) {
        return c.isQuitada() || c.isControleVencimentoProgramado();
    }

    private static void redistribuiRestinhos(Caixinha[] caixinhasArray, BigDecimal restoTemp) {
        for (Caixinha c: caixinhasArray) {
            if (!c.isQuitada()){
                BigDecimal valorQueFaltaParaQuitar = c.getTotal().subtract(c.getArrecadado()).subtract(c.getInvestimento());
                if (valorQueFaltaParaQuitar.compareTo(restoTemp) < 0) {
                    c.adicionaInvestimento(valorQueFaltaParaQuitar);
                    restoTemp = restoTemp.subtract(valorQueFaltaParaQuitar);
                    c.setQuitada(true);
                }
            }
        }
        if (restoTemp.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal quantidadeNaoQuitada = BigDecimal.valueOf(Arrays.stream(caixinhasArray).filter(item -> !item.isQuitada()).toList().size());
            BigDecimal restinho = restoTemp.divide(quantidadeNaoQuitada, MathContext.DECIMAL128);
            Arrays.stream(caixinhasArray).toList().forEach(item -> {
                if (!item.isQuitada()){
                    item.adicionaInvestimento(restinho);
                }
            });
        }
    }

    private SomaPontuacaoDTO calculaSomaPontuacao(Caixinha[] caixinhasArray) {
        SomaPontuacaoDTO somaPontuacaoDTO = new SomaPontuacaoDTO();
        for (Caixinha c : caixinhasArray) {
            if (c.isQuitada()) continue;
            c.calculaPontuacao();
            somaPontuacaoDTO.setTotalSomaPontuacao(somaPontuacaoDTO.getTotalSomaPontuacao().add(c.getPontuacao()));
            somaPontuacaoDTO.setSoma(somaPontuacaoDTO.getSoma().add(c.getTotal().subtract(c.getArrecadado())));
        }
        return somaPontuacaoDTO;
    }

    public List<CaixinhaResponse> calculaDitribuicaoInvestimento(BigDecimal valorSobrou, long usuarioId) {
        Usuario usuario = usuarioRepository.findByIdAndAtivoIsTrue(usuarioId);
        if (Objects.isNull(usuario)) {
            return new ArrayList<>();
        }

        List<Caixinha> caixinhas = usuario.getCaixinhas();

        if (valorSobrou.compareTo(BigDecimal.valueOf(caixinhas.size()).multiply(VALOR_MINIMO)) < 0) {
            return new ArrayList<>();
        }

        Caixinha[] caixinhasArray = ordenaPreRedistribuicao(caixinhas);

        return doRedistribuicao(valorSobrou, caixinhasArray);
    }
}
