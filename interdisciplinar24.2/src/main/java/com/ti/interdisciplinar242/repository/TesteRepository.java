package com.ti.interdisciplinar242.repository;

import com.ti.interdisciplinar242.Models.TesteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TesteRepository extends JpaRepository<TesteModel, Integer> {
    Optional<TesteModel> findByNome(String nome);
    Optional<TesteModel> findByLogin(String login);

}
