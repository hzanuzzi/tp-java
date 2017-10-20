/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Departamento;
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author mdominguez
 */
public class DepartamentoDaoJPA implements DepartamentoDao{
    private EntityManager em;
    
    @Override
    public Departamento crear(Departamento d) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        em.persist(d);
        // forzar el insert en la base de datos
        em.flush();
        // refrescar con los datos de la base de datos
        em.refresh(d);
        em.getTransaction().commit();
        em.close();   
        return d;
    }

    @Override
    public void borrar(Integer id) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Departamento aBorrar = em.find(Departamento.class, id);
        em.remove(aBorrar);
        em.getTransaction().commit();
        em.close();   
    }

    @Override
    public Departamento actualizar(Departamento d) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        Departamento resultado = em.merge(d);
        em.getTransaction().commit();
        em.close();   
        return resultado;
    }

    @Override
    public Departamento buscarPorId(Integer id) {
        em = ConexionJPA.getInstance().em();
        em.getTransaction().begin();
        //Departamento resultado = em.find(Departamento.class,id);
        //resultado.getEmpleados().size();
        //TODO resolver problema de lazy inicialization con alguno de las soluciones planteadas
        Query q = em.createQuery("SELECT d FROM Departamento d LEFT JOIN FETCH d.empleados WHERE d.id = :id");
        q.setParameter("id", id);
        Departamento resultado = (Departamento) q.getSingleResult();

        em.getTransaction().commit();
        em.close();   
        return resultado;
    }

    @Override
    public List<Departamento> buscarTodos() {
        return em.createQuery("SELECT d FROM Departamento d").getResultList();
    }
    
}
