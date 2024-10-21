package com.ti.interdisciplinar242.repository;

import com.ti.interdisciplinar242.Models.AtendimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.*;

public interface AtendimentoRepository extends JpaRepository<AtendimentoModel, Integer> {
//    List<AtendimentoModel> findByPacienteNome(String nome);
 //   List<AtendimentoModel> findByStatus(char status);
 //   List<AtendimentoModel> findByDataAtendimentoBetween(LocalDateTime startDate, LocalDateTime endDate);
 //   Optional<AtendimentoModel> findByPrestadorCodPrestador(Integer prestador);

}
