package com.liferay.localevent.dto;

import com.liferay.localevent.model.Funcionario;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class EventoDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String local;

    @NotBlank
    private String categoria;

    @NotBlank
    private String data;

    @NotBlank
    private String horarioInicio;

    @NotBlank
    private String horarioFinal;

    @NotBlank
    private String vagas;

    private String criadorDoEvento;

    private String descricao;

    private List<Funcionario> inscritos;

    private String quantidade;

    private String start;

    private String status;

    private String date;

    private String email;

}
