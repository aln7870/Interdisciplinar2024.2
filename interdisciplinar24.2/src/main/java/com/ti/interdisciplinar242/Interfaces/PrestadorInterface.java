package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.PrestadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PrestadorInterface extends JpaRepository<PrestadorModel, Integer> {
    List<PrestadorModel> findByNomePrestador(String nomePrestador);
}
