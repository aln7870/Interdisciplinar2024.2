package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.TesteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TesteInterface extends JpaRepository<TesteModel, Integer> {
    Optional<TesteModel> findByNome(String nome);
    Optional<TesteModel> findByLogin(String login);

}
