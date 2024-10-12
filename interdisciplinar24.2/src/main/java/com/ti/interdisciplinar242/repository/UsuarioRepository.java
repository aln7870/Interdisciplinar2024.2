package com.ti.interdisciplinar242.repository;

import com.ti.interdisciplinar242.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Integer> {
   Optional<UsuarioModel> findByLogin(String login);

}
