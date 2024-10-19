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
    private int idUsuario;
    private int idContenido;

    public Like() {
    }

    public Like(int idUsuario, int idContenido) {
        this.idUsuario = idUsuario;
        this.idContenido = idContenido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(int idContenido) {
        this.idContenido = idContenido;
    }
    // To string
    @Override
    public String toString() {
        return "Like{" + "idUsuario=" + idUsuario + ", idContenido=" + idContenido + '}';
    }
    
}
