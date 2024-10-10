package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Interfaces.DenteInterface;
import com.ti.interdisciplinar242.Models.DenteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dente")
public class DenteController {

    @Autowired
    DenteInterface denteInterface;

    @GetMapping
    public ResponseEntity<List<DenteModel>> getAllDente(){
         return ResponseEntity.status(HttpStatus.OK).body(denteInterface.findAll());
    }

    /*@GetMapping("/{codDente}")
    public Optional<DenteModel> getDenteById(@PathVariable Integer codDente){
        return denteInterface.findById(codDente);
    }*/

    @GetMapping("/{codDente}")
    public ResponseEntity<Object> getDenteById(@PathVariable(value = "codDente")Integer codDente){
        Optional<DenteModel> dente = denteInterface.findById(codDente);
        if (dente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("o não encontrado😢");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(dente.get());
    }
}
