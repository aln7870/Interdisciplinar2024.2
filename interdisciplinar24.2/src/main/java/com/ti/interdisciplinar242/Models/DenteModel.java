package com.ti.interdisciplinar242.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Dente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DenteModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codDente;

    @Column(nullable = false)
    private String nomeDente;

    @Column(name = "NumeroDente")
    private int numeroDentel;

    @Column(nullable = false, length = 1, columnDefinition = "CHAR(1) DEFAULT 'A'")
    @Pattern(regexp = "[AI]")
    private String status = "A";
    // validando que por padrão status tera A
    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = "A";
        }
    }

}