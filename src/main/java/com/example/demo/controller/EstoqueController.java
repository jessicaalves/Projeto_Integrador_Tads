/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Estoque;
import com.example.demo.model.Venda;
import com.example.demo.services.EstoqueService;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jessica
 */
@RestController
@RequestMapping(value = "/estoque")
public class EstoqueController {
    
    @Autowired
    EstoqueService estoqueService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarVenda(@RequestBody Estoque est) {

        estoqueService.cadastrarEstoque(est);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    ResponseEntity removerEstoque(@PathVariable Long id) {

       estoqueService.excluirEstoque(id);
       
       return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    void editarEstoque() {
        System.out.println("edita");
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Venda> mostraEstoque(@PathVariable Long id) {
        
        Estoque est;
        try {
            est = estoqueService.buscaEstoque(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(est, HttpStatus.OK);
    }
    
}
