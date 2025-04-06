package org.example.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controller.response.CaixinhaResponse;
import org.example.mapper.CaixinhaMapper;
import org.example.model.entity.Caixinha;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.TreeMap;


@Service
public class CaixinhaUtils {

    private static final Logger logger = LogManager.getLogger(CaixinhaUtils.class);
    public static final BigDecimal VALOR_ABSURDAMENTE_ALTO = BigDecimal.valueOf(10000000);
    public static final BigDecimal DIVISOR_100 = BigDecimal.valueOf(100);

    private CaixinhaUtils() {}

    public static List<CaixinhaResponse> ordenaCaixinhas(List<Caixinha> caixinhas) {
        logger.info("Ordenando por valor calculado de investimento, maior -> menor");
        TreeMap<BigDecimal, CaixinhaResponse> ordenaFinal = new TreeMap<>();
        for (Caixinha c: caixinhas) {
            logger.trace("Montando chave para caixinha: {}", c.getNome());
            BigDecimal evitaRepeticoesUsandoId = BigDecimal.ONE.divide(BigDecimal.valueOf(c.getId()), MathContext.DECIMAL32).divide(DIVISOR_100, MathContext.DECIMAL32);
            BigDecimal key = VALOR_ABSURDAMENTE_ALTO.subtract(c.getInvestimento().add(evitaRepeticoesUsandoId));
            ordenaFinal.putIfAbsent(key, CaixinhaMapper.toResponse(c));
            logger.debug("Chave: {} Caixinha: {}", key.round(MathContext.DECIMAL128), c.getNome());
        }
        if (ordenaFinal.size() != caixinhas.size()) {
            logger.warn("Erro ao ordenar caixinhas após cálculo");
            return caixinhas.stream().map(CaixinhaMapper::toResponse).toList();
        }
        logger.info("Fim ordenação");
        return ordenaFinal.values().stream().toList();
    }

    public static List<Caixinha> ordenaPreRedistribuicao(List<Caixinha> caixinhas) {
        logger.info("Ordenando por data mais próxima e menor valor para quitação");

        TreeMap<BigDecimal, Caixinha> arvoreOrdenada = new TreeMap<>();

        for (Caixinha c: caixinhas) {
            BigDecimal diferencaMeses;
            if (c.getDataVencimento() != null)
                diferencaMeses = BigDecimal.valueOf(ChronoUnit.MONTHS.between(LocalDate.now(), c.getDataVencimento()));
            else
                diferencaMeses = VALOR_ABSURDAMENTE_ALTO;

            logger.trace("Montando chave para caixinha: {}", c.getNome());
            BigDecimal key = diferencaMeses.add(c.getTotal().subtract(c.getArrecadado()).add(BigDecimal.valueOf(c.getId())).divide(VALOR_ABSURDAMENTE_ALTO, MathContext.DECIMAL32));
            arvoreOrdenada.putIfAbsent(key, c);
            logger.debug("Chave: {} Caixinha: {}", key.round(MathContext.DECIMAL128), c.getNome());
        }
        if (arvoreOrdenada.size() != caixinhas.size()) {
            logger.warn("Erro ao ordenar caixinhas pré cálculo");
            return caixinhas;
        }
        logger.info("Fim ordenação");
        return arvoreOrdenada.values().stream().toList();
    }
}
