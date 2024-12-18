/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba.clases;

/**
 *
 * @author carlo
 */
public class Visualizacion {
    
    private String idVisualizacion;
    private String idUsuario;
    private String idContenido;
    
    // CONSTRUCTOR LECTURA
    public Visualizacion(String idVisualizacion, String idUsuario, String idContenido) {
        this.idVisualizacion = idVisualizacion;
        this.idUsuario = idUsuario;
        this.idContenido = idContenido;
    }
    
    // Constructor escritura
    public Visualizacion(String idUsuario, String idContenido) {
        this.idUsuario = idUsuario;
        this.idContenido = idContenido;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(String idContenido) {
        this.idContenido = idContenido;
    }
    
    public String getIdVisualizacion() {
        return idVisualizacion;
    }

    public void setIdVisualizacion(String idVisualizacion) {
        this.idVisualizacion = idVisualizacion;
    }

    @Override
    public String toString() {
        return "Visualizacion{" + "idUsuario=" + idUsuario + ", idContenido=" + idContenido + '}';
    }
    
}
