/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jessica
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
