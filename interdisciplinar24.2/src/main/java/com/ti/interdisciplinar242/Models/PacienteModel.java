package com.ti.interdisciplinar242.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteModel {
    /*
CREATE TABLE Paciente (
CodPaciente INT UNSIGNED AUTO_INCREMENT,
NomePaciente VARCHAR(100) NOT NULL,
Sexo VARCHAR(20) DEFAULT 'NÃO INFORMADO',
DataNascimento DATE NOT NULL,
CPF VARCHAR(14) UNIQUE NULL,
CPFResponsavel VARCHAR(14) UNIQUE NULL,
Telefone VARCHAR(15) NOT NULL,
DataCadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
Status CHAR(1) DEFAULT 'A' CHECK (Status IN ('A', 'I')),
PRIMARY KEY (CodPaciente)
    );
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(columnDefinition = "TINYINT UNSIGNED")
    private Integer codPaciente;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false, length = 15)
    private String contato;

    @Column(length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'NÃO INFORMADO'")
    private String Sexo;

    @Column(length = 14, unique = true)
    private String cpf;

    @Column(length = 14)
    private String cpfResponsavel;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @Column(nullable = false, columnDefinition = "DATE")
    private Date dataNascimento;

    //testando para o spring aceitar somente A OU I em status
    @Column(nullable = false, length = 1, columnDefinition = "CHAR(1) DEFAULT 'A'")
    @Pattern(regexp = "[AI]")
    private String status = "A";

}
