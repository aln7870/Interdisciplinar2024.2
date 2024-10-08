package com.ti.interdisciplinar242.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Date datacriacao;

    @Column(name = "Status",nullable = false)
    private char status;
    @ManyToOne
    @JoinColumn(name = "CodTipoPrestador", nullable = false)
    private TipoPrestadorModel tipoPrestador;

//    @ManyToOne
//    @JoinColumn(name = "CodUsuario", nullable = false)
//    private UsuarioModel usuario;

}
