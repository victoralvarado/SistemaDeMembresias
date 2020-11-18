package com.vistas;

import com.dao.DaoCobertura;
import com.dao.DaoEnvioProducto;
import com.dao.DaoPersonaExterna;
import com.dao.DaoProducto;
import com.dao.DaoSuscriptor;
import com.modelo.EnvioProducto;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmPrincipalProcesoEnvio
 Fecha: 22-10-2020
 Versión: 1.0
 CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmPrincipalProcesoEnvio extends javax.swing.JInternalFrame {
    DaoEnvioProducto envio = new DaoEnvioProducto();
    DaoProducto daop = new DaoProducto();
    DaoSuscriptor daos = new DaoSuscriptor();
    DaoCobertura daoC= new DaoCobertura();
    DaoPersonaExterna daoper = new DaoPersonaExterna();
    EnvioProducto enp = new EnvioProducto();
    JLabel label = new JLabel();
    public FrmPrincipalProcesoEnvio() {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }
    
    public FrmPrincipalProcesoEnvio(int idSuscriptor) {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        label.setText(String.valueOf(idSuscriptor));
        mostrar();
    }
    
    public void mostrar() {
        try {
              String[] Columnas = {"Codigo", "Suscriptor", "idPersonaExterna","Fecha envio",
                  "idProducto","Detalle Envio","Estado","IdCobertura"};
              Object[] datos = new Object[8];
              DefaultTableModel tabla = new DefaultTableModel(null, Columnas) {
                  @Override
                  public boolean isCellEditable(int row, int col) {
                      return false;
                  }
              };

              List lst;
              lst = envio.mostrarEnvioProductoSus(Integer.parseInt(label.getText()));
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
              this.tblPedidos.setModel(tabla);
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Error al mostrar datos " + e,
                      "Error", JOptionPane.ERROR_MESSAGE);
          }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 250, 250)));
        setTitle("CARRITO");

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setAutoscrolls(true);
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblPedidos);

        jLabel1.setText("ESTADO DE PEDIDOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1178, Short.MAX_VALUE)
                .addGap(67, 67, 67))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(587, 587, 587)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1306, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPedidos;
    // End of variables declaration//GEN-END:variables

}
