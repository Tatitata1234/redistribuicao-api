package org.example.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLRestriction("ativo = true")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nome_usuario", nullable = false, unique = true, length = 100)
    private String nomeUsuario;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "usuario")
    private List<Caixinha> caixinhas;

    @OneToMany(mappedBy = "usuario")
    private List<Classificacao> classificacoes;

    @OneToMany(mappedBy = "usuario")
    private List<Utilidade> utilidades;
}
