/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author SimoneBarbosa
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;

    }

public void setUploadDir (String UploadDir){
    this.uploadDir=UploadDir;
    
}
}