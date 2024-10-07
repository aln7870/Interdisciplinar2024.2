package com.ti.interdisciplinar242.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ImagensRadiologicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagensRadiologicasModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codImagemRadiologica;

    @Column(nullable = false)
    private String caminho;

    @Column
    private LocalDateTime dataCriacao;

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

    public Integer getCodImagemRadiologica() {
        return codImagemRadiologica;
    }

    public void setCodImagemRadiologica(Integer codImagemRadiologica) {
        this.codImagemRadiologica = codImagemRadiologica;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
