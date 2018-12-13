/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Carrinho;
import com.example.demo.model.ItemCarrinho;
import com.example.demo.model.Produto;
import com.example.demo.repository.CarrinhoRepository;
import com.example.demo.services.CarrinhoService;
import com.example.demo.services.ItemCarrinhoService;
import com.example.demo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jessica
 */
@RestController
@RequestMapping(value = "/auth/carrinho")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    ItemCarrinhoService itemCarrinhoService;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity adicionarProdutoCarrinho(@RequestBody Produto prod,
            @RequestHeader(value = "Authorization") String autorizacao) {

        Carrinho car = new Carrinho();
        car.setId(carrinhoService.retornaIdCarrinho(autorizacao));

        Produto produtoEstoque = produtoService.buscaProduto(prod.getId());

        ItemCarrinho item = itemCarrinhoService.buscaItemCarrinho(car, prod);
        if (item == null) {
            if (prod.getQuantidade() > produtoEstoque.getQuantidade()) {
                return new ResponseEntity("Produto indisponível no estoque!", HttpStatus.BAD_REQUEST);
            }

            ItemCarrinho itemCar = new ItemCarrinho();

            itemCar.setCarrinho(car);
            itemCar.setProduto(prod);
            itemCar.setQuantidade(prod.getQuantidade());

            ItemCarrinho itemCarrinhoSalvo = itemCarrinhoService.salvarItemCarrinho(itemCar);

            if (itemCarrinhoSalvo == null) {
                return new ResponseEntity("Erro ao adicionar produto no carrinho! Tente novamente!", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity(itemCarrinhoSalvo, HttpStatus.CREATED);

        } else {
            if ((item.getQuantidade() + prod.getQuantidade()) > produtoEstoque.getQuantidade()) {
                return new ResponseEntity("Produto indisponível no estoque!", HttpStatus.BAD_REQUEST);
            }

            item.setQuantidade(item.getQuantidade() + prod.getQuantidade());

           ItemCarrinho itemCarrinhoSalvo = itemCarrinhoService.salvarItemCarrinho(item);
           
           return new ResponseEntity(itemCarrinhoSalvo, HttpStatus.CREATED);
        }
       

    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity mostrarCarrinho(@RequestHeader(value = "Authorization") String autorizacao) {
        Carrinho car = carrinhoRepository.findById(carrinhoService.retornaIdCarrinho(autorizacao)).get();

        if (car == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(car, HttpStatus.OK);

    }

    

}
