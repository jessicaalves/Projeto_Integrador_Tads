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
import static com.example.demo.services.Autenticacao.key;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jessica
 */
@Service
@Transactional
public class CarrinhoService {

    @Autowired
    CarrinhoRepository carrinhoRepository;
    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;
    private final long TEMPO_DE_EXPIRACAO = TimeUnit.DAYS.toMillis(15);

    public Carrinho cadastrarCarrinho(Carrinho car) {
        return carrinhoRepository.save(car);
    }

    public Carrinho editarCarrinho(Carrinho car) {
        return carrinhoRepository.save(car);
    }

    public void limpaCarrinho(Carrinho c) {
        itemCarrinhoRepository.deleteByCarrinho(c);
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
            itemCarrinhoRepository.deleteByCarrinho(carrinho);
            carrinho.setExpTime(new Date(System.currentTimeMillis() + TEMPO_DE_EXPIRACAO));
        }

        return carrinho;

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

    public Carrinho retornaTodoCarrinho(String token) {
        Long id = retornaIdCarrinho(token);

        Carrinho c = buscaCarrinho(id);

        return c;
    }
}
