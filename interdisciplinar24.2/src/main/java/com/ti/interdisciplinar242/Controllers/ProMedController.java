package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Controllers.DTOs.ProMedDto;
import com.ti.interdisciplinar242.repository.ProMedRepository;
import com.ti.interdisciplinar242.Models.DenteModel;
import com.ti.interdisciplinar242.Models.ProMedModel;
import com.ti.interdisciplinar242.Models.AtendimentoModel;
import com.ti.interdisciplinar242.Models.ProcedimentoModel;
import com.ti.interdisciplinar242.repository.AtendimentoRepository;
import com.ti.interdisciplinar242.repository.DenteRepository;
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
@RequestMapping("/promed")
@CrossOrigin(origins = "*")
public class ProMedController {

    @Autowired
    ProMedRepository proMedRepository;

    @Autowired
    DenteRepository denteRepository;

    @Autowired
    AtendimentoRepository atendimentoRepository;

    @Autowired
    ProcedimentoRepository procedimentoRepository;

    @PostMapping
    public ResponseEntity<ProMedModel> cadastroProMed(@RequestBody @Valid ProMedDto proMedDto) {
        var proMedModel = new ProMedModel();

        // Copia os dados do DTO para o Model
        BeanUtils.copyProperties(proMedDto, proMedModel);

        // Buscar Dente
        Optional<DenteModel> dente = denteRepository.findById(proMedDto.codDente());
        if (dente.isPresent()) {
            proMedModel.setDente(dente.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Buscar Atendimento
        Optional<AtendimentoModel> atendimento = atendimentoRepository.findById(proMedDto.codAtendim());
        if (atendimento.isPresent()) {
            proMedModel.setAtendimento(atendimento.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Buscar Procedimento
        Optional<ProcedimentoModel> procedimento = procedimentoRepository.findById(proMedDto.codProcedimento());
        if (procedimento.isPresent()) {
            proMedModel.setProcedimento(procedimento.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Salva o ProMed
        return ResponseEntity.status(HttpStatus.CREATED).body(proMedRepository.save(proMedModel));
    }

    @GetMapping
    public ResponseEntity<List<ProMedModel>> listarTodosProMed() {
        return ResponseEntity.status(HttpStatus.OK).body(proMedRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> puxarUmProMed(@PathVariable(value = "id") Integer id) {
        Optional<ProMedModel> proMed = proMedRepository.findById(id);
        if (proMed.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProMed não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(proMed.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaProMed(@PathVariable(value = "id") Integer id,
                                                 @RequestBody @Valid ProMedDto proMedDto) {
        Optional<ProMedModel> proMed = proMedRepository.findById(id);
        if (proMed.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProMed não encontrado.");
        }

        var proMedModel = proMed.get();
        BeanUtils.copyProperties(proMedDto, proMedModel);
        return ResponseEntity.status(HttpStatus.OK).body(proMedRepository.save(proMedModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletaProMed(@PathVariable(value = "id") Integer id) {
        Optional<ProMedModel> proMed = proMedRepository.findById(id);
        if (proMed.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ProMed não encontrado.");
        }
        proMedRepository.delete(proMed.get());
        return ResponseEntity.status(HttpStatus.OK).body("ProMed deletado com sucesso.");
    }
}
