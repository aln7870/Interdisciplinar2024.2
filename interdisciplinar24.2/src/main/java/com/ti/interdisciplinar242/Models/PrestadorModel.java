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
@Table(name = "prestador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PrestadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodPrestador", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer codPrestador;

    @Column(name = "NomePrestador", nullable = false, length = 100)
    private String nomePrestador;

    @Column(name = "DataCriacao",nullable = false)
    private LocalDateTime datacriacao;

    @Column(name = "Status",nullable = false, columnDefinition = "CHAR(1) DEFAULT 'A'")
    private char status;

    @ManyToOne
    @JoinColumn(name = "CodTipoPrestador", nullable = false)
    private TipoPrestadorModel tipoPrestador;

    @ManyToOne
    @JoinColumn(name = "CodUsuario")
    private UsuarioModel usuario;

}
