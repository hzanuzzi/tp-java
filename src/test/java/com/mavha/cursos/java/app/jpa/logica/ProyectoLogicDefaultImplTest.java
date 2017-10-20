/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.logica;

import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import com.mavha.cursos.java.app.jpa.modelo.Proyecto;
import com.mavha.cursos.java.app.jpa.modelo.Tarea;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author mdominguez
 */
public class ProyectoLogicDefaultImplTest {
    
    ProyectoLogic logica;
    EmpleadoLogic empleadoLogic;

    
    @Before
    public void setUp() {
        logica = new ProyectoLogicDefaultImpl();
        empleadoLogic = new EmpleadoLogicDefaultImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of crearProyecto method, of class ProyectoLogicDefaultImpl.
     */
    @Test
    public void testCrearProyecto() {
        Empleado lider =  empleadoLogic.crearEmpleado("Martin", 100.0, new Date());
        assertNotNull(lider);
        Proyecto proyecto = logica.crearProyecto("Proyecto1", 1000.0, lider.getId());
        assertNotNull(proyecto);
        Double disponible = logica.presupuestoDisponible(proyecto.getId());
        Double disponibleEsperado = 1000.0;
        assertEquals(disponibleEsperado,disponible );
    }
    
    @Test
    public void testAsignarTarea() {
        Empleado lider =  empleadoLogic.crearEmpleado("Proyecto N", 100.0, new Date());
        assertNotNull(lider);
        Empleado empleado1 =  empleadoLogic.crearEmpleado("Empleado 1", 80.0, new Date());
        assertNotNull(empleado1);
        Proyecto proyecto = logica.crearProyecto("ProyectoX", 1000.0, lider.getId());
        assertNotNull(proyecto);
        Tarea tareaAsignada = logica.asignarTarea(proyecto.getId(), empleado1.getId(), "UNA TAREA sencilla", 10);
        assertNotNull(tareaAsignada);
        Double disponible = logica.presupuestoDisponible(proyecto.getId());
        Double disponibleEsperado = 200.0;
        assertEquals(disponible,disponibleEsperado );        
    }    
    
}
