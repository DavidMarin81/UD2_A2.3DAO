/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2.controller;

import es.teis.ud2.exceptions.InstanceNotFoundException;
import es.teis.ud2.model.Empleado;
import es.teis.ud2.model.dao.empleado.EmpleadoSQLServerDao;
import es.teis.ud2.model.dao.empleado.IEmpleadoDao;
import es.teis.ud2.services.empleado.EmpleadoService;
import es.teis.ud2.services.empleado.IEmpleadoService;
import java.util.ArrayList;

/**
 *
 * @author David Marín
 */
public class EmpleadoController {
    
    private IEmpleadoDao empleadoDao;
    private IEmpleadoService empleadoService;

    public EmpleadoController() {
        this.empleadoDao = new EmpleadoSQLServerDao();
        this.empleadoService = new EmpleadoService(empleadoDao);
    }
    
    public ArrayList<Empleado> findAll() {
        return this.empleadoService.findAll();
    }
    
    public String verDetalles(int id) {
        Empleado empleado = null;
        String resultado;
        
        try {
            empleado = empleadoService.findById(id);

            resultado = "Empleado: " + empleado;

        } catch (InstanceNotFoundException ex) {
            //Para el sistema de gestión de errores
            System.err.println("Ha ocurrido una exception: " + ex.getMessage());
            //Para informar al usuario
            resultado = "No se ha encontrado el departamento";
        }
        return resultado;
    }
    
    public ArrayList<Integer> getEmpleadoByEmpno(int id) {
        return this.empleadoService.getEmpleadoNamesByEmpno(id);
    }
    
}
