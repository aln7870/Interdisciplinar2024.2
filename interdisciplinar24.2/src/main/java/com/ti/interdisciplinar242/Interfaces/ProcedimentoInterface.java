package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.ProcedimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcedimentoInterface extends JpaRepository<ProcedimentoModel, Integer> {
    Optional<ProcedimentoModel> findById(Integer codProcedimento);
}
