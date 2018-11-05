/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Venda;
import com.example.demo.services.VendaService;
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
@RequestMapping(value = "/venda")
public class VendaController {
    
    @Autowired
    VendaService vendaService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarVenda(@RequestBody Venda ven) {

        vendaService.cadastrarVenda(ven);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    ResponseEntity removerVenda(@PathVariable Long id) {

       vendaService.excluirVenda(id);
       
       return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    void editarVenda() {
        System.out.println("edita");
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Venda> mostraVenda(@PathVariable Long id) {
        
        Venda ven;
        try {
            ven = vendaService.buscaVenda(id);

        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(ven, HttpStatus.OK);
    }
 
}
