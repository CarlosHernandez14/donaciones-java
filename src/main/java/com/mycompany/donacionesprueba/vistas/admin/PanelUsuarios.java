/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.donacionesprueba.vistas.admin;

import com.mycompany.donacionesprueba.clases.Contenido;
import com.mycompany.donacionesprueba.clases.CreadorContenido;
import com.mycompany.donacionesprueba.dao.WSManager;
//import com.mycompany.donacionesprueba.dao.Dao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author carlo
 */
public class PanelUsuarios extends javax.swing.JPanel {

    private CreadorContenido influencer;

    /**
     * Creates new form PanelUsuarios
     */
    public PanelUsuarios() {
        initComponents();
    }

    public PanelUsuarios(CreadorContenido influencer) {
        initComponents();

        this.influencer = influencer;

        initData();
    }

    public void initData() {

        // Seteamos btn hacer partner
        this.btnPartner.setText(this.influencer.isPartner() ? "Deshacer partner" : "Hacer partner");

        // Seteamos los labels con los datos
        this.btnProfile.setText(this.influencer.getNombre());

        // Calculamos promedio de  likes y vistas
        double donaciones = calcularTotalDonaciones();
        double averageLikes = calcularPromedioLikes();
        double averageViews = calcularPromedioVisualizaciones();

        this.labelDonations.setText("$ " + donaciones);
        this.labelLikes.setText(String.valueOf(averageLikes));
        this.labelViews.setText(String.valueOf(averageViews));

    }

    // Método para calcular el total de donaciones
    private double calcularTotalDonaciones() {
        try {
            // Obtenemos los contenidos del influencer
            //List<Contenido> contenidos = Dao.obtenerContenidos();
            List<Contenido> contenidos = WSManager.obtenerContenidos();
            
            // Variable para almacenar el total de donaciones
            double totalDonaciones = 0;

            // Recorremos los contenidos
            for (Contenido contenido : contenidos) {
                // Verificamos si el contenido pertenece al influencer
                if (contenido.getIdCreador().equals(this.influencer.getId())) {
                    // Sumamos el total de donaciones
                    totalDonaciones += contenido.getDonaciones();
                }
            }

            return totalDonaciones;

        } catch (Exception ex) {
            System.err.println("Error al calcular el total de donaciones: " + ex.getMessage());
            // JOptionPane.showMessageDialog se usa en Java para mostrar mensajes
            javax.swing.JOptionPane.showMessageDialog(null, "Error al calcular el total de donaciones");
        }
        return 0;
    }

    // Método para calcular el promedio de likes de los contenidos del influencer
    private double calcularPromedioLikes() {
        try {
            // Obtenemos los contenidos del influencer
            //List<Contenido> contenidos = Dao.obtenerContenidos();
            List<Contenido> contenidos = WSManager.obtenerContenidos();
            
            // Filtramos los contenidos del influencer
            List<Contenido> contenidosInfluencer = new ArrayList<>();
            for (Contenido contenido : contenidos) {
                if (contenido.getIdCreador().equals(this.influencer.getId())) {
                    contenidosInfluencer.add(contenido);
                }
            }

            // Imprimir cada contenido del influencer para depuración
            for (Contenido contenido : contenidosInfluencer) {
                System.out.println(contenido.toString());
            }

            // Variable para almacenar el total de likes
            double totalLikes = 0;

            // Recorremos los contenidos del influencer
            for (Contenido contenido : contenidosInfluencer) {
                // Sumamos el total de likes
                totalLikes += contenido.getLikes().size();
            }

            return totalLikes / contenidosInfluencer.size();

        } catch (Exception ex) {
            System.err.println("Error al calcular el promedio de likes: " + ex.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "Error al calcular el promedio de likes");
        }
        return 0;
    }

