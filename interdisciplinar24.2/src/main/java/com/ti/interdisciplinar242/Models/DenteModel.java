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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codDente;

    @Column(nullable = false)
    private String nomeDente;

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

    public Integer getCodDente() {
        return codDente;
    }

    public void setCodDente(Integer codDente) {
        this.codDente = codDente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomeDente() {
        return nomeDente;
    }

    public void setNomeDente(String nomeDente) {
        this.nomeDente = nomeDente;
    }
}
