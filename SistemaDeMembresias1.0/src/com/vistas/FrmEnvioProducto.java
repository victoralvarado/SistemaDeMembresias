package com.vistas;

import com.dao.DaoCobertura;
import com.dao.DaoEnvioProducto;
import com.dao.DaoPersonaExterna;
import com.dao.DaoProducto;
import com.dao.DaoSuscriptor;
import com.modelo.EnvioProducto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmEnvioProducto
 * Fecha: 16-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmEnvioProducto extends javax.swing.JInternalFrame {
    DaoEnvioProducto envio = new DaoEnvioProducto();
    DaoProducto daop = new DaoProducto();
    DaoSuscriptor daos = new DaoSuscriptor();
    DaoCobertura daoC= new DaoCobertura();
    DaoPersonaExterna daoper = new DaoPersonaExterna();
    EnvioProducto enp = new EnvioProducto();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    Date FechaActual = new Date();
    public FrmEnvioProducto() {
        initComponents();
        dateFechaEnvio.setDate(FechaActual);
        mostrar();
    }
    
    public void mostrar() {
        try {
              String[] Columnas = {"Codigo", "Suscriptor", "idPersonaExterna","fecha",
                  "idProducto","Detalle Envio","Estado","IdCobertura"};
              Object[] datos = new Object[8];
              DefaultTableModel tabla = new DefaultTableModel(null, Columnas) {
                  @Override
                  public boolean isCellEditable(int row, int col) {
                      return false;
                  }
              };

              List lst;
              lst = envio.mostrarEnvioProducto();
              for (int i = 0; i < lst.size(); i++) {
                   enp = (EnvioProducto) lst.get(i);
                  datos[0] = enp.getIdEnvio();
                  datos[1] = enp.getIdSuscriptor();
                  datos[2] = enp.getIdPersonaExterna();
                  datos[3] = enp.getFechaEnvio();
                  datos[4] = enp.getIdProducto();
                  datos[5] = enp.getDetalleEnvio();
                  switch (enp.getEstado()) {
                      case 1:
                          datos[6] = "Procesando";
                          break;
                      case 2:
                          datos[6] = "Procesado";
                          break;
                      default:
                          datos[6] = "Entregado";
                          break;
                  }
                  datos[7] = enp.getIdCobertura();
                  tabla.addRow(datos);
              }
              this.tblEnvioP.setModel(tabla);
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Error al mostrar datos " + e,
                      "Error", JOptionPane.ERROR_MESSAGE);
          }
    }
    
    public void modificarFechaEstado() {
        try {
            enp.setFechaEnvio(formatoFecha.format(this.dateFechaEnvio.getDate()));
            enp.setEstado(comboEstadoEnvio.getSelectedIndex());
            enp.setIdEnvio(Integer.parseInt(this.txtCodigoEnvio.getText()));
            envio.modificarFechaEstado(enp);
            mostrar();
            
        } catch (Exception e) {
            System.err.println("Error en modificar fecha");
        }
    }
    
    public void limpiar() {
        txtCodigoEnvio.setText("");
        comboEstadoEnvio.setSelectedIndex(0);
        dateFechaEnvio.setDate(FechaActual);
        tblEnvioP.clearSelection();
    }
    
    public boolean validar() {
        boolean val;
        if (txtCodigoEnvio.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla",
                      "Validacion", JOptionPane.WARNING_MESSAGE);
            val = true;
        } else if (comboEstadoEnvio.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un estado valido",
                      "Validacion", JOptionPane.WARNING_MESSAGE);
            comboEstadoEnvio.requestFocus();
            val = true;
        }else {
            val = false;
        }
        return val;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEnvioP = new javax.swing.JTable();
        comboEstadoEnvio = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        dateFechaEnvio = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnProcesarEnvio = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtCodigoEnvio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        tblEnvioP.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEnvioP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEnvioPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEnvioP);

        comboEstadoEnvio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione --", "Procesando", "Procesado", "Entregado" }));

        jLabel1.setText("TABLA ENVIO DE PRODUCTOS");

        dateFechaEnvio.setDateFormatString("yyyy-MM-dd");

        jLabel2.setText("Seleccionar fecha de envio");

        jLabel3.setText("Estado");

        btnProcesarEnvio.setText("Procesar Envio");
        btnProcesarEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarEnvioActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtCodigoEnvio.setEnabled(false);

        jLabel5.setText("Codigo Envio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(332, 332, 332))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel5)))
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFechaEnvio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(45, 45, 45))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel3))
                            .addComponent(comboEstadoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(btnProcesarEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnCancelar)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addComponent(comboEstadoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProcesarEnvio)
                    .addComponent(btnCancelar))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcesarEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarEnvioActionPerformed
        if (!validar()) {
            modificarFechaEstado();
        }
    }//GEN-LAST:event_btnProcesarEnvioActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tblEnvioPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEnvioPMouseClicked
        int fila = this.tblEnvioP.getSelectedRow();
        if (fila > -1) {
            this.txtCodigoEnvio.setText(String.valueOf(this.tblEnvioP.getValueAt(fila, 0)));
            this.comboEstadoEnvio.setSelectedItem(String.valueOf(this.tblEnvioP.getValueAt(fila, 6)));
        }
    }//GEN-LAST:event_tblEnvioPMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnProcesarEnvio;
    private javax.swing.JComboBox<String> comboEstadoEnvio;
    private com.toedter.calendar.JDateChooser dateFechaEnvio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEnvioP;
    private javax.swing.JTextField txtCodigoEnvio;
    // End of variables declaration//GEN-END:variables

}
