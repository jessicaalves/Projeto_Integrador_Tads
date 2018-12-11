/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Categoria;
import com.example.demo.model.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jessica
 */
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {
   @Modifying
   @Query("delete from ItemCarrinho i where i.carrinho.id = ?1")        
   void apagaItensCarrinho( long idCarrinho);
}
