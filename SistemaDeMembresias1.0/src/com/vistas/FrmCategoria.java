
package com.vistas;

import com.dao.DaoCategoria;
import com.modelo.Categoria;
import com.utilidades.ValidarCampos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmCategoria
 * Fecha: 19/10/2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Luna-
 */
public class FrmCategoria extends javax.swing.JInternalFrame {
    
    DaoCategoria daocat=new DaoCategoria();
    Categoria cat=new Categoria();
    ValidarCampos val=new ValidarCampos();
    
    public FrmCategoria() {
        initComponents();
        mostrar();
    }

    public void mostrar()
    {
        String[] Columnas={"Codigo","Categoria"};
        Object[] datos=new Object[2];
        DefaultTableModel tabla=new DefaultTableModel(null, Columnas)
                {
                    @Override
                    public boolean isCellEditable(int row, int col)
                    {
                        return false;
                    }
                };
        try {
            List lst;
            lst=daocat.mostrarCategoria();
            for(int i=0;i<lst.size();i++)
            {
                cat=(Categoria) lst.get(i);
                datos[0]=cat.getIdCategoria();
                datos[1]=cat.getCategoria();
                tabla.addRow(datos);
            }
            this.tblCategoria.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos de la categoria " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiar()
    {
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnAgregar.setEnabled(true);
        txtCodigoCategoria.setEnabled(true);
        txtCodigoCategoria.setText("");
        comboTipo.setSelectedIndex(0);
        txtCategoria.setText("");
        tblCategoria.clearSelection();
        txtCodigoCategoria.requestFocus();
    }
    
    public void insertar() throws Exception
    {
        cat.setIdCategoria(Integer.parseInt(this.txtCodigoCategoria.getText()));
        cat.setCategoria(this.txtCategoria.getText());
        daocat.insertarCategoria(cat);
        mostrar();
        limpiar();
    }
    
    public void modificar()
    {
        try {
            cat.setIdCategoria(Integer.parseInt(this.txtCodigoCategoria.getText()));
            cat.setCategoria(this.txtCategoria.getText());
            
            int SiONo = JOptionPane.showConfirmDialog(this, "Desea modificar el coordinador",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            //Confirmacion para modificar
            if (SiONo == 0) 
            {
                daocat.modificarCategoria(cat);
                mostrar();
                limpiar();
            } 
            else 
            {
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el registro" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminar()
    {
        try {
            cat.setIdCategoria(Integer.parseInt(this.txtCodigoCategoria.getText()));
            int SiONo = JOptionPane.showConfirmDialog(this, "Desea eliminar la categoria",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            //Confirmacion para eliminar
            if (SiONo == 0) 
            {
                daocat.eliminarCategoria(cat);
                mostrar();
                limpiar();
            } 
            else
            {
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean validar() {
        boolean val;
        if (this.txtCodigoCategoria.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un código", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            txtCodigoCategoria.requestFocus();
            val = true;
        } else if (comboTipo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            txtCategoria.requestFocus();
            val = true;
        } else if (this.txtCategoria.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre", "VALIDACIÓN",
                    JOptionPane.WARNING_MESSAGE);
            txtCategoria.requestFocus();
            val = true;
        } else {
            val = false;
        }
        return val;
    }
    
    public void llenarTabla() 
    {
        int fila = this.tblCategoria.getSelectedRow();
        if (fila > -1) 
        {
            txtCodigoCategoria.setText(String.valueOf(this.tblCategoria.getValueAt(fila, 0)));
            comboTipo.getModel().setSelectedItem(String.valueOf(this.tblCategoria.getValueAt(fila, 1)));
            txtCategoria.setText(String.valueOf(this.tblCategoria.getValueAt(fila, 2)));
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnAgregar.setEnabled(false);
            txtCodigoCategoria.setEnabled(false);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoCategoria = new javax.swing.JTextField();
        txtCategoria = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategoria = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        comboTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setText("CATEGORIA");

        jLabel2.setText("Codigo categoria");

        jLabel3.setText("Categoria");

        txtCodigoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoCategoriaActionPerformed(evt);
            }
        });
        txtCodigoCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoCategoriaKeyTyped(evt);
            }
        });

        txtCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCategoriaKeyTyped(evt);
            }
        });

        tblCategoria.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategoria);

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

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione --", "Vino", "Licor" }));

        jLabel4.setText("Tipo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(16, 16, 16))
                            .addComponent(txtCodigoCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(51, 51, 51)))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCategoriaActionPerformed

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        if (this.btnAgregar.isEnabled())
        {
            try {
                if (!validar()) {
                    insertar();
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        if (this.btnModificar.isEnabled()) {
            try {
                if (!validar()) {
                    modificar();
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        if (this.btnEliminar.isEnabled()) {
            eliminar();
        }
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        limpiar();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void tblCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriaMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tblCategoriaMouseClicked

    private void txtCodigoCategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoCategoriaKeyTyped
        val.numbersOnly(evt);
    }//GEN-LAST:event_txtCodigoCategoriaKeyTyped

    private void txtCategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCategoriaKeyTyped
        val.wordsOnly(evt);
    }//GEN-LAST:event_txtCategoriaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCategoria;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtCodigoCategoria;
    // End of variables declaration//GEN-END:variables
}
