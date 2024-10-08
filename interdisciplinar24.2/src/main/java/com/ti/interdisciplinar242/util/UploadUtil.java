package com.ti.interdisciplinar242.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
// (Alan) - Criei essa classe para testar a questão das imagens, qnd terminar os tokens jwt vejo isso.
public class UploadUtil {

    public static boolean uploadImagem(MultipartFile imagem){

        boolean sucessoUpload = false;

        if(!imagem.isEmpty()){
            String nomeArquivo = imagem.getOriginalFilename();
            try{


                String pastaUploadImg = "C:\\Users\\Alan\\Documents\\estudos\\JAVA\\interdisciplinar24.2\\interdisciplinar24.2\\src\\main\\resources\\static\\raioX\\imgRaioX";
                File diretorio = new File(pastaUploadImg);
                if (!diretorio.exists()){
                    diretorio.mkdirs();
                }

                File file = new File(diretorio.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));

                stream.write(imagem.getBytes());
                stream.close();


                System.out.println("Armazenando em: " + file.getAbsolutePath());
                System.out.println("vc fez upload do arquivo: " + nomeArquivo + "Arquivo com sucesso!");

            } catch (Exception exception){
                System.out.println("erro ao upar a imagem: " + nomeArquivo + "e" + exception.getMessage());
            }
    }else{
            System.out.println("erro ao carregar a imagem pq está vazio.");
        }
        return sucessoUpload;
    }
}
