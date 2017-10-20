/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Tarea;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author mdominguez
 */
public class TareaDaoJPA implements TareaDao{
    private EntityManager em;

    @Override
    public Tarea crear(Tarea t) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(t);
        em.flush();
        em.refresh(t);
        em.getTransaction().commit();
        em.close(); 
        return t;   
    }

    @Override
    public void borrar(Integer t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tarea actualizar(Tarea t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tarea buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tarea> buscarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
