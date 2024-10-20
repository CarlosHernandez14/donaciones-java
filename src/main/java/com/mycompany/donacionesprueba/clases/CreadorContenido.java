/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba.clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author carlo
 */
public class CreadorContenido extends Usuario implements Serializable {
    private ArrayList<Contenido> contenidos;
    private boolean cuentaBloqueada;
    private ArrayList<Usuario> suscriptores;

    public CreadorContenido(String idUsuario, String nombre, String email, String contrasena) {
        super(idUsuario, nombre, email, contrasena);
        this.contenidos = new ArrayList<>();
        this.suscriptores = new ArrayList<>();
        this.cuentaBloqueada = false;
    }

    public void crearContenido(String titulo, String descripcion, String imagePath) {
        if (cuentaBloqueada) {
            System.out.println("Cuenta bloqueada. No puedes crear contenido.");
            return;
        }
        Contenido nuevoContenido = new Contenido(titulo, descripcion, this, imagePath);
        contenidos.add(nuevoContenido);

        // Actualizamos la base de datos
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

    public ArrayList<Usuario> getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(ArrayList<Usuario> suscriptores) {
        this.suscriptores = suscriptores;
    }

    @Override
    public String toString() {
        return "CreadorContenido{" + "contenidos=" + contenidos + ", cuentaBloqueada=" + cuentaBloqueada
                + ", suscriptores=" + suscriptores + '}';
    }

}