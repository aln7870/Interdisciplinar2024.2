package com.ti.interdisciplinar242.repository;

import com.ti.interdisciplinar242.Models.FaturamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FaturamentoRepository extends JpaRepository<FaturamentoModel, Integer> {
    Optional<FaturamentoModel> findById(Integer codFaturamento);
}