package com.ti.interdisciplinar242.Controllers;



import com.ti.interdisciplinar242.DTOs.testelogin;
import com.ti.interdisciplinar242.DTOs.testeDto;
import com.ti.interdisciplinar242.Interfaces.teste;
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
public class testeController {

    //construtor de outras classes
    @Autowired
    teste teste;

    @Autowired
    PasswordEncoder passwordEncoder;

    //
    @PostMapping
    public ResponseEntity<com.ti.interdisciplinar242.Models.teste> cadastroUsuario(@RequestBody @Valid testeDto testeDto){
        var usuarioModel = new com.ti.interdisciplinar242.Models.teste();
        BeanUtils.copyProperties(testeDto,usuarioModel);
        usuarioModel.setSenha(passwordEncoder.encode(testeDto.senha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(teste.save(usuarioModel));
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody @Valid testelogin testelogin){
        Optional<com.ti.interdisciplinar242.Models.teste> usuariologin = teste.findByLogin(testelogin.login());
        if (usuariologin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar este usuário no sistema, verifique se escreveu corretamente.");
        }
        if (!passwordEncoder.matches(testelogin.senha(), usuariologin.get().getSenha())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login ou senha incorreto, verifique se escreveu corretamente.");
        }
        return ResponseEntity.ok("Login realizado com sucesso.");
    }


    @GetMapping
    public ResponseEntity<List<com.ti.interdisciplinar242.Models.teste>> puxarTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(teste.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> puxarUmUsuario(@PathVariable(value = "id")UUID id){
        Optional<com.ti.interdisciplinar242.Models.teste> usuario = teste.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado😢");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(usuario.get());
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Object> atualizaUsuario(@PathVariable(value = "id") UUID id,
                                                   @RequestBody @Valid testeDto testeDto){
        Optional<com.ti.interdisciplinar242.Models.teste> usuario = teste.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado😢");
        }
        var usuarioModel = usuario.get();
        BeanUtils.copyProperties(testeDto,usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(teste.save(usuarioModel));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deletaUsuario(@PathVariable(value = "id") UUID id) {
        Optional<com.ti.interdisciplinar242.Models.teste> usuario = teste.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado😢");
        }
        teste.delete(usuario.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado👌");
    }

}
