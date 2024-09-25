package com.ti.interdisciplinar242.Models;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "usuario")
public class teste {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int idade;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    
    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }



}
