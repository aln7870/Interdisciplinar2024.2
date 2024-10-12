package com.ti.interdisciplinar242.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/*
    CREATE TABLE Usuario (
    CodUsuario TINYINT UNSIGNED AUTO_INCREMENT,
    Nome VARCHAR(100) UNIQUE NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Senha VARCHAR(255) NOT NULL,
    DataCriacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status CHAR(1) DEFAULT 'A' CHECK (Status IN ('A', 'I')),
	CodPrestador TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY (CodUsuario)
); */
/**/
public class UsuarioModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer codUsuario;

    @Column(name = "nome",unique = true,length = 100)
    private String login;

    @Column(nullable = false)
    private String senha;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
/*
    @Column(unique = true,length = 100)
    private String email;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dataCriacao;
*/

    /*
    //testando para o spring aceitar somente A OU I em status
    @Column(nullable = false, length = 1, columnDefinition = "CHAR(1) DEFAULT 'A'")
    @Pattern(regexp = "[AI]")
    private String status = "A";

    @Column(nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer codPrestador;

    // validando que por padrão status tera A
    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = "A";
        }
    }
*/

}
