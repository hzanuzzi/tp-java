/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.logica;

import com.mavha.cursos.java.app.jpa.dao.EmpleadoDao;
import com.mavha.cursos.java.app.jpa.dao.EmpleadoDaoJPA;
import com.mavha.cursos.java.app.jpa.dao.ProyectoDao;
import com.mavha.cursos.java.app.jpa.dao.ProyectoDaoJPA;
import com.mavha.cursos.java.app.jpa.dao.TareaDao;
import com.mavha.cursos.java.app.jpa.dao.TareaDaoJPA;
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import com.mavha.cursos.java.app.jpa.modelo.Proyecto;
import com.mavha.cursos.java.app.jpa.modelo.Tarea;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 *
 * @author mdominguez
 */
public class ProyectoLogicDefaultImpl implements ProyectoLogic{

    private ProyectoDao proyectoDao;
    private EmpleadoDao empleadoDao;
    private TareaDao tareaDao;
    
    public ProyectoLogicDefaultImpl(){
        proyectoDao = new ProyectoDaoJPA();
        empleadoDao = new EmpleadoDaoJPA();
        tareaDao = new TareaDaoJPA();
    }
    
    @Override
    public Proyecto crearProyecto(String nombre, Double presupuesto, Integer idLider) {
        Empleado e = empleadoDao.buscarPorId(idLider);        
        Proyecto p = null;
        if(nombre!=null && nombre.length()>1 && nombre.length()<20 && presupuesto>0.0 && presupuesto <100000.0){
            p=new Proyecto();
            p.setNombre(nombre);
            p.setPresupuesto(presupuesto);
            p.setLider(e);
            p = proyectoDao.crear(p);
        }
        return p;        
    }
            
    @Override
    public Double presupuestoDisponible(Integer idProyecto) {
        // TODO 3.5 buscar el proyecto de la capa DAO
        Proyecto p = proyectoDao.buscarPorId(idProyecto);
        // TODO 3.5 con una expresion lambda, procesar todas las tareas de un proyecto p 
        //          usando mapToDouble calcular cuanto cuesta cada tarea y usar el operador sum() para sumarizar
        
        
        Double gastado = p.getTarea().stream().mapToDouble((tarea)-> tarea.getDuracionEstimada() * tarea.getResponsable().getSalarioHora() ).sum();
        // TODO 3.5 el presupuesto disponible es la diferencia entre el presupuesto y lo gastado
        return p.getPresupuesto()-gastado;
    }
    
    @Override
    public Tarea asignarTarea(Integer idProyecto, Integer idResponsable,String descripcion,Integer duracionEstimada) {
        Tarea t =null;
        // TODO 3.6 si el presupuesto disponible menos el costo de la tarea al responsable propuesto es mayor que cero
        // entonces se puede asignar la tarea al proyecto.
        // Buscar una instancia de empleado por ID
        Empleado responsable = empleadoDao.buscarPorId(idResponsable);

        // Buscar una instancia de Proyecto por ID
        Proyecto proyecto = proyectoDao.buscarPorId(idProyecto); 
        
        Double valor = presupuestoDisponible(idProyecto) - responsable.getSalarioHora() * duracionEstimada.doubleValue();
        
        // Invocar a TareaDAO para que realice la creación de la tarea        
        if (valor > 0)
        {
            // Crear una tarea
            t = new Tarea();
            // Setear todos los atributos 
            t.setDescripcion(descripcion);
            t.setDuracionEstimada(duracionEstimada);
            t.setProyecto(proyecto);
            t.setResponsable(responsable);
            
            tareaDao.crear(t);
        }
        
        return t;
    } 
}