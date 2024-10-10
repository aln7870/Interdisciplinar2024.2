package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioInterface extends JpaRepository<UsuarioModel,Integer> {
   UserDetails findByLogin(String nome);
}
