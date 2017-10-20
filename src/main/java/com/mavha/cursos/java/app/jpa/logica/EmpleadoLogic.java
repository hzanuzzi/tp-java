/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.logica;

import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import java.util.Date;

/**
 *
 * @author mdominguez
 */
public interface EmpleadoLogic {

    /**
     * Si se cumple la condicion 
     * @param nombre: el nombre del proyecto es obligatorio
     * @param presupuesto: el presupuesto del proyecto es obligatorio
     * @param idLider: el ID del lider del proyecto. 
     */
    public Empleado crearEmpleado(String nombre, Double salarioHora,Date fecAlta);
    public void asignarDepartamento(Integer idEmpleado,Integer idDepto);
}
