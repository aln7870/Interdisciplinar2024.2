package com.ti.interdisciplinar242.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "promed")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProMedModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodProMed", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer codProMed;

    @Column(name = "Observacao", nullable = false, columnDefinition = "LONGTEXT")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "CodDente", nullable = false)
    private DenteModel dente;

    @ManyToOne
    @JoinColumn(name = "CodAtendim", nullable = false)
    private AtendimentoModel atendimento;

    @ManyToOne
    @JoinColumn(name = "CodProcedimento", nullable = false)
    private ProcedimentoModel procedimento;
}
