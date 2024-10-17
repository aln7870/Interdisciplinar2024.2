package com.ti.interdisciplinar242.Controllers.DTOs;
import com.ti.interdisciplinar242.Enums.TipoStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record AtendimentoDto(
//                            @NotNull int codPaciente,
//                             @NotNull int codDente,
                             @NotNull Integer codPrestador,
                             LocalDateTime dataAtendimento,
                             String observacoes,
                             @NotNull TipoStatus tipoStatus,
                             @NotNull char status) { }



