/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba.dao;

import com.google.gson.reflect.TypeToken;
import com.mycompany.donacionesprueba.clases.Administrador;
import com.mycompany.donacionesprueba.clases.CreadorContenido;
import com.mycompany.donacionesprueba.clases.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author carlo
 */
public class Dao {

    public Dao() {

    }

    // Metodo para verificar si el usuario existe
    public static Usuario verificarUsuario(String correo, String contrasena) {
        GenericDao<Usuario> usuarioDao = new GenericDao<>("usuario.json", new TypeToken<List<Usuario>>() {
        });
        List<Usuario> usuarios = usuarioDao.cargar();

        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(sha1Encrypt(contrasena))) {
                return usuario;
            }
        }

        // Si no se encuentra en usuarios lo buscamos en creadores de contenido
        GenericDao<CreadorContenido> creadorContenidoDao = new GenericDao<>("creadorContenido.json",
                new TypeToken<List<CreadorContenido>>() {
                });

        List<CreadorContenido> creadoresContenido = creadorContenidoDao.cargar();

        for (CreadorContenido creadorContenido : creadoresContenido) {
            if (creadorContenido.getCorreo().equals(correo)
                    && creadorContenido.getContrasena().equals(sha1Encrypt(contrasena))) {
                return creadorContenido;
            }
        }

        // Si no se encuentra en creadores de contenido lo buscamos en administradores
        GenericDao<Administrador> administradorDao = new GenericDao<>("administrador.json",
                new TypeToken<List<Administrador>>() {
                });

        List<Administrador> administradores = administradorDao.cargar();

        for (Administrador administrador : administradores) {
            if (administrador.getCorreo().equals(correo)
                    && administrador.getContrasena().equals(sha1Encrypt(contrasena))) {
                return administrador;
            }
        }

        return null;
    }

    // Metodo para guardar un usuario
    public static void guardarUsuario(Usuario usuario) throws IOException {
        try {
            GenericDao<Usuario> usuarioDao = new GenericDao<>("usuario.json", new TypeToken<List<Usuario>>() {
            });
            List<Usuario> usuarios = usuarioDao.cargar();

            // Encriptamos la contraseña
            usuario.setContrasena(sha1Encrypt(usuario.getContrasena()));

            usuarios.add(usuario);
            usuarioDao.guardar(usuarios);
        } catch (IOException ex) {
            System.out.println("Error al guardar el usuario");
            // Arrojamos una excepción
            throw new IOException("Error al agregar al usuario");
        }
    }

    // Metodo para guardar un creador de contenido
    public static void guardarCreadorContenido(CreadorContenido creadorContenido) throws IOException {
        try {
            GenericDao<CreadorContenido> creadorContenidoDao = new GenericDao<>("creadorContenido.json",
                    new TypeToken<List<CreadorContenido>>() {
                    });
            List<CreadorContenido> creadoresContenido = creadorContenidoDao.cargar();

            // Encriptamos la contraseña
            creadorContenido.setContrasena(sha1Encrypt(creadorContenido.getContrasena()));

            creadoresContenido.add(creadorContenido);
            creadorContenidoDao.guardar(creadoresContenido);
        } catch (IOException ex) {
            System.out.println("Error al guardar el creador de contenido");
            // Arrojamos una excepción
            throw new IOException("Error al guardar al creador de contenido");
        }
    }

    // Metodo para guardar un administrador
    public static void guardarAdministrador(Administrador administrador) throws IOException {
        try {
            GenericDao<Administrador> administradorDao = new GenericDao<>("administrador.json",
                    new TypeToken<List<Administrador>>() {
                    });
            List<Administrador> administradores = administradorDao.cargar();

            // Encriptamos la contraseña
            administrador.setContrasena(sha1Encrypt(administrador.getContrasena()));

            administradores.add(administrador);
            administradorDao.guardar(administradores);
        } catch (IOException ex) {
            System.out.println("Error al guardar el administrador");
            // Arrojamos una excepción
            throw new IOException("Error al guardar el administrador");
        }
    }

    // Metodo para encryptar el password del usuario en sha1
    public static String sha1Encrypt(String contrasena) {
        return DigestUtils.sha1Hex(contrasena);
    }
}
