package com.ti.interdisciplinar242.DTOs;

import jakarta.validation.constraints.NotBlank;


public record LoginUsuarioDto(@NotBlank String login, @NotBlank String senha) {
}
