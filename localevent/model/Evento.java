package com.liferay.localevent.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "EVENTO")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", length = 100)
    @NotNull
    private String nome;

    @Column(name = "local", length = 50)
    @NotNull
    private String local;

    @Column(name = "categoria", length = 50)
    @NotNull
    private String categoria;

    @Column(name = "data", length = 10)
    @NotNull
    private String data;

    @Column(name = "horarioInicial", length = 10)
    @NotNull
    private String horarioInicial;

    @Column(name = "horarioFinal", length = 10)
    @NotNull
    private String horarioFinal;

    @Column(name = "vagas", length = 5)
    @NotNull
    private int vagas;

    @Column(name = "criadorDoEvento", length = 100)
    @NotNull
    private String criadorDoEvento;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "inscritos", length = 100)
    @ElementCollection
    private List<Funcionario> inscritos;
}
