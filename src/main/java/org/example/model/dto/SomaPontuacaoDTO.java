package org.example.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SomaPontuacaoDTO {
    private BigDecimal totalSomaPontuacao = new BigDecimal(0);
    private BigDecimal soma = new BigDecimal(0);
}
