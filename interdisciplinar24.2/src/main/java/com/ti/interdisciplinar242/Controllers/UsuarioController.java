package com.ti.interdisciplinar242.Controllers;



import com.ti.interdisciplinar242.DTOs.LoginUsuarioDto;
import com.ti.interdisciplinar242.DTOs.UsuarioDto;
import com.ti.interdisciplinar242.Interfaces.UsuarioInterface;
import com.ti.interdisciplinar242.Models.UsuarioModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    //construtor de outras classes
    @Autowired
    UsuarioInterface usuarioInterface;

    @Autowired
    PasswordEncoder passwordEncoder;

    //
    @PostMapping
    public ResponseEntity<UsuarioModel> cadastroUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto,usuarioModel);
        usuarioModel.setSenha(passwordEncoder.encode(usuarioDto.senha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioInterface.save(usuarioModel));
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody @Valid LoginUsuarioDto loginUsuarioDto){
        Optional<UsuarioModel> usuariologin = usuarioInterface.findByLogin(loginUsuarioDto.login());
        if (usuariologin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar este usuário no sistema, verifique se escreveu corretamente.");
        }
        if (!passwordEncoder.matches(loginUsuarioDto.senha(), usuariologin.get().getSenha())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login ou senha incorreto, verifique se escreveu corretamente.");
        }
        return ResponseEntity.ok("Login realizado com sucesso.");
    }


    @GetMapping
    public ResponseEntity<List<UsuarioModel>> puxarTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioInterface.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> puxarUmUsuario(@PathVariable(value = "id")UUID id){
        Optional<UsuarioModel> usuario = usuarioInterface.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado😢");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(usuario.get());
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Object> atualizaUsuario(@PathVariable(value = "id") UUID id,
                                                   @RequestBody @Valid UsuarioDto usuarioDto){
        Optional<UsuarioModel> usuario = usuarioInterface.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado😢");
        }
        var usuarioModel = usuario.get();
        BeanUtils.copyProperties(usuarioDto,usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioInterface.save(usuarioModel));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deletaUsuario(@PathVariable(value = "id") UUID id) {
        Optional<UsuarioModel> usuario = usuarioInterface.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado😢");
        }
        usuarioInterface.delete(usuario.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado👌");
    }

}
