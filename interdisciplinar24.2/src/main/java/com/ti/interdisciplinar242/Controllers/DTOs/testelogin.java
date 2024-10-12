package com.ti.interdisciplinar242.Controllers.DTOs;

import jakarta.validation.constraints.NotBlank;


public record testelogin(@NotBlank String login, @NotBlank String senha) {
}
