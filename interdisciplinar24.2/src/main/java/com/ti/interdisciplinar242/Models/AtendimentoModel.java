package com.ti.interdisciplinar242.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

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
//    @ManyToOne
//    @JoinColumn(name = "CodDente")
//    private DenteModel dente;

//    @ManyToOne
//    @JoinColumn("CodImagensRadiologicas")
//    private ImagensRadiologicasModel imagens;

    @ManyToOne
    @JoinColumn(name = "CodPrestador",nullable = false)
    private PrestadorModel prestador;

    @Column(name = "DataAtendimento", nullable = false)
    private Date dataAtendimento;

    @Column(name = "Observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoStatus", nullable = false)
    private TipoStatus tipoStatus;

    @Column(name = "Status", nullable = false, length = 1)
    private char status;

    @Column(name = "DataCriacao", nullable = false)
    private Date dataCriacao;

//    public PacienteModel getPaciente() {
//        return paciente;
//    }
//
//    public void setPaciente(PacienteModel paciente) {
//        this.paciente = paciente;
//    }
//
//    public DenteModel getDente() {
//        return dente;
//    }
//
//    public void setDente(DenteModel dente) {
//        this.dente = dente;
//    }

//    public ImagensRadiologicasModel getImagens() {
//        return imagens;
//    }
//
//    public void setImagens(ImagensRadiologicasModel imagens) {
//        this.imagens = imagens;
//    }
}
