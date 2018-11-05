/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Imagem;
import com.example.demo.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jessica
 */
@Service
public class ImagemService {
    
    @Autowired   
    ImagemRepository imagemRepository;
    
    public void cadastrarImagem(Imagem img) {
        imagemRepository.save(img);
    }

    public Imagem editarImagem(Imagem img) {
       return imagemRepository.save(img);
    }

    public void excluirImagem(Long id) {
        imagemRepository.deleteById(id);
    }

    public Imagem buscaImagem(Long id) {
        return imagemRepository.findById(id).get();
    }
    
}
