/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.donacionesprueba.vistas.influencer;

import com.mycompany.donacionesprueba.clases.Administrador;
import com.mycompany.donacionesprueba.clases.Contenido;
import com.mycompany.donacionesprueba.clases.CreadorContenido;
import com.mycompany.donacionesprueba.clases.Like;
import com.mycompany.donacionesprueba.clases.Usuario;
import com.mycompany.donacionesprueba.clases.Visualizacion;
import com.mycompany.donacionesprueba.dao.WSManager;
//import com.mycompany.donacionesprueba.dao.Dao;
import com.mycompany.donacionesprueba.vistas.CommentsForm;
import com.mycompany.donacionesprueba.vistas.admin.HomeAdminForm;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author carlo
 */
public class PanelContenido extends javax.swing.JPanel {

    private Usuario usuario;
    private Contenido contenido;
    private CreadorContenido creador;
    
    // Forms para actualizar data
    private HomeInfluencerForm homeInfluecerForm;
    private HomeAdminForm homeAdminForm;

    /**
     * Creates new form PanelContenido
     */
    public PanelContenido() {
        initComponents();
    }

    public PanelContenido(Contenido contenido, Usuario usuario, HomeInfluencerForm homeInfluencerForm, HomeAdminForm homeAdminForm) {
        initComponents();
        this.usuario = usuario;
        this.contenido = contenido;
        
        this.homeInfluecerForm = homeInfluencerForm;
        this.homeAdminForm = homeAdminForm;

        // Inicializar data
        CreadorContenido creadorDelContent = WSManager.obtenerCreadorContenido(this.contenido.getIdCreador());
        
        btnProfile.setText(creadorDelContent.getNombre());
        labelTitulo.setText(contenido.getTitulo());
        labelDescription.setText(contenido.getDescripcion());
        panelImage.setIcon(new javax.swing.ImageIcon(contenido.getImagePath()));

        // Si el usuario es el creador del contenido, ocultamos el botón de suscribirse
        // if (usuario.getId().equals(contenido.getIdCreador())) {
        //     btnSuscribe.setVisible(false);
        // }
        // Si el usuario ya ha dado like, cambiamos el icono por el de likeado
        if (usuario != null) {
            for (Like like : contenido.getLikes()) {
                if (like.getIdUsuario().equals(usuario.getId())) {
                    btnLike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/donacionesprueba/vistas/assets/icon-like.png")));
                }
            }

            // Si el usuario ya visualizó el contenido ocultamos el boton de visualizar
            for (Visualizacion visualizacion : contenido.getVisualizaciones()) {
                System.out.println(visualizacion.toString());
                if (visualizacion.getIdUsuario().equals(usuario.getId())) {
                    this.btnVer.setVisible(false);
                }
            }

            // Mostramos la cantida de comentarios
            btnComentar.setText("Comentarios (" + contenido.getComentarios().size() + ")");

            // Verificamos si el usuario esta suscrito al creador del contenido
            // Obtenemos al creador del contenido
            //this.creador = Dao.obtenerCreadorContenido(contenido.getIdCreador());
            this.creador = WSManager.obtenerCreadorContenido(contenido.getIdCreador());
            
            // Verificamos si el usuario esta suscrito al creador

            // Si el usuario esta suscrito al creador, cambiamos el texto del boton de suscribirse
            if (creador.getSuscriptores().contains(usuario.getId())) {
                btnSuscribe.setText("Desuscribirse");
            } else {
                btnSuscribe.setText("Suscribirse");
            }
            
            // Si el contenido no es del usuario o es un usuario 
            //comun eliminamos btn de options

            if (this.usuario instanceof CreadorContenido) {
                //System.out.println("EL USUARIO ES CREADOR DE CONTENIDO");
                // Si el contenido es suyo se muestra si no no
                if (!((CreadorContenido)this.usuario).getIdCreador().equals(contenido.getIdCreador())) {
                    this.btnOptions.setVisible(false);
                }
            } else if (this.usuario instanceof Administrador) {
                //System.out.println("EL USUARIO ES ADMINISTRADOR");
                // Se muestra el boton de opciones
                this.btnOptions.setVisible(true);
            } else {
                //System.out.println("EL USUARIO ES USUARIO COMUN");
                // Si es usuario comun ocultamos boton
                this.btnOptions.setVisible(false);
            }

            // Verificamos si el usuario ya vio el contenido
            for (Visualizacion visualizacion : contenido.getVisualizaciones()) {
                if (visualizacion.getIdUsuario().equals(usuario.getId())) {
                    this.btnVer.setVisible(false);
                }
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuOpciones = new javax.swing.JPopupMenu();
        itemEliminar = new javax.swing.JMenuItem();
        panelHeader = new javax.swing.JPanel();
        btnProfile = new javax.swing.JButton();
        btnOptions = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();
        btnSuscribe = new javax.swing.JButton();
        panelContainerContent = new javax.swing.JPanel();
        panelImage = new org.edisoncor.gui.panel.PanelImage();
        labelDescription = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnLike = new javax.swing.JButton();
        btnDonar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnComentar = new javax.swing.JButton();

        itemEliminar.setText("Eliminar contenido");
        itemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEliminarActionPerformed(evt);
            }
        });
        menuOpciones.add(itemEliminar);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new org.edisoncor.gui.util.DropShadowBorder());

        panelHeader.setBackground(new java.awt.Color(255, 255, 255));

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

        btnOptions.setBackground(new java.awt.Color(232, 232, 232));
        btnOptions.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnOptions.setForeground(new java.awt.Color(0, 0, 0));
        btnOptions.setText("...");
        btnOptions.setBorderPainted(false);
        btnOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOptionsActionPerformed(evt);
            }
        });

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(51, 51, 51));
        labelTitulo.setText("Titulo");

        btnSuscribe.setBackground(new java.awt.Color(255, 102, 51));
        btnSuscribe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuscribe.setForeground(new java.awt.Color(255, 255, 255));
        btnSuscribe.setText("Suscribirse");
        btnSuscribe.setBorderPainted(false);
        btnSuscribe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuscribeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addComponent(btnProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuscribe)
                        .addGap(162, 162, 162)
                        .addComponent(btnOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProfile)
                    .addComponent(btnOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuscribe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTitulo)
                .addGap(90, 90, 90))
        );

        panelContainerContent.setBackground(new java.awt.Color(255, 255, 255));

        panelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/donacionesprueba/vistas/assets/default-image.jpg"))); // NOI18N

        javax.swing.GroupLayout panelImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelImageLayout.setVerticalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );

        labelDescription.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDescription.setText("Descripcion de la publicacion");
        labelDescription.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelDescription.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnLike.setBackground(new java.awt.Color(255, 255, 255));
        btnLike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/donacionesprueba/vistas/assets/icon-nolikeado.png"))); // NOI18N
        btnLike.setText("Like");
        btnLike.setBorderPainted(false);
        btnLike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLikeActionPerformed(evt);
            }
        });

        btnDonar.setBackground(new java.awt.Color(255, 255, 255));
        btnDonar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDonar.setForeground(new java.awt.Color(255, 102, 0));
        btnDonar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/donacionesprueba/vistas/assets/icon-donar.png"))); // NOI18N
        btnDonar.setText("Donar");
        btnDonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonarActionPerformed(evt);
            }
        });

        btnVer.setBackground(new java.awt.Color(255, 255, 255));
        btnVer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVer.setForeground(new java.awt.Color(255, 102, 0));
        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnComentar.setBackground(new java.awt.Color(255, 255, 255));
        btnComentar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnComentar.setForeground(new java.awt.Color(153, 153, 153));
        btnComentar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/donacionesprueba/vistas/assets/icon-comentar.png"))); // NOI18N
        btnComentar.setText("Comentar");
        btnComentar.setBorderPainted(false);
        btnComentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComentarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnLike)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnComentar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDonar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDonar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLike)
                        .addComponent(btnComentar)
                        .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelContainerContentLayout = new javax.swing.GroupLayout(panelContainerContent);
        panelContainerContent.setLayout(panelContainerContentLayout);
        panelContainerContentLayout.setHorizontalGroup(
            panelContainerContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelDescription, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelContainerContentLayout.setVerticalGroup(
            panelContainerContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerContentLayout.createSequentialGroup()
                .addComponent(panelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelContainerContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContainerContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnLikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLikeActionPerformed
        try {
            // TODO add your handling code here:

            // Si el contenido ya tiene un like del usuario, lo eliminamos
            for (Like like : contenido.getLikes()) {
                if (like.getIdUsuario().equals(usuario.getId())) {
                    // Eliminamos el like del contenido
                    this.contenido.eliminarLike(this.usuario.getId(), this.contenido.getId());
                    // Cambiamos el icono
                    btnLike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/donacionesprueba/vistas/assets/icon-nolikeado.png")));
                    return;
                }
            }

            // Si no tiene like, lo agregamos
            // Agregamos el lke al contenido
            this.contenido.agregarLike(this.usuario.getId(), this.contenido.getId());

            // Si todo salio bien al darle like cambiamos el icono
            btnLike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/donacionesprueba/vistas/assets/icon-like.png")));

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el like");
        }
    }//GEN-LAST:event_btnLikeActionPerformed

    private void btnSuscribeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuscribeActionPerformed
        // TODO add your handling code here:

        //Obtenemos el  texto del button
        String textButton = this.btnSuscribe.getText();
        try {
            if (textButton.equals("Suscribirse")) {
                // Suscribimos al usuario
                this.creador.agregarSuscriptor(this.usuario.getId());
                
                // Si todo sale bien cambiamos el texto del button
                this.btnSuscribe.setText("Desuscribirse");
            } else if (textButton.equals("Desuscribirse")) {
                // Eliminamos la suscripcion
                this.creador.eliminarSuscriptor(this.usuario.getId());
                
                // Si tood sale bien cambiamos el texto
                this.btnSuscribe.setText("Suscribirse");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo guardar la suscripcion, intentalo mas tarde");
        }
        

    }//GEN-LAST:event_btnSuscribeActionPerformed

    private void btnOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOptionsActionPerformed
        // TODO add your handling code here:
        
        this.menuOpciones.show(this.btnOptions, 0, this.btnOptions.getHeight());
    }//GEN-LAST:event_btnOptionsActionPerformed

    private void itemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEliminarActionPerformed
        try {
            // TODO add your handling code here:
            
            // Eliminamos el contenido
            //Dao.eliminarContenido(this.contenido.getId());
            WSManager.eliminarContenido(this.contenido.getId());
            
            // Actualizamos la UI de HomeInfluencer o Admin
            if (this.homeInfluecerForm != null) {
                this.homeInfluecerForm.actualizarDatos();
            } else if(this.homeAdminForm != null) {
                this.homeAdminForm.LoadContent();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el contenido");
        }
    }//GEN-LAST:event_itemEliminarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        try {
            // TODO add your handling code here:
            
            //Agregamos la visualizacion
            this.contenido.agregarVisualizacion(this.usuario.getId());
            
            // Escondemos el btn una vez visto
            this.btnVer.setVisible(false);
        } catch (IOException ex) {
            
            ex.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "No se pudo agregar la visualizacion");
        }
        
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnDonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonarActionPerformed
        // TODO add your handling code here:
        
        //Agregamos una donacion
        double monto = 0;
        try {
            monto = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el monto a donar"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Monto invalido");
            return;
        }
        
        try {
            // Si el monto ese valido agregamos la donacion
            this.contenido.recibirDonacion(monto);
            
            JOptionPane.showMessageDialog(null, "Donacion enviada con exito ;D");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo enviar la donacio, intentalo mas tarde");
        }
        
    }//GEN-LAST:event_btnDonarActionPerformed

    private void btnComentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComentarActionPerformed
        // TODO add your handling code here:
        
        //Abrimos una ventana de Comentarios
        CommentsForm commentsForm = new CommentsForm(contenido, usuario);
        
        commentsForm.setVisible(true);
    }//GEN-LAST:event_btnComentarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComentar;
    private javax.swing.JButton btnDonar;
    private javax.swing.JButton btnLike;
    private javax.swing.JButton btnOptions;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnSuscribe;
    private javax.swing.JButton btnVer;
    private javax.swing.JMenuItem itemEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPopupMenu menuOpciones;
    private javax.swing.JPanel panelContainerContent;
    private javax.swing.JPanel panelHeader;
    private org.edisoncor.gui.panel.PanelImage panelImage;
    // End of variables declaration//GEN-END:variables
}
