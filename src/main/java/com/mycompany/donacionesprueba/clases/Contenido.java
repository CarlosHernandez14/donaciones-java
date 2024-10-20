/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contenido implements Serializable{
    private String titulo;
    private String descripcion;
    private CreadorContenido creador;
    private int visualizaciones;
    private ArrayList<Like> likes;
    private List<String> comentarios;
    private double donaciones;
    private String imagePath;

    public Contenido(String titulo, String descripcion, CreadorContenido creador, String imagePath) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.creador = creador;
        this.visualizaciones = 0;
        this.likes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.donaciones = 0.0;
        this.imagePath = imagePath;
    }

    public void agregarComentario(String comentario, Usuario usuario) {
        comentarios.add(usuario.getNombre() + ": " + comentario);
    }

    public void agregarLike(int idUsuario, int idContenido) {
        Like like = new Like(idUsuario, idContenido);
        likes.add(like);
        
        // Agregamos el like a la DB
        
    }

    public void recibirDonacion(Usuario usuario, double cantidad) {
        donaciones += cantidad;
    }

    // Getters y setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CreadorContenido getCreador() {
        return creador;
    }

    public void setCreador(CreadorContenido creador) {
        this.creador = creador;
    }

    public int getVisualizaciones() {
        return visualizaciones;
    }

    public void setVisualizaciones(int visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public double getDonaciones() {
        return donaciones;
    }

    public void setDonaciones(double donaciones) {
        this.donaciones = donaciones;
    }
    
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    

    @Override
    public String toString() {
        return "Contenido{" + "titulo=" + titulo + ", descripcion=" + descripcion + ", creador=" + creador + ", visualizaciones=" + visualizaciones + ", likes=" + likes + ", comentarios=" + comentarios + ", donaciones=" + donaciones + '}';
    }
    
    
}
