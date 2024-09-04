package org.example;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigCaixinha {
    private Classificacao classificacao;
    private Utililidade utililidade;
}
