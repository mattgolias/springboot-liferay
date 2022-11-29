package com.liferay.localevent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "FUNCIONARIO")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", length = 100)
    @NotNull
    private String nome;

    @Column(name = "emailFunc", length = 100)
    @NotNull
    private String emailFunc;
}
