/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Categoria;
import com.example.demo.model.Produto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ItemCarrinhoRepository;
import com.example.demo.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jessica
 */
@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    public void cadastrarCategoria(Categoria cat) {
        categoriaRepository.save(cat);
    }

    public Categoria editarCategoria(Categoria cat) {
        return categoriaRepository.save(cat);
    }

    public void excluirCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    

    public List<Produto> buscaTodosProdutosCategoria(Categoria cat) {
        return produtoRepository.findByCategoria(cat);

    }
    
    public List<Categoria> buscaTodasCategorias(){
        return categoriaRepository.findAll();
    }
    


}
