package com.ti.interdisciplinar242.DTOs;


import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record FaturamentoDto(@NotNull BigDecimal valor) {
}
