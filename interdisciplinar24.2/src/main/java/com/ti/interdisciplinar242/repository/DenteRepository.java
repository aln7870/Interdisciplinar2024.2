package com.ti.interdisciplinar242.repository;

import com.ti.interdisciplinar242.Models.DenteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DenteRepository extends JpaRepository<DenteModel, Integer> {
    Optional<DenteModel> findById(Integer codDent);
}