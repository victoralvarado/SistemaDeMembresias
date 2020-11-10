package com.vistas;

import com.dao.DaoCategoria;
import com.dao.DaoMarca;
import com.dao.DaoProducto;
import com.dao.DaoProductoInicio;
import com.modelo.Producto;
import com.modelo.ProductoInicio;
import com.utilidades.CustomImageIcon;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmProductoInicio
 * Fecha: 01-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmProductoInicio extends javax.swing.JInternalFrame {
    DaoProducto daop = new DaoProducto();
    Producto prod = new Producto();
    DaoMarca daom = new DaoMarca();
    DaoCategoria daoc = new DaoCategoria();
    DaoProductoInicio daopi = new DaoProductoInicio();
    ProductoInicio pi = new ProductoInicio();
    public FrmProductoInicio() {
        initComponents();
        mostrarProducto();
        mostrarPI();
        llenarPaneles();
        btnAgregar.setEnabled(false);
    }
    
    public void mostrarProducto(){
        try {
            String[] Columnas = {"Código", "Categoria","Marca", "Tipo", "Nombre", "Descripcion", "Stock",
                "Precio Venta", "Fecha"};
            Object[] datos = new Object[9];
            tblProducto.getTableHeader().setReorderingAllowed(false) ;
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
            this.tblProducto.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos del producto " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrarPI() {
        try {
            String[] Columnas = {"Codigo", "Producto"};
            Object[] datos = new Object[2];
            tblProducto.getTableHeader().setReorderingAllowed(false) ;
            DefaultTableModel tabla = new DefaultTableModel(null, Columnas) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            
            List lst;
            lst = daopi.mostrarPInicio();
            for (int i = 0; i < lst.size(); i++) {
                pi = (ProductoInicio) lst.get(i);
                datos[0] = pi.getCodigo();
                datos[1] = daop.getProducto(pi.getIdProducto()).getNombre();
                tabla.addRow(datos);
            }
            this.tblInicio.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void llenarTabla() {
        int fila = this.tblProducto.getSelectedRow();
        if (fila > -1) {
            //Habilitar botones
            btnAgregar.setEnabled(true);
            //deshabilitar boton y codigo
            String id = tblProducto.getValueAt(fila, 0).toString();
            this.txtCodigo.setText(String.valueOf(this.tblProducto.getValueAt(fila, 0)));
            this.txtNombre.setText(String.valueOf(this.tblProducto.getValueAt(fila, 4)));
            try {
                CustomImageIcon imagen = daop.getImagen(Integer.parseInt(id));
                lblImagen.setIcon(imagen);
                lblImagen.updateUI();
            } catch (Exception ex) {
                Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void agregar() {
        try {
            
                pi.setCodigo(comboPanel.getSelectedIndex());
                pi.setIdProducto(Integer.parseInt(this.txtCodigo.getText()));
                if (tblInicio.getRowCount() == 4) {
                     daopi.modificarPInicio(pi);
                } else {
                    daopi.insertarPInicio(pi);
                }
                if (this.comboPanel.getSelectedIndex() == 1) {
                    try {
                        CustomImageIcon imagen = daop.getImagen(Integer.parseInt(this.txtCodigo.getText()));
                        lbl1.setIcon(imagen);
                        lbl1.updateUI();
                    } catch (Exception ex) {
                        Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (this.comboPanel.getSelectedIndex() == 2) {
                    try {
                        CustomImageIcon imagen = daop.getImagen(Integer.parseInt(this.txtCodigo.getText()));
                        lbl2.setIcon(imagen);
                        lbl2.updateUI();
                    } catch (Exception ex) {
                        Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (this.comboPanel.getSelectedIndex() == 3) {
                    try {
                        CustomImageIcon imagen = daop.getImagen(Integer.parseInt(this.txtCodigo.getText()));
                        lbl3.setIcon(imagen);
                        lbl3.updateUI();
                    } catch (Exception ex) {
                        Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (this.comboPanel.getSelectedIndex() == 4) {
                    try {
                        CustomImageIcon imagen = daop.getImagen(Integer.parseInt(this.txtCodigo.getText()));
                        lbl4.setIcon(imagen);
                        lbl4.updateUI();
                    } catch (Exception ex) {
                        Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            limpiarP();
            mostrarPI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar " + e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void llenarPaneles() {
        try {
            CustomImageIcon imagen = daop.getImagen(daopi.getIdPrducto(1).getIdProducto());
            lbl1.setIcon(imagen);
            lbl1.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            CustomImageIcon imagen = daop.getImagen(daopi.getIdPrducto(2).getIdProducto());
            lbl2.setIcon(imagen);
            lbl2.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            CustomImageIcon imagen = daop.getImagen(daopi.getIdPrducto(3).getIdProducto());
            lbl3.setIcon(imagen);
            lbl3.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            CustomImageIcon imagen = daop.getImagen(daopi.getIdPrducto(4).getIdProducto());
            lbl4.setIcon(imagen);
            lbl4.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validar() {
        boolean val;
        if (comboPanel.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un panel",
                    "Validacion", JOptionPane.WARNING_MESSAGE);
            comboPanel.requestFocus();
            val = true;
        }else {
            val = false;
        }
        return val;
    }
    
    public void limpiarP() {
        txtCodigo.setText("");
        txtNombre.setText("");
        btnAgregar.setEnabled(false);
        lblImagen.setIcon(null);
        tblProducto.clearSelection();
        comboPanel.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInicio = new javax.swing.JTable();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        comboPanel = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

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
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducto);

        tblInicio.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblInicio);

        lbl2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));

        lbl3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));

        lbl1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setText("1");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setText("PRODUCTOS INICIO");

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setText("2");

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setText("3");

        lblImagen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));

        comboPanel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione --", "Panel 1", "Panel 2", "Panel 3", "Panel 4" }));

        jLabel1.setText("Panel");

        txtCodigo.setEnabled(false);

        jLabel2.setText("Nombre");

        txtNombre.setEnabled(false);

        jLabel3.setText("Codigo");

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

        jLabel8.setText("Buscar");

        lbl4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel9.setText("4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addComponent(jLabel1))
                                    .addComponent(comboPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel3)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addGap(90, 90, 90)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(420, 420, 420))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar)
                                .addGap(13, 13, 13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(56, 56, 56)))
                                .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(jLabel6)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(44, 44, 44)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addGap(35, 35, 35)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tblProductoMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        limpiarP();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        if (!validar()) {
            agregar();
        }
    }//GEN-LAST:event_btnAgregarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> comboPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTable tblInicio;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
