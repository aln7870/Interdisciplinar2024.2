package com.ti.interdisciplinar242.repository;

import com.ti.interdisciplinar242.Models.ProcedimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ProcedimentoRepository extends JpaRepository<ProcedimentoModel, Integer> {
    Optional<ProcedimentoModel> findById(Integer codProcedimento);
}