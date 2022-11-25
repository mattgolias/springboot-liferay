package com.liferay.localevent.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FuncionarioDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String emailFunc;
}
