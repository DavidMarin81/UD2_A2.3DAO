/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2.model.dao.empleado;

import es.teis.ud2.data.DBCPDataSourceFactory;
import es.teis.ud2.exceptions.InstanceNotFoundException;
import es.teis.ud2.model.Departamento;
import es.teis.ud2.model.Empleado;
import es.teis.ud2.model.dao.AbstractGenericDao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author mfernandez
 */
public class EmpleadoSQLServerDao extends AbstractGenericDao<Empleado> implements IEmpleadoDao {

    private DataSource dataSource;

    public EmpleadoSQLServerDao() {
        this.dataSource = DBCPDataSourceFactory.getDataSource();
    }

    @Override
    public Empleado create(Empleado empleado) {
        try (
                 Connection conexion = this.dataSource.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                "INSERT INTO [dbo].[EMP]\n"
                + "           ([ENAME]\n"
                + "           ,[JOB]\n"
                + "           ,[MGR]\n"
                + "           ,[HIREDATE]\n"
                + "           ,[SAL]\n"
                + "           ,[COMM]\n"
                + "           ,[DEPTNO])\n"
                + "     VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
        );) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getPuesto());
            pstmt.setInt(3, empleado.getJefe().getEmpleadoId());
            pstmt.setDate(4, Date.valueOf(empleado.getFechaContratacion()));
            pstmt.setBigDecimal(5, empleado.getSalario());
            pstmt.setBigDecimal(6, empleado.getComision());
            pstmt.setInt(7, empleado.getDepartamento().getDeptno());

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            int result = pstmt.executeUpdate();

            ResultSet clavesResultado = pstmt.getGeneratedKeys();

            if (clavesResultado.next()) {
                int empleadoId = clavesResultado.getInt(1);
                empleado.setEmpleadoId(empleadoId);
            } else {
                empleado = null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
            empleado = null;
        }
        return empleado;
    }

    @Override
    public Empleado read(int id) throws InstanceNotFoundException {
        //int empno;
        String ename;
        String job;
        Empleado mgr;
        LocalDate hiredate;
        BigDecimal sal;
        BigDecimal comm;
        Departamento deptno;

        int contador = 0;

        Empleado empleado = null;

        try (
                 Connection conexion = this.dataSource.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                "SELECT EMPNO FROM EMP WHERE EMPNO = ?", Statement.RETURN_GENERATED_KEYS
        );) {

            pstmt.setInt(1, id);

            ResultSet clavesResultado = pstmt.executeQuery();

            if (clavesResultado.next()) {
                ename = clavesResultado.getString(++contador);
                job = clavesResultado.getString(++contador);
                //Convertir a Empleado
                //mgr = clavesResultado.getInt(++contador);
                mgr = null;
                ++contador;
                //Convertir a LocalDate
                hiredate = clavesResultado.getDate(++contador).toLocalDate();
                sal = clavesResultado.getBigDecimal(++contador);
                comm = clavesResultado.getBigDecimal(++contador);
                //Convertir a Departamento
                //deptno = clavesResultado.getInt(++contador);
                deptno = null;

                empleado = new Empleado(ename, job, mgr, hiredate, sal, comm, deptno);

            } else {
                empleado = null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
            empleado = null;
        }

        return empleado;
    }

    @Override
    public boolean update(Empleado empleado) {
        boolean actualizado = false;
        try (
                 Connection conexion = this.dataSource.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                "UPDATE EMP "
                + " SET ENAME=?,  JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=? WHERE EMPNO = ?")) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getPuesto());
            //Setear con objeto empleado
            pstmt.setObject(3, empleado.getEmpleadoId());
            //Setear con LocalDate
            pstmt.setDate(4, Date.valueOf(empleado.getFechaContratacion()));
            pstmt.setBigDecimal(5, empleado.getSalario());
            pstmt.setBigDecimal(6, empleado.getComision());
            //Setear con Departamento
            pstmt.setObject(7, empleado.getDepartamento());

            int result = pstmt.executeUpdate();
            actualizado = (result == 1);

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return actualizado;
    }

    @Override
    public boolean delete(int id) {
        int result = 0;
        try (
                 Connection conexion = this.dataSource.getConnection();  PreparedStatement pstmt = conexion.prepareStatement("DELETE FROM EMP WHERE EMPNO=?");) {

            pstmt.setInt(1, id);

            result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return (result == 1);
    }

    @Override
    public ArrayList<Empleado> findAll() {
        int empno;
        String ename;
        String job;
        Empleado mgr;
        LocalDate hiredate;
        BigDecimal sal;
        BigDecimal comm;
        Departamento deptno;

        int contador = 1;

        Empleado empleado = null;
        ArrayList<Empleado> empleados = new ArrayList<>();

        try (
                 Connection conexion = this.dataSource.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(""
                + "SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO "
                + "FROM EMP ORDER BY ENAME");) {
            
            ResultSet result = pstmt.executeQuery();
            
            while (result.next()) {
                contador = 0;

                empno = result.getInt(++contador);
                ename = result.getString(++contador);
                job = result.getString(++contador);
                //Getter con Empleado
                mgr = null;
                ++contador;
                //Getter con LocalDate
                hiredate = result.getDate(++contador).toLocalDate();
                sal = result.getBigDecimal(++contador);
                comm = result.getBigDecimal(++contador);
                //Getter con Departamento
                //depnto = result.getDepartamento(++contador);
                deptno = null;
                
                empleado = new Empleado(ename, job, mgr, hiredate, sal, comm, deptno);
                empleado.setEmpleadoId(empno);
                empleados.add(empleado);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return empleados;
    }

    @Override
    public ArrayList<Integer> getEmpleadoNamesByEmpno(int numEmpleado) {
        int numeroEmpleado;
        ArrayList<Integer> numerosEmpleados = new ArrayList<>();

        try (
                 Connection conexion = this.dataSource.getConnection();  PreparedStatement pstmt = conexion.prepareStatement("SELECT ENAME "
                + "FROM EMP WHERE EMPNO LIKE ? ORDER BY ENAME");) {

            pstmt.setInt(1, numEmpleado);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {

                numeroEmpleado = result.getInt(1);
                numerosEmpleados.add(numEmpleado);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }

        return numerosEmpleados;
    }

}
