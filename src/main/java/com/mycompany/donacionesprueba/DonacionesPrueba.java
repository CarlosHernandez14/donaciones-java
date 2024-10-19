/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.donacionesprueba;

import com.formdev.flatlaf.FlatDarkLaf;
import com.google.gson.reflect.TypeToken;
import com.mycompany.donacionesprueba.clases.CreadorContenido;
import com.mycompany.donacionesprueba.clases.Usuario;
import com.mycompany.donacionesprueba.dao.Dao;
import com.mycompany.donacionesprueba.dao.GenericDao;
import com.mycompany.donacionesprueba.vistas.Login;
import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;

/**
 *
 * @author carlo
 */
public class DonacionesPrueba {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Prueba DAO

        // GenericDao<Usuario> usuarioDao = new GenericDao<>("usuario.json", new TypeToken<List<Usuario>>() {
        // });

        // // Usuario
        // Usuario juan = new Usuario("Carlos", "carlos@gmail.com", Dao.sha1Encrypt("123"));
        // Usuario maria = new Usuario("maria", "maria@gmail.com", Dao.sha1Encrypt("123"));

        // List<Usuario> usuarios = new ArrayList<>();

        // usuarios.add(juan);
        // usuarios.add(maria);

        // usuarioDao.guardar(usuarios);

        // // Crear un creador de contenido
        // GenericDao<CreadorContenido> creadorContenidoDao = new GenericDao<>("creadorContenido.json",
        //         new TypeToken<List<CreadorContenido>>() {
        //         });

        // CreadorContenido creador = new CreadorContenido("Juan", "juan@gmail.com", Dao.sha1Encrypt("123"));

        // List<CreadorContenido> creadores = new ArrayList<>();

        // creadores.add(creador);

        // creadorContenidoDao.guardar(creadores);

        try {
            // Establecer el Look and Feel FlatLaf
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Error al cargar flatlaf look and feel");
        }

        new Login().setVisible(true);
    }
}
