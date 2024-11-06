/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.donacionesprueba.vistas;

import com.mycompany.donacionesprueba.clases.Contenido;
import com.mycompany.donacionesprueba.clases.Usuario;
import com.mycompany.donacionesprueba.dao.Dao;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author carlo
 */
public class CommentsForm extends javax.swing.JFrame {

    private Contenido contenido;
    private Usuario usuario;

    /**
     * Creates new form CommentsForm
     */
    public CommentsForm() {
        initComponents();
    }

    public CommentsForm(Contenido contenido, Usuario usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.contenido = contenido;
        this.usuario = usuario;

        this.containerComments.setLayout(new BoxLayout(this.containerComments, BoxLayout.Y_AXIS));

        // Cargamos los comentarios
        loadComments();
    }

    public void loadComments() {
        this.containerComments.removeAll();

        // Seteamos los comentarios
        for (String comentario : contenido.getComentarios()) {
            // Agregamos un panel de comentario

            PanelComment panelComment = new PanelComment(comentario);

            // Agregamos el comentario al panel
//            panelComment.setMaximumSize(containerComments.getPreferredSize());
//            panelComment.setPreferredSize(containerComments.getPreferredSize());
            this.containerComments.add(panelComment);
        }

        // Refrescamos la visa
        this.containerComments.revalidate();
        this.containerComments.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaNewComment = new javax.swing.JTextArea();
        btnAddComments = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        containerComments = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Comentarios");

        textAreaNewComment.setBackground(new java.awt.Color(255, 255, 255));
        textAreaNewComment.setColumns(20);
        textAreaNewComment.setRows(5);
        textAreaNewComment.setText("Agregar comentario...");
        jScrollPane1.setViewportView(textAreaNewComment);

        btnAddComments.setBackground(new java.awt.Color(255, 255, 255));
        btnAddComments.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddComments.setForeground(new java.awt.Color(255, 102, 51));
        btnAddComments.setText("Agregar");
        btnAddComments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCommentsActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        containerComments.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout containerCommentsLayout = new javax.swing.GroupLayout(containerComments);
        containerComments.setLayout(containerCommentsLayout);
        containerCommentsLayout.setHorizontalGroup(
            containerCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
        );
        containerCommentsLayout.setVerticalGroup(
            containerCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(containerComments);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddComments, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnAddComments, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddCommentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCommentsActionPerformed
        // TODO add your handling code here:

        try {
            // Obtenemos el texto
            String commentInput = this.textAreaNewComment.getText();
            
            if (commentInput.equals("Agregar comentario...")) {
                JOptionPane.showMessageDialog(null, "Escribe un comentario para agregarlo");
                return;
            }

            // Agregamos el nuevo comentario
            this.contenido.agregarComentario(commentInput, usuario);

            // Guardamos en la db 
            
            Dao.actualizarContenido(contenido);
            
            // Actualizamos la ui
            loadComments();
            
            // Reseteamos el valor del text area
            this.textAreaNewComment.setText("Agregar comentario...");
            
            JOptionPane.showMessageDialog(null, "Comentario agregado con exito");
        } catch (Exception e) {
            System.out.println("Error al agregar el comentario:" + e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo agregar tu comentario");
        }
    }//GEN-LAST:event_btnAddCommentsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CommentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CommentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CommentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CommentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CommentsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddComments;
    private javax.swing.JPanel containerComments;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textAreaNewComment;
    // End of variables declaration//GEN-END:variables
}