package com.ti.interdisciplinar242.repository;

import com.ti.interdisciplinar242.Models.TipoPrestadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoPrestadorRepository extends JpaRepository<TipoPrestadorModel, Integer> {
    Optional<TipoPrestadorModel> findByFuncao(String funcao);
}
