/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2;

import es.teis.ud2.controller.DepartamentoController;
import es.teis.ud2.model.Departamento;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class Main {

    public static void main(String[] args) {

        mostrarDepartamentos();
        verDetalleDepartamento(666);

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

}
