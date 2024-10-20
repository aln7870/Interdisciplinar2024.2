package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Controllers.DTOs.FaturamentoDto;
import com.ti.interdisciplinar242.Models.FaturamentoModel;
import com.ti.interdisciplinar242.repository.FaturamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faturamento")
@CrossOrigin("*")
public class FaturamentoController {

    @Autowired
    private FaturamentoRepository faturamentoRepository;

    @PostMapping
    public ResponseEntity<FaturamentoModel> createFaturamento(@Valid @RequestBody FaturamentoDto faturamentoDto) {
        var faturamentoModel = new FaturamentoModel();
        BeanUtils.copyProperties(faturamentoDto, faturamentoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(faturamentoRepository.save(faturamentoModel));
    }

    @GetMapping
    public ResponseEntity<List<FaturamentoModel>> getAllFaturamento() {
        return ResponseEntity.status(HttpStatus.OK).body(faturamentoRepository.findAll());
    }


    @GetMapping("/{codFaturamento}")
    public ResponseEntity<Object> getFaturamentoById(@PathVariable(value = "codFaturamento") Integer codFaturamento) {
        Optional<FaturamentoModel> faturamento = faturamentoRepository.findById(codFaturamento);
        if (faturamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faturamento não encontrado😢");
        }
        return ResponseEntity.status(HttpStatus.OK).body(faturamento.get());
    }

    @PutMapping("/{codFaturamento}")
    public ResponseEntity<Object> putFaturamento(@PathVariable(value = "codFaturament") Integer codFaturamento,
                                                 @RequestBody @Valid FaturamentoDto faturamenteDto) {
        Optional<FaturamentoModel> faturamento = faturamentoRepository.findById(codFaturamento);
        if (faturamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faturamento não encontrado");
        }
        var faturamentoModel = faturamento.get();
        BeanUtils.copyProperties(faturamenteDto, faturamentoModel);
        return ResponseEntity.status(HttpStatus.OK).body(faturamentoRepository.save(faturamentoModel));
    }

    @DeleteMapping("/{codFaturamento}")
    public ResponseEntity<Object> deleteFaturamento(@PathVariable(value = "codFaturamento") Integer codFaturamento) {
        Optional<FaturamentoModel> faturamento = faturamentoRepository.findById(codFaturamento);
        if (faturamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faturamento não encontrado");
        }
        faturamentoRepository.delete(faturamento.get());

        return ResponseEntity.status(HttpStatus.OK).body("Faturamento deletado.");

    }

}


