package com.ti.interdisciplinar242.Controllers;



import com.ti.interdisciplinar242.DTOs.UsuarioDto;
import com.ti.interdisciplinar242.Interfaces.UsuarioInterface;
import com.ti.interdisciplinar242.Models.UsuarioModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioInterface usuarioInterface;


    @PostMapping
    public ResponseEntity<UsuarioModel> cadastroUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto,usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioInterface.save(usuarioModel));
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
