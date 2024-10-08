package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.FaturamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FaturamentoInterface extends JpaRepository<FaturamentoModel, Integer> {
    Optional<FaturamentoModel> findById(Integer codFaturamento);
}