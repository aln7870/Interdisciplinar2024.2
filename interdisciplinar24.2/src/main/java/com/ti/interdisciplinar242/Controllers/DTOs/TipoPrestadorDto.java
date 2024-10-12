package com.ti.interdisciplinar242.Controllers.DTOs;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TipoPrestadorDto(
        @NotNull String funcao,
        @NotNull char status
//        @NotNull UUID codPrestador
        ) {
}
