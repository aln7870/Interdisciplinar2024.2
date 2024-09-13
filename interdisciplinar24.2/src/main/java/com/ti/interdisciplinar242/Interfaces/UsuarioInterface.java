package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioInterface extends JpaRepository<UsuarioModel, UUID> {
    Optional<UsuarioModel> findByNome(String nome);
    Optional<UsuarioModel> findByLogin(String login);

}
