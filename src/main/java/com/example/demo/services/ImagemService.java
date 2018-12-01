/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SimoneBarbosa
 */
@Service
public class ImagemService {
    private final Path fileStorageLocation;
    @Autowired
    private ImagemService(FileStorageProperties fsp){
        this.fileStorageLocation=Paths.get(fsp.getUploadDir()).toAbsolutePath().normalize();
        try{
            Files.createDirectories(fileStorageLocation);
        }catch(IOException e){
            System.out.println("não foi possível criar o diretorio raiz de uploads" + e);
        }
    }
}
