
package com.vistas;

import com.conexion.Conexion;
import com.dao.DaoCategoria;
import com.dao.DaoMarca;
import com.dao.DaoProducto;
import com.modelo.Categoria;
import com.modelo.Marca;
import com.modelo.Producto;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Nombre de la clase: FrmProductos
 * Fecha: 18/11/2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Luna-
 */
public class FrmProductos extends javax.swing.JInternalFrame {

    DaoProducto daop=new DaoProducto();
    Producto prod=new Producto();
    DaoMarca daom=new DaoMarca();
    Marca mc=new Marca();
    DaoCategoria daoc=new DaoCategoria();
    Categoria cat=new Categoria();
    JasperReport reporte;
    Conexion con=new Conexion();

    public FrmProductos() {
        initComponents();
    }

    public void mostrar(){
        try {
            String[] Columnas = {"Código", "Categoria","Marca", "Tipo", "Nombre", "Descripcion", "Stock",
                "Precio Venta", "Fecha"};
            Object[] datos = new Object[9];
            tblProductos.getTableHeader().setReorderingAllowed(false) ;
            DefaultTableModel tabla = new DefaultTableModel(null, Columnas) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            
            List lst;
            lst = daop.mostrarProducto();
            
            for (int i = 0; i < lst.size(); i++) {
                prod = (Producto) lst.get(i);
                datos[0] = prod.getIdProducto();
                datos[1] = daoc.getCategoria(prod.getIdCategoria()).getCategoria();
                datos[2] = daom.getMarca(prod.getIdMarca()).getNombre();
                datos[3] = prod.getTipo();
                datos[4] = prod.getNombre();
                datos[5] = prod.getDescripcion();
                datos[6] = prod.getStock();
                datos[7] = prod.getPrecioVenta();
                datos[8] = prod.getFecha();
                tabla.addRow(datos);
            }
            this.tblProductos.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos del producto " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("PRODUCTOS");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblProductos);

        jButton1.setText("Reporte");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
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
        try {
            con.conectar();
            reporte= JasperCompileManager.compileReport("src/com/reportes/reporteProductos.jrxml");
            JasperPrint jp=JasperFillManager.fillReport(reporte, null,con.getCon());
            JasperViewer.viewReport(jp,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
