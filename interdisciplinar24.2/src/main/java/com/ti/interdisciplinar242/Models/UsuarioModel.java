package com.ti.interdisciplinar242.Models;

import com.ti.interdisciplinar242.Enums.UsuarioEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

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
);*/
/**/
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer codUsuario;

    @Column(name = "nome",unique = true,length = 100)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private UsuarioEnum role;
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
    public UsuarioModel(String login, String senha, UsuarioEnum role){
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioEnum.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_DENTISTA"), new SimpleGrantedAuthority("ROLE_RECEPCIONISTA"));
    }else if (this.role == UsuarioEnum.DENTISTA){
            return List.of(new SimpleGrantedAuthority("ROLE_DENTISTA"));
    }else
        return List.of(new SimpleGrantedAuthority("ROLE_RECEPCIONISTA"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
