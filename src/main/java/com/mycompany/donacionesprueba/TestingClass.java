/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba;

import com.mycompany.donacionesprueba.clases.Administrador;
import com.mycompany.donacionesprueba.clases.Contenido;
import com.mycompany.donacionesprueba.clases.CreadorContenido;
import com.mycompany.donacionesprueba.clases.Usuario;
import com.mycompany.donacionesprueba.dao.WSManager;
import java.util.List;

/**
 *
 * @author carlo
 */
public class TestingClass {

    public static void main(String[] args) {

//        String correo = "admin@gmail.com";
//        String pass = "admin";
//
//        Usuario usuario = WSManager.verificarUsuario(correo, pass);
//
//        // Si el usuario es diferente de null verificamos si es admin, creador o usuario
//        if (usuario != null) {
//            if (usuario instanceof Administrador) {
//                System.out.println("Es un administrador");
//            } else if (usuario instanceof CreadorContenido) {
//                System.out.println("Es un creador de contenido");
//            } else {
//                System.out.println("Es un usuario");
//            }
//        } else {
//            System.out.println("Usuario no encontrado");
//        }
//
//        // Prueba creadores
//
//        List<CreadorContenido> creadores = WSManager.obtenerCreadoresContenido();
//        creadores.forEach(creador -> {
//            System.out.println(creador.toString());
//        });
//
//        List<Administrador> admins = WSManager.obtenerAdmins();
//        admins.forEach(admin -> {
//            System.out.println(admin.toString());
//        });
//
//        // Obetenemos un creador por su id
//        System.out.println("Creador por id:");
//        CreadorContenido creador = WSManager.obtenerCreadorContenido("31be130f-b019-11ef-a2bf-04d4c447d499");
//        System.out.println(creador.toString());
//
//        // Obtenemos los contenidos
//
//        System.out.println("Contenidos:");
//        List<Contenido> contenidos = WSManager.obtenerContenidos();
//        contenidos.forEach(contenido -> {
//            System.out.println(contenido.toString());
//        });
        // Crear un usuario
//        Usuario user = new Usuario("maria", "maria@gmail.com", "maria");
//
////        String response = WSManager.guardarUsuario(user);
////        System.out.println("Creando usuario: ");
////        System.out.println(response != null ? response : "error, no se creo");
//
//        CreadorContenido creadorCreate = new CreadorContenido("maria", "maria@gmail.com", "maria");
//        
//        String creatorResponse = WSManager.guardarCreadorContenido(creadorCreate);
//        System.out.println("Creando creador: ");
//        System.out.println(creatorResponse != null ? creatorResponse : "error, no se creo");
//        
//        // Creamos el contenido
//        Contenido content =  new Contenido("Me fui a mexico", "Ando de paseo por mexico", creatorResponse, "/image/imagge/test");
//        String contentResponse = WSManager.guardarContenido(content);
//        System.out.println(contentResponse != null ? contentResponse : "error, no se creo");
//        Contenido contentUpdated = new Contenido(
//                "4a01b6f0-b12b-11ef-9ce4-04d4c447d499",
//                "Me fui a australia con unos compas", 
//                "Ando de paseo por mexico", 
//                "49fcf6cf-b12b-11ef-9ce4-04d4c447d499", 
//                0,
//                "/image/imagge/test"
//        );
        String contentResponse = WSManager.eliminarContenido("4a01b6f0-b12b-11ef-9ce4-04d4c447d499");
        System.out.println("Contenido eliminado:");
        System.out.println(contentResponse != null ? contentResponse : "error, no se elimino");

    }
}
