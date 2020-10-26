
package com.vistas;

import com.dao.DaoSuscriptor;
import com.dao.DaoTipoSucriptor;
import com.modelo.Suscriptor;
import com.modelo.TipoSucriptor;
import com.utilidades.ComboItem;
import com.utilidades.ValidarCampos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manza
 */
public class FrmSuscriptor extends javax.swing.JInternalFrame {

    Suscriptor sus;
    DaoSuscriptor daoS = new DaoSuscriptor();
    ValidarCampos vc = new ValidarCampos();
    DaoTipoSucriptor daoT = new DaoTipoSucriptor();
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    Date FechaActual = new Date();
    public FrmSuscriptor() throws Exception {
        initComponents();
        mostrar();
        cargarCombo(cmbTipo,daoT.mostrarTipoSus());
    }

    public void mostrar()
    {
        DefaultTableModel tabla;
        String encabezados[] = {"ID","Nombres","Apellidos","E-mail","Télefono", "Dirección","Genero","Tipo de suscriptor","Tiempo","Costo","Fecha de nac","Total","Fecha"};
        tabla=new DefaultTableModel(null,encabezados);
        Object datos[] = new Object[13];
        try
        {
            List lista;
            lista = daoS.mostrarSuscriptor();
            for(int i=0;i<lista.size();i++)
            {
                sus=(Suscriptor)lista.get(i);
                datos[0]=sus.getIdSuscriptor();
                datos[1]=sus.getNombre();
                datos[2]=sus.getApellido();
                datos[3]=sus.getEmail();
                datos[4]=sus.getTelefono();
                datos[5]=sus.getDireccion();
                datos[6]=sus.getGenero();
                datos[7]=daoT.getTipoSus(sus.getTipoSuscriptor()).getNombre();
                datos[8]=sus.getTiempoSus();
                datos[9]=sus.getCostoSus();
                datos[10]=sus.getFechaNacimiento();
                datos[11]=sus.getTotalCompra();
                datos[12]=sus.getFecha();
                tabla.addRow(datos);
            }
            this.tblSuscriptor.setModel(tabla);
            
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al mostrar en formulario");
            
        }
    }
    public void cargarCombo(JComboBox combo,List<TipoSucriptor>list)
    {
        for(TipoSucriptor item:list)
        {
           combo.addItem(new ComboItem(item.getTipoSuscriptor(), item.getNombre()));
        }
        
    }
    public void seleccionar()
    {
       
       if(this.cmbTipo.getSelectedIndex() == 0 && this.cmbTiempo.getSelectedIndex() == 0)   
       {
           this.txtCosto.setText("90.50");
       }
       else if (this.cmbTipo.getSelectedIndex() == 0 && this.cmbTiempo.getSelectedIndex() == 1)
       {
           this.txtCosto.setText("110.50");
       }
       else if (this.cmbTipo.getSelectedIndex() == 0 && this.cmbTiempo.getSelectedIndex() == 2)
       {
           this.txtCosto.setText("120.50");
       }
       else if (this.cmbTipo.getSelectedIndex() == 0 && this.cmbTiempo.getSelectedIndex() == 3)
       {
           this.txtCosto.setText("130.50");
       }
        else if (this.cmbTipo.getSelectedIndex() == 1 && this.cmbTiempo.getSelectedIndex() == 0)
       {
           this.txtCosto.setText("110.50");
       }
        else if (this.cmbTipo.getSelectedIndex() == 1 && this.cmbTiempo.getSelectedIndex() == 1)
       {
           this.txtCosto.setText("125.50");
       }
        else if (this.cmbTipo.getSelectedIndex() == 1 && this.cmbTiempo.getSelectedIndex() == 2)
       {
           this.txtCosto.setText("135.50");
       }
        else if (this.cmbTipo.getSelectedIndex() == 1 && this.cmbTiempo.getSelectedIndex() == 3)
       {
           this.txtCosto.setText("145.50");
       }
        else if (this.cmbTipo.getSelectedIndex() == 2 && this.cmbTiempo.getSelectedIndex() == 1)
       {
           this.txtCosto.setText("150.50");
       }
        else if (this.cmbTipo.getSelectedIndex() == 2 && this.cmbTiempo.getSelectedIndex() == 2)
       {
           this.txtCosto.setText("160.50");
       }
        else if (this.cmbTipo.getSelectedIndex() == 2 && this.cmbTiempo.getSelectedIndex() == 2)
       {
           this.txtCosto.setText("170.50");
       }
        else if (this.cmbTipo.getSelectedIndex() == 2 && this.cmbTiempo.getSelectedIndex() == 3)
       {
           this.txtCosto.setText("180.50");
       }
       
       
       
        
            
    }
    public void insertar()
    {
        try
        {
            sus.setIdSuscriptor(Integer.parseInt(this.txtId.getText()));
            sus.setNombre(this.txtNombre.getText());
            sus.setApellido(this.txtApellido.getText());
            sus.setEmail(this.txtEmail.getText());
            sus.setTelefono(this.txtTelefono.getText());
            sus.setDireccion(this.txtDireccion.getText());
            if(this.rdFemenino.isSelected())
            {
                sus.setGenero("Femenino");
            }
            else
            {
                sus.setGenero("Masculino");
            }
            sus.setTipoSuscriptor(this.cmbTipo.getSelectedIndex());
            String tipo = cmbTipo.getSelectedItem().toString();
            ComboItem item = new ComboItem();
            for(int i = 0; i <cmbTipo.getItemCount();i++)
            {
              if(tipo.equals(cmbTipo.getItemAt(i).toString()))
              {
                  item = cmbTipo.getModel().getElementAt(i);
              }
            }
            sus.setTipoSuscriptor(item.getValue());
            sus.setTiempoSus(this.cmbTiempo.getSelectedItem().toString());
            sus.setCostoSus(Double.parseDouble(this.txtCosto.getText()));
            sus.setFechaNacimiento(formato.format(this.DtNaci.getDate()));
            sus.setTotalCompra(Double.parseDouble(this.txtCompras.getText()));
            sus.setFecha(formato.format(this.DtFecha.getDate()));
            daoS.insertarSuscriptor(sus);
             JOptionPane.showMessageDialog(null, "Suscriptor insertado correctamente");
            mostrar();
            limpiar();
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al insertar en formulario"+ e.getMessage());
            //JOptionPane.showMessageDialog(null, "bu"+sus.getTiempoSus());
           
           
        }   
    }
    public void limpiar()
    {
        this.txtApellido.setText("");
        this.txtCompras.setText("");
        this.txtDireccion.setText("");
        this.txtEmail.setText("");
        this.txtId.setText("");
        this.txtNombre.setText("");
        this.txtTelefono.setText("");
        this.buttonGroup1.clearSelection();
        this.cmbTipo.setSelectedIndex(0);
        this.DtNaci.setDate(FechaActual);
        this.DtFecha.setDate(FechaActual);
        this.txtCosto.setText("");
        this.cmbTipo.setSelectedIndex(0);
    }
    public void llenarT()
    {
        int fila = this.tblSuscriptor.getSelectedRow();
        if(fila>-1)
        {
            this.txtId.setText(String.valueOf(this.tblSuscriptor.getValueAt(fila, 0)));
            this.txtNombre.setText(String.valueOf(this.tblSuscriptor.getValueAt(fila, 1)));
            this.txtApellido.setText(String.valueOf(this.tblSuscriptor.getValueAt(fila, 2)));
            this.txtEmail.setText(String.valueOf(this.tblSuscriptor.getValueAt(fila, 3)));
            this.txtTelefono.setText(String.valueOf(this.tblSuscriptor.getValueAt(fila, 4)));
            this.txtDireccion.setText(String.valueOf(this.tblSuscriptor.getValueAt(fila, 5)));
            String genero = String.valueOf(this.tblSuscriptor.getValueAt(fila, 6));
            if(genero.equalsIgnoreCase("Femenino"))
            {
                rdFemenino.setSelected(true);
            }
            else
            {
                rdMasculino.setSelected(true);
            }
            String sup = String.valueOf(this.tblSuscriptor.getValueAt(fila, 7));
            cmbTipo.getModel().setSelectedItem(sup);
            String tiem = String.valueOf(this.tblSuscriptor.getValueAt(fila, 8));
            cmbTiempo.getModel().setSelectedItem(tiem);
            this.txtCosto.setText(String.valueOf(this.tblSuscriptor.getValueAt(fila, 9)));
            this.DtNaci.setDate(parseFecha(String.valueOf(this.tblSuscriptor.getValueAt(fila, 10))));
            this.txtCompras.setText(String.valueOf(this.tblSuscriptor.getValueAt(fila,11 )));
            this.DtFecha.setDate(parseFecha(String.valueOf(this.tblSuscriptor.getValueAt(fila, 12))));
            }
    }
    
