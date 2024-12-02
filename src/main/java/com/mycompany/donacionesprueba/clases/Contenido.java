/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba.clases;

import com.mycompany.donacionesprueba.dao.Dao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contenido implements Serializable {
    private String id;
    private String titulo;
    private String descripcion;
    private String idCreador;
    private ArrayList<Visualizacion> visualizaciones;
    private ArrayList<Like> likes;
    private List<Comentario> comentarios;
    private double donaciones;
    private String imagePath;

    // CONSTRUCTOR PARA CREACION
    public Contenido(String titulo, String descripcion, String idCreador, String imagePath) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idCreador = idCreador;
        this.visualizaciones = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.donaciones = 0.0;
        this.imagePath = imagePath;
        this.id = generarId();
    }

    // Constructor para cargar contenido desde la DB
    public Contenido(String id, String titulo, String descripcion, String idCreador,
            ArrayList<Visualizacion> visualizaciones,
            ArrayList<Like> likes, List<Comentario> comentarios, double donaciones, String imagePath) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.idCreador = idCreador;
        this.visualizaciones = visualizaciones;
        this.likes = likes;
        this.comentarios = comentarios;
        this.donaciones = donaciones;
        this.imagePath = imagePath;
    }

    public void agregarComentario(Comentario comentario, Usuario usuario) {
        comentarios.add(comentario);
    }

    public void agregarLike(String idUsuario, String idContenido) throws IOException {
        Like like = new Like(idUsuario, idContenido);
        likes.add(like);

        // Actualizamos el contenido con los nuevos likes en la db

        Dao.actualizarContenido(this);
    }

    // Metdodo para eliminar un like
    public void eliminarLike(String idUsuario, String idContenido) throws IOException {
        for (Like like : likes) {
            if (like.getIdUsuario().equals(idUsuario) && like.getIdContenido().equals(idContenido)) {
                likes.remove(like);
                break;
            }
        }

        // Actualizamos el contenido con los likes eliminados en la db
        Dao.actualizarContenido(this);
    }

    // Metodo para agrear una visualizacion
    public void agregarVisualizacion(String idUsuario) throws IOException {
        Visualizacion visualizacion = new Visualizacion(idUsuario, this.id);
        visualizaciones.add(visualizacion);

        // Actualizamos el contenido con las nuevas visualizaciones en la db
        Dao.actualizarContenido(this);
    }

    public void recibirDonacion(double cantidad) throws IOException {
        donaciones += cantidad;

        // Actualizamos el contenido con la nueva cantidad de donaciones en la db
        Dao.actualizarContenido(this);
    }

    // Metodo para generar un id unico para el contenido que incluya letras y
    // numeros
    public static String generarId() {
        String id = "";
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        for (int i = 0; i < 5; i++) {
            id += letras.charAt((int) (Math.random() * letras.length()));
            id += numeros.charAt((int) (Math.random() * numeros.length()));
        }
        return id;
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getIdCreador() {
        return this.idCreador;
    }

    public void setIdCreador(String idCreador) {
        this.idCreador = idCreador;
    }

    public ArrayList<Visualizacion> getVisualizaciones() {
        return visualizaciones;
    }

    public void setVisualizaciones(ArrayList<Visualizacion> visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
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
        return "Contenido{" + "titulo=" + titulo + ", descripcion=" + descripcion + ", creador=" + idCreador
                + ", visualizaciones=" + visualizaciones + ", likes=" + likes + ", comentarios=" + comentarios
                + ", donaciones=" + donaciones + '}';
    }

}
