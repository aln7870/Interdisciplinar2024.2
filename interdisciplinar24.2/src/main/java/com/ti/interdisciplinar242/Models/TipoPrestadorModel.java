package com.ti.interdisciplinar242.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tipoPrestador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoPrestadorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodTipoPrestador", nullable = false)
    private Integer codTipoPrestador;

    @Column(name = "Funcao", nullable = false)
    private String funcao;

    @Column(name = "Status", nullable = false)
    private char status;

    @OneToMany(mappedBy = "tipoPrestador", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PrestadorModel> prestador;

}
