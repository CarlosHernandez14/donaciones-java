/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba.clases;

import java.io.Serializable;

/**
 *
 * @author carlo
 */
public class Administrador extends Usuario implements Serializable {
    public Administrador(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena);
    }

    public void eliminarContenido(Contenido contenido) {
        // Lógica para eliminar contenido
    }

    public void asignarPartner(CreadorContenido creador) {
        if (creador.esPartner()) {
            // Lógica para asignar "partner"
        }
    }

    public void descargarReportePDF() {
        // Lógica para descargar un PDF con los datos de los "partners"
    }

    public void descargarReporteExcel() {
        // Lógica para descargar un Excel con los usuarios que reciben más donaciones
    }
}