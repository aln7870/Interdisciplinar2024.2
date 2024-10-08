package com.ti.interdisciplinar242.Interfaces;

import com.ti.interdisciplinar242.Models.AtendimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AtendimentoInterface extends JpaRepository<AtendimentoModel, Integer> {
//    List<AtendimentoModel> findByPacienteNome(String nome);
    List<AtendimentoModel> findByStatus(char status);
    List<AtendimentoModel> findByDataAtendimentoBetween(Date startDate, Date endDate);
    Optional<AtendimentoModel> findByPrestadorCodPrestador(Integer prestador);

}
