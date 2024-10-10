package com.ti.interdisciplinar242.DTOs;
import com.ti.interdisciplinar242.Enums.TipoStatus;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AtendimentoDto(
//                            @NotNull int codPaciente,
//                             @NotNull int codDente,
                             @NotNull Integer codPrestador,
                             @NotNull Date dataAtendimento,
                             String observacoes,
                             @NotNull TipoStatus tipoStatus,
                             @NotNull char status,
                             @NotNull Date dataCriacao) { }




