package com.ti.interdisciplinar242.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "Procedimentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcedimentoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codProcedimento;

    @Column(nullable = false)
    private String descricaoProcedimento;

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
    public Integer getCodProcedimento() {
        return codProcedimento;
    }

    public void setCodProcedimento(Integer codProcedimento) {
        this.codProcedimento = codProcedimento;
    }

    public String getDescricaoProcedimento() {
        return descricaoProcedimento;
    }

    public void setDescricaoProcedimento(String descricaoProcedimento) {
        this.descricaoProcedimento = descricaoProcedimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
