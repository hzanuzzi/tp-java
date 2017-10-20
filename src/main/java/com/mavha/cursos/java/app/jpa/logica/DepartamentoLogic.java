/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.logica;

import com.mavha.cursos.java.app.jpa.modelo.Departamento;
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface DepartamentoLogic {
    public Departamento crear(String  nombre);
    public List<Empleado> empleados(Integer id);
    
}
