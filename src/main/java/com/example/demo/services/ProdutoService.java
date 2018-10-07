/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jessica
 */
@Service
public class ProdutoService {
    
    @Autowired   
    ProdutoRepository produtoRepository;
    
    public void cadastrarProduto(Produto pro) {
        produtoRepository.save(pro);
    }

    public Produto editarProduto(Produto pro) {
       return produtoRepository.save(pro);
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto buscaProduto(Long id) {
        return produtoRepository.findById(id).get();
    }
    
}
