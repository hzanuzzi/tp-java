/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.logica;

import com.mavha.cursos.java.app.jpa.modelo.Departamento;
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mdominguez
 */
public class EmpleadoLogicDefaultImplTest {
    
    private EmpleadoLogic empleadoLogic;
    private DepartamentoLogic deptoLogic;
    
    public EmpleadoLogicDefaultImplTest() {
    }
    
    @Before
    public void setUp() {
        empleadoLogic = new EmpleadoLogicDefaultImpl();
        deptoLogic = new DepartamentoLogicDefaultImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of crearEmpleado method, of class EmpleadoLogicDefaultImpl.
     */
    @Test
    public void testCrearEmpleado() {
        // esta clase permite generar fechas de tipo Date desde un string con el metodo parse
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Empleado emp1 = empleadoLogic.crearEmpleado("Martin", 100.0,sdf.parse("01/01/2011") );
            assertNotNull(emp1);
            Empleado emp2 = empleadoLogic.crearEmpleado("Salario Muy alto", 155.0,sdf.parse("01/01/2012") );
            assertNull(emp2);
            Empleado emp3 = empleadoLogic.crearEmpleado("Salario Muy Bajo", 70.0,sdf.parse("01/01/2012") );
            assertNull(emp3);
            Empleado emp4 = empleadoLogic.crearEmpleado("Salario Alto OK", 120.0,sdf.parse("01/01/2012") );
            assertNotNull(emp4);
            assertNotNull(emp4.getId());
            Empleado emp5 = empleadoLogic.crearEmpleado("Salario Bajo OK", 89.0,sdf.parse("01/01/2012") );
            assertNotNull(emp5);
            assertNotNull(emp5.getId());
        } catch (ParseException ex) {
            Logger.getLogger(EmpleadoLogicDefaultImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of asignarDepartamento method, of class EmpleadoLogicDefaultImpl.
     */
    @Test
    public void testAsignarDepartamento() {
        // esta clase permite generar fechas de tipo Date desde un string con el metodo parse
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Departamento d1 = deptoLogic.crear("Departamento 1");
            assertNotNull(d1);
            Empleado emp1 = empleadoLogic.crearEmpleado("EMP 1", 100.0,sdf.parse("01/01/2011") );
            assertNotNull(emp1);
            Empleado emp2 = empleadoLogic.crearEmpleado("EMP 2", 110.0,sdf.parse("01/01/2011") );
            assertNotNull(emp2);
            Empleado emp3 = empleadoLogic.crearEmpleado("EMP 2", 90.0,sdf.parse("01/01/2011") );
            assertNotNull(emp3);
            Empleado emp4 = empleadoLogic.crearEmpleado("EMP 4", 79.0,sdf.parse("01/01/2011") );
            assertNotNull(emp4);
            Empleado emp5 = empleadoLogic.crearEmpleado("EMP 4", 121.0,sdf.parse("01/01/2011") );
            assertNotNull(emp5);
            
            empleadoLogic.asignarDepartamento(emp1.getId(), d1.getId());            
            List<Empleado> empleadosAsignados = deptoLogic.empleados(d1.getId());
            assertTrue(empleadosAsignados.contains(emp1));
            
            empleadoLogic.asignarDepartamento(emp2.getId(), d1.getId());
            empleadosAsignados = deptoLogic.empleados(d1.getId());
            assertTrue(empleadosAsignados.contains(emp2));

            empleadoLogic.asignarDepartamento(emp3.getId(), d1.getId());
            empleadosAsignados = deptoLogic.empleados(d1.getId());
            assertTrue(empleadosAsignados.contains(emp3));

            empleadoLogic.asignarDepartamento(emp4.getId(), d1.getId());
            empleadosAsignados = deptoLogic.empleados(d1.getId());
            assertFalse(empleadosAsignados.contains(emp4));

            empleadoLogic.asignarDepartamento(emp5.getId(), d1.getId());
            empleadosAsignados = deptoLogic.empleados(d1.getId());
            assertFalse(empleadosAsignados.contains(emp5));

        } catch (ParseException ex) {
            Logger.getLogger(EmpleadoLogicDefaultImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
