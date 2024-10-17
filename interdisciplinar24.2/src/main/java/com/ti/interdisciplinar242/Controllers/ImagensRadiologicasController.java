package com.ti.interdisciplinar242.Controllers;

import com.ti.interdisciplinar242.Controllers.DTOs.ImagensRadiologicasDto;
import com.ti.interdisciplinar242.Models.ImagensRadiologicasModel;
import com.ti.interdisciplinar242.repository.ImgRadiologicasRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ImagensRadiologicasController {

    @Autowired
    ImgRadiologicasRepository imgRadiologicasRepository;

    @PostMapping
    public ResponseEntity<ImagensRadiologicasModel> postImagens(@RequestBody @Valid ImagensRadiologicasDto imagensRadiologicasDto){
        var imagensModel = new ImagensRadiologicasModel();
        BeanUtils.copyProperties(imagensRadiologicasDto, imagensModel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(imgRadiologicasRepository.save(imagensModel));
    }

    @GetMapping
    public ResponseEntity<List<ImagensRadiologicasModel>> getAllImagens(){
        return ResponseEntity.status(HttpStatus.OK).body(imgRadiologicasRepository.findAll());
    }

    @GetMapping("/{codImagemRadiologica}")
    public ResponseEntity<Object> getImagensById(@PathVariable(value = "codImagemRadiologica") Integer codImagemRadiologica){
        Optional<ImagensRadiologicasModel> imagem = imgRadiologicasRepository.findById(codImagemRadiologica);
        if (imagem.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(imagem.get());
    }

    @PutMapping("/{codImagemRadiologica}")
    public ResponseEntity<Object> putImagem(@PathVariable(value = "codImagemRadiologica") Integer codImagemRadiologica,
                                            @RequestBody @Valid ImagensRadiologicasDto imagensRadiologicasDto){
        Optional<ImagensRadiologicasModel> imagem = imgRadiologicasRepository.findById(codImagemRadiologica);
        if (imagem.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem não encontrada");
        }
        var imagemModel = imagem.get();
        BeanUtils.copyProperties(imagensRadiologicasDto, imagemModel);
        return ResponseEntity.status(HttpStatus.OK).body(imgRadiologicasRepository.save(imagemModel));
    }

    @DeleteMapping("/{codImagemRadiologica}")
    public ResponseEntity<Object> deleteImagem(@PathVariable(value = "codImagemRadiologica") Integer codImagemRadiologica){
        Optional<ImagensRadiologicasModel> imagem = imgRadiologicasRepository.findById(codImagemRadiologica);
        if (imagem.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem não encontrada");
        }
        imgRadiologicasRepository.delete(imagem.get());
        return ResponseEntity.status(HttpStatus.OK).body("Imagem deletada");
    }
}
