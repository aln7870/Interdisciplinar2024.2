package com.ti.interdisciplinar242.DTOs;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(@NotBlank String nome) {

}
