/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba;

import com.mycompany.donacionesprueba.clases.Administrador;
import com.mycompany.donacionesprueba.clases.Comentario;
import com.mycompany.donacionesprueba.clases.Contenido;
import com.mycompany.donacionesprueba.clases.CreadorContenido;
import com.mycompany.donacionesprueba.clases.Like;
import com.mycompany.donacionesprueba.clases.Usuario;
import com.mycompany.donacionesprueba.clases.Visualizacion;
import com.mycompany.donacionesprueba.dao.WSManager;
import java.util.List;

/**
 *
 * @author carlo
 */
public class TestingClass {

    public static void main(String[] args) {
        String correo = "admin@gmail.com";
        String pass = "admin";

        Usuario usuario = WSManager.verificarUsuario(correo, pass);
        // Si el usuario es diferente de null verificamos si es admin, creador o
        if (usuario != null) {
            if (usuario instanceof Administrador) {
                System.out.println("Es un administrador");
            } else if (usuario instanceof CreadorContenido) {
                System.out.println("Es un creador de contenido");
            } else {
                System.out.println("Es un usuario");
            }
        } else {
            System.out.println("Usuario no encontrado");
        }
        

    }
}
