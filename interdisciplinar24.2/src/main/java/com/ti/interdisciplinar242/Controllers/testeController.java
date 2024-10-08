package com.ti.interdisciplinar242.Controllers;



import com.ti.interdisciplinar242.DTOs.testelogin;
import com.ti.interdisciplinar242.DTOs.testeDto;
import com.ti.interdisciplinar242.Interfaces.TesteInterface;
import com.ti.interdisciplinar242.Models.TesteModel;
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
//permite a requisição de qualquer ip
//por enquanto deixar como comentário até a hr de testar com o front.
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teste")
public class testeController {

    //construtor de outras classes
    @Autowired
    TesteInterface TesteInterface;

    @Autowired
    PasswordEncoder passwordEncoder;



/*      (Alan) - ainda to testando essa personalização da resposta mas caso n pegue vou criar exceptions
    @Operation(description = "Personalizando respostas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro feito com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados invalidos"),
            @ApiResponse(responseCode = "404", description = "parametro errado jumento"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "401", description = "usuario não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao fazer o upload"),
    })*/
    @PostMapping
    public ResponseEntity<TesteModel> cadastroUsuario(@RequestBody @Valid testeDto testeDto){
        var usuarioModel = new TesteModel();
        BeanUtils.copyProperties(testeDto,usuarioModel);
        usuarioModel.setSenha(passwordEncoder.encode(testeDto.senha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(TesteInterface.save(usuarioModel));
        
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody @Valid testelogin testelogin){
        Optional<TesteModel> usuariologin = TesteInterface.findByLogin(testelogin.login());
        if (usuariologin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar este usuário no sistema, verifique se escreveu corretamente.");
        }
        if (!passwordEncoder.matches(testelogin.senha(), usuariologin.get().getSenha())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login ou senha incorreto, verifique se escreveu corretamente.");
        }
        return ResponseEntity.ok("Login realizado com sucesso.");
    }


    @GetMapping
    public ResponseEntity<List<TesteModel>> puxarTodosUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(TesteInterface.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> puxarUmUsuario(@PathVariable(value = "id")Integer id){
        Optional<TesteModel> usuario = TesteInterface.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado😢");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(usuario.get());
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Object> atualizaUsuario(@PathVariable(value = "id") Integer id,
                                                   @RequestBody @Valid testeDto testeDto){
        Optional<TesteModel> usuario = TesteInterface.findById(id);
        if (usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado😢");
        }
        var usuarioModel = usuario.get();
        BeanUtils.copyProperties(testeDto,usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body(TesteInterface.save(usuarioModel));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deletaUsuario(@PathVariable(value = "id") Integer id) {
        Optional<TesteModel> usuario = TesteInterface.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado😢");
        }
        TesteInterface.delete(usuario.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado👌");
    }

}
