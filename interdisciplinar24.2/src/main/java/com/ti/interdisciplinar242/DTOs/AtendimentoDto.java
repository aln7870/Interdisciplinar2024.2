package com.ti.interdisciplinar242.DTOs;
import com.ti.interdisciplinar242.Models.TipoStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record AtendimentoDto(
//                            @NotNull int codPaciente,
//                             @NotNull int codDente,
                             @NotNull Integer codPrestador,
                             @NotNull Date dataAtendimento,
                             String observacoes,
                             @NotNull TipoStatus tipoStatus,
                             @NotNull char status,
                             @NotNull Date dataCriacao) { }




