/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class GenericDao<T> {
    // Data Acess Object
    // Objeto de acceso a los datos para guardar y leer jsons
    
    private final String filePath;
    // Libreria de google para manejar Json's
    private final Gson gson;
    // Usamos typetoken para que la libreria Gson para JSON sepa el tipo de la lista
    // al momento de deserializar el la lista del archivo en  objetos java
    private final TypeToken listType;

    public GenericDao(String filePath, TypeToken listType) {
        this.filePath = filePath;
        this.gson = new Gson();
        this.listType = listType;
    }

    // Método para cargar los datos de un archivo .json
    public List<T> cargar() {
        try (FileReader reader = new FileReader(filePath)) {
            return (List<T>) gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Método para guardar la lista en un archivo .json
    public void guardar(List<T> lista) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
