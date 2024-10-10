package com.ti.interdisciplinar242.DTOs;

import jakarta.validation.constraints.NotBlank;

public record DenteDto(@NotBlank String nomeDente, @NotBlank String status) {
}
