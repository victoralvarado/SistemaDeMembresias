package com.vistas;

import com.dao.DaoMarca;
import com.modelo.Marca;
import com.utilidades.ValidarCampos;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmMarca
 * Fecha: 01-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmMarca extends javax.swing.JInternalFrame {
    ValidarCampos vc=new ValidarCampos();
    Marca mar = new Marca();
    DaoMarca daoM = new DaoMarca();
    public FrmMarca() {
        initComponents();
        mostrar();
        //deshabilitar botones
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    public void mostrar()
    {
        DefaultTableModel tabla;
        String encabezados[] = {"Codigo","Nombre"};
        tabla=new DefaultTableModel(null,encabezados);
        Object datos[] = new Object[2];
        try
        {
            List lista;
            lista = daoM.mostrarMarca();
            for(int i=0;i<lista.size();i++)
            {
                mar=(Marca)lista.get(i);
                datos[0]=mar.getIdMarca();
                datos[1]=mar.getNombre();
                tabla.addRow(datos);
            }
            this.tblMarca.setModel(tabla);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al mostrar en formulario");
            
        }
    }
    public void limpiar()
    {
        //Limpiar cajas de texto
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnInsertar.setEnabled(true);
        txtCodigo.setEnabled(true);
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        tblMarca.clearSelection();
        txtCodigo.requestFocus();
    }
    public void llenarT()
    {
        //Habilitar botones
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            //deshabilitar boton y codigo
            btnInsertar.setEnabled(false);
            txtCodigo.setEditable(true);
        int fila = this.tblMarca.getSelectedRow();
        this.txtCodigo.setText(String.valueOf(this.tblMarca.getValueAt(fila, 0)));
        this.txtNombre.setText(String.valueOf(this.tblMarca.getValueAt(fila, 1)));
        this.txtCodigo.setEnabled(false);
    }
    public void insertar()
    {
        try
        {
            mar.setIdMarca(Integer.parseInt(this.txtCodigo.getText()));
            mar.setNombre(this.txtNombre.getText());
            daoM.insertarMarca(mar);
            JOptionPane.showMessageDialog(null, "Marca insertada correctamente");
            limpiar();
            mostrar();
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Error al insertar en formulario " +e.getMessage());
        }
        
    }
    public void modificar()
    {
        try
        {
            mar.setIdMarca(Integer.parseInt(this.txtCodigo.getText()));
            mar.setNombre(this.txtNombre.getText());
            int respuesta = JOptionPane.showConfirmDialog(this,"¿Desea modificar la marca?","Modificar",JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.OK_OPTION)
            {
                daoM.modificarMarca(mar);
                JOptionPane.showMessageDialog(null, "Datos modificados con exito");
                mostrar();
                limpiar();
                this.txtCodigo.setEnabled(true);
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
            mar.setIdMarca(Integer.parseInt(this.txtCodigo.getText()));
            int respuesta = JOptionPane.showConfirmDialog(this,"¿Desea eliminar la marca?","Eliminar",JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.OK_OPTION)
            {
               daoM.eliminarMarca(mar);
                JOptionPane.showMessageDialog(null, "Datos eliminados con exito");
                mostrar();
                limpiar();
                this.txtCodigo.setEnabled(true);
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
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnInsertar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMarca = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setText("MANTENIMIENTO \"MARCA\"");

        jLabel2.setText("Codigo:");

        jLabel3.setText("Nombre:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnInsertar.setBackground(new java.awt.Color(0, 255, 204));
        btnInsertar.setForeground(new java.awt.Color(0, 0, 255));
        btnInsertar.setText("Insertar");
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(0, 204, 255));
        btnModificar.setForeground(new java.awt.Color(51, 0, 153));
        btnModificar.setText("Modificar");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(0, 204, 255));
        btnEliminar.setForeground(new java.awt.Color(51, 0, 153));
        btnEliminar.setText("Eliminar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(0, 204, 255));
        btnCancelar.setForeground(new java.awt.Color(51, 0, 153));
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
                .addGap(18, 18, 18)
                .addComponent(btnInsertar)
                .addGap(32, 32, 32)
                .addComponent(btnModificar)
                .addGap(37, 37, 37)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblMarca.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMarcaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMarca);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        vc.numbersOnly(evt);
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        vc.wordsOnly(evt);
    }//GEN-LAST:event_txtNombreKeyTyped

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

    private void tblMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMarcaMouseClicked
        llenarT();
    }//GEN-LAST:event_tblMarcaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMarca;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
