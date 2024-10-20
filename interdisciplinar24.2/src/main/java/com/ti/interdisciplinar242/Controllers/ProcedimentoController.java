package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Controllers.DTOs.ProcedimentoDto;
import com.ti.interdisciplinar242.Models.ProcedimentoModel;
import com.ti.interdisciplinar242.repository.ProcedimentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/procedimento")
@CrossOrigin("*")
public class ProcedimentoController {

    @Autowired
    ProcedimentoRepository procedimentoRepository;

    @PostMapping
    public ResponseEntity<ProcedimentoModel> createProcedimento(@Valid @RequestBody ProcedimentoDto procedimentoDto){
        var procedimentoModel = new ProcedimentoModel();
        BeanUtils.copyProperties(procedimentoDto, procedimentoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(procedimentoRepository.save(procedimentoModel));
    }

    @GetMapping
    public ResponseEntity<List<ProcedimentoModel>> getAllProcedimento(){
        return ResponseEntity.status(HttpStatus.OK).body(procedimentoRepository.findAll());
    }

    @GetMapping("/{codProcedimento}")
    public ResponseEntity<Object> getProcedimentoById(@PathVariable(value = "codProcedimento") Integer codProcedimento){
        Optional<ProcedimentoModel> procedimento = procedimentoRepository.findById(codProcedimento);
        if (procedimento.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedimento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(procedimento.get());
    }

    @PutMapping("/{codProcedimento}")
    public ResponseEntity<Object> putProcedimento(@PathVariable(value = "codProcedimento") Integer codProcedimento,
                                                  @RequestBody @Valid ProcedimentoDto procedimentoDto){
        Optional<ProcedimentoModel> procedimento = procedimentoRepository.findById(codProcedimento);
        if (procedimento.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedimento não encontrado");
        }
        var procedimentoModel = new ProcedimentoModel();
        BeanUtils.copyProperties(procedimentoDto, procedimentoModel);
        return ResponseEntity.status(HttpStatus.OK).body(procedimentoRepository.save(procedimentoModel));
    }

    @DeleteMapping("/{codProcedimento}")
    public ResponseEntity<Object> deleteProcedimento(@PathVariable(value = "codProcedimento") Integer codProcedimento){
        Optional<ProcedimentoModel> procedimento = procedimentoRepository.findById(codProcedimento);
        if (procedimento.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedimento não encontrado");
        }
        procedimentoRepository.delete(procedimento.get());
        return ResponseEntity.status(HttpStatus.OK).body("Procedimento deletado");
    }
}
