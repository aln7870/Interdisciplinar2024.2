package com.ti.interdisciplinar242.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "teste")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TesteModel {
//todo o model,interface,controller etc com o nome *TESTE*
// não deve ser considerado, apenas serve para teste e aprendizado.
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int idade;

    private String email;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

}
