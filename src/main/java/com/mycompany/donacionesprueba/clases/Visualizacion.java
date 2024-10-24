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
    
    private String idUsuario;
    private String idContenido;
    
    

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

    @Override
    public String toString() {
        return "Visualizacion{" + "idUsuario=" + idUsuario + ", idContenido=" + idContenido + '}';
    }
    
}
