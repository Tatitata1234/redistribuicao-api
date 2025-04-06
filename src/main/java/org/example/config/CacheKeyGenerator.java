package org.example.config;

import org.example.model.entity.Caixinha;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CacheKeyGenerator {

    public static String gerarChave(BigDecimal valor, List<Caixinha> caixinhas) {
        String valorNormalizado = valor.stripTrailingZeros().toPlainString();
        String idsCaixinhas = caixinhas.stream()
                .filter(c -> c.getId() != null)
                .sorted(Comparator.comparing(Caixinha::getId))
                .map(c -> c.getId().toString())
                .collect(Collectors.joining(","));

        return valorNormalizado + "|" + idsCaixinhas;
    }
}
