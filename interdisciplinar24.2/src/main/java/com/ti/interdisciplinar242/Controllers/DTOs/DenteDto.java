package com.ti.interdisciplinar242.Controllers.DTOs;

import jakarta.validation.constraints.NotBlank;

public record DenteDto(@NotBlank String nomeDente, @NotBlank String status) {
}
