package com.vistas;

import com.dao.DaoTipoSucriptor;
import com.modelo.TipoSucriptor;
import com.utilidades.ValidarCampos;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmTipoSuscriptor
 * Fecha: 05-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmTipoSuscriptor extends javax.swing.JInternalFrame {
    TipoSucriptor tSus;
    DaoTipoSucriptor daoTs = new DaoTipoSucriptor();
    ValidarCampos vc = new ValidarCampos();
    public FrmTipoSuscriptor() {
        initComponents();
        mostrar();
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    
    public void mostrar()
   {
       DefaultTableModel tabla;
       String encabezados[] = {"ID","Nombre","Costo","Detalle"};
       tabla = new DefaultTableModel(null, encabezados);
       Object datos[] = new Object[4];
       try {
           List lista;
           lista = daoTs.mostrarTipoSus();
           for (int i = 0; i < lista.size(); i++) {
               tSus = (TipoSucriptor) lista.get(i);
               datos[0] = tSus.getTipoSuscriptor();
               datos[1] = tSus.getNombre();
               datos[2] = tSus.getCosto();
               datos[3] = tSus.getDetalle();
               tabla.addRow(datos);
           }
           this.tblTipo.setModel(tabla);

       } catch (Exception e)
       {
            JOptionPane.showMessageDialog(null, "Error al mostrar en formulario");
       }
   }
   
    public void limpiar()
    {
        this.txtId.setText("");
        this.txtNombre.setText("");
        this.txtDetalle.setText("");
        this.txtCosto.setText("");
    }
    
    public void llenarT()
    {
        int fila = this.tblTipo.getSelectedRow();
        this.txtId.setText(String.valueOf(this.tblTipo.getValueAt(fila, 0)));
        this.txtNombre.setText(String.valueOf(this.tblTipo.getValueAt(fila, 1)));
        this.txtCosto.setText(String.valueOf(this.tblTipo.getValueAt(fila, 2)));
        this.txtDetalle.setText(String.valueOf(this.tblTipo.getValueAt(fila, 3)));
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnAgregar.setEnabled(false);
        this.txtId.setEnabled(false);
    }
     public void insertar()
    {
        try
        {
            tSus.setTipoSuscriptor(Integer.parseInt(this.txtId.getText()));
            tSus.setNombre(this.txtNombre.getText());
            tSus.setCosto(Double.parseDouble(this.txtCosto.getText()));
            tSus.setDetalle(this.txtDetalle.getText());
            daoTs.insertarTipoSus(tSus);
            JOptionPane.showMessageDialog(null, "Tipo de suscripción insertada correctamente");
            limpiar();
            mostrar();
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Error al insertar en formulario");
        }
        
    }
      public void modificar()
    {
        try
        {
            tSus.setTipoSuscriptor(Integer.parseInt(this.txtId.getText()));
            tSus.setNombre(this.txtNombre.getText());
            tSus.setCosto(Double.parseDouble(this.txtCosto.getText()));
            tSus.setDetalle(this.txtDetalle.getText());
            int respuesta = JOptionPane.showConfirmDialog(this,"¿Desea modificar el tipo de suscripción?","Modificar",JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.OK_OPTION)
            {
                daoTs.modificarTipoSus(tSus);
                JOptionPane.showMessageDialog(null, "Datos modificados con exito");
                mostrar();
                limpiar();
                this.txtId.setEnabled(true);
            }
            else
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
            tSus.setTipoSuscriptor(Integer.parseInt(this.txtId.getText()));
            int respuesta = JOptionPane.showConfirmDialog(this,"¿Desea eliminar el tipo de suscripción?","Eliminar",JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.OK_OPTION)
            {
                daoTs.eliminarTipoSus(tSus);
                JOptionPane.showMessageDialog(null, "Datos eliminados con exito");
                mostrar();
                limpiar();
                this.txtId.setEnabled(true);
            }
            else
            {
                mostrar();
                limpiar();
            }
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Error al eliminar en formulario");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDetalle = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTipo = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setText("MANTENIMIENTO");

        jLabel2.setText("ID:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Detalle:");

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtDetalle.setColumns(20);
        txtDetalle.setRows(5);
        jScrollPane1.setViewportView(txtDetalle);

        jLabel5.setText("Costo:");

        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        tblTipo.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTipoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTipo);

        btnAgregar.setText("Insertar");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        vc.numbersOnly(evt);
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        vc.wordsOnly(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void tblTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoMouseClicked
        llenarT();
    }//GEN-LAST:event_tblTipoMouseClicked

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        insertar();
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        limpiar();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void txtCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTipo;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextArea txtDetalle;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
