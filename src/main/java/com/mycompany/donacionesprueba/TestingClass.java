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
        // String correo = "admin@gmail.com";
        // String pass = "admin";
        //
        // Usuario usuario = WSManager.verificarUsuario(correo, pass);
        //
        // // Si el usuario es diferente de null verificamos si es admin, creador o
        // usuario
        // if (usuario != null) {
        // if (usuario instanceof Administrador) {
        // System.out.println("Es un administrador");
        // } else if (usuario instanceof CreadorContenido) {
        // System.out.println("Es un creador de contenido");
        // } else {
        // System.out.println("Es un usuario");
        // }
        // } else {
        // System.out.println("Usuario no encontrado");
        // }
        //
        // // Prueba creadores
        //
        // List<CreadorContenido> creadores = WSManager.obtenerCreadoresContenido();
        // creadores.forEach(creador -> {
        // System.out.println(creador.toString());
        // });
        //
        // List<Administrador> admins = WSManager.obtenerAdmins();
        // admins.forEach(admin -> {
        // System.out.println(admin.toString());
        // });
        //
        // // Obetenemos un creador por su id
        // System.out.println("Creador por id:");
        // CreadorContenido creador =
        // WSManager.obtenerCreadorContenido("31be130f-b019-11ef-a2bf-04d4c447d499");
        // System.out.println(creador.toString());
        //
        // // Obtenemos los contenidos
        //
        // System.out.println("Contenidos:");
        // List<Contenido> contenidos = WSManager.obtenerContenidos();
        // contenidos.forEach(contenido -> {
        // System.out.println(contenido.toString());
        // });
        // Crear un usuario
        // Usuario user = new Usuario("maria", "maria@gmail.com", "maria");
        //
        //// String response = WSManager.guardarUsuario(user);
        //// System.out.println("Creando usuario: ");
        //// System.out.println(response != null ? response : "error, no se creo");
        //
        // CreadorContenido creadorCreate = new CreadorContenido("maria",
        // "maria@gmail.com", "maria");
        //
        // String creatorResponse = WSManager.guardarCreadorContenido(creadorCreate);
        // System.out.println("Creando creador: ");
        // System.out.println(creatorResponse != null ? creatorResponse : "error, no se
        // creo");
        //
        // // Creamos el contenido
        // Contenido content = new Contenido("Me fui a mexico", "Ando de paseo por
        // mexico", creatorResponse, "/image/imagge/test");
        // String contentResponse = WSManager.guardarContenido(content);
        // System.out.println(contentResponse != null ? contentResponse : "error, no se
        // creo");
        // Contenido contentUpdated = new Contenido(
        // "4a01b6f0-b12b-11ef-9ce4-04d4c447d499",
        // "Me fui a australia con unos compas",
        // "Ando de paseo por mexico",
        // "49fcf6cf-b12b-11ef-9ce4-04d4c447d499",
        // 0,
        // "/image/imagge/test"
        // );
        // String contentResponse =
        // WSManager.eliminarContenido("4a01b6f0-b12b-11ef-9ce4-04d4c447d499");
        // System.out.println("Contenido eliminado:");
        // System.out.println(contentResponse != null ? contentResponse : "error, no se
        // elimino");

        // Comentario comment = new Comentario("9a2eec97-b009-11ef-a2bf-04d4c447d499",
        // "feec39ce-b02b-11ef-a2bf-04d4c447d499", "Te la pasaste bien brother?");
        // String commentResponse = WSManager.guardarComentario(comment);
        // System.out.println(commentResponse != null ? commentResponse : "error, no se
        // guardo el comentario" );
        // Agregamos un like
        //  Like like = new Like("9a2eec97-b009-11ef-a2bf-04d4c447d499",
        //  "feec39ce-b02b-11ef-a2bf-04d4c447d499");
        //  String likeResponse = WSManager.guardarLike(like);
        //  System.out.println("Like guardado:");
        //  System.out.println(likeResponse != null ? likeResponse : "error, no se guardo el like");

        // Eliminar like
        // String likeResponse = WSManager.eliminarLike("4320ccb5-b18d-11ef-901f-04d4c447d499");
        // System.out.println("Like eliminado:");
        // System.out.println(likeResponse != null ? likeResponse : "error, no se elimino el like");

        // Guardar suscripcion
        // String suscripcionResponse = WSManager.guardarSuscripcion("49f7d416-b12b-11ef-9ce4-04d4c447d499",
        //         "31be130f-b019-11ef-a2bf-04d4c447d499");

        // System.out.println("Suscripcion guardada:");
        // System.out.println(suscripcionResponse != null ? suscripcionResponse : "error, no se guardo la suscripcion");

        // Eliminar suscripcion
        // String suscripcionResponse = WSManager.eliminarSuscripcion("49f7d416-b12b-11ef-9ce4-04d4c447d499",
        //         "31be130f-b019-11ef-a2bf-04d4c447d499");

        // System.out.println("Suscripcion eliminada:");

        // System.out.println(suscripcionResponse != null ? suscripcionResponse : "error, no se elimino la suscripcion");

        // Guardar una visualizacion
//        Visualizacion visualizacion = new Visualizacion("49f7d416-b12b-11ef-9ce4-04d4c447d499",
//                "feec39ce-b02b-11ef-a2bf-04d4c447d499");
//        String visualizacionResponse = WSManager.guardarVisualizacion(visualizacion);
//
//        System.out.println("Visualizacion guardada:");
//
//        System.out.println(visualizacionResponse != null ? visualizacionResponse : "error, no se guardo la visualizacion");

        
        // Eliminar like por id de usuario y contenido
        // String likeDeleteResponse = WSManager.eliminarLike("9a2eec97-b009-11ef-a2bf-04d4c447d499", "feec39ce-b02b-11ef-a2bf-04d4c447d499");
        // System.out.println("Like eliminado:");
        // System.out.println(likeDeleteResponse != null ? likeDeleteResponse : "error, no se elimino el like");

        // Actualizar creador de contenido
//        CreadorContenido creadorUpdate = new  CreadorContenido("49fcf6cf-b12b-11ef-9ce4-04d4c447d499", false , false);
//        String creadorUpdateResponse = WSManager.actualizarCreadorContenido(creadorUpdate);
//        System.out.println("Creador actualizado:");
//        System.out.println(creadorUpdateResponse != null ? creadorUpdateResponse : "error, no se actualizo el creador");

    }
}
