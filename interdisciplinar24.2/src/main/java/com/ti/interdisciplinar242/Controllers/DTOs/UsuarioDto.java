package com.ti.interdisciplinar242.Controllers.DTOs;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(@NotBlank String nome,@NotBlank String login,@NotBlank String senha, String email) {
}
