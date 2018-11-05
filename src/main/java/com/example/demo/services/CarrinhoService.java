/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Carrinho;
import com.example.demo.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jessica
 */
@Service
public class CarrinhoService {
    
    @Autowired   
    CarrinhoRepository carrinhoRepository;
 
    public void cadastrarCarrinho(Carrinho car) {
        carrinhoRepository.save(car);
    }

    public Carrinho editarCarrinho(Carrinho car) {
       return carrinhoRepository.save(car);
    }

    public void excluirCarrinho(Long id) {
        carrinhoRepository.deleteById(id);
    }

    public Carrinho buscaCarrinho(Long id) {
        return carrinhoRepository.findById(id).get();
    }
    
}
