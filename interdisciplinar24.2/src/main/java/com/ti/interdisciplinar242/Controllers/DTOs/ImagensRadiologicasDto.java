package com.ti.interdisciplinar242.Controllers.DTOs;

import jakarta.validation.constraints.NotBlank;

public record ImagensRadiologicasDto(@NotBlank String caminho, @NotBlank String status) {
}
