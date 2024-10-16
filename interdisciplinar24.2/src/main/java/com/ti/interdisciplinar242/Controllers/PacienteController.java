package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Controllers.DTOs.PacienteDto;
import com.ti.interdisciplinar242.repository.PacienteRepository;
import com.ti.interdisciplinar242.Models.PacienteModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente") // Correção aqui
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping
    ResponseEntity<PacienteModel> cadastroPaciente(@RequestBody @Valid PacienteDto pacienteDto) {
        if (pacienteDto.dataNascimento() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        var pacienteModel = new PacienteModel();
        Date dataNascimento = Date.valueOf(pacienteDto.dataNascimento());
        pacienteModel.setDataNascimento(dataNascimento);
        BeanUtils.copyProperties(pacienteDto, pacienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteRepository.save(pacienteModel));
    }


    @GetMapping
    public ResponseEntity<List<PacienteModel>> getAllPacientes() {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPacienteById(@PathVariable(value = "id") Integer id) {
        Optional<PacienteModel> paciente = pacienteRepository.findById(id);
        if (paciente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(paciente.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizaPaciente(@PathVariable(value = "id") Integer id,
                                                   @RequestBody @Valid PacienteDto pacienteDto) {
        Optional<PacienteModel> paciente = pacienteRepository.findById(id);
        if (paciente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
        }
        var pacienteModel = paciente.get();
        BeanUtils.copyProperties(pacienteDto, pacienteModel, "id"); // Evita alterar o ID
        Date dataNascimento = Date.valueOf(pacienteDto.dataNascimento());
        pacienteModel.setDataNascimento(dataNascimento);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.save(pacienteModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletaPaciente(@PathVariable(value = "id") Integer id) {
        Optional<PacienteModel> paciente = pacienteRepository.findById(id);
        if (paciente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
        }
        pacienteRepository.delete(paciente.get());
        return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado com sucesso");
    }
}
