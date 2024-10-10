package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteInterface extends JpaRepository<PacienteModel,Integer> {

}

