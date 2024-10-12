package com.ti.interdisciplinar242.repository;

import com.ti.interdisciplinar242.Models.PrestadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestadorRepository extends JpaRepository<PrestadorModel, Integer> {
    List<PrestadorModel> findByNomePrestador(String nomePrestador);
}
