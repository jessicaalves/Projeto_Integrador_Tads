/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Estoque;
import com.example.demo.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jessica
 */
@Service
public class EstoqueService {
    
    @Autowired   
    EstoqueRepository estoqueRepository;
    
    public void cadastrarEstoque(Estoque est) {
        estoqueRepository.save(est);
    }

    public Estoque editarEstoque(Estoque est) {
       return estoqueRepository.save(est);
    }

    public void excluirEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }

    public Estoque buscaEstoque(Long id) {
        return estoqueRepository.findById(id).get();
    }
    
}
