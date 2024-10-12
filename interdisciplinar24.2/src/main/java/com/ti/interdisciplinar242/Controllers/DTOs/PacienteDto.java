package com.ti.interdisciplinar242.Controllers.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public record PacienteDto(
        @NotBlank String nome, @NotBlank String contato, String sexo, String cpf,
        String cpfResponsavel, String dataNascimento,@NotBlank String status
) {
}
