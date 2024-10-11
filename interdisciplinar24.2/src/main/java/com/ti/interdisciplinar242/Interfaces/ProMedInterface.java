package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.ProMedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProMedInterface extends JpaRepository<ProMedModel, Integer> {
    Optional<ProMedModel> findById(Integer codProMed);
}
