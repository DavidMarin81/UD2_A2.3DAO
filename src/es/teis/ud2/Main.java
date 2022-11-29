/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2;

import es.teis.ud2.controller.DepartamentoController;
import es.teis.ud2.controller.EmpleadoController;
import es.teis.ud2.model.Departamento;
import es.teis.ud2.model.Empleado;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class Main {

    public static void main(String[] args) {

        mostrarDepartamentos();
        //probar a encontrar un departamento que no existe
        //     verDetalleDepartamento(666);
        getDepartmentNamesByLoc("DALLAS");
        
        //Mostrar los empleados
        System.out.println("\n=============================");
        mostrarEmpleados();
        //getEmpleadoByEmpno(7902);

    }
    
    private static void mostrarEmpleados() {
        EmpleadoController controlador = new EmpleadoController();
        ArrayList<Empleado> empleados = controlador.findAll();
        
        for (Empleado empleado : empleados) {
            System.out.println("Empleado: " + empleado);
        }
    }

    private static void mostrarDepartamentos() {
        DepartamentoController controlador = new DepartamentoController();
        ArrayList<Departamento> departamentos = controlador.findAll();

        for (Departamento departamento : departamentos) {
            System.out.println("Departamento: " + departamento);

        }

    }

    private static void verDetalleDepartamento(int id) {

        DepartamentoController controlador = new DepartamentoController();

        String mensaje = controlador.verDetalles(id);
        System.out.println(mensaje);

    }
    
    private static void getEmpleadoByEmpno(int id) {
        EmpleadoController controlador = new EmpleadoController();
        
        ArrayList<Integer> empleados = controlador.getEmpleadoByEmpno(id);
        for(int numero : empleados){
            System.out.println(numero);
        }
    }

    private static void getDepartmentNamesByLoc(String ubicacion) {

        DepartamentoController controlador = new DepartamentoController();

        ArrayList<String> nombres = controlador.getDepartamentNamesByLoc(ubicacion);
        for (String nombre : nombres) {
            System.out.println("Nombre dept: " + nombre);
        }

    }

}
