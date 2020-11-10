package com.vistas;

import com.dao.DaoCategoria;
import com.dao.DaoMarca;
import com.dao.DaoProducto;
import com.modelo.Categoria;
import com.modelo.Marca;
import com.modelo.Producto;
import com.utilidades.ComboItem;
import com.utilidades.CustomImageIcon;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmProducto
 * Fecha: 17-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmProducto extends javax.swing.JInternalFrame {
    FileInputStream fis;
    int longitudBytes;
    DaoProducto daop = new DaoProducto();
    Producto prod = new Producto();
    DaoMarca daom = new DaoMarca();
    DaoCategoria daoc = new DaoCategoria();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagen","jpg","png","jpeg");
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    Date FechaActual = new Date();
    public FrmProducto() {
        initComponents();
        dateFecha.setDate(FechaActual);
        mostrar();
        try {
            cargarComboM(comboMarca, (ArrayList<Marca>)daom.mostrarMarca());
            cargarComboC(comboCategoria, (ArrayList<Categoria>)daoc.mostrarCategoria());
        } catch (Exception ex) {
            Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        //deshabilitar botones
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    
    public void mostrar(){
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
    
    private void cargarComboM(JComboBox combo, ArrayList<Marca> list) {
        combo.addItem("-- Seleccione --");
        list.forEach((item) -> {
            combo.addItem(new ComboItem(item.getIdMarca(), item.getNombre()));
        });
    }
    
    private void cargarComboC(JComboBox combo, ArrayList<Categoria> list) {
        combo.addItem("-- Seleccione --");
        list.forEach((item) -> {
            combo.addItem(new ComboItem(item.getIdCategoria(), item.getCategoria()));
        });
    }
    
    public void eliminar() {
        try {
            prod.setIdProducto(Integer.parseInt(this.txtCodigo.getText()));
            int SiONo = JOptionPane.showConfirmDialog(this, "Desea eliminar al producto",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            //Confirmacion para eliminar
            if (SiONo == 0) {
                daop.eliminarProducto(prod);
                mostrar();
                limpiar();
                JOptionPane.showMessageDialog(null, "Datos eliminados correctamente",
                    "ELIMINAR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void insertar() throws Exception {
        try {
            prod.setNombre(this.txtNombre.getText());
            prod.setDescripcion(this.txtDescripcion.getText());
            prod.setStock(Integer.parseInt(this.spStock.getValue().toString()));
            prod.setPrecioVenta(Double.parseDouble(this.txtPrecioVenta.getText()));
            prod.setFecha(formatoFecha.format(this.dateFecha.getDate()));
            prod.setImagen(fis);
            prod.setTipo(this.comboTipo.getSelectedItem().toString());
            String marca = comboMarca.getSelectedItem().toString();
            ComboItem itemM = new ComboItem();

            for (int i = 1; i < comboMarca.getItemCount(); i++) {
                if (marca.equals(comboMarca.getItemAt(i).toString())) {
                    itemM = comboMarca.getModel().getElementAt(i);
                }
            }
            prod.setIdMarca(itemM.getValue());
            String ca = comboCategoria.getSelectedItem().toString();
            ComboItem itemC = new ComboItem();

            for (int i = 1; i < comboCategoria.getItemCount(); i++) {
                if (ca.equals(comboCategoria.getItemAt(i).toString())) {
                    itemC = comboCategoria.getModel().getElementAt(i);
                }
            }
            prod.setIdMarca(itemM.getValue());
            prod.setIdCategoria(itemC.getValue());
            daop.insertarProducto(prod);
            mostrar();
            limpiar();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos del producto " + e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void modificar() throws Exception {
        try {
            prod.setIdProducto(Integer.parseInt(this.txtCodigo.getText()));
            prod.setNombre(this.txtNombre.getText());
            prod.setDescripcion(this.txtDescripcion.getText());
            prod.setStock(Integer.parseInt(this.spStock.getValue().toString()));
            prod.setPrecioVenta(Double.parseDouble(this.txtPrecioVenta.getText()));
            prod.setFecha(formatoFecha.format(this.dateFecha.getDate()));
            if (fis != null) {
              prod.setImagen(fis);  
            }
            else {
              prod.setImagen(null);
            }
            prod.setTipo(this.comboTipo.getSelectedItem().toString());
            String marca = comboMarca.getSelectedItem().toString();
            ComboItem itemM = new ComboItem();

            for (int i = 1; i < comboMarca.getItemCount(); i++) {
                if (marca.equals(comboMarca.getItemAt(i).toString())) {
                    itemM = comboMarca.getModel().getElementAt(i);
                }
            }
            prod.setIdMarca(itemM.getValue());
            String ca = comboCategoria.getSelectedItem().toString();
            ComboItem itemC = new ComboItem();

            for (int i = 1; i < comboCategoria.getItemCount(); i++) {
                if (ca.equals(comboCategoria.getItemAt(i).toString())) {
                    itemC = comboCategoria.getModel().getElementAt(i);
                }
            }
            prod.setIdMarca(itemM.getValue());
            prod.setIdCategoria(itemC.getValue());
            int SiONo = JOptionPane.showConfirmDialog(this, "Desea modificar los datos",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            //Confirmacion para modificar
            if (SiONo == 0) {
                daop.modificarProducto(prod);
                mostrar();
                limpiar();
            } else {
                limpiar();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos del producto " + e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiar() {
        //Limpiar cajas de texto
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnAgregar.setEnabled(true);
        txtCodigo.setText("#");
        txtDescripcion.setText("");
        txtNombre.setText("");
        txtPrecioVenta.setText("");
        comboCategoria.setSelectedIndex(0);
        comboTipo.setSelectedIndex(0);
        dateFecha.setDate(FechaActual);
        lblImagen.setIcon(null);
        spStock.setValue(0);
        this.fis = null;
        tblProducto.clearSelection();
    }
    
    public void llenarTabla() {
        int fila = this.tblProducto.getSelectedRow();
        if (fila > -1) {
            this.fis = null;
            //Habilitar botones
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            //deshabilitar boton y codigo
            btnAgregar.setEnabled(false);
            String id = tblProducto.getValueAt(fila, 0).toString();
            this.txtCodigo.setText(String.valueOf(this.tblProducto.getValueAt(fila, 0)));
            this.comboCategoria.getModel().setSelectedItem(String.valueOf(this.tblProducto.getValueAt(fila, 1)));
            this.comboMarca.getModel().setSelectedItem(String.valueOf(this.tblProducto.getValueAt(fila, 2)));
            this.comboTipo.setSelectedItem(String.valueOf(this.tblProducto.getValueAt(fila, 3)));
            this.txtNombre.setText(String.valueOf(this.tblProducto.getValueAt(fila, 4)));
            this.txtDescripcion.setText(String.valueOf(this.tblProducto.getValueAt(fila, 5)));
            this.spStock.setValue(Integer.parseInt(this.tblProducto.getValueAt(fila, 6).toString()));
            this.txtPrecioVenta.setText(String.valueOf(this.tblProducto.getValueAt(fila, 7)));
            this.dateFecha.setDate(parseFecha(String.valueOf(this.tblProducto.getValueAt(fila, 8))));
            try {
                CustomImageIcon imagen = daop.getImagen(Integer.parseInt(id));
                lblImagen.setIcon(imagen);
                lblImagen.updateUI();
            } catch (Exception ex) {
                Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static Date parseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        comboCategoria = new javax.swing.JComboBox<>();
        lblImagen = new javax.swing.JLabel();
        spStock = new javax.swing.JSpinner();
        txtPrecioVenta = new javax.swing.JTextField();
        dateFecha = new com.toedter.calendar.JDateChooser();
        btnImagen = new javax.swing.JButton();
        comboMarca = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Productos");

        pnlBackground.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray, java.awt.Color.darkGray));

        jLabel1.setText("PRODUCTOS");

        jLabel2.setText("Codigo Producto");

        jLabel4.setText("Nombre");

        jLabel5.setText("Descripcion");

        jLabel6.setText("Categoria");

        jLabel7.setText("Imagen");

        jLabel8.setText("Stock");

        jLabel10.setText("Precio Venta");

        jLabel11.setText("Fecha");

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

        btnAgregar.setText("Agregar");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        lblImagen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));

        dateFecha.setDateFormatString("yyyy-MM-dd");
        dateFecha.setEnabled(false);

        btnImagen.setText("Seleccionar");
        btnImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImagenMouseClicked(evt);
            }
        });

        jLabel9.setText("Marca");

        txtCodigo.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCodigo.setText("#");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione --", "Vino", "Licor" }));

        jLabel12.setText("Tipo");

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(499, 499, 499)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(355, 355, 355)
                                .addComponent(btnAgregar)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCancelar)))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(47, 47, 47))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(91, 91, 91)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel8))
                                            .addComponent(spStock, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(122, 122, 122))
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(46, 46, 46)))
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(57, 57, 57)))))
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel10)))
                                        .addGap(175, 175, 175)
                                        .addComponent(jLabel11))
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(jLabel6))
                                            .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)))
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(227, 227, 227)
                .addComponent(jLabel7)
                .addGap(52, 52, 52))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(btnImagen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(txtCodigo)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(spStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar)
                            .addComponent(btnModificar)
                            .addComponent(btnEliminar)
                            .addComponent(btnCancelar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImagenMouseClicked
        try {
            JFileChooser se = new JFileChooser();
            se.setFileFilter(filter);
            
            int estado = se.showOpenDialog(null);
            if (estado == JFileChooser.APPROVE_OPTION) {
                fis = new FileInputStream(se.getSelectedFile());
                longitudBytes = (int) se.getSelectedFile().length();

                Image icono = ImageIO.read(se.getSelectedFile()).getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT);
                lblImagen.setIcon(new ImageIcon(icono));
                lblImagen.updateUI();

            }
            else {
                fis = new FileInputStream(se.getSelectedFile());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar la imagen " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnImagenMouseClicked

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        try {
            if (btnAgregar.isEnabled()) {
                insertar();
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tblProductoMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        limpiar();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        if (btnEliminar.isEnabled()) {
            eliminar();
        }
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        if (btnModificar.isEnabled()) {
            try {
                modificar();
            } catch (Exception ex) {
                Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnModificarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<ComboItem> comboCategoria;
    private javax.swing.JComboBox<ComboItem> comboMarca;
    private javax.swing.JComboBox<String> comboTipo;
    private com.toedter.calendar.JDateChooser dateFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JSpinner spStock;
    private javax.swing.JTable tblProducto;
    private javax.swing.JLabel txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioVenta;
    // End of variables declaration//GEN-END:variables

}
