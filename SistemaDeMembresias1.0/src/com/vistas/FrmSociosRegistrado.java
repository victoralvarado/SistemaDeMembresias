/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vistas;

import com.conexion.Conexion;
import com.dao.DaoSuscriptor;
import com.dao.DaoTipoSucriptor;
import com.modelo.Suscriptor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Nombre de la clase: FrmSocioRegistro
 * Fecha: 18-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Andrea Rosales
 */
public class FrmSociosRegistrado extends javax.swing.JInternalFrame {

    DaoSuscriptor daoS = new DaoSuscriptor();
    Suscriptor sus = new Suscriptor();
    DaoTipoSucriptor daoT = new DaoTipoSucriptor();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    Date fechaActual = new Date();
    JasperReport reporte;
    Conexion con = new Conexion();
    public FrmSociosRegistrado() {
        initComponents();
        mostrar();
        dateFechaActual.setDate(fechaActual);
    }

    public void mostrar()
    {
        DefaultTableModel tabla;
        String encabezados[] = {"ID","Nombre","Apellido","E-mail","Teléfono","Direccion","Fecha Nac.","Genero","Tipo Suc.","Tiempo Suc.","Total compras","Fecha"};
        tabla=new DefaultTableModel(null,encabezados);
        Object datos[] = new Object[12];
        try
        {
            List lista;
            lista = daoS.mostrarSuscriptor();
            for(int i=0;i<lista.size();i++)
            {
                sus=(Suscriptor)lista.get(i);
                datos[0]=sus.getIdSuscriptor();
                datos[1]=sus.getNombre();
                datos[2]=sus.getApellido();
                datos[3]=sus.getEmail();
                datos[4]=sus.getTelefono();
                datos[5]=sus.getDireccion();
                datos[6]=sus.getFechaNacimiento();
                datos[7]=sus.getGenero();
                datos[8]=daoT.getTipoSus(sus.getTipoSuscriptor()).getNombre();
                datos[9]=sus.getTiempoSus();
                datos[10]=sus.getTotalCompra();
                datos[11]=sus.getFecha();
                tabla.addRow(datos);
            }
            this.tblSuscriptor.setModel(tabla);
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al mostrar en formulario");
            
        }
    }
      public static Date parseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSuscriptor = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        dateFechaActual = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        btnSuscripcion = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("SOCIOS REGISTRADOS");

        tblSuscriptor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblSuscriptor);

        jLabel2.setText("Fecha: ");

        dateFechaActual.setDateFormatString("yyyy-MM-dd");

        jButton1.setText("Generar reporte diario");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        btnSuscripcion.setText("Reporte Suscripcion");
        btnSuscripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuscripcionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(jLabel1)
                        .addGap(0, 274, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 578, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSuscripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(dateFechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuscripcion)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try
        {
            con.conectar();
            Map parametros = new HashMap();
            parametros.put("fech", formatoFecha.format(dateFechaActual.getDate()));
            reporte = JasperCompileManager.compileReport("src/com/reportes/reporteSociosDiario.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(reporte, parametros, con.getCon());
            JasperViewer.viewReport(jp,false);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnSuscripcionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuscripcionMouseClicked
        try {
            con.conectar();
            Map parametros = new HashMap();
            parametros.put("fech", formatoFecha.format(dateFechaActual.getDate()));
            reporte = JasperCompileManager.compileReport("src/com/reportes/reporteTipoSuscriptor.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(reporte, null, con.getCon());
            JasperViewer.viewReport(jp,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuscripcionMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuscripcion;
    private com.toedter.calendar.JDateChooser dateFechaActual;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSuscriptor;
    // End of variables declaration//GEN-END:variables
}
