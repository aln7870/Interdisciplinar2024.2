package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.DTOs.PacienteDto;
import com.ti.interdisciplinar242.Interfaces.PacienteInterface;
import com.ti.interdisciplinar242.Models.PacienteModel;
import com.ti.interdisciplinar242.Models.TesteModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("paciente")
public class PacienteController {

        @Autowired
    PacienteInterface pacienteInterface;


        @PostMapping
    ResponseEntity<PacienteModel> cadastroPaciente(@RequestBody @Valid PacienteDto pacienteDto){
            var pacienteModel = new PacienteModel();
            Date dataNascimento = Date.valueOf(pacienteDto.dataNascimento());
            pacienteModel.setDataNascimento(dataNascimento);
            BeanUtils.copyProperties(pacienteDto, pacienteModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteInterface.save(pacienteModel));
        }

        @GetMapping
    public ResponseEntity<List<PacienteModel>> getAllPacientesProjecao() {
            return ResponseEntity.status(HttpStatus.OK).body(pacienteInterface.findAll());
    }

}
