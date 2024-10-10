package com.ti.interdisciplinar242.DTOs;

import jakarta.validation.constraints.NotBlank;

public record ProcedimentoDto(@NotBlank String descricaoProcedimento, @NotBlank String status) {
}
