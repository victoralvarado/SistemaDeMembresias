package com.vistas;

import com.dao.DaoProducto;
import com.modelo.Producto;
import com.utilidades.CustomImageIcon;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmBuscarLicores
 * Fecha: 25-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmBuscarLicores extends javax.swing.JInternalFrame {
    DaoProducto daop = new DaoProducto();
    Producto prod = new Producto();
    FrmPnlProducto panel = new FrmPnlProducto();
    public FrmBuscarLicores() {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        mostrar();
    }
    public void mostrar(){
        try {
            String[] Columnas = {"Código", "Categoria", "Nombre", "Descripcion", "Marca", "Stock",
                 "Precio Venta", "Fecha", "Imagen"};
            Object[] datos = new Object[9];
            tblProducto.getTableHeader().setReorderingAllowed(false) ;
            DefaultTableModel tabla = new DefaultTableModel(null, Columnas) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }

                @Override //Redefinimos el método getColumnClass
                public Class getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return Object.class;
                        case 1:
                            return Object.class;
                        case 2:
                            return Object.class;
                        case 4:
                            return Object.class;
                        case 5:
                            return Object.class;
                        case 6:
                            return Object.class;
                        case 7:
                            return Object.class;
                        case 8:
                            return ImageIcon.class;
                        default:
                            return Object.class;
                    }
                }
            };
            
            List lst;
            lst = daop.mostrarProducto();
             
            for (int i = 0; i < lst.size(); i++) {
                prod = (Producto) lst.get(i);
                String id = String.valueOf(prod.getIdProducto());
                datos[0] = prod.getIdProducto();
                datos[1] = prod.getIdCategoria();
                datos[2] = prod.getNombre();
                datos[3] = prod.getDescripcion();
                datos[4] = prod.getIdMarca();
                datos[5] = prod.getStock();
                datos[6] = prod.getPrecioVenta();
                datos[7] = prod.getFecha();
                CustomImageIcon imagen = daop.getImagen(Integer.parseInt(id));
                datos[8] = imagen;
                tabla.addRow(datos);
            }
            this.tblProducto.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos del producto " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void llenarTabla() {
        int fila = this.tblProducto.getSelectedRow();
        if (fila > -1) {
            String id = tblProducto.getValueAt(fila, 0).toString();
            String categoria = tblProducto.getValueAt(fila, 1).toString();
            String nombre = tblProducto.getValueAt(fila, 2).toString();
            String tooltip = tblProducto.getValueAt(fila, 3).toString();
            Double precio = Double.parseDouble(this.tblProducto.getValueAt(fila, 6).toString());
            panel.cargarProd(id, tooltip, nombre, precio);
            JOptionPane.showOptionDialog(null, panel,categoria, JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, 
                    null, new Object[]{}, null);
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 250, 250)));

        panelContenedor.setBackground(new java.awt.Color(250, 250, 250));
        panelContenedor.setBorder(null);

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProducto.setRowHeight(150);
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducto);

        jLabel1.setText("Buscar");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Categoria");

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addGap(147, 147, 147)
                        .addComponent(jLabel1))
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(987, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContenedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tblProductoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JTable tblProducto;
    // End of variables declaration//GEN-END:variables

}
