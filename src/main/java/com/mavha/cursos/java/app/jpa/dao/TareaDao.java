/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Tarea;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface TareaDao {
    public Tarea crear(Tarea t);
    public void borrar(Integer t);
    public Tarea actualizar(Tarea t);
    public Tarea buscarPorId(Integer id);
    public List<Tarea> buscarTodas();
}
