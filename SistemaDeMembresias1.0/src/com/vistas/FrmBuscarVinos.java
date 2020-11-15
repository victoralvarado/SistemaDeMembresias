package com.vistas;

import com.dao.DaoCategoria;
import com.dao.DaoMarca;
import com.dao.DaoProducto;
import com.modelo.Producto;
import com.utilidades.CustomImageIcon;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmBuscarVinos
 * Fecha: 25-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmBuscarVinos extends javax.swing.JInternalFrame {
    DaoProducto daop = new DaoProducto();
    Producto prod = new Producto();
    DaoMarca daom = new DaoMarca();
    DaoCategoria daoc = new DaoCategoria();
    JLabel lbl = new JLabel();
    public Timer timer = new Timer();
    public FrmBuscarVinos() {
        initComponents();
    }
    
    public FrmBuscarVinos(int idSuscriptor) {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        mostrar();
        lbl.setText(String.valueOf(idSuscriptor));
    }
    
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
    public TimerTask tarea003 = new TimerTask() {
        @Override
        public void run() {
            adaptarTabla();
        }
    };
    public TimerTask tarea004 = new TimerTask() {
        @Override
        public void run() {
            adaptarTabla();
        }
    };
    public TimerTask tarea005 = new TimerTask() {
        @Override
        public void run() {
            adaptarTabla();
        }
    };
    public TimerTask tarea006 = new TimerTask() {
        @Override
        public void run() {
            adaptarTabla();
        }
    };
    
    public void adaptarTabla(){
        int w = tblProducto.getWidth();
            int div = (w - 150) / 6;
            int[] anchos = {div, div, div, div, div, div, 150};
            for (int i = 0; i < tblProducto.getColumnCount(); i++) {
                tblProducto.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
    }
    public void mostrar(){
        try {
            String[] Columnas = {"Código", "Categoria", "Nombre", "Descripcion", "Marca",
                 "Precio Venta", "Imagen"};
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
                            return ImageIcon.class;
                        default:
                            return Object.class;
                    }
                }
            };
            
            List lst;
            lst = daop.mostrarVL("Vino");
             
            for (int i = 0; i < lst.size(); i++) {
                prod = (Producto) lst.get(i);
                String id = String.valueOf(prod.getIdProducto());
                datos[0] = prod.getIdProducto();
                datos[1] = daoc.getCategoria(prod.getIdCategoria()).getCategoria();
                datos[2] = prod.getNombre();
                datos[3] = prod.getDescripcion();
                datos[4] = daom.getMarca(prod.getIdMarca()).getNombre();
                datos[5] = prod.getPrecioVenta();
                CustomImageIcon imagen = daop.getImagen(Integer.parseInt(id));
                datos[6] = imagen;
                tabla.addRow(datos);
            }
            this.tblProducto.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos del producto " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrarVino(String por){
        try {
            String[] Columnas = {"Código", "Categoria", "Nombre", "Descripcion", "Marca",
                 "Precio Venta", "Imagen"};
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
                            return ImageIcon.class;
                        default:
                            return Object.class;
                    }
                }
            };
            
            List lst;
            lst = daop.buscarV(por);
             
            for (int i = 0; i < lst.size(); i++) {
                prod = (Producto) lst.get(i);
                String id = String.valueOf(prod.getIdProducto());
                datos[0] = prod.getIdProducto();
                datos[1] = daoc.getCategoria(prod.getIdCategoria()).getCategoria();
                datos[2] = prod.getNombre();
                datos[3] = prod.getDescripcion();
                datos[4] = daom.getMarca(prod.getIdMarca()).getNombre();
                datos[5] = prod.getPrecioVenta();
                CustomImageIcon imagen = daop.getImagen(Integer.parseInt(id));
                datos[6] = imagen;
                tabla.addRow(datos);
            }
            this.tblProducto.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos del producto " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrarpp(Double inicio, Double fin){
        try {
            String[] Columnas = {"Código", "Categoria", "Nombre", "Descripcion", "Marca",
                 "Precio Venta", "Imagen"};
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
                            return ImageIcon.class;
                        default:
                            return Object.class;
                    }
                }
            };
            
            List lst;
            lst = daop.buscarVP(inicio, fin);
             
            for (int i = 0; i < lst.size(); i++) {
                prod = (Producto) lst.get(i);
                String id = String.valueOf(prod.getIdProducto());
                datos[0] = prod.getIdProducto();
                datos[1] = daoc.getCategoria(prod.getIdCategoria()).getCategoria();
                datos[2] = prod.getNombre();
                datos[3] = prod.getDescripcion();
                datos[4] = daom.getMarca(prod.getIdMarca()).getNombre();
                datos[5] = prod.getPrecioVenta();
                CustomImageIcon imagen = daop.getImagen(Integer.parseInt(id));
                datos[6] = imagen;
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
            Double precio = Double.parseDouble(this.tblProducto.getValueAt(fila, 5).toString());
            String stock = daop.info("stock", Integer.parseInt(id));
            FrmPnlProducto panel = new FrmPnlProducto(Integer.parseInt(lbl.getText()));
            panel.cargarProd(id, tooltip, nombre, precio,Integer.parseInt(stock));
            JOptionPane.showOptionDialog(null, panel,categoria, JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, 
                    null, new Object[]{}, null);
            tblProducto.clearSelection();
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarVino = new javax.swing.JTextField();
        spInicio = new javax.swing.JSpinner();
        spFin = new javax.swing.JSpinner();
        btnBuscarpp = new javax.swing.JButton();
        btnTodo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

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

        txtBuscarVino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarVinoKeyReleased(evt);
            }
        });

        spInicio.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        spFin.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        btnBuscarpp.setText("Buscar");
        btnBuscarpp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarppMouseClicked(evt);
            }
        });

        btnTodo.setText("Mostrar Todo");
        btnTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTodoMouseClicked(evt);
            }
        });

        jLabel2.setText("Filtrar por rango de precio");

        jLabel3.setText("Inicio");

        jLabel4.setText("Fin");

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1314, Short.MAX_VALUE))
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBuscarVino, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(spInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(spFin, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnBuscarpp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTodo))
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel1)
                        .addGap(207, 207, 207)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addGap(352, 352, 352)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenedorLayout.createSequentialGroup()
                        .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarVino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarpp)
                    .addComponent(btnTodo)
                    .addComponent(spInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
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

    private void txtBuscarVinoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarVinoKeyReleased
        String buscar = '%' + txtBuscarVino.getText() + '%';
        if (txtBuscarVino.getText().isEmpty()) {
            mostrar();
            timer.cancel();
            timer = new java.util.Timer();
            timer.schedule(tarea002, 0);
        } else {
            mostrarVino(buscar);
            timer.cancel();
            timer = new java.util.Timer();
            timer.schedule(tarea003, 0);
        }
    }//GEN-LAST:event_txtBuscarVinoKeyReleased

    private void btnBuscarppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarppMouseClicked
        Double i = Double.parseDouble(String.valueOf(spInicio.getValue()));
        Double f = Double.parseDouble(String.valueOf(spFin.getValue()));
        mostrarpp(i, f);
        timer.cancel();
        timer = new java.util.Timer();
        timer.schedule(tarea004, 0);
    }//GEN-LAST:event_btnBuscarppMouseClicked

    private void btnTodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTodoMouseClicked
        txtBuscarVino.setText("");
        spFin.setValue(0);
        spInicio.setValue(0);
        mostrar();
        timer.cancel();
        timer = new java.util.Timer();
        timer.schedule(tarea005, 0);
    }//GEN-LAST:event_btnTodoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarpp;
    private javax.swing.JButton btnTodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JSpinner spFin;
    private javax.swing.JSpinner spInicio;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtBuscarVino;
    // End of variables declaration//GEN-END:variables

}
