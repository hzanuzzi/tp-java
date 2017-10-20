/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.logica;

import com.mavha.cursos.java.app.jpa.dao.DepartamentoDao;
import com.mavha.cursos.java.app.jpa.dao.DepartamentoDaoJPA;
import com.mavha.cursos.java.app.jpa.dao.EmpleadoDao;
import com.mavha.cursos.java.app.jpa.dao.EmpleadoDaoJPA;
import com.mavha.cursos.java.app.jpa.modelo.Departamento;
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import java.util.Date;
import java.util.function.BiPredicate;

/**
 *
 * @author mdominguez
 */
public class EmpleadoLogicDefaultImpl implements EmpleadoLogic{

    private EmpleadoDao empleadoDao;
    private DepartamentoDao departamentoDao;
    
    //TODO  2.1 defina un predicado donde un salario es valido si la media de la empresa es 0.0 o si el salario se encuentra entre el 0.75 y el 1.5 del salario medio
    private BiPredicate<Double,Double> salarioValidoEmpresa = (mediaEmpresa,salEmpleado)-> 
    mediaEmpresa == 0 || (salEmpleado >= mediaEmpresa * 0.75  && salEmpleado <= mediaEmpresa * 1.5) ;
    //TODO  2.2 defina un predicado donde un salario es valido si la media del departamento es 0.0 o si el salario se encuentra entre el 0.8 y el 1.2 del salario medio del departamento
    private BiPredicate<Double,Double> salarioValidoDepto = (mediaDepto,salEmpleado)-> 
    mediaDepto == 0 || (salEmpleado >= mediaDepto * 0.8 && salEmpleado <= mediaDepto * 1.2);

    
    public EmpleadoLogicDefaultImpl(){
        this.empleadoDao = new EmpleadoDaoJPA();
        this.departamentoDao = new DepartamentoDaoJPA();
    }

    /**
     * El salario por hora de un empleado no puede ser mayor que el 50% del salario promedio 
     * del resto de los empleados ni menor que el 25%
     * @param nombre EMPLEADO
     * @param salarioHora del empleado
     * @param fecAlta fecha de alta de la relacion laboral
     * @return 
     */
    @Override
    public Empleado crearEmpleado(String nombre, Double salarioHora, Date fecAlta) {
        Empleado emp = null;
        Double salarioHoraMedioEmpresa = empleadoDao.salarioPromedioTodos();       
    
        if(salarioValidoEmpresa.test(salarioHoraMedioEmpresa,salarioHora)){
            emp = new Empleado();
            emp.setNombre(nombre);
            emp.setFechaContratacion(fecAlta);
            emp.setSalarioHora(salarioHora);
            emp = empleadoDao.guardar(emp);
        }
        return emp;
    }

    @Override
    public void asignarDepartamento(Integer idEmpleado, Integer idDepto) {
        Empleado emp = empleadoDao.buscarPorId(idEmpleado);
        Departamento depto = departamentoDao.buscarPorId(idDepto);
        //TODO 2.3 implementar una expresión de stream para calcular el salario promedio
        Double salarioMedioDepto = depto.getEmpleados().stream().mapToDouble((empleado) -> empleado.getSalarioHora()).average().orElse(0.0);
        if(salarioValidoDepto.test(salarioMedioDepto,emp.getSalarioHora())){
            emp.setTrabajaEn(depto);
            empleadoDao.actualizar(emp);
        }        
    }
    

    
}
