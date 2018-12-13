/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Carrinho;
import com.example.demo.model.ItemCarrinho;
import com.example.demo.model.Produto;
import com.example.demo.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jessica
 */

@Service
public class ItemCarrinhoService {
    
    @Autowired  
    ItemCarrinhoRepository itemCarrinhoRepository;
    
    public ItemCarrinho salvarItemCarrinho(ItemCarrinho itCar) {
       return itemCarrinhoRepository.save(itCar);
    }
    
    public void excluirItemCarrinho(Long id) {
        itemCarrinhoRepository.deleteById(id);
    }
    
    public ItemCarrinho buscaItemCarrinho(Carrinho car, Produto prod){
        return itemCarrinhoRepository.findByProdutoAndCarrinho(prod, car);
    
    }

    
}
