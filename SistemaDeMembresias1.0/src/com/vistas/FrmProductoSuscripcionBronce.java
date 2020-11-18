package com.vistas;

import com.dao.DaoBronce;
import com.dao.DaoCobertura;
import com.dao.DaoEnvioProducto;
import com.dao.DaoPersonaExterna;
import com.dao.DaoProducto;
import com.dao.DaoSuscriptor;
import com.modelo.Bronce;
import com.modelo.Cobertura;
import com.modelo.EnvioProducto;
import com.modelo.PersonaExterna;
import com.utilidades.ComboItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmProductoSuscripcionOro
 * Fecha: 13-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmProductoSuscripcionBronce extends javax.swing.JInternalFrame {
    DaoCobertura daoc = new DaoCobertura();
    DaoBronce daoB = new DaoBronce();
    Bronce br = new Bronce();
    DaoProducto daop = new DaoProducto();
    DaoPersonaExterna daope = new DaoPersonaExterna();
    DaoEnvioProducto daoe = new DaoEnvioProducto();
    PersonaExterna pe = new PersonaExterna();
    EnvioProducto envio = new EnvioProducto();
    DaoSuscriptor daos = new DaoSuscriptor();
    JLabel lblEmail = new JLabel();
    int idS = 0;
    int pedido = 0;
    public FrmProductoSuscripcionBronce() {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        try {
            cargarComboC(comboDepartamento, (ArrayList<Cobertura>)daoc.mostrarDepartamento());
        } catch (Exception ex) {
            Logger.getLogger(FrmProductoSuscripcionBronce.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboMunicipio.setEnabled(false);
    }
    
    public FrmProductoSuscripcionBronce(String email) {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        try {
            cargarComboC(comboDepartamento, (ArrayList<Cobertura>)daoc.mostrarDepartamento());
        } catch (Exception ex) {
            Logger.getLogger(FrmProductoSuscripcionBronce.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboMunicipio.setEnabled(false);
        radioSi.setSelected(true);
        lblEmail.setText(email);
        cargarBronce();
    }
    
    public void insertarEnvio() {
        try {
            pedido = 1;
            pe.setNombre(this.txtNombre.getText());
            pe.setDui(this.txtDui.getText());
            pe.setTelefonoMovil(this.txtTelefono.getText());
            pe.setIdSuscriptor(daos.getIdSuscriptor(lblEmail.getText()));
            daope.insertarPersonaExterna(pe);
            
            int contaf = tblBronce.getRowCount();
            int idCobertura = daoc.getIdCobertura(comboMunicipio.getSelectedItem().toString());
            for (int i = 0; i < contaf; i++) {
                int idP = (Integer.parseInt(this.tblBronce.getValueAt(i, 1).toString()));
                envio.setIdSuscriptor(daos.getIdSuscriptor(lblEmail.getText()));
                envio.setIdPersonaExterna(daope.getIdPersonaExterna(daos.getIdSuscriptor(lblEmail.getText())));
                envio.setFechaEnvio("En Proceso");
                envio.setIdProducto(idP);
                envio.setDetalleEnvio(txtDireccion.getText());
                envio.setEstado(1);
                envio.setIdCobertura(idCobertura);
                daoe.insertarProducto(envio);
            }
            FrmProductoSuscripcion sus = new FrmProductoSuscripcion(lblEmail.getText(), "Bronce");
            sus.tim.cancel();
            sus.tim = new Timer();
            sus.tim.schedule(sus.tareacerrar, 0);
        } catch (Exception e) {
            System.err.println("error: "+e);
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
        } else if (comboDepartamento.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un departamento valido", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            comboDepartamento.requestFocus();
            val = true;
        } else if (comboMunicipio.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un municipio valido", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            comboMunicipio.requestFocus();
            val = true;
        } else if (txtDireccion.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese una direccion", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            txtDireccion.requestFocus();
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
    
    public void cargarBronce() {
        try {
            String[] Columnas = {"ID","Cod. Producto", "Producto"};
            Object[] datos = new Object[3];
            DefaultTableModel tabla = new DefaultTableModel(null, Columnas) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };
            
            List lst;
            lst = daoB.mostrarSuscipcionBronce();
            for (int i = 0; i < lst.size(); i++) {
                br = (Bronce) lst.get(i);
                datos[0] = br.getIdBronce();
                datos[1] = daop.getProducto(br.getIdProducto()).getIdProducto();
                datos[2] = daop.getProducto(br.getIdProducto()).getNombre();
                tabla.addRow(datos);
            }
            this.tblBronce.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
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
    
    public void mostrarMunicipio(String departamento) {
        try {
            comboMunicipio.removeAllItems();
            cargarComboM(comboMunicipio, (ArrayList<Cobertura>) daoc.mostrarMunicipio(departamento));
        } catch (Exception ex) {
            Logger.getLogger(FrmProductoSuscripcionBronce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgSiNo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBronce = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        radioSi = new javax.swing.JRadioButton();
        radioNo = new javax.swing.JRadioButton();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDui = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        comboDepartamento = new javax.swing.JComboBox<>();
        comboMunicipio = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        btnFinalizarPedido = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblBronce.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblBronce);

        jLabel2.setText("Productos a entregar por suscripcion");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(335, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(280, 280, 280))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jLabel1.setText("SUSCRIPCION BRONCE");

        btnCerrar.setText("Cerrar");
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Formulario Envio");

        jLabel4.setText("Agregar persona externa");

        bgSiNo.add(radioSi);
        radioSi.setText("SI");
        radioSi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioSiItemStateChanged(evt);
            }
        });

        bgSiNo.add(radioNo);
        radioNo.setText("NO");
        radioNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radioNoItemStateChanged(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel5.setText("Nombre");

        jLabel6.setText("DUI");

        jLabel7.setText("Telefono Movil");

        comboDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboDepartamentoItemStateChanged(evt);
            }
        });

        jLabel8.setText("Departamento");

        jLabel9.setText("Municipio");

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane3.setViewportView(txtDireccion);

        jLabel10.setText("Especificar direccion de envio");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                        .addComponent(txtDui, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(comboDepartamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(52, 52, 52)))
                                    .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel10)))
                                .addGap(23, 23, 23))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(radioSi)
                                .addGap(18, 18, 18)
                                .addComponent(radioNo))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addComponent(jLabel3)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(93, 93, 93))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioSi)
                            .addComponent(radioNo))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(10, 10, 10)
                        .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)))
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(2, 2, 2)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnFinalizarPedido.setText("Realizar Pedido");
        btnFinalizarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFinalizarPedidoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(538, 538, 538)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinalizarPedido)
                        .addGap(347, 347, 347))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnCerrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFinalizarPedido))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(229, 229, 229))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        if (pedido == 0) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿esta seguro que desea cerrar el fomulario?,\nSi no realiza el pedido no se enviaran los productos", "Cerrando", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
                FrmProductoSuscripcion sus = new FrmProductoSuscripcion(lblEmail.getText(), "Bronce");
                sus.tim.cancel();
                sus.tim = new Timer();
                sus.tim.schedule(sus.tareacerrar, 0);
            } else {
                btnFinalizarPedido.requestFocus();
            }
        } else {
            FrmProductoSuscripcion sus = new FrmProductoSuscripcion(lblEmail.getText(), "Bronce");
            sus.tim.cancel();
            sus.tim = new Timer();
            sus.tim.schedule(sus.tareacerrar, 0);
        }
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void radioSiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioSiItemStateChanged
        if (radioSi.isSelected()) {
            txtNombre.setEnabled(true);
            txtDui.setEnabled(true);
            txtTelefono.setEnabled(true);
        }
    }//GEN-LAST:event_radioSiItemStateChanged

    private void radioNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radioNoItemStateChanged
        if (radioNo.isSelected()) {
            txtNombre.setEnabled(false);
            txtDui.setEnabled(false);
            txtTelefono.setEnabled(false);
        }
    }//GEN-LAST:event_radioNoItemStateChanged

    private void comboDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboDepartamentoItemStateChanged
        if (comboDepartamento.getSelectedIndex() > 0) {
            comboMunicipio.setEnabled(true);
            mostrarMunicipio(comboDepartamento.getSelectedItem().toString());
        } else {
            comboMunicipio.setEnabled(false);
        }
    }//GEN-LAST:event_comboDepartamentoItemStateChanged

    private void btnFinalizarPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarPedidoMouseClicked
        if (!validarPE()) {
                insertarEnvio();
        }
    }//GEN-LAST:event_btnFinalizarPedidoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgSiNo;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnFinalizarPedido;
    private javax.swing.JComboBox<ComboItem> comboDepartamento;
    private javax.swing.JComboBox<ComboItem> comboMunicipio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton radioNo;
    private javax.swing.JRadioButton radioSi;
    private javax.swing.JTable tblBronce;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JFormattedTextField txtDui;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

}
