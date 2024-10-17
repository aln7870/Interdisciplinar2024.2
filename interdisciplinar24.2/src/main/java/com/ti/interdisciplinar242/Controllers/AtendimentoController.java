package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Controllers.DTOs.AtendimentoDto;
import com.ti.interdisciplinar242.Models.DenteModel;
import com.ti.interdisciplinar242.Models.PacienteModel;
import com.ti.interdisciplinar242.repository.AtendimentoRepository;
import com.ti.interdisciplinar242.repository.DenteRepository;
import com.ti.interdisciplinar242.repository.PacienteRepository;
import com.ti.interdisciplinar242.repository.PrestadorRepository;
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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/atendimento")


public class AtendimentoController {

    @Autowired
    AtendimentoRepository AtendimentoRepository;
    @Autowired
    PrestadorRepository prestadorRepository;
    @Autowired
    DenteRepository denteRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<AtendimentoModel> criarAtendimento(@RequestBody @Valid AtendimentoDto atendimentoDto) {
        PrestadorModel prestador = prestadorRepository.findById(atendimentoDto.codPrestador())
                .orElseThrow(() -> new EntityNotFoundException("Prestador não encontrado."));
        // Busca o paciente
        PacienteModel paciente = pacienteRepository.findById(atendimentoDto.codPaciente())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado."));
        AtendimentoModel atendimento = new AtendimentoModel();
        atendimento.setPaciente(paciente);
        atendimento.setDataAtendimento(atendimentoDto.dataAtendimento());
        atendimento.setObservacoes(atendimentoDto.observacoes());
        atendimento.setTipoStatus(atendimentoDto.tipoStatus());
        atendimento.setStatus(atendimentoDto.status());
        atendimento.setPrestador(prestador);

        if (atendimentoDto.codDente() != null) {
            Optional<DenteModel> dente = denteRepository.findById(atendimentoDto.codDente());
            dente.ifPresent(atendimento::setDente);  // Se o dente existir, associá-lo ao atendimento
        }

        // Se quiser que a data de atendimento também seja a atual, caso não tenha sido fornecida
        if (atendimento.getDataAtendimento() == null) {
            atendimento.setDataAtendimento(LocalDateTime.now());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(AtendimentoRepository.save(atendimento));
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoModel>> puxarTodosAtendimentos(){
        return ResponseEntity.status(HttpStatus.OK).body(AtendimentoRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> puxarUmAtendimento(@PathVariable(value = "id") Integer id) {
        Optional<AtendimentoModel> atendimento = AtendimentoRepository.findById(id);
        if (atendimento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atendimento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(atendimento.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaAtendimento(@PathVariable(value = "id") Integer id,
                                                      @RequestBody @Valid AtendimentoDto atendimentoDto) {
        Optional<AtendimentoModel> atendimento = AtendimentoRepository.findById(id);
        if (atendimento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atendimento não encontrado");
        }
        var atendimentoModel = atendimento.get();
        BeanUtils.copyProperties(atendimentoDto, atendimentoModel);
        if (atendimentoDto.codDente() != null) {
            Optional<DenteModel> dente = denteRepository.findById(atendimentoDto.codDente());
            dente.ifPresent(atendimentoModel::setDente);
        }
        return ResponseEntity.status(HttpStatus.OK).body(AtendimentoRepository.save(atendimentoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletaAtendimento(@PathVariable(value = "id") Integer id) {
        Optional<AtendimentoModel> atendimento = AtendimentoRepository.findById(id);
        if (atendimento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atendimento não encontrado");
        }
        AtendimentoRepository.delete(atendimento.get());
        return ResponseEntity.status(HttpStatus.OK).body("Atendimento deletado");
    }
}

