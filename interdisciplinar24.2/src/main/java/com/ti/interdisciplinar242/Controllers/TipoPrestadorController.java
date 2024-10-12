package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Controllers.DTOs.TipoPrestadorDto;
import com.ti.interdisciplinar242.repository.TipoPrestadorRepository;
import com.ti.interdisciplinar242.Models.TipoPrestadorModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoprestador")
@CrossOrigin(origins = "*")
public class TipoPrestadorController {

    @Autowired
    private TipoPrestadorRepository tipoPrestadorRepository;

    @PostMapping
    public ResponseEntity<TipoPrestadorModel> criarTipoPrestador(@RequestBody @Valid TipoPrestadorDto tipoPrestadorDto) {
        TipoPrestadorModel tipoPrestadorModel = new TipoPrestadorModel();
        tipoPrestadorModel.setFuncao(tipoPrestadorDto.funcao());
        tipoPrestadorModel.setStatus(tipoPrestadorDto.status());

        // Removido a lógica que vincula o Prestador

        return ResponseEntity.status(HttpStatus.CREATED).body(tipoPrestadorRepository.save(tipoPrestadorModel));
    }

    @GetMapping
    public ResponseEntity<List<TipoPrestadorModel>> listarTiposPrestadores() {
        List<TipoPrestadorModel> tiposPrestadores = tipoPrestadorRepository.findAll();
        return ResponseEntity.ok(tiposPrestadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarTipoPrestador(@PathVariable(value = "id") Integer id) {
        Optional<TipoPrestadorModel> tipoPrestador = tipoPrestadorRepository.findById(id);
        if (tipoPrestador.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Prestador não encontrado.");
        }
        return ResponseEntity.ok(tipoPrestador.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarTipoPrestador(@PathVariable(value = "id") Integer id,
                                                         @RequestBody @Valid TipoPrestadorDto tipoPrestadorDto) {
        Optional<TipoPrestadorModel> tipoPrestador = tipoPrestadorRepository.findById(id);
        if (tipoPrestador.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Prestador não encontrado.");
        }
        TipoPrestadorModel tipoPrestadorModel = tipoPrestador.get();
        tipoPrestadorModel.setFuncao(tipoPrestadorDto.funcao());
        tipoPrestadorModel.setStatus(tipoPrestadorDto.status());

        // Removido a lógica que vincula o Prestador

        return ResponseEntity.ok(tipoPrestadorRepository.save(tipoPrestadorModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarTipoPrestador(@PathVariable(value = "id") Integer id) {
        Optional<TipoPrestadorModel> tipoPrestador = tipoPrestadorRepository.findById(id);
        if (tipoPrestador.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Prestador não encontrado.");
        }
        tipoPrestadorRepository.delete(tipoPrestador.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tipo de Prestador deletado com sucesso.");
    }
}