    public void modificar()
    {
        try
        {
            sus.setIdSuscriptor(Integer.parseInt(this.txtId.getText()));
            sus.setNombre(this.txtNombre.getText());
            sus.setApellido(this.txtApellido.getText());
            sus.setEmail(this.txtEmail.getText());
            sus.setTelefono(this.txtTelefono.getText());
            sus.setDireccion(this.txtDireccion.getText());
            if(this.rdFemenino.isSelected())
            {
                sus.setGenero("Femenino");
            }
            else
            {
                sus.setGenero("Masculino");
            }
            sus.setTipoSuscriptor(this.cmbTipo.getSelectedIndex());
            String tipo = cmbTipo.getSelectedItem().toString();
            ComboItem item = new ComboItem();
            for(int i = 0; i <cmbTipo.getItemCount();i++)
            {
              if(tipo.equals(cmbTipo.getItemAt(i).toString()))
              {
                  item = cmbTipo.getModel().getElementAt(i);
              }
            }
            sus.setTipoSuscriptor(item.getValue());
            sus.setFechaNacimiento(formato.format(this.DtNaci.getDate()));
            sus.setTotalCompra(Double.parseDouble(this.txtCompras.getText()));
            sus.setFecha(formato.format(this.DtFecha.getDate()));
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea modificar al suscriptor?",
            "Modificar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.OK_OPTION) {
            daoS.modificarSuscriptor(sus);
            JOptionPane.showMessageDialog(null, "Datos modificados con exito");
            mostrar();
            limpiar();
            } else 
            {
            mostrar();
            limpiar();
            }
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Error al modificar en formulario");
        }
    }
    public void eliminar()
    {
        try
        {
            sus.setIdSuscriptor(Integer.parseInt(this.txtId.getText()));
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el registro?","Eliminar",JOptionPane.YES_NO_OPTION);
        if(respuesta == JOptionPane.OK_OPTION)
        {
            daoS.eliminarSuscriptor(sus);
            JOptionPane.showMessageDialog(null, "Registro eliminado");
            mostrar();
            limpiar();
        }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtCompras = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        DtNaci = new com.toedter.calendar.JDateChooser();
        DtFecha = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        rdFemenino = new javax.swing.JRadioButton();
        rdMasculino = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        cmbTiempo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnInsertar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSuscriptor = new javax.swing.JTable();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("SUSCRIPCION");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 11, -1, -1));

        jLabel2.setText("ID:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 36, -1, -1));

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 56, 79, -1));

        jLabel3.setText("Nombres:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 216, -1));

        jLabel4.setText("Apellidos:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 216, -1));

        jLabel5.setText("E-mail:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 170, -1));

        jLabel6.setText("Teléfono:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1, -1));

        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 120, -1));

        jLabel7.setText("Fecha de nacimiento:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        jLabel8.setText("Dirección:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane1.setViewportView(txtDireccion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 290, 60));

        jLabel9.setText("Tipo de suscriptor:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        cmbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 210, -1));

        jLabel10.setText("Total a pagar:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        txtCompras.setText("0");
        txtCompras.setEnabled(false);
        jPanel1.add(txtCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 150, -1));

        jLabel11.setText("Fecha:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, -1, -1));

        DtNaci.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(DtNaci, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 140, -1));

        DtFecha.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(DtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 150, -1));

        jLabel12.setText("Genero:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, -1, -1));

        buttonGroup1.add(rdFemenino);
        rdFemenino.setText("Fem");
        jPanel1.add(rdFemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));

        buttonGroup1.add(rdMasculino);
        rdMasculino.setText("Masc");
        jPanel1.add(rdMasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, -1, -1));

        jLabel13.setText("Tiempo de suscripcion:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, -1, -1));

        cmbTiempo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 Meses", "6 Mese", "9 Meses", "12 Meses" }));
        cmbTiempo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTiempoItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 130, -1));

        jLabel14.setText("Costo de suscripcion:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, -1, -1));

        txtCosto.setEnabled(false);
        jPanel1.add(txtCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 130, -1));

        btnInsertar.setText("Insertar");
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
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

        tblSuscriptor.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSuscriptor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSuscriptorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSuscriptor);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addComponent(btnInsertar)
                .addGap(29, 29, 29)
                .addComponent(btnModificar)
                .addGap(45, 45, 45)
                .addComponent(btnEliminar)
                .addGap(72, 72, 72)
                .addComponent(btnCancelar)
                .addGap(255, 255, 255))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar)
                    .addComponent(btnInsertar)
                    .addComponent(btnCancelar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        vc.numbersOnly(evt);
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        vc.wordssOnly(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        vc.wordssOnly(evt);
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost

    }//GEN-LAST:event_txtEmailFocusLost

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        vc.arroba(evt, txtEmail);
    }//GEN-LAST:event_txtEmailKeyTyped

    private void cmbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoItemStateChanged

    }//GEN-LAST:event_cmbTipoItemStateChanged

    private void cmbTiempoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTiempoItemStateChanged
        seleccionar();
    }//GEN-LAST:event_cmbTiempoItemStateChanged

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        insertar();
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        limpiar();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void tblSuscriptorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuscriptorMouseClicked
        llenarT();
    }//GEN-LAST:event_tblSuscriptorMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DtFecha;
    private com.toedter.calendar.JDateChooser DtNaci;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnModificar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbTiempo;
    private javax.swing.JComboBox<ComboItem> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdFemenino;
    private javax.swing.JRadioButton rdMasculino;
    private javax.swing.JTable tblSuscriptor;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCompras;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
