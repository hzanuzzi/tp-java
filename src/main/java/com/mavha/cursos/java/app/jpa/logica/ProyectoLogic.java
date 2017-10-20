/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.logica;

import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import com.mavha.cursos.java.app.jpa.modelo.Proyecto;
import com.mavha.cursos.java.app.jpa.modelo.Tarea;

/**
 *
 * @author mdominguez
 */
public interface ProyectoLogic {

    /**
     * Si se cumple la condicion 
     * @param nombre: el nombre del proyecto es obligatorio
     * @param presupuesto: el presupuesto del proyecto es obligatorio
     * @param idLider: el ID del lider del proyecto. 
     */
    public Proyecto crearProyecto(String nombre, Double presupuesto,Integer idLider);
    public Double presupuestoDisponible(Integer idProyecto);
    public Tarea asignarTarea(Integer idProyecto, Integer idResponsable,String descripcion,Integer duracionEstimada);
}
