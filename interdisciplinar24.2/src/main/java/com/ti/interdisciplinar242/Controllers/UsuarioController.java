package com.ti.interdisciplinar242.Controllers;


import com.ti.interdisciplinar242.Controllers.DTOs.UsuarioDto;
import com.ti.interdisciplinar242.Controllers.DTOs.testeDto;
import com.ti.interdisciplinar242.Models.Role;

import com.ti.interdisciplinar242.Models.TesteModel;
import com.ti.interdisciplinar242.repository.RoleRepository;
import com.ti.interdisciplinar242.repository.UsuarioRepository;
import com.ti.interdisciplinar242.Models.UsuarioModel;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<UsuarioModel> saveUser(@RequestBody @Valid UsuarioDto usuarioDto){
        var role = roleRepository.findByName(Role.values.USER.name());
        var user = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, user);
        user.setSenha(passwordEncoder.encode(usuarioDto.senha()));
        user.setRoles(Set.of(role));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(user));
    }

    @GetMapping
    //PERMITINDO QUE SOMENTE ADMINS POSSAM DAR GETALL
    //@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> getAllUsers(){
        List<UsuarioModel> users = usuarioRepository.findAll();
        if (users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum usuario registrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
/*
    @PutMapping("/{codUsuario}")
    public  ResponseEntity<Object> atualizaUsuario(@PathVariable(value = "codUsuario") Integer codUsuario,
                                                   @RequestBody @Valid UsuarioDto usuarioDto){
        Optional<UsuarioModel> usuario = usuarioRepository.findById(codUsuario);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado😢");
        }
        var roleAdmin = roleRepository.findByName(Role.values.ADMIN.name());



        return ResponseEntity.status(HttpStatus.OK).body();
    }

*/
}
