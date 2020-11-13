package com.vistas;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JInternalFrame;

/**
 * Nombre de la clase: FrmProductoSuscripcion
 * Fecha: 13-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmProductoSuscripcion extends javax.swing.JFrame {
    
    public FrmProductoSuscripcion() {
        initComponents();
        //this.setExtendedState(MAXIMIZED_BOTH);
        Timer timer = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                FrmProductoSuscripcionOro oro = new FrmProductoSuscripcionOro();
                dpContenedor.add(oro);
                //Mostar formulario centrado
                Dimension desktopSize = dpContenedor.getSize();
                Dimension FrameSize = oro.getSize();
                oro.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                //Fin mostrar formulario centrado
                oro.setVisible(true);
            }
        };
        timer.schedule(tarea, 0000);

    }
    
    public FrmProductoSuscripcion(int idSuscriptor) {
        initComponents();
        //this.setExtendedState(MAXIMIZED_BOTH);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dpContenedor = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        dpContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 3));

        javax.swing.GroupLayout dpContenedorLayout = new javax.swing.GroupLayout(dpContenedor);
        dpContenedor.setLayout(dpContenedorLayout);
        dpContenedorLayout.setHorizontalGroup(
            dpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1237, Short.MAX_VALUE)
        );
        dpContenedorLayout.setVerticalGroup(
            dpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpContenedor)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpContenedor)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


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
            java.util.logging.Logger.getLogger(FrmProductoSuscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProductoSuscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProductoSuscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProductoSuscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProductoSuscripcion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dpContenedor;
    // End of variables declaration//GEN-END:variables

}
