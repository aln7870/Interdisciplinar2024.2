package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Controllers.DTOs.LoginRequest;
import com.ti.interdisciplinar242.Controllers.DTOs.LoginResponse;
import com.ti.interdisciplinar242.Models.Role;
import com.ti.interdisciplinar242.Models.UsuarioModel;
import com.ti.interdisciplinar242.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class TokenLoginController {
    @Autowired
    JwtEncoder jwtEncoder;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;




    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest){

        Optional<UsuarioModel> user = usuarioRepository.findByLogin(loginRequest.login());
        // horario atual
        var now = Instant.now();
        //5 minutos
        var expiresIn = 300L;

        var scope = user.get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining());

        var claims = JwtClaimsSet.builder()
                .issuer("gerado pelo back end")
                .subject(user.get().getCodUsuario().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("escopo", scope)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        if (user.isEmpty()){
            throw new BadCredentialsException("usuario vazio");
        }
        System.out.println("chegou ate senha");
   /*     if (user.get().getLogin().equals("ADMIN") && user.get().getSenha().equals("123")){
            return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
        }*/
        //NUNCA ESQUEÇA DA DEPENDENCIA VALIDATIONS E DE VALIDAR OS DTOS PQP AAAAAAAAAAAAA
        if (!passwordEncoder.matches(loginRequest.senha(), user.get().getSenha())){
            //(!user.get().isLoginCorrect(loginRequest, passwordEncoder)){
            throw new BadCredentialsException("senha incorreta");
        }
        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }

}

