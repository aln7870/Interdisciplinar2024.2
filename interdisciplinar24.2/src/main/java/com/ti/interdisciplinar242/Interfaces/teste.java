package com.ti.interdisciplinar242.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface teste extends JpaRepository<com.ti.interdisciplinar242.Models.teste, UUID> {
    Optional<com.ti.interdisciplinar242.Models.teste> findByNome(String nome);
    Optional<com.ti.interdisciplinar242.Models.teste> findByLogin(String login);

}
