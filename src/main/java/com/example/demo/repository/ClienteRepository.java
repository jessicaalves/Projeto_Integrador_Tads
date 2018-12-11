/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jessica
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    public Cliente findByEmailAndSenha(@Param("email")String email,@Param("senha")String senha);
}
