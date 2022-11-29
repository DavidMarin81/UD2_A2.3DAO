/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.teis.ud2.services.empleado;

import es.teis.ud2.exceptions.InstanceNotFoundException;
import es.teis.ud2.model.Empleado;
import java.util.ArrayList;

/**
 *
 * @author David Marín
 */
public interface IEmpleadoService {
    
    public ArrayList<Empleado> findAll();
    public Empleado findById(int id) throws InstanceNotFoundException;
    
    public ArrayList<Integer> getEmpleadoNamesByEmpno(int numEmpleado);
    
}