    // Método para calcular el promedio de visualizaciones de los contenidos del influencer
    private double calcularPromedioVisualizaciones() {
        try {
            // Obtenemos los contenidos del influencer
            //List<Contenido> contenidos = Dao.obtenerContenidos();
            List<Contenido> contenidos = WSManager.obtenerContenidos();
            
            // Filtramos los contenidos del influencer
            List<Contenido> contenidosInfluencer = new ArrayList<>();
            for (Contenido contenido : contenidos) {
                if (contenido.getIdCreador().equals(this.influencer.getId())) {
                    contenidosInfluencer.add(contenido);
                }
            }

            // Variable para almacenar el total de visualizaciones
            double totalVisualizaciones = 0;

            // Recorremos los contenidos del influencer
            for (Contenido contenido : contenidosInfluencer) {
                // Sumamos el total de visualizaciones
                totalVisualizaciones += contenido.getVisualizaciones().size();
            }

            return totalVisualizaciones / contenidosInfluencer.size();

        } catch (Exception ex) {
            System.err.println("Error al calcular el promedio de visualizaciones: " + ex.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, "Error al calcular el promedio de visualizaciones");
        }
        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnProfile = new javax.swing.JButton();
        labelDonations = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelViews = new javax.swing.JLabel();
        labelLikes = new javax.swing.JLabel();
        btnPartner = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        btnProfile.setBackground(new java.awt.Color(255, 255, 255));
        btnProfile.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProfile.setForeground(new java.awt.Color(51, 51, 51));
        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/donacionesprueba/vistas/assets/icon-usuario.png"))); // NOI18N
        btnProfile.setText("Nombre usuario");
        btnProfile.setBorder(null);
        btnProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProfile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        labelDonations.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDonations.setForeground(new java.awt.Color(0, 0, 0));
        labelDonations.setText("$0.0");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Donaciones");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Promedio de likes");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Promedio de vistas");

        labelViews.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelViews.setForeground(new java.awt.Color(0, 0, 0));
        labelViews.setText("0");

        labelLikes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLikes.setForeground(new java.awt.Color(0, 0, 0));
        labelLikes.setText("0");

        btnPartner.setBackground(new java.awt.Color(255, 255, 255));
        btnPartner.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPartner.setForeground(new java.awt.Color(255, 102, 0));
        btnPartner.setText("Hacer partner");
        btnPartner.setToolTipText("");
        btnPartner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPartnerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(labelDonations))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelViews, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLikes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnPartner)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelDonations)
                                    .addComponent(labelViews)
                                    .addComponent(labelLikes)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btnPartner, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnPartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPartnerActionPerformed
        // TODO add your handling code here:

        try {
            // Si es partner lo deshacemos sino lo hacemos partner
            if (this.influencer.isPartner()) {
                // Deshacemos el parter si ya lo es
                this.influencer.setPartner(false);

                // Guardamos en el json db
                //Dao.actualizarCreadorContenido(influencer);
                WSManager.actualizarCreadorContenido(influencer);
                
                // Si todo salio bien mostramos mensaje de exito
                JOptionPane.showMessageDialog(null, "Se elimino el partener correctamente");
            } else {
                // Lo hacemos partner
                this.influencer.setPartner(true);
                
                // Guardamos en la db
                //Dao.actualizarCreadorContenido(influencer);
                WSManager.actualizarCreadorContenido(influencer);
                // Mensaje de exito
                JOptionPane.showMessageDialog(null, "Se actualizo a partner con exito");
            }
            
            this.btnPartner.setText(this.influencer.isPartner() ? "Deshacer partner": "Hacer partner");
        } catch (Exception e) {
            System.out.println("Error al actualizar partnership:" + e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el partnership");
        }
    }//GEN-LAST:event_btnPartnerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPartner;
    private javax.swing.JButton btnProfile;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelDonations;
    private javax.swing.JLabel labelLikes;
    private javax.swing.JLabel labelViews;
    // End of variables declaration//GEN-END:variables
}
