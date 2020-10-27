package com.vistas;

import com.vistas.*;
import com.dao.DaoProducto;
import com.modelo.Producto;
import com.utilidades.CustomImageIcon;
import com.utilidades.CustomImageIcon;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmVenta
 Fecha: 17-10-2020
 Versión: 1.0
 CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmVenta extends javax.swing.JInternalFrame {
    FileInputStream fis;
    int longitudBytes;
    DaoProducto daop = new DaoProducto();
    Producto prod = new Producto();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagen","jpg","png","jpeg");
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
    Date FechaActual = new Date();
    public FrmVenta() {
        initComponents();
        mostrar();
    }
    
    public void mostrar(){
        try {
            String[] Columnas = {"Código", "Categoria", "Nombre", "Descripcion", "Marca", "Stock",
                "Precio Compra", "Precio Venta", "Fecha"};
            Object[] datos = new Object[9];
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
                datos[1] = prod.getIdCategoria();
                datos[2] = prod.getNombre();
                datos[3] = prod.getDescripcion();
                datos[4] = prod.getIdMarca();
                datos[5] = prod.getStock();
                //datos[6] = prod.getPrecioCompra();
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
    
    public void insertar() throws Exception {
        try {
            prod.setIdProducto(Integer.parseInt(txtCodigoCliente.getText()));
            prod.setIdCategoria(Integer.parseInt(this.comboMetodoPagp.getSelectedItem().toString()));
            prod.setNombre(this.txtCodigoProducto.getText());
            prod.setStock(Integer.parseInt(this.spStock.getValue().toString()));
            prod.setPrecioVenta(Double.parseDouble(this.txtPrecioVenta.getText()));
            prod.setImagen(fis);
            daop.insertarProducto(prod);
            mostrar();
            limpiar();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos del producto " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiar() {
        txtCodigoCliente.setText("");
        txtCodigoProducto.setText("");
        txtPrecioVenta.setText("");
        comboMetodoPagp.setSelectedIndex(0);
        lblImagen.setIcon(null);
        spStock.setValue(0);
        txtCodigoCliente.requestFocus();
        tblProducto.clearSelection();
    }
    
    public void llenarTabla() {
        int fila = this.tblProducto.getSelectedRow();
        if (fila > -1) {
            String id = tblProducto.getValueAt(fila, 0).toString();
            this.txtCodigoCliente.setText(String.valueOf(this.tblProducto.getValueAt(fila, 0)));
            this.comboMetodoPagp.setSelectedItem(String.valueOf(this.tblProducto.getValueAt(fila, 1)));
            this.txtCodigoProducto.setText(String.valueOf(this.tblProducto.getValueAt(fila, 2)));
            //this.txtDescripcion.setText(String.valueOf(this.tblProducto.getValueAt(fila, 3)));
            //this.comboMarca.setSelectedItem(String.valueOf(this.tblProducto.getValueAt(fila, 4)));
            this.spStock.setValue(Integer.parseInt(this.tblProducto.getValueAt(fila, 5).toString()));
            //this.txtPrecioCompra.setText(String.valueOf(this.tblProducto.getValueAt(fila, 6)));
            this.txtPrecioVenta.setText(String.valueOf(this.tblProducto.getValueAt(fila, 7)));
            //this.dateFecha.setDate(parseFecha(String.valueOf(this.tblProducto.getValueAt(fila, 8))));
            try {
                CustomImageIcon imagen = daop.getImagen(Integer.parseInt(id));
                lblImagen.setIcon(imagen);
                lblImagen.updateUI();
            } catch (Exception ex) {
                Logger.getLogger(FrmVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static Date parseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
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

        pnlBackground = new javax.swing.JPanel();
        lblventa = new javax.swing.JLabel();
        lblMetodoPago = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        comboMetodoPagp = new javax.swing.JComboBox<>();
        spStock = new javax.swing.JSpinner();
        pDatosCliente = new javax.swing.JPanel();
        lblCodigoVenta = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        lblCodigoVenta2 = new javax.swing.JLabel();
        txtTipoSuscripcion = new javax.swing.JTextField();
        lblSuscripcion = new javax.swing.JLabel();
        btnBuscarC = new javax.swing.JButton();
        pDProducto = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        lblCodigoVenta1 = new javax.swing.JLabel();
        txtNombreP = new javax.swing.JTextField();
        txtUnidadesDisp = new javax.swing.JTextField();
        lblCodigoVenta3 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnBuscarP = new javax.swing.JButton();
        txtCodigoProducto = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregar1 = new javax.swing.JButton();
        btnEliminar1 = new javax.swing.JButton();
        txtCodigoProducto5 = new javax.swing.JTextField();
        lblSuscripcion1 = new javax.swing.JLabel();
        lblMetodoPago1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Venta");

        pnlBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));

        lblventa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblventa.setText("VENTA");

        lblMetodoPago.setText("Metodo pago");

        jLabel8.setText("Cantidad");

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

        comboMetodoPagp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", " " }));

        pDatosCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(45, 45, 45), 1, true), "Datos cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        pDatosCliente.setForeground(new java.awt.Color(0, 0, 0));

        lblCodigoVenta.setText("Codigo cliente");

        txtNombreCliente.setEnabled(false);

        lblCodigoVenta2.setText("Nombre");

        txtTipoSuscripcion.setEnabled(false);

        lblSuscripcion.setText("Suscripcion");

        btnBuscarC.setText("Buscar");
        btnBuscarC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarCMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pDatosClienteLayout = new javax.swing.GroupLayout(pDatosCliente);
        pDatosCliente.setLayout(pDatosClienteLayout);
        pDatosClienteLayout.setHorizontalGroup(
            pDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDatosClienteLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSuscripcion)
                    .addComponent(txtTipoSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodigoVenta)
                    .addComponent(lblCodigoVenta2)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pDatosClienteLayout.createSequentialGroup()
                        .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pDatosClienteLayout.setVerticalGroup(
            pDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDatosClienteLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblCodigoVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarC))
                .addGap(10, 10, 10)
                .addComponent(lblCodigoVenta2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSuscripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTipoSuscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pDProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(45, 45, 45), 1, true), "Datos producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel10.setText("Precio Venta");

        txtPrecioVenta.setEnabled(false);

        lblCodigoVenta1.setText("Nombre ");

        txtNombreP.setEnabled(false);

        txtUnidadesDisp.setEnabled(false);

        lblCodigoVenta3.setText("Unidades Dispo.");

        lblImagen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));

        jLabel7.setText("Imagen");

        btnBuscarP.setText("Buscar");
        btnBuscarP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarPMouseClicked(evt);
            }
        });

        lblNombre.setText("Codigo producto");

        javax.swing.GroupLayout pDProductoLayout = new javax.swing.GroupLayout(pDProducto);
        pDProducto.setLayout(pDProductoLayout);
        pDProductoLayout.setHorizontalGroup(
            pDProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarP, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(txtCodigoProducto)
                    .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(pDProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUnidadesDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodigoVenta1)
                    .addComponent(lblCodigoVenta3))
                .addGap(29, 29, 29)
                .addGroup(pDProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImagen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDProductoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(49, 49, 49)))
                .addGap(16, 16, 16))
        );
        pDProductoLayout.setVerticalGroup(
            pDProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDProductoLayout.createSequentialGroup()
                .addGroup(pDProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDProductoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pDProductoLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(pDProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pDProductoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pDProductoLayout.createSequentialGroup()
                                .addComponent(lblCodigoVenta1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pDProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarP))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCodigoVenta3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUnidadesDisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 31, Short.MAX_VALUE))
        );

        btnAgregar.setText("Agregar");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        btnEliminar.setText("Eliminar");

        btnAgregar1.setText("Generar venta");
        btnAgregar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregar1MouseClicked(evt);
            }
        });

        btnEliminar1.setText("Cancelar");

        txtCodigoProducto5.setEnabled(false);

        lblSuscripcion1.setText("Total a pagar:");

        lblMetodoPago1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblMetodoPago1.setText("VENTA");

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(pDatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGap(200, 200, 200)
                                        .addComponent(lblMetodoPago1)
                                        .addGap(609, 609, 609)
                                        .addComponent(lblventa))
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(62, 62, 62)
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(comboMetodoPagp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(spStock, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel8)
                                            .addComponent(lblMetodoPago)))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblSuscripcion1)
                        .addGap(18, 18, 18)
                        .addComponent(txtCodigoProducto5, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(lblventa)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                        .addComponent(lblMetodoPago1)
                        .addGap(18, 18, 18)))
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                            .addComponent(lblMetodoPago)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboMetodoPagp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel8)
                            .addGap(12, 12, 12)
                            .addComponent(spStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(btnAgregar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnEliminar))
                        .addComponent(pDProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar1)
                    .addComponent(btnEliminar1)
                    .addComponent(txtCodigoProducto5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSuscripcion1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregar1MouseClicked

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        try {
            insertar();
        } catch (Exception ex) {
            Logger.getLogger(FrmVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnBuscarPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarPMouseClicked
        limpiar();
    }//GEN-LAST:event_btnBuscarPMouseClicked

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tblProductoMouseClicked

    private void btnBuscarCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarCMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarCMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregar1;
    private javax.swing.JButton btnBuscarC;
    private javax.swing.JButton btnBuscarP;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JComboBox<String> comboMetodoPagp;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigoVenta;
    private javax.swing.JLabel lblCodigoVenta1;
    private javax.swing.JLabel lblCodigoVenta2;
    private javax.swing.JLabel lblCodigoVenta3;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblMetodoPago;
    private javax.swing.JLabel lblMetodoPago1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSuscripcion;
    private javax.swing.JLabel lblSuscripcion1;
    private javax.swing.JLabel lblventa;
    private javax.swing.JPanel pDProducto;
    private javax.swing.JPanel pDatosCliente;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JSpinner spStock;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtCodigoProducto5;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreP;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtTipoSuscripcion;
    private javax.swing.JTextField txtUnidadesDisp;
    // End of variables declaration//GEN-END:variables

}
