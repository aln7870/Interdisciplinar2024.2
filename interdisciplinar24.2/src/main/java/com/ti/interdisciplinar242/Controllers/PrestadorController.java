package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Controllers.DTOs.PrestadorDto;
import com.ti.interdisciplinar242.repository.PrestadorRepository;
import com.ti.interdisciplinar242.repository.TipoPrestadorRepository;
import com.ti.interdisciplinar242.Models.PrestadorModel;
import com.ti.interdisciplinar242.Models.TipoPrestadorModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestador")
@CrossOrigin(origins = "*")
public class PrestadorController {

    @Autowired
    PrestadorRepository prestadorRepository;
    @Autowired
    TipoPrestadorRepository tipoPrestadorRepository;

    @PostMapping
    public ResponseEntity<PrestadorModel> cadastroPrestador(@RequestBody @Valid PrestadorDto prestadorDto) {
        var prestadorModel = new PrestadorModel();
        BeanUtils.copyProperties(prestadorDto, prestadorModel);
        prestadorModel.setDatacriacao(LocalDateTime.now()); // Setando a data de criação como a data atual

        // Buscar o TipoPrestador pelo nome da função
        Optional<TipoPrestadorModel> tipoPrestador = tipoPrestadorRepository.findByFuncao(prestadorDto.funcao());
        if (tipoPrestador.isPresent()) {
            prestadorModel.setTipoPrestador(tipoPrestador.get()); // Vincula o TipoPrestador ao Prestador
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Retorna null se o TipoPrestador não for encontrado
        }

        // Salva o prestador
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prestadorRepository.save(prestadorModel));
    }


    @GetMapping
    public ResponseEntity<List<PrestadorModel>> puxarTodosPrestadores() {
        return ResponseEntity.status(HttpStatus.OK).body(prestadorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> puxarUmPrestador(@PathVariable(value = "id") Integer id) {
        Optional<PrestadorModel> prestador = prestadorRepository.findById(id);
        if (prestador.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestador não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(prestador.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaPrestador(@PathVariable(value = "id") Integer id,
                                                    @RequestBody @Valid PrestadorDto prestadorDto) {
        Optional<PrestadorModel> prestador = prestadorRepository.findById(id);
        if (prestador.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestador não encontrado.");
        }
        var prestadorModel = prestador.get();
        BeanUtils.copyProperties(prestadorDto, prestadorModel);
        return ResponseEntity.status(HttpStatus.OK).body(prestadorRepository.save(prestadorModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletaPrestador(@PathVariable(value = "id") Integer id) {
        Optional<PrestadorModel> prestador = prestadorRepository.findById(id);
        if (prestador.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestador não encontrado.");
        }
        prestadorRepository.delete(prestador.get());
        return ResponseEntity.status(HttpStatus.OK).body("Prestador deletado com sucesso.");
    }
}
