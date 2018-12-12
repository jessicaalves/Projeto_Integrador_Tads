/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Carrinho;
import com.example.demo.model.ItemCarrinho;
import com.example.demo.model.Produto;
import static com.example.demo.services.Autenticacao.key;
import com.example.demo.services.CarrinhoService;
import com.example.demo.services.ItemCarrinhoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
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

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity adicionarProdutoCarrinho(@RequestBody Produto prod,
            @RequestHeader(value = "Authorization") String autorizacao) {

        Carrinho car = new Carrinho();
        car.setId(retornaIdCarrinho(autorizacao));
        ItemCarrinho itemCar = new ItemCarrinho();
        
        itemCar.setCarrinho(car);
        itemCar.setProduto(prod);
        itemCar.setQuantidade(prod.getQuantidade());
        
        ItemCarrinho itemCarrinhoSalvo;
        
        itemCarrinhoSalvo = itemCarrinhoService.salvarItemCarrinho(itemCar);
        
        if(itemCarrinhoSalvo == null){
          return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);

    }

    public Long retornaIdCarrinho(String token) {//Recebe o token completo com bearer
        String somentetoken = token.substring(7);
        Long idCarrinho;
        try {

            Claims c = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(somentetoken)
                    .getBody();

            idCarrinho = Long.parseLong("" + c.get("idCarrinho"));
            return idCarrinho;

        } catch (JwtException e) {

        }
        return null;
    }

}
