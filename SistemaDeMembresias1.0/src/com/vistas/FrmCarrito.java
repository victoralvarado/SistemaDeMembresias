package com.vistas;

import com.conexion.Conexion;
import com.dao.DaoCarrito;
import com.dao.DaoProducto;
import com.modelo.Carrito;
import com.modelo.Producto;
import com.modelo.Suscriptor;
import com.utilidades.CustomImageIcon;
import com.utilidades.GenerarNumeross;
import com.utilidades.Render;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * Nombre de la clase: FrmCarrito
 * Fecha: 17/10/2020
 * CopyRight: EAG-2020
 * Versión: 1.0
 * @author Ever-AlexanderGarcia
 */

public class FrmCarrito extends javax.swing.JInternalFrame {
    JLabel lbl = new JLabel();
    DaoProducto daop = new DaoProducto();
    Producto prod = new Producto();
    Suscriptor sus = new Suscriptor();
    DaoCarrito daoc = new DaoCarrito();
    Carrito car = new Carrito();
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    double totalPagar = 0;
    public FrmCarrito() {
        initComponents();
    }
    
    public FrmCarrito(int idSuscriptor) {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        lbl.setText(String.valueOf(idSuscriptor));
        mostrarCarrito();
    }
    
    
    public void mostrarCarrito() {
        try {
            String[] Columnas = {"Código", "Codigo producto", "Nombre", "Precio","Cantidad","Total", "Imagen"};
            Object[] datos = new Object[7];
            tbCarrito.getTableHeader().setReorderingAllowed(false) ;
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
                            return Object.class;
                        case 4:
                            return Object.class;
                        case 5:
                            return Object.class;
                        case 6:
                            return ImageIcon.class;
                        default:
                            return Object.class;
                    }
                }
            };
            List lst;
            lst = daoc.mostrarCarritoId(Integer.parseInt(lbl.getText()));
             
            for (int i = 0; i < lst.size(); i++) {
                car = (Carrito) lst.get(i);
                String id = String.valueOf(car.getIdProducto());
                datos[0] = car.getIdCarrito();
                datos[1] = car.getIdProducto();
                datos[2] = daop.getProducto(car.getIdProducto()).getNombre();
                double precio = Double.parseDouble(daop.info("precioVenta", Integer.parseInt(id)));
                datos[3] = nf.format(precio);
                double cantidad = car.getCantidad();
                datos[4] = car.getCantidad();
                
                double total = Double.parseDouble(daop.info("precioVenta", Integer.parseInt(id)));
                datos[5] = nf.format(total * cantidad);
                totalPagar +=(total * cantidad);
                CustomImageIcon imagen = daop.getImagen(Integer.parseInt(id));
                datos[6] = imagen;
                tabla.addRow(datos);
            }
            this.tbCarrito.setModel(tabla);
            txtTotalPagar.setText(nf.format(totalPagar));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llenar tabla carrito " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCarrito = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnPagar = new javax.swing.JButton();
        btnEliminarTodo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Carrito");
        setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbCarrito.setBackground(new java.awt.Color(250, 250, 250));
        tbCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbCarrito.setRowHeight(155);
        tbCarrito.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbCarrito);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBackground(new java.awt.Color(250, 250, 250));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnPagar.setText("Pagar");
        btnPagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPagarMouseClicked(evt);
            }
        });

        btnEliminarTodo.setText("Eliminar Todo");
        btnEliminarTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarTodoMouseClicked(evt);
            }
        });

        btnEliminar.setText("Eliminar ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(250, 250, 250));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Total pagar ");

        txtTotalPagar.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(469, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarTodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarTodoMouseClicked
        try {
            if (tbCarrito.getRowCount() > 0) {
                int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar todos los productos del carrito?", "Eliminar", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.OK_OPTION) {
                    List lst;
                    lst = daoc.mostrarCarritoId(Integer.parseInt(lbl.getText()));
                    for (int i = 0; i < lst.size(); i++) {
                        car = (Carrito) lst.get(i);
                        int st = Integer.parseInt(daop.info("stock", car.getIdProducto()));
                        int cant = car.getCantidad();
                        prod.setStock(st + cant);
                        prod.setIdProducto(car.getIdProducto());
                        daop.modificarStock(prod);
                    }
                    daoc.eliminarTodo(Integer.parseInt(lbl.getText()));
                    mostrarCarrito();
                    totalPagar = 0;
                } else {
                    mostrarCarrito();
                }
            } else {
                JOptionPane.showMessageDialog(null, "El carrito se encuentra vacio",
                        "Carrito", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar productos del carrito " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarTodoMouseClicked

    private void btnPagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPagarMouseClicked
        if (tbCarrito.getRowCount() > 0) {
            //Codigo
        } else {
            JOptionPane.showMessageDialog(null, "El carrito se encuentra vacio",
                    "Carrito", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnPagarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarTodo;
    private javax.swing.JButton btnPagar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCarrito;
    private javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables
}
