package com.ti.interdisciplinar242.Models;

import com.ti.interdisciplinar242.Enums.TipoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "atendimento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CodAtendimento;

//    @ManyToOne
//    @JoinColumn(name = "CodPaciente", nullable = false)
//    private PacienteModel paciente;
//
    @ManyToOne
    @JoinColumn(name = "CodDente")
    private DenteModel dente;

    @ManyToOne
    @JoinColumn(name = "codImagemRadiologica")
    private ImagensRadiologicasModel imagens;

    @ManyToOne
    @JoinColumn(name = "CodPrestador",nullable = false)
    private PrestadorModel prestador;

    @Column(name = "DataAtendimento", nullable = false)
    private LocalDateTime dataAtendimento;

    @Column(name = "Observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoStatus", nullable = false)
    private TipoStatus tipoStatus;

    @Column(name = "Status", nullable = false, length = 1)
    private char status;




}
