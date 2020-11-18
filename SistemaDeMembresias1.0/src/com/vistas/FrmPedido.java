package com.vistas;

import com.conexion.Conexion;
import com.dao.DaoCarrito;
import com.dao.DaoCobertura;
import com.dao.DaoEnvioProducto;
import com.dao.DaoPersonaExterna;
import com.dao.DaoProducto;
import com.dao.DaoSuscriptor;
import com.dao.DaoUsuario;
import com.modelo.Carrito;
import com.modelo.Cobertura;
import com.modelo.EnvioProducto;
import com.modelo.PersonaExterna;
import com.modelo.Producto;
import com.utilidades.ComboItem;
import com.utilidades.CustomImageIcon;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Nombre de la clase: FrmPedido
 * Fecha: 14-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmPedido extends javax.swing.JInternalFrame {
    JLabel lbl = new JLabel();
    DaoCobertura daoco = new DaoCobertura();
    DaoCarrito daoc = new DaoCarrito();
    Carrito car = new Carrito();
    DaoProducto daop = new DaoProducto();
    DaoUsuario daous = new DaoUsuario();
    Producto prod = new Producto();
    DaoSuscriptor daos = new DaoSuscriptor();
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    double totalPagar = 0;
    int tiposuscriptor = 0;
    double des = 0;
    double caldes = 0;
    Conexion con = new Conexion();
    JasperReport reporte;
    EnvioProducto envio = new EnvioProducto();
    DaoPersonaExterna daope = new DaoPersonaExterna();
    DaoEnvioProducto daoe = new DaoEnvioProducto();
    PersonaExterna pe = new PersonaExterna();
    public FrmPedido() {
        initComponents();
        
    }
    
    public FrmPedido(int idSuscriptor) {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        lbl.setText(String.valueOf(idSuscriptor));
        radioSi.setSelected(true);
        try {
            cargarComboC(cmbDepartamento, (ArrayList<Cobertura>)daoco.mostrarDepartamento());
        } catch (Exception ex) {
            Logger.getLogger(FrmProductoSuscripcionOro.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbMunicipio.setEnabled(false);
        tiposuscriptor = daos.tipoSuscriptor(Integer.parseInt(lbl.getText()));
        switch (tiposuscriptor) {
            case 1:
                lblDescuento.setText("5%");
                des = 0.05;
                break;
            case 2:
                lblDescuento.setText("20%");
                des = 0.20;
                break;
            case 3:
                lblDescuento.setText("40%");
                des = 0.40;
                break;
            default:
                break;
        }
        
        mostrarCarrito();
    }
    
    private void cargarComboC(JComboBox combo, ArrayList<Cobertura> list) {
        combo.addItem("-- Seleccione --");
        list.forEach((item) -> {
            combo.addItem(new ComboItem(item.getIdCobertura(), item.getDepartamento()));
        });
    }
    
    private void cargarComboM(JComboBox combo, ArrayList<Cobertura> listM) {
        combo.addItem("-- Seleccione --");
        listM.forEach((item) -> {
            combo.addItem(new ComboItem(item.getIdCobertura(), item.getMunicipio()));
        });
    }
    
    public void mostrarCarrito() {
        try {
            String[] Columnas = {"Código", "Codigo producto", "Nombre", "Precio","Cantidad","Total"};
            Object[] datos = new Object[6];
            tblEnvio.getTableHeader().setReorderingAllowed(false) ;
            DefaultTableModel tabla = new DefaultTableModel(null, Columnas) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
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
                datos[3] = precio;
                double cantidad = car.getCantidad();
                datos[4] = car.getCantidad();
                
                double total = Double.parseDouble(daop.info("precioVenta", Integer.parseInt(id)));
                datos[5] = nf.format(total * cantidad);
                totalPagar +=(total * cantidad);
                
                tabla.addRow(datos);
            }
            this.tblEnvio.setModel(tabla);
            txtSubTotal.setText(nf.format(totalPagar));
            caldes = totalPagar * des;
            txtTotalPagar.setText(nf.format(totalPagar - caldes));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llenar tabla carrito " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrarMunicipio(String departamento) {
        try {
            cmbMunicipio.removeAllItems();
            cargarComboM(cmbMunicipio, (ArrayList<Cobertura>) daoco.mostrarMunicipio(departamento));
        } catch (Exception ex) {
            Logger.getLogger(FrmProductoSuscripcionOro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validarPE() {
        boolean val;
        if (radioSi.isSelected()) {
            if (txtNombre.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Ingrese un nombre", "VALIDACIÓN",
                        JOptionPane.WARNING_MESSAGE);
                txtNombre.requestFocus();
                val = true;
            } else if (txtDui.getText().trim().length() < 10) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de DUI", "VALIDACIÓN",
                        JOptionPane.WARNING_MESSAGE);
                txtDui.requestFocus();
                val = true;
            } else if (validarTel(txtTelefono.getText()) == false) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de telefono movil valido", "VALIDACIÓN",
                        JOptionPane.WARNING_MESSAGE);
                txtTelefono.requestFocus();
                val = true;
            } else {
                val = false;
            }
        } else if (cmbDepartamento.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un departamento valido", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            cmbDepartamento.requestFocus();
            val = true;
        } else if (cmbMunicipio.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un municipio valido", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            cmbMunicipio.requestFocus();
            val = true;
        } else if (txtDireccion.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese una direccion", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            txtDireccion.requestFocus();
            val = true;
        } else if (cmbBanco.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un banco", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            cmbBanco.requestFocus();
            val = true;
        } else if (cmbTipoTargeta.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo de targeta", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            cmbTipoTargeta.requestFocus();
            val = true;
        } else {
            val = false;
        }
        return val;
    }
    
    
    public boolean validarTel(String tel) {
        //Validacion de numero telefonico
        Pattern patternTel = Pattern.compile("^[6-7{1}]+([0-9]{3})(\\-[0-9]{4})$");
        Matcher mather = patternTel.matcher(tel);
        return mather.find();
    }
    
    
    public void insertarPedido() {
        try {
            if (tblEnvio.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No tiene productos en el carrito",
                        "Mensaje", JOptionPane.WARNING_MESSAGE);
            } else {
                pe.setNombre(this.txtNombre.getText());
                pe.setDui(this.txtDui.getText());
                pe.setTelefonoMovil(this.txtTelefono.getText());
                pe.setIdSuscriptor(Integer.parseInt(lbl.getText()));
                daope.insertarPersonaExterna(pe);

                int contaf = tblEnvio.getRowCount();
                int idCobertura = daoco.getIdCobertura(cmbMunicipio.getSelectedItem().toString());
                for (int i = 0; i < contaf; i++) {
                    int idP = (Integer.parseInt(this.tblEnvio.getValueAt(i, 1).toString()));
                    double precio = (Double.parseDouble(this.tblEnvio.getValueAt(i, 3).toString()));
                    int cantidad = (Integer.parseInt(this.tblEnvio.getValueAt(i, 4).toString()));
                    envio.setIdSuscriptor(Integer.parseInt(lbl.getText()));
                    envio.setIdPersonaExterna(daope.getIdPersonaExterna(Integer.parseInt(lbl.getText())));
                    envio.setFechaEnvio("En Proceso");
                    envio.setIdProducto(idP);
                    envio.setDetalleEnvio("Precio Unitario: " + precio + " Cantidad: " + cantidad + " " + txtDireccion.getText());
                    envio.setEstado(1);
                    envio.setIdCobertura(idCobertura);
                    daoe.insertarProducto(envio);
                }
                try {
                    con.conectar();
                    Map parametros = new HashMap();
                    parametros.put("id", Integer.parseInt(lbl.getText()));
                    parametros.put("subtotal", (totalPagar));
                    parametros.put("totalfinal", (totalPagar - caldes));
                    parametros.put("descuento", lblDescuento.getText());
                    reporte = JasperCompileManager.compileReport("src/com/reportes/reporteFacturaCarrito.jrxml");
                    JasperPrint jp = JasperFillManager.fillReport(reporte, parametros, con.getCon());
                    JasperViewer.viewReport(jp, false);

                } catch (NumberFormatException | JRException e) {
                    System.err.println(e.getMessage());
                }
                daoc.eliminarTodo(Integer.parseInt(lbl.getText()));
                mostrarCarrito();
            }

        } catch (Exception ex) {
            Logger.getLogger(FrmPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpiarCarrito() {
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEnvio = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRealizarPedido = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        radioSi = new javax.swing.JRadioButton();
        radioNo = new javax.swing.JRadioButton();
        txtDui = new javax.swing.JFormattedTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbDepartamento = new javax.swing.JComboBox<>();
        cmbMunicipio = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblinfoPE = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmbBanco = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cmbTipoTargeta = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblEnvio.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblEnvio);

        jLabel1.setText("TABLA DE PEDIDOS");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/help.png"))); // NOI18N
        jLabel2.setToolTipText("<html>En el menu de usuario en la opcion ver pedidos,<br>\npuede ver el proceso de su pedido</html>");

        btnRealizarPedido.setText("Realizar Pedido");
        btnRealizarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRealizarPedidoMouseClicked(evt);
            }
        });
        btnRealizarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarPedidoActionPerformed(evt);
            }
        });

        jLabel3.setText("Persona Externa");

        buttonGroup1.add(radioSi);
        radioSi.setText("SI");
        radioSi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioSiItemStateChanged(evt);
            }
        });

        buttonGroup1.add(radioNo);
        radioNo.setText("NO");
        radioNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioNoItemStateChanged(evt);
            }
        });

        try {
            txtDui.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre Completo");

        jLabel5.setText("DUI");

        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Telefono Movil");

        cmbDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepartamentoItemStateChanged(evt);
            }
        });

        jLabel7.setText("Direccion");

        jLabel8.setText("Departamento");

        jLabel9.setText("Municipio");

        lblinfoPE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/help.png"))); // NOI18N
        lblinfoPE.setToolTipText("<html>Modifique el datos de las cajas de texto<br>\nsi desea hacer un cambio</html>");
        lblinfoPE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblinfoPEMouseClicked(evt);
            }
        });

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane2.setViewportView(txtDireccion);

        jLabel11.setText("Especificar direccion segun Municipio");

        jLabel12.setText("Pago");

        cmbBanco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione --", "Agricola", "Cuscatlán ", "Davivienda ", "Hipotecario ", "Promerica", "BAC", "ABANK", "Azul" }));

        jLabel13.setText("Banco");

        cmbTipoTargeta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione --", "Debito", "Credito" }));

        jLabel14.setText("Tipo de tarjeta");

        jLabel15.setText("Numero de targeta");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(radioSi)
                                    .addGap(18, 18, 18)
                                    .addComponent(radioNo))))
                        .addGap(6, 6, 6)
                        .addComponent(lblinfoPE, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre)
                                    .addComponent(txtDui)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel5))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(jLabel6)))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel8)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel11)
                                .addGap(16, 16, 16)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jTextField2)
                                                    .addComponent(cmbBanco, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cmbTipoTargeta, javax.swing.GroupLayout.Alignment.LEADING, 0, 204, Short.MAX_VALUE))
                                                .addGap(98, 98, 98))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(142, 142, 142))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(15, 90, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13))
                                        .addGap(177, 177, 177))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(160, 160, 160))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(lblinfoPE, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioSi)
                    .addComponent(radioNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(7, 7, 7)
                                        .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTipoTargeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );

        txtSubTotal.setEnabled(false);

        jLabel10.setText("SubTotal:");

        jLabel16.setText("Descuento por membresia: ");

        lblDescuento.setText("0%");

        txtTotalPagar.setEnabled(false);

        jLabel18.setText("Total a pagar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(606, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(544, 544, 544))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRealizarPedido)
                        .addGap(592, 592, 592))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(lblDescuento)
                        .addGap(116, 116, 116)
                        .addComponent(jLabel18)
                        .addGap(27, 27, 27)
                        .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(24, 24, 24))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel16)
                    .addComponent(lblDescuento)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)))
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRealizarPedido)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRealizarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRealizarPedidoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void lblinfoPEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblinfoPEMouseClicked
        
    }//GEN-LAST:event_lblinfoPEMouseClicked

    private void radioSiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioSiItemStateChanged
        if (radioSi.isSelected()) {
            txtNombre.setEnabled(true);
            txtDui.setEnabled(true);
            txtTelefono.setEnabled(true);
        } else {
            txtNombre.setEnabled(false);
            txtDui.setEnabled(false);
            txtTelefono.setEnabled(false);
        }
    }//GEN-LAST:event_radioSiItemStateChanged

    private void radioNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioNoItemStateChanged
       if (radioNo.isSelected()) {
            txtNombre.setEnabled(false);
            txtDui.setEnabled(false);
            txtTelefono.setEnabled(false);
        } else {
            txtNombre.setEnabled(true);
            txtDui.setEnabled(true);
            txtTelefono.setEnabled(true);
        }
    }//GEN-LAST:event_radioNoItemStateChanged

    private void cmbDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDepartamentoItemStateChanged
       if (cmbDepartamento.getSelectedIndex() > 0) {
            cmbMunicipio.setEnabled(true);
            mostrarMunicipio(cmbDepartamento.getSelectedItem().toString());
        } else {
            cmbMunicipio.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDepartamentoItemStateChanged

    private void btnRealizarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRealizarPedidoMouseClicked
        if (!validarPE()) {
                insertarPedido();
        }
    }//GEN-LAST:event_btnRealizarPedidoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRealizarPedido;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbBanco;
    private javax.swing.JComboBox<String> cmbDepartamento;
    private javax.swing.JComboBox<String> cmbMunicipio;
    private javax.swing.JComboBox<String> cmbTipoTargeta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblinfoPE;
    private javax.swing.JRadioButton radioNo;
    private javax.swing.JRadioButton radioSi;
    private javax.swing.JTable tblEnvio;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JFormattedTextField txtDui;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JFormattedTextField txtTelefono;
    private javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables

}
