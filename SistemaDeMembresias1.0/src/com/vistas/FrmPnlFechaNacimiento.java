/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vistas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author victor alvarado
 */
public class FrmPnlFechaNacimiento extends javax.swing.JPanel {
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    Date fechaActual = new Date();
    int conta =0;
    public FrmPnlFechaNacimiento() {
        initComponents();
        dateNacimiento.setDate(fechaActual);
    }
    public void calcularEdad() {
        int yearn = dateNacimiento.getCalendar().get(Calendar.YEAR);
        int mesn = dateNacimiento.getCalendar().get(Calendar.MONTH);
        int dayn = dateNacimiento.getCalendar().get(Calendar.DAY_OF_MONTH);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        for (int i = yearn; i < year; i++) {
            conta++;
        }
        
        if (conta < 18) {
            JOptionPane.showMessageDialog(this, "Debe ser mayor de edad para suscribirse");
        } else if (conta == 18 && mesn+1 >= mes+1 && dayn >= day) {
            JOptionPane.showMessageDialog(this, "Usted es mayor de edad \nFecha Cumple"+yearn+"-"+mesn+"-"+dayn+
                    "\n Fecha actual"+year+"-"+mes+"-"+day);
        } else if (conta == 18 && mesn+1 >= mes+1 && dayn < day) {
            JOptionPane.showMessageDialog(this, "Debe ser mayor de edad para suscribirse");
        } else if (conta  == 18 && mesn+1 < mes+1) {
            JOptionPane.showMessageDialog(this, "Debe ser mayor de edad para suscribirse");
        }
        else {
            FrmLogin login = new FrmLogin();
            login.dispose();
            FrmSuscripcion suscripcion = new FrmSuscripcion();
            suscripcion.show();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dateNacimiento = new com.toedter.calendar.JDateChooser();
        btnContinuar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        dateNacimiento.setDateFormatString("yyyy-MM-dd");

        btnContinuar.setText("Continuar");
        btnContinuar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnContinuarMouseClicked(evt);
            }
        });

        jLabel1.setText("Ingrese su fecha de nacimiento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(btnContinuar)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(dateNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnContinuar)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinuarMouseClicked
        calcularEdad();
    }//GEN-LAST:event_btnContinuarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private com.toedter.calendar.JDateChooser dateNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
