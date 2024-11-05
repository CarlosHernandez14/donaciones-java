/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.donacionesprueba.dao;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.donacionesprueba.clases.Contenido;
import com.mycompany.donacionesprueba.clases.CreadorContenido;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author carlo
 */
public class DataExport {
    // Método para generar el reporte PDF
    public static void generarReportePDF(List<CreadorContenido> creadores) {
        // Crear un JFileChooser para que el usuario seleccione el directorio
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = folderChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = folderChooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + "/ReporteCreadoresContenido.pdf";

            try {
                // Crear el documento PDF
                Document pdfDoc = new Document(PageSize.A4);
                PdfWriter.getInstance(pdfDoc, new FileOutputStream(filePath));

                pdfDoc.open();

                // Agregar un título al documento
                pdfDoc.add(new Paragraph("Reporte de Creadores de Contenido"));
                pdfDoc.add(new Paragraph("Fecha: " + java.time.LocalDate.now().toString()));
                pdfDoc.add(new Paragraph("\n\n"));

                // Recorrer la lista de creadores de contenido y agregar la información
                for (CreadorContenido creador : creadores) {
                    pdfDoc.add(new Paragraph("Nombre: " + creador.getNombre()));
                    pdfDoc.add(new Paragraph("Email: " + creador.getCorreo()));
                    pdfDoc.add(new Paragraph("Cuenta Bloqueada: " + (creador.isCuentaBloqueada() ? "Sí" : "No")));
                    pdfDoc.add(new Paragraph("Partner: " + (creador.isPartner() ? "Sí" : "No")));
                    pdfDoc.add(new Paragraph("Número de Suscriptores: " + creador.getSuscriptores().size()));
                    pdfDoc.add(new Paragraph("Número de Contenidos: " + creador.getContenidos().size()));

                    // Detalles de los contenidos
                    if (!creador.getContenidos().isEmpty()) {
                        pdfDoc.add(new Paragraph("Contenidos:"));
                        for (Contenido contenido : creador.getContenidos()) {
                            pdfDoc.add(new Paragraph("  - Título: " + contenido.getTitulo()));
                            pdfDoc.add(new Paragraph("    Descripción: " + contenido.getDescripcion()));
                        }
                    }
                    pdfDoc.add(new Paragraph("\n"));
                }

                pdfDoc.close();
                JOptionPane.showMessageDialog(null, "El reporte ha sido generado correctamente en: " + filePath);
            } catch (FileNotFoundException | DocumentException ex) {
                JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó un directorio válido.");
        }
    }
    
    
    
    public static void generarReporteExcelTopDonaciones(List<CreadorContenido> creadores) {
        // Crear un diálogo para que el usuario seleccione la carpeta donde guardar el archivo Excel
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String folderPath = fileChooser.getSelectedFile().getAbsolutePath();
            String filePath = Paths.get(folderPath, "TopCreadoresDonaciones.xlsx").toString();

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Top Creadores Donaciones");

                // Encabezados de las columnas
                String[] encabezados = {"Posición", "Nombre del Creador", "Email", "Total de Donaciones"};
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < encabezados.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(encabezados[i]);
                }

//                // Calcular las donaciones totales por cada creador
//                List<CreadorContenido> topCreadores = creadores.stream()
//                        .sorted((a, b) -> Double.compare(b.getTotalDonaciones(), a.getTotalDonaciones()))
//                        .limit(10)
//                        .collect(Collectors.toList());

                // Agregar los datos de los creadores
                int rowIdx = 1;
                int posicion = 1;
                for (CreadorContenido creador : creadores) {
                    Row row = sheet.createRow(rowIdx++);

                    row.createCell(0).setCellValue(posicion++);
                    row.createCell(1).setCellValue(creador.getNombre());
                    row.createCell(2).setCellValue(creador.getCorreo());
                    row.createCell(3).setCellValue(creador.obtenerTotalDonaciones());
                }

                // Escribir el archivo en la ubicación seleccionada
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }

                JOptionPane.showMessageDialog(null, "El reporte Excel se ha generado correctamente en: " + filePath);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó un directorio válido.");
        }
    }
}
