/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.teis.ud2.model.dao.empleado;

import es.teis.ud2.exceptions.InstanceNotFoundException;
import es.teis.ud2.model.Empleado;
import es.teis.ud2.model.dao.IGenericDao;
import java.util.ArrayList;

/**
 *
 * @author mfernandez
 */
public interface IEmpleadoDao extends IGenericDao<Empleado>{

    @Override
    public Empleado create(Empleado entity);

    @Override
    public Empleado read(int id) throws InstanceNotFoundException;

    @Override
    public boolean update(Empleado entity);

    @Override
    public boolean delete(int id);
    
    public ArrayList<Empleado> findAll();
    
    public ArrayList<Integer> getEmpleadoNamesByEmpno(int empno);
    
}
