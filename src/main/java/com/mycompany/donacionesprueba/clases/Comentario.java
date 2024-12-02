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
public class Comentario implements Serializable{
    
    private String id;
    private String idUsuario; 
    private String idContenido;
    private String comentario;

    public Comentario() {
    }

    // CONSTRUCTOR DE LECTURA
    public Comentario(String id, String idUsuario, String idContenido, String comentario) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idContenido = idContenido;
        this.comentario = comentario;
    }

    // CONSTRUCTOR DE CREACION
    public Comentario(String idUsuario, String idContenido, String comentario) {
        this.idUsuario = idUsuario;
        this.idContenido = idContenido;
        this.comentario = comentario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Comentario{" + "id=" + id + ", idUsuario=" + idUsuario + ", idContenido=" + idContenido + '}';
    }

    
}
