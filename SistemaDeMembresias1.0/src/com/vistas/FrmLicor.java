package com.vistas;

import com.dao.DaoCategoria;
import com.dao.DaoLicor;
import com.dao.DaoMarca;
import com.dao.DaoProducto;
import com.dao.DaoVino;
import com.modelo.Licor;
import com.modelo.Producto;
import com.modelo.Vino;
import com.utilidades.CustomImageIcon;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Nombre de la clase: FrmVino
 * Fecha: 07-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmLicor extends javax.swing.JInternalFrame {
    DaoProducto daop = new DaoProducto();
    Producto prod = new Producto();
    Licor li = new Licor();
    DaoLicor daoli = new DaoLicor();
    DaoCategoria daoc = new DaoCategoria();
    DaoMarca daom = new DaoMarca();
    public FrmLicor() {
        initComponents();
        mostrar();
        mostrarV();
    }
    
    public void mostrar() {
        try {
            String[] Columnas = {"Código", "Categoria", "Nombre", "Descripcion", "Marca",
                 "Precio Venta"};
            Object[] datos = new Object[8];
            tblProductoLicor.getTableHeader().setReorderingAllowed(false) ;
            DefaultTableModel tabla = new DefaultTableModel(null, Columnas) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }

            };
            
            List lst;
            lst = daop.mostrarVL("Licor");
             
            for (int i = 0; i < lst.size(); i++) {
                prod = (Producto) lst.get(i);
                datos[0] = prod.getIdProducto();
                datos[1] = daoc.getCategoria(prod.getIdCategoria()).getCategoria();
                datos[2] = prod.getNombre();
                datos[3] = prod.getDescripcion();
                datos[4] = daom.getMarca(prod.getIdMarca()).getNombre();
                datos[5] = prod.getPrecioVenta();
                tabla.addRow(datos);
            }
            this.tblProductoLicor.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos del producto " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrarV() {
        try {
            String[] Columnas = {"Código","Codigo Producto","Nombre", "imagen"};
            Object[] datos = new Object[4];
            tblLicor.getTableHeader().setReorderingAllowed(false) ;
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
                        case 3:
                            return ImageIcon.class;
                        default:
                            return Object.class;
                    }
                }
            };
             List lst;
            lst = daoli.mostrarLicor();
             for (int i = 0; i < lst.size(); i++) {
                li = (Licor) lst.get(i);
                String id = String.valueOf(prod.getIdProducto());
                datos[0] = li.getIdLicor();
                datos[1] = li.getIdProducto();
                datos[2] = daop.getProducto(li.getIdProducto()).getNombre();
                CustomImageIcon imagen = daop.getImagen(li.getIdProducto());
                datos[3] = imagen;
                tabla.addRow(datos);
            }
            this.tblLicor.setModel(tabla);
            int[] anchos = {114, 114, 513,115};
                for(int i = 0;i< tblLicor.getColumnCount();i++) {
                    tblLicor.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                }
        } catch (Exception e) {
        }
    }
    
    public void agregar() {
        try {
            
                li.setIdLicor(comboPanel.getSelectedIndex());
                li.setIdProducto(Integer.parseInt(this.txtCodigoL.getText()));
                if (tblLicor.getRowCount() == 12) {
                     daoli.modificarLicor(li);
                } else {
                    daoli.insertarLicor(li);
                }
            limpiar();
            mostrarV();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar " + e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiar() {
        txtCodigoL.setText("");
        txtNombreLicor.setText("");
        comboPanel.setSelectedIndex(0);
        lblImagen.setIcon(null);
        tblProductoLicor.clearSelection();
    }
    
    public void llenarTabla() {
        int fila = this.tblProductoLicor.getSelectedRow();
        if (fila > -1) {
            //Habilitar botones
            btnAgregar.setEnabled(true);
            //deshabilitar boton y codigo
            String id = tblProductoLicor.getValueAt(fila, 0).toString();
            this.txtCodigoL.setText(String.valueOf(this.tblProductoLicor.getValueAt(fila, 0)));
            this.txtNombreLicor.setText(String.valueOf(this.tblProductoLicor.getValueAt(fila, 4)));
            try {
                CustomImageIcon imagen = daop.getImagen(Integer.parseInt(id));
                lblImagen.setIcon(imagen);
                lblImagen.updateUI();
            } catch (Exception ex) {
                Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLicor = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProductoLicor = new javax.swing.JTable();
        LICORES = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        comboPanel = new javax.swing.JComboBox<>();
        txtCodigoL = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        txtNombreLicor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        tblLicor.setModel(new javax.swing.table.DefaultTableModel(
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
        tblLicor.setRowHeight(115);
        jScrollPane1.setViewportView(tblLicor);

        tblProductoLicor.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProductoLicor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoLicorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProductoLicor);

        LICORES.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        LICORES.setText("LICORES");

        comboPanel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione --", "Panel 1", "Panel 2", "Panel 3", "Panel 4", "Panel 5", "Panel 6", "Panel 7", "Panel 8", "Panel 9", "Panel 10", "Panel 11", "Panel 12" }));

        txtCodigoL.setEnabled(false);

        jLabel2.setText("Panel");

        jLabel3.setText("Codigo Vino");

        lblImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txtNombreLicor.setEnabled(false);

        jLabel5.setText("Nombre Vino");

        btnAgregar.setText("Agregar");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(415, 415, 415)
                                .addComponent(LICORES))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(75, 75, 75)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtCodigoL, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNombreLicor, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(comboPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(103, 103, 103)
                                        .addComponent(jLabel2)))
                                .addGap(18, 18, 18)
                                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LICORES)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCodigoL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreLicor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar)
                                .addGap(15, 15, 15))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void tblProductoLicorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoLicorMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tblProductoLicorMouseClicked

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        agregar();
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        limpiar();
    }//GEN-LAST:event_btnCancelarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LICORES;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> comboPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTable tblLicor;
    private javax.swing.JTable tblProductoLicor;
    private javax.swing.JTextField txtCodigoL;
    private javax.swing.JTextField txtNombreLicor;
    // End of variables declaration//GEN-END:variables

}
