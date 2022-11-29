package es.teis.ud2.services.empleado;

import es.teis.ud2.exceptions.InstanceNotFoundException;
import es.teis.ud2.model.Empleado;
import es.teis.ud2.model.dao.empleado.IEmpleadoDao;
import java.util.ArrayList;

/**
 *
 * @author David Marín
 */
public class EmpleadoService implements IEmpleadoService{
    
    private IEmpleadoDao empleadoDao;
    
    public EmpleadoService(IEmpleadoDao empleadoDao){
        this.empleadoDao = empleadoDao;
    }
    
    @Override
    public ArrayList<Empleado> findAll() {
        return empleadoDao.findAll();
    }

    @Override
    public Empleado findById(int id) throws InstanceNotFoundException {
        return this.empleadoDao.read(id);
    }

    @Override
    public ArrayList<Integer> getEmpleadoNamesByEmpno(int numEmpleado) {
        return this.empleadoDao.getEmpleadoNamesByEmpno(numEmpleado);
    }
    
}
