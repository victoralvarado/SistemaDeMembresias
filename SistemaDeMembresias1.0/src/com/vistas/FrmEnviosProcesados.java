package com.vistas;

import com.conexion.Conexion;
import com.dao.DaoCobertura;
import com.dao.DaoEnvioProducto;
import com.dao.DaoPersonaExterna;
import com.dao.DaoProducto;
import com.dao.DaoSuscriptor;
import com.modelo.EnvioProducto;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Nombre de la clase: FrmEnviosProcesados
 * Fecha: 20-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmEnviosProcesados extends javax.swing.JInternalFrame {
    DaoEnvioProducto envio = new DaoEnvioProducto();
    DaoProducto daop = new DaoProducto();
    DaoSuscriptor daos = new DaoSuscriptor();
    DaoCobertura daoC= new DaoCobertura();
    DaoPersonaExterna daoper = new DaoPersonaExterna();
    EnvioProducto enp = new EnvioProducto();
    Conexion con = new Conexion();
    JasperReport reporte;
    public FrmEnviosProcesados() {
        initComponents();
        mostrar();
    }
         public void mostrar() {
        try {
              String[] Columnas = {"Codigo", "Suscriptor", "PersonaExterna","fecha",
                  "Producto","Detalle Envio","Estado","IdCobertura"};
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
              this.tbEnvios.setModel(tabla);
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
        tbEnvios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnreporte = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        tbEnvios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbEnvios);

        jLabel1.setText("ENVIOS");

        btnreporte.setText("Generar reporte de envios procesados");
        btnreporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(373, 373, 373))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(313, Short.MAX_VALUE)
                .addComponent(btnreporte)
                .addGap(274, 274, 274))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(btnreporte)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnreporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreporteActionPerformed
        try
        {
            con.conectar();
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/com/reportes/reporteEnviosdeProducto.jasper"));
            JasperPrint jp = JasperFillManager.fillReport(reporte, null, con.getCon());
            JasperViewer.viewReport(jp,false);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnreporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnreporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbEnvios;
    // End of variables declaration//GEN-END:variables

}
