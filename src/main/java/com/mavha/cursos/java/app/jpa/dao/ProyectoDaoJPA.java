/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Proyecto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author mdominguez
 */
public class ProyectoDaoJPA implements ProyectoDao{
    private EntityManager em;

    @Override
    public Proyecto crear(Proyecto t) {
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
    public Proyecto actualizar(Proyecto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Proyecto buscarPorId(Integer id) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Proyecto resultado = em.find(Proyecto.class, id);
        resultado.getTarea().size();
        em.getTransaction().commit();
        em.close();     
        return resultado;

    }

    @Override
    public List<Proyecto> buscarTodos() {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        List<Proyecto> p = em.createQuery("SELECT p FROM Proyecto p ").getResultList();
        em.getTransaction().commit();
        em.close();     
        return p;
    }
    
}
