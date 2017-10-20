/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.dao;

import com.mavha.cursos.java.app.jpa.modelo.Departamento;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface DepartamentoDao {
    public Departamento crear(Departamento t);
    public void borrar(Integer id);
    public Departamento actualizar(Departamento t);
    public Departamento buscarPorId(Integer id);
    public List<Departamento> buscarTodos();

}
