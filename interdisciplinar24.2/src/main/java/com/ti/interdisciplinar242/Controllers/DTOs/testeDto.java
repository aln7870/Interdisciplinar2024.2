package com.ti.interdisciplinar242.Controllers.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record testeDto(@NotBlank String nome, @NotNull int idade, @NotBlank String login, @NotBlank String senha) {

}
