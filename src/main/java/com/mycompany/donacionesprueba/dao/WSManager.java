/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.mycompany.donacionesprueba.clases.Administrador;
import com.mycompany.donacionesprueba.clases.Comentario;
import com.mycompany.donacionesprueba.clases.Contenido;
import com.mycompany.donacionesprueba.clases.CreadorContenido;
import com.mycompany.donacionesprueba.clases.Like;
import com.mycompany.donacionesprueba.clases.Usuario;
import com.mycompany.donacionesprueba.clases.Visualizacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WSManager {

    public static String URL = "http://localhost/WS_Donaciones/";

    public static Usuario verificarUsuario(String correo, String contrasena) {

        String endpoint = WSManager.URL + "users.php";

        String endpointCreadores = WSManager.URL + "creators.php";
        String endpointAdmins = WSManager.URL + "admins.php";

        try {
            Usuario usuario = null;

            String result = Request.Get(endpoint)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            if (!Boolean.parseBoolean(json.get("OK").toString())) {
                String errorMessage = (String) json.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray data = (JSONArray) json.get("data");
            // Generamos el hass sha1 para comparar las contrasenas

            String hashedPassword = hashSHA1(contrasena);

            for (Object object : data) {
                JSONObject userJson = (JSONObject) object;

                String userEmail = (String) userJson.get("correo");
                String userPass = (String) userJson.get("contrasena");

                // COMPARAMOS LOS VALORES DEL CORREO Y CONTRASENA
                if (correo.equals(userEmail) && hashedPassword.equals(userPass)) {
                    // Creamos el objeto de users

                    String userName = (String) userJson.get("nombre");
                    String userId = (String) userJson.get("idUsuario");

                    usuario = new Usuario(userId, userName, userEmail, userPass);

                }

            }

            // Si el usuario existe buscamos si es Admin, Creador o solo Usuario
            if (usuario != null) {
                // Obtenemos los creadores
                List<CreadorContenido> creadores = WSManager.obtenerCreadoresContenido();
                // Obtenemos los admins
                List<Administrador> admins = WSManager.obtenerAdmins();

                // Verificamos si el usuario es un creador
                for (CreadorContenido creador : creadores) {
                    if (creador.getId().equals(usuario.getId())) {
                        return creador;
                    }
                }

                // Verificamos si el usuario es un admin
                for (Administrador admin : admins) {
                    if (admin.getId().equals(usuario.getId())) {
                        return admin;
                    }
                }
                return usuario;
            }

        } catch (IOException ex) {
            System.out.println("Error al verificar al usuario:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al verificar el usuario:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al verificar el usuario:" + ex.getMessage());
        }

        return null;
    }

    // Metodo para obtener los usuarios
    public static List<Usuario> getUsers() {
        String endpoint = WSManager.URL + "users.php";

        try {
            List<Usuario> users = new ArrayList<>();

            String result = Request.Get(endpoint)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            if (!Boolean.parseBoolean(json.get("OK").toString())) {
                String errorMessage = (String) json.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray data = (JSONArray) json.get("data");

            for (Object object : data) {
                JSONObject userJson = (JSONObject) object;

                String userId = (String) userJson.get("idUsuario");
                String userName = (String) userJson.get("nombre");
                String userEmail = (String) userJson.get("correo");
                String userPass = (String) userJson.get("contrasena");

                Usuario user = new Usuario(userId, userName, userEmail, userPass);

                users.add(user);
            }

            return users;

        } catch (IOException ex) {
            System.out.println("Error al obtener los usuarios:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener los usuarios:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener los usuarios:" + ex.getMessage());
        }

        return null;
    }

    // Metodo para obtener un usuario por su id
    public static Usuario getUserById(String userId) {
        String endpoint = WSManager.URL + "users.php?id=" + userId;

        try {
            String result = Request.Get(endpoint)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            if (!Boolean.parseBoolean(json.get("OK").toString())) {
                String errorMessage = (String) json.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONObject data = (JSONObject) json.get("data");

            String userName = (String) data.get("nombre");
            String userEmail = (String) data.get("correo");
            String userPass = (String) data.get("contrasena");

            Usuario user = new Usuario(userId, userName, userEmail, userPass);

            return user;

        } catch (IOException ex) {
            System.out.println("Error al obtener el usuario:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener el usuario:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener el usuario:" + ex.getMessage());
        }

        return null;
    }

    // Metodo para guardar un usuario
    public static boolean guardarUsuario(Usuario user) {
        String endpoint = WSManager.URL + "users.php";

        try {
            String result = Request.Post(endpoint)
                    .bodyForm(Form.form()
                            .add("nombre", user.getNombre())
                            .add("correo", user.getCorreo())
                            .add("contrasena", user.getContrasena())
                            .build())
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            return Boolean.parseBoolean(json.get("OK").toString());

        } catch (IOException ex) {
            System.out.println("Error al guardar el usuario:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al guardar el usuario:" + ex.getMessage());
        }

        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    // Metodos para manejar creadores de contenido

    public static List<CreadorContenido> obtenerCreadoresContenido() {
        String endpoint = WSManager.URL + "creators.php";

        try {
            List<CreadorContenido> creators = new ArrayList<>();

            String result = Request.Get(endpoint)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            if (!Boolean.parseBoolean(json.get("OK").toString())) {
                String errorMessage = (String) json.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray data = (JSONArray) json.get("data");

            for (Object object : data) {
                JSONObject creatorsJson = (JSONObject) object;

                String userId = (String) creatorsJson.get("idUsuario");
                String creatorId = (String) creatorsJson.get("idCreador");
                boolean cuentaBloqueada = creatorsJson.get("cuenta_bloqueada").toString().equals("1");
                boolean partner = creatorsJson.get("partner").toString().equals("1");

                // Llamamos al metodo para obtener los datos del usuario asociado al userId

                Usuario user = getUserById(userId);

                CreadorContenido creator = new CreadorContenido(creatorId, userId, user.getNombre(), user.getCorreo(),
                        user.getContrasena());

                creators.add(creator);
            }

            return creators;

        } catch (IOException ex) {
            System.out.println("Error al obtener los creadores:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener los creadores:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener los creadores:" + ex.getMessage());
        }

        return null;
    }

    // Metodo para obtener un creador de contenido por su id
    public static CreadorContenido obtenerCreadorContenido(String creatorId) {
        String endpoint = WSManager.URL + "creators.php?id=" + creatorId;

        try {
            String result = Request.Get(endpoint)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            if (!Boolean.parseBoolean(json.get("OK").toString())) {
                String errorMessage = (String) json.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONObject data = (JSONObject) json.get("data");

            String userId = (String) data.get("idUsuario");
            boolean cuentaBloqueada = data.get("cuenta_bloqueada").toString().equals("1");
            boolean partner = data.get("partner").toString().equals("1");

            // Llamamos al metodo para obtener los datos del usuario asociado al userId

            Usuario user = getUserById(userId);

            CreadorContenido creator = new CreadorContenido(creatorId, userId, user.getNombre(), user.getCorreo(),
                    user.getContrasena());

            return creator;

        } catch (IOException ex) {
            System.out.println("Error al obtener el creador:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener el creador:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener el creador:" + ex.getMessage());
        }

        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // METODOS PARA MANEJO DE ADMINISTRADORES

    // Metodo para obtener los administradores
    public static List<Administrador> obtenerAdmins() {
        String endpoint = WSManager.URL + "admins.php";

        try {
            List<Administrador> admins = new ArrayList<>();

            String result = Request.Get(endpoint)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            if (!Boolean.parseBoolean(json.get("OK").toString())) {
                String errorMessage = (String) json.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray data = (JSONArray) json.get("data");

            for (Object object : data) {
                JSONObject adminJson = (JSONObject) object;

                String userId = (String) adminJson.get("idUsuario");
                String adminId = (String) adminJson.get("idAdministrador");

                // Obtenemos el usuario asociado al admin

                Usuario user = WSManager.getUserById(userId);
                // Creamos el administrador
                Administrador admin = new Administrador(adminId, userId, user.getNombre(), user.getCorreo(),
                        user.getContrasena());

                admins.add(admin);
            }

            return admins;

        } catch (IOException ex) {
            System.out.println("Error al obtener los administradores:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener los administradores:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener los administradores:" + ex.getMessage());
        }

        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // METODOS PARA MANEJO DE CONTENIDOS DEL WEBSERVICE

    // Metodo para obtener los contenidos
    public static List<Contenido> obtenerContenidos() {
        String endpoint = WSManager.URL + "contents.php";

        try {
            List<Contenido> contents = new ArrayList<>();

            String result = Request.Get(endpoint)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            if (!Boolean.parseBoolean(json.get("OK").toString())) {
                String errorMessage = (String) json.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray data = (JSONArray) json.get("data");

            for (Object object : data) {
                JSONObject contentJson = (JSONObject) object;

                String contentId = (String) contentJson.get("idContenido");
                String creatorId = (String) contentJson.get("idCreador");
                String title = (String) contentJson.get("titulo");
                String description = (String) contentJson.get("descripcion");
                double donaciones = Double.parseDouble(contentJson.get("donaciones").toString());
                String imagePath = (String) contentJson.get("image_path");

                // Obtenemos el creador asociado al contenido

                CreadorContenido creator = WSManager.obtenerCreadorContenido(creatorId);
                
                // Obtenemos los comentarios
                List<Comentario> comentarios = WSManager.getComentarios(contentId);
                // Obtenemos las visualizaciones
                List<Visualizacion> visualizaciones = WSManager.getVisualizaciones(contentId);
                // Obtenemos los likes
                List<Like> likes = WSManager.getLikes(contentId);

                // Creamos el contenido
                Contenido content = new Contenido(contentId, title, description, creatorId, (ArrayList<Visualizacion>) visualizaciones, (ArrayList<Like>) likes,
                        comentarios, donaciones, imagePath);

                contents.add(content);
            }

            return contents;

        } catch (IOException ex) {
            System.out.println("Error al obtener los contenidos:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener los contenidos:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener los contenidos:" + ex.getMessage());
        }

        return null;
    }

    /////////////////////////////////////////////////////////////////////////////
    // METODOS PARA EL MANEJO DE LIKES
    // Metodo para obtener los likes
    public static List<Like> getLikes(String contentId) {
        String endpoint = WSManager.URL + "likes.php?idContenido=" + contentId;

        try {
            List<Like> likes = new ArrayList<>();

            String result = Request.Get(endpoint)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            if (!Boolean.parseBoolean(json.get("OK").toString())) {
                String errorMessage = (String) json.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray data = (JSONArray) json.get("data");

            for (Object object : data) {
                JSONObject likeJson = (JSONObject) object;

                String likeId = (String) likeJson.get("idLike");
                String userId = (String) likeJson.get("idUsuario");

                // Creamos el like
                Like like = new Like(likeId, userId, contentId);

                likes.add(like);

            }

            return likes;

        } catch (IOException ex) {
            System.out.println("Error al obtener los likes:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener los likes:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener los likes:" + ex.getMessage());
        }

        return null;
    }

    /////////////////////////////////////////////////////////////////////////////
    // METODOS PARA EL MANEJO DE COMENTARIOS
    // Metodo para obtener los comentarios
    public static List<Comentario> getComentarios(String contentId) {
        String endpoint = WSManager.URL + "comments.php?idContenido=" + contentId;

        try {
            List<Comentario> comentarios = new ArrayList<>();

            String result = Request.Get(endpoint)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            if (!Boolean.parseBoolean(json.get("OK").toString())) {
                String errorMessage = (String) json.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray data = (JSONArray) json.get("data");

            for (Object object : data) {
                JSONObject commentJson = (JSONObject) object;

                String commentId = (String) commentJson.get("idComentario");
                String userId = (String) commentJson.get("idUsuario");
                String comment = (String) commentJson.get("comentario");

                // Creamos el comentario
                Comentario comentario = new Comentario(commentId, userId, contentId, comment);

                comentarios.add(comentario);
            }

            return comentarios;

        } catch (IOException ex) {
            System.out.println("Error al obtener los comentarios:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener los comentarios:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener los comentarios:" + ex.getMessage());
        }

        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // METODOS PARA EL MANEJO DE VISUALIZACIONES
    // Metodo para obtener las visualizaciones
    public static List<Visualizacion> getVisualizaciones(String contentId) {
        String endpoint = WSManager.URL + "views.php?idContenido=" + contentId;

        try {
            List<Visualizacion> visualizaciones = new ArrayList<>();

            String result = Request.Get(endpoint)
                    .execute().returnContent().asString();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);

            if (!Boolean.parseBoolean(json.get("OK").toString())) {
                String errorMessage = (String) json.get("error");
                throw new Exception("Error en la solicitud: " + errorMessage);
            }

            JSONArray data = (JSONArray) json.get("data");

            for (Object object : data) {

                JSONObject viewJson = (JSONObject) object;

                String idView = (String) viewJson.get("idVisualizacion");
                String idContent = (String) viewJson.get("idContenido");
                String idUser = (String) viewJson.get("idUsuario");

                // Creamos la visualizacion
                Visualizacion view = new Visualizacion(idView, idContent, idUser);

                visualizaciones.add(view);

            }

            return visualizaciones;

        } catch (IOException ex) {
            System.out.println("Error al obtener las visualizaciones:" + ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Error al obtener las visualizaciones:" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error al obtener las visualizaciones:" + ex.getMessage());
        }

        return null;
    }

    // Método para generar el hash SHA-1 de una contraseña
    private static String hashSHA1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            // Convertir el hash a un string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar el hash SHA-1", e);
        }
    }
}
