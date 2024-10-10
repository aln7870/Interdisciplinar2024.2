package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.DTOs.AuthenticationDto;
import com.ti.interdisciplinar242.DTOs.Usuarioteste;
import com.ti.interdisciplinar242.Interfaces.UsuarioInterface;
import com.ti.interdisciplinar242.Models.UsuarioModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioInterface usuarioInterface;

    @PostMapping("/login")
    public ResponseEntity<UsuarioModel> login(@RequestBody @Valid AuthenticationDto authenticationDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.login(), authenticationDto.senha());
        System.out.println(usernamePassword);
        var auth = authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioModel> register(@RequestBody @Valid Usuarioteste usuarioteste){
        if(this.usuarioInterface.findByLogin(usuarioteste.login())!= null) return ResponseEntity.badRequest().build();

        String encode = new BCryptPasswordEncoder().encode(usuarioteste.senha());
        UsuarioModel usuarioModel = new UsuarioModel(usuarioteste.login(), encode, usuarioteste.role());
        this.usuarioInterface.save(usuarioModel);
        return ResponseEntity.ok().build();

    }


}
