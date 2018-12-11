/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.model.Carrinho;
import com.example.demo.model.Cliente;
import com.example.demo.repository.CarrinhoRepository;
import com.example.demo.repository.ItemCarrinhoRepository;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
    @Autowired
    ItemCarrinhoRepository itemCarrinho;
    private final long TEMPO_DE_EXPIRACAO = TimeUnit.DAYS.toMillis(15);

    public Carrinho cadastrarCarrinho(Carrinho car) {
        return carrinhoRepository.save(car);
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

    public Carrinho buscaCarrinhoPorCliente(Cliente cli) {
        Carrinho carrinho = carrinhoRepository.findByCliente(cli);
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setCliente(cli);
            carrinho.setExpTime(new Date(System.currentTimeMillis() + TEMPO_DE_EXPIRACAO));
            return cadastrarCarrinho(carrinho);
        } else if (new Date(System.currentTimeMillis()).after(carrinho.getExpTime())) {
            itemCarrinho.apagaItensCarrinho(carrinho.getId());
            carrinho.setExpTime(new Date(System.currentTimeMillis() + TEMPO_DE_EXPIRACAO));
        }

        return carrinho;

    }
}
