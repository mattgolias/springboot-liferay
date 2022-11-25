package com.liferay.localevent.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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
    private String horarioInical;

    @NotBlank
    private String horarioFinal;



    @NotBlank
    private String criadorDoEvento;
}
