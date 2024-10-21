package com.ti.interdisciplinar242.Controllers.DTOs;
import com.ti.interdisciplinar242.Enums.TipoStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record AtendimentoDto(
                            @NotNull Integer codPaciente,
                             @NotNull Integer codPrestador,
                             LocalDateTime dataAtendimento,
                             String observacoes,
                             @NotNull TipoStatus tipoStatus,
                             Integer codDente,
                            Integer imagens) { }




