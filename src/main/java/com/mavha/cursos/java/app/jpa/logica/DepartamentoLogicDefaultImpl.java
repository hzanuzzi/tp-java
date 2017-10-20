/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavha.cursos.java.app.jpa.logica;

import com.mavha.cursos.java.app.jpa.dao.DepartamentoDao;
import com.mavha.cursos.java.app.jpa.dao.DepartamentoDaoJPA;
import com.mavha.cursos.java.app.jpa.modelo.Departamento;
import com.mavha.cursos.java.app.jpa.modelo.Empleado;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public class DepartamentoLogicDefaultImpl implements DepartamentoLogic{

    private DepartamentoDao deptoDao;
    
    public DepartamentoLogicDefaultImpl(){
        this.deptoDao = new DepartamentoDaoJPA();
    }
    
    @Override
    public Departamento crear(String nombre) {
        Departamento d = new Departamento();
        d.setNombre(nombre);
        return deptoDao.crear(d);
    }

    @Override
    public List<Empleado> empleados(Integer id) {
        return deptoDao.buscarPorId(id).getEmpleados();
    }

    
}
