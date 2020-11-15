package com.vistas;

import com.dao.DaoCarrito;
import com.dao.DaoProducto;
import com.modelo.Carrito;
import com.modelo.Producto;
import com.modelo.Suscriptor;
import com.utilidades.CustomImageIcon;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

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
    public Timer time = new Timer();
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
    public TimerTask tarea = new TimerTask() {
        @Override
        public void run() {
            adaptarTabla();
        }
    };
    
    public TimerTask tarea001 = new TimerTask() {
        @Override
        public void run() {
            adaptarTabla();
        }
    };
    
    public TimerTask tarea002 = new TimerTask() {
        @Override
        public void run() {
            adaptarTabla();
        }
    };
    
    public void adaptarTabla(){
        int w = tbCarrito.getWidth();
            int div = (w - 100) / 6;
            int[] anchos = {div, div, div, div, div, div, 100};
            for (int i = 0; i < tbCarrito.getColumnCount(); i++) {
                tbCarrito.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
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
        btnEliminarTodo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        setTitle("Carrito");

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
        tbCarrito.setDragEnabled(true);
        tbCarrito.setRowHeight(100);
        tbCarrito.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBackground(new java.awt.Color(250, 250, 250));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnEliminarTodo.setText("Eliminar Todo");
        btnEliminarTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarTodoMouseClicked(evt);
            }
        });

        btnEliminar.setText("Eliminar Producto Seleccionado");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarTodo)
                    .addComponent(btnEliminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(250, 250, 250));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Total pagar ");

        txtTotalPagar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        txtTotalPagar.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotalPagar.setEnabled(false);
        txtTotalPagar.setSelectedTextColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(467, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                    totalPagar = 0;
                    mostrarCarrito();
                    time.cancel();
                    time = new java.util.Timer();
                    time.schedule(tarea001, 0);
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

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = this.tbCarrito.getSelectedRow();
        if (fila > -1) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el "
                    + "producto seleccionado?", "Eliminar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                
                try {
                    int st = Integer.parseInt(daop.info("stock", (Integer.parseInt(this.tbCarrito.getValueAt(fila, 1).toString()))));
                    int cant = (Integer.parseInt(this.tbCarrito.getValueAt(fila, 4).toString()));
                    prod.setStock(st + cant);
                    prod.setIdProducto((Integer.parseInt(this.tbCarrito.getValueAt(fila, 1).toString())));
                    daop.modificarStock(prod);
                    JOptionPane.showMessageDialog(null, "Se eliminó el producto del carrito",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    car.setIdCarrito((Integer.parseInt(this.tbCarrito.getValueAt(fila, 0).toString())));
                    daoc.eliminarCarrito(car);
                    totalPagar = 0;
                    mostrarCarrito();
                    time.cancel();
                    time = new java.util.Timer();
                    time.schedule(tarea002, 0);
                } catch (Exception ex) {
                    Logger.getLogger(FrmCarrito.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el producto que desea eliminar",
                            "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarTodo;
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
