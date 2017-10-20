/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mdominguez
 */
public class ConexionJPA {
    
    private static ConexionJPA gestor = null;
    private EntityManagerFactory emf;
    
    private ConexionJPA(){
         emf = Persistence.createEntityManagerFactory("lab01");
    }
    

    public static ConexionJPA getInstance(){
        if(gestor==null) gestor = new ConexionJPA();
        return gestor;
    }
    
    public EntityManager em(){        
        return emf.createEntityManager();
    }

}
