package com.ti.interdisciplinar242.Controllers.DTOs;

import jakarta.validation.constraints.NotNull;

public record ProMedDto(
        @NotNull String observacao,
        @NotNull Integer codDente,
        @NotNull Integer codAtendim,
        @NotNull Integer codProcedimento) {
}
