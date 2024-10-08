package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioInterface extends JpaRepository<UsuarioModel,Integer> {
}
