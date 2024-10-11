package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.DTOs.AuthenticationDto;
import com.ti.interdisciplinar242.DTOs.Usuarioteste;
import com.ti.interdisciplinar242.Interfaces.RoleInterface;
import com.ti.interdisciplinar242.Interfaces.UsuarioInterface;
import com.ti.interdisciplinar242.Models.RoleModel;
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

import java.util.Set;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioInterface usuarioInterface;

    @Autowired
    RoleInterface roleRerpository;

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
        var ROLE_USER = roleRerpository.findById(RoleModel.Values.USER.id()).orElseThrow(() -> new RuntimeException("role nao encontrada"));

        String encode = new BCryptPasswordEncoder().encode(usuarioteste.senha());
        UsuarioModel usuarioModel = new UsuarioModel(usuarioteste.login(), encode, Set.of(ROLE_USER));
        this.usuarioInterface.save(usuarioModel);
        return ResponseEntity.ok().build();

    }


}
