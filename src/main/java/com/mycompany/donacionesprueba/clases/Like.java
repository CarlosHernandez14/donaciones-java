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
public class Like implements Serializable{
    private String idUsuario;
    private String idContenido;

    public Like() {
    }

    public Like(String idUsuario, String idContenido) {
        this.idUsuario = idUsuario;
        this.idContenido = idContenido;
    }
    
    // To string
    @Override
    public String toString() {
        return "Like{" + "idUsuario=" + idUsuario + ", idContenido=" + idContenido + '}';
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
    
}
