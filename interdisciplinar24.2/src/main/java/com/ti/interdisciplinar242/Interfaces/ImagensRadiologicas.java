package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.ImagensRadiologicasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagensRadiologicas extends JpaRepository<ImagensRadiologicasModel, Integer> {
    Optional<ImagensRadiologicasModel> findById(Integer codImagemRadiologica);
}
