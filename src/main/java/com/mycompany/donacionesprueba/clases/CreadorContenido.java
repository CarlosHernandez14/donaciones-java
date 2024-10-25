/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba.clases;

import com.mycompany.donacionesprueba.dao.Dao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class CreadorContenido extends Usuario implements Serializable {

    private ArrayList<Contenido> contenidos;
    private boolean cuentaBloqueada;
    private ArrayList<String> suscriptores; // Guarda el id de los usuarios suscritos

    public CreadorContenido(String idUsuario, String nombre, String email, String contrasena) {
        super(idUsuario, nombre, email, contrasena);
        this.contenidos = new ArrayList<>();
        this.suscriptores = new ArrayList<>();
        this.cuentaBloqueada = false;
    }

    public void crearContenido(String titulo, String descripcion, String imagePath) throws Exception {
        try {

            if (cuentaBloqueada) {
                System.out.println("Cuenta bloqueada. No puedes crear contenido.");
                return;
            }
            Contenido nuevoContenido = new Contenido(titulo, descripcion, this.getId(), imagePath);
            contenidos.add(nuevoContenido);

            // Actualizamos la base de datos
            Dao.guardarContenido(nuevoContenido);

            // Actualizamos los datos de CreadorContenido en la DB
            Dao.actualizarCreadorContenido(this);

        } catch (Exception e) {
            // Propagamos la excepción
            throw e;
        }

    }

    // Metodo para agregar suscriptores
    public void agregarSuscriptor(String idUsuario) throws IOException {

        // Agregar el id del usuario a la lista de suscriptores
        this.suscriptores.add(idUsuario);

        // Actualizamos los datos de CreadorContenido en la DB
        Dao.actualizarCreadorContenido(this);
    }

    // Metodo para eliminar suscriptores
    public void eliminarSuscriptor(String idUsuario) throws IOException {

        // Eliminar el id del usuario de la lista de suscriptores
        this.suscriptores.remove(idUsuario);

        // Actualizamos los datos de CreadorContenido en la DB
        Dao.actualizarCreadorContenido(this);
    }

    public void verificarActividad() {
        // Verificar si ha creado al menos 3 contenidos por semana
        if (contenidos.size() < 3) {
            cuentaBloqueada = true;
        }
    }

    public boolean esPartner() {
        // Lógica para verificar si es un "partner"
        return (this.getSuscriptores().size() >= 1000 && calcularPromedioLikes() >= 500);
    }

    // Método para calcular el promedio de likes de los contenidos
    public double calcularPromedioLikes() {
        double totalLikes = 0;
        for (Contenido contenido : contenidos) {
            totalLikes += contenido.getLikes().size();
        }
        return totalLikes / contenidos.size();
    }

    // Getters y setters
    public ArrayList<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(ArrayList<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public boolean isCuentaBloqueada() {
        return cuentaBloqueada;
    }

    public void setCuentaBloqueada(boolean cuentaBloqueada) {
        this.cuentaBloqueada = cuentaBloqueada;
    }

    public ArrayList<String> getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(ArrayList<String> suscriptores) {
        this.suscriptores = suscriptores;
    }

    @Override
    public String toString() {
        return super.toString() + "CreadorContenido{" + "contenidos=" + contenidos + ", cuentaBloqueada="
                + cuentaBloqueada
                + ", suscriptores=" + suscriptores + '}';
    }

}
