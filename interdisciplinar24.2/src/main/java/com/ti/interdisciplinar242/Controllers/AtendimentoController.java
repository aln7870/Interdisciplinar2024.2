package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.DTOs.AtendimentoDto;
import com.ti.interdisciplinar242.Interfaces.AtendimentoInterface;
import com.ti.interdisciplinar242.Interfaces.PrestadorInterface;
import com.ti.interdisciplinar242.Models.AtendimentoModel;
import com.ti.interdisciplinar242.Models.PrestadorModel;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/atendimento")


public class AtendimentoController {

    @Autowired
    AtendimentoInterface AtendimentoInterface;
    @Autowired
    PrestadorInterface prestadorInterface;
    @PostMapping
    public ResponseEntity<AtendimentoModel> criarAtendimento(@RequestBody @Valid AtendimentoDto atendimentoDto) {
        PrestadorModel prestador = prestadorInterface.findById(atendimentoDto.codPrestador())
                .orElseThrow(() -> new EntityNotFoundException("Prestador não encontrado."));

        AtendimentoModel atendimento = new AtendimentoModel();
        atendimento.setDataAtendimento(atendimentoDto.dataAtendimento());
        atendimento.setObservacoes(atendimentoDto.observacoes());
        atendimento.setTipoStatus(atendimentoDto.tipoStatus());
        atendimento.setStatus(atendimentoDto.status());
        atendimento.setPrestador(prestador);

        // Se quiser que a data de atendimento também seja a atual, caso não tenha sido fornecida
        if (atendimento.getDataAtendimento() == null) {
            atendimento.setDataAtendimento(LocalDateTime.now());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(AtendimentoInterface.save(atendimento));
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoModel>> puxarTodosAtendimentos(){
        return ResponseEntity.status(HttpStatus.OK).body(AtendimentoInterface.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> puxarUmAtendimento(@PathVariable(value = "id") Integer id) {
        Optional<AtendimentoModel> atendimento = AtendimentoInterface.findById(id);
        if (atendimento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atendimento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(atendimento.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaAtendimento(@PathVariable(value = "id") Integer id,
                                                      @RequestBody @Valid AtendimentoDto atendimentoDto) {
        Optional<AtendimentoModel> atendimento = AtendimentoInterface.findById(id);
        if (atendimento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atendimento não encontrado");
        }
        var atendimentoModel = atendimento.get();
        BeanUtils.copyProperties(atendimentoDto, atendimentoModel);
        return ResponseEntity.status(HttpStatus.OK).body(AtendimentoInterface.save(atendimentoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletaAtendimento(@PathVariable(value = "id") Integer id) {
        Optional<AtendimentoModel> atendimento = AtendimentoInterface.findById(id);
        if (atendimento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atendimento não encontrado");
        }
        AtendimentoInterface.delete(atendimento.get());
        return ResponseEntity.status(HttpStatus.OK).body("Atendimento deletado");
    }
}

