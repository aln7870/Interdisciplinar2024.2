package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.TipoPrestadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TipoPrestadorInterface extends JpaRepository<TipoPrestadorModel, Integer> {
    Optional<TipoPrestadorModel> findByFuncao(String funcao);
}
