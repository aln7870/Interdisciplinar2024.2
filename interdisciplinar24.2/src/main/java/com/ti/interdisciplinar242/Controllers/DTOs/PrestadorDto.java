package com.ti.interdisciplinar242.Controllers.DTOs;

import ch.qos.logback.core.status.Status;
import com.ti.interdisciplinar242.Models.TipoPrestadorModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jshell.Snippet;

import java.util.UUID;

public record PrestadorDto(
        @NotBlank String nomePrestador,
        @NotNull char status,
        @NotBlank String funcao,
        Integer usuario
        ) {
}
