package org.example.service.validation;

import org.example.exception.DiferencaMesesNegativaException;

import java.math.BigDecimal;

public class CaixinhaValidator {

    private CaixinhaValidator() {}

    public static void validateMesesDiferenca(BigDecimal diferencaMeses) {
        if (BigDecimal.ZERO.compareTo(diferencaMeses) > 0) {
            throw new DiferencaMesesNegativaException(
                    "Corrija a data de vencimento da caixinha ou marque como quitada!");
        }
    }
}
