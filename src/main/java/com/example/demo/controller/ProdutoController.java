/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.model.Venda;
import com.example.demo.services.ProdutoService;
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
@RequestMapping(value = "/admin")
public class ProdutoController {
    
    @Autowired
    ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.POST,value = "/produto",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarProduto(@RequestBody Produto pro) {

     if(produtoService.cadastrarProduto(pro) == null){
         return new ResponseEntity(HttpStatus.BAD_REQUEST);
     }

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    ResponseEntity removerProduto(@PathVariable Long id) {

       produtoService.excluirProduto(id);
       
       return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    void editarProduto() {
        System.out.println("edita");
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Venda> mostraProduto(@PathVariable Long id) {
        
        Produto pro;
        try {
            pro = produtoService.buscaProduto(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pro, HttpStatus.OK);
    }
    
}
