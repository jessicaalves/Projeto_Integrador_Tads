/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Carrinho;
import com.example.demo.model.ItemCarrinho;
import com.example.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

/**
 *
 * @author jessica
 */
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

//    @Modifying
//    @Query("delete from ItemCarrinho i where i.carrinho.id = ?1")
//    void apagaItensCarrinho(long idCarrinho);
    public ItemCarrinho findByProdutoAndCarrinho(Produto prod, Carrinho car);

    @Modifying
    public void deleteByCarrinho(Carrinho car);

}
