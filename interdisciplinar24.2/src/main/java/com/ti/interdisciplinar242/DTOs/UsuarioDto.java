package com.ti.interdisciplinar242.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(@NotBlank String nome, @NotNull int idade, @NotBlank String login, @NotBlank String senha) {

}
