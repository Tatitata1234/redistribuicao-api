package org.example.service;

import org.example.controller.request.CaixinhaRequest;
import org.example.controller.response.CaixinhaResponse;
import org.example.mapper.CaixinhaMapper;
import org.example.model.dto.SomaPontuacaoDTO;
import org.example.model.entity.Caixinha;
import org.example.model.entity.Usuario;
import org.example.repository.UsuarioRepository;
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

import static org.example.service.utils.CaixinhaUtils.ordenaCaixinhas;

@Service
public class RedistribuicaoService {

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

    private List<CaixinhaResponse> doRedistribuicao(BigDecimal valorSobrou, Caixinha[] caixinhasArray) {
        SomaPontuacaoDTO somaPontuacaoDTO = calculaSomaPontuacao(caixinhasArray);

        BigDecimal soma = somaPontuacaoDTO.getSoma();
        BigDecimal totalSomaPontuacao = somaPontuacaoDTO.getTotalSomaPontuacao();

        if (valorSobrou.compareTo(soma) >= 0) {
            Arrays.stream(caixinhasArray).toList().forEach(CaixinhaMapper::toQuitada);
            return Arrays.stream(caixinhasArray).toList().stream().map(CaixinhaMapper::toResponse).toList();
        }

        redistribuir(valorSobrou, caixinhasArray, totalSomaPontuacao);

        BigDecimal somaDiferencas = BigDecimal.ZERO;
        int contador = 0;
        for (Caixinha caixinha : caixinhasArray) {
            if (caixinha.getInvestimento().compareTo(Caixinha.VALOR_MINIMO) < 0) {
                somaDiferencas = somaDiferencas.add(Caixinha.VALOR_MINIMO.subtract(caixinha.getInvestimento()));
                caixinha.setInvestimento(Caixinha.VALOR_MINIMO);
                contador++;
            } else if (caixinha.isQuitada()) {
                contador++;
            } else if (caixinha.getInvestimento().compareTo(Caixinha.VALOR_MINIMO.multiply(BigDecimal.valueOf(caixinhasArray.length))) < 0) {
                contador++;
            }
        }
        BigDecimal valorARemover = somaDiferencas.divide(BigDecimal.valueOf(caixinhasArray.length-contador), MathContext.DECIMAL128);
        for (Caixinha caixinha : caixinhasArray) {
            if (!caixinha.isQuitada() && caixinha.getInvestimento().compareTo(Caixinha.VALOR_MINIMO) != 0
                    && caixinha.getInvestimento().compareTo(Caixinha.VALOR_MINIMO.multiply(BigDecimal.valueOf(caixinhasArray.length)))>0) {
                caixinha.adicionaInvestimento(valorARemover.negate());
            }
        }

        return ordenaCaixinhas(caixinhasArray);
    }

    private void redistribuir(BigDecimal valorSobrou, Caixinha[] caixinhasArray, BigDecimal totalSomaPontuacao) {
        BigDecimal totalSomaPontuacaoTemp = totalSomaPontuacao;
        BigDecimal resto = valorSobrou;
        BigDecimal restoTemp = valorSobrou;
        int ck = 1;
        boolean primeiraVez = true;
        do {
            for (Caixinha c : caixinhasArray) {

                if (c.isQuitada() || c.isControleVencimentoProgramado()) continue;
                BigDecimal investimentoCalculado = c.getPontuacao().divide(totalSomaPontuacao, MathContext.DECIMAL128).multiply(restoTemp);
                BigDecimal diferenca = c.getTotal().subtract(c.getArrecadado()).subtract(c.getInvestimento());

                if (primeiraVez && c.isVencimentoProgramado()) {
                    BigDecimal mesesDiferenca = BigDecimal.valueOf(ChronoUnit.MONTHS.between(LocalDate.now(), c.getDataVencimento()));
                    BigDecimal parcelaMinima = diferenca.divide(mesesDiferenca, MathContext.DECIMAL128);
                    if (parcelaMinima.compareTo(restoTemp) < 0) {
                        c.adicionaInvestimento(parcelaMinima);
                        restoTemp = restoTemp.subtract(parcelaMinima);
                        c.setControleVencimentoProgramado(true);
                        if (investimentoCalculado.compareTo(diferenca) >= 0) {
                            c.setQuitada(true);
                        }
                        continue;
                    }
                }

                if (investimentoCalculado.compareTo(diferenca) >= 0) {
                    c.adicionaInvestimento(diferenca);
                    restoTemp = restoTemp.subtract(diferenca);
                    c.setQuitada(true);
                    totalSomaPontuacaoTemp = totalSomaPontuacao.subtract(c.getPontuacao());
                } else {

                    c.adicionaInvestimento(investimentoCalculado);
                    restoTemp = restoTemp.subtract(investimentoCalculado);
                }
            }
            primeiraVez = false;
            ck++;
            resto = restoTemp;
            totalSomaPontuacao = totalSomaPontuacaoTemp;
        } while (resto.compareTo(BigDecimal.valueOf(0.01)) >= 0);

        System.out.println(ck);
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

    // @Cacheable("caixinha")
    public List<CaixinhaResponse> calculaDitribuicaoInvestimento(BigDecimal valorSobrou, long usuarioId) {
        Usuario usuario = usuarioRepository.findByIdAndAtivoIsTrue(usuarioId);
        if (Objects.isNull(usuario)) {
            return new ArrayList<>();
        }

        //ordena primeiro por quem tem maior porcentagem paga
        Caixinha[] caixinhasArray = usuario.getCaixinhas().stream()
                .sorted((o1, o2) -> o2.getArrecadado()
                        .divide(o2.getTotal(), MathContext.DECIMAL128)
                .compareTo(o1.getArrecadado()
                        .divide(o1.getTotal(),MathContext.DECIMAL128))
                )
                .toArray(Caixinha[]::new);

        //coloca os de vencimento programado na frnte da lista
        List<Caixinha> lista = new ArrayList<>();
        List<Caixinha> copia = new ArrayList<>(Arrays.stream(caixinhasArray.clone()).toList());
        for (Caixinha caixinha : caixinhasArray) {
            if (caixinha.isVencimentoProgramado()) {
                lista.add(caixinha);
                copia.remove(caixinha);
            }
        }
        lista.addAll(copia);
        caixinhasArray = lista.toArray(new Caixinha[caixinhasArray.length]);



        return doRedistribuicao(valorSobrou, caixinhasArray);
    }
}
