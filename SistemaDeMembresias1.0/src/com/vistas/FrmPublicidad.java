
package com.vistas;

import com.dao.DaoPublicidad;
import com.modelo.Publicidad;
import com.utilidades.CustomImageIcon;
import com.utilidades.ValidarCampos;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: Publicidad
 * Fecha: 08/11/2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author adrian luna
 */

public class FrmPublicidad extends javax.swing.JInternalFrame {
    FileInputStream fis;
    int longitudBytes;
    DaoPublicidad daopub=new DaoPublicidad();
    Publicidad pub=new Publicidad();
    ValidarCampos val=new ValidarCampos();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagen","jpg","png","jpeg");
    
    public FrmPublicidad() {
        initComponents();
        mostrar();
    }
    
    public void mostrar()
    {
        try {
            String[] Columnas = {"IdPublicidad","url"};
            Object[] datos = new Object[2];
            tblPublicidad.getTableHeader().setReorderingAllowed(false) ;
            DefaultTableModel tabla = new DefaultTableModel(null, Columnas) 
                {
                    @Override
                    public boolean isCellEditable(int row, int col) 
                    {
                        return false;
                    }
                };
            List lst;
            lst = daopub.mostrarPublicidad();    
            for (int i = 0; i < lst.size(); i++)
            {
                pub = (Publicidad) lst.get(i);
                datos[0] = pub.getIdPublicidad();
                datos[1] = pub.getUrl();
                tabla.addRow(datos);
            }
            this.tblPublicidad.setModel(tabla);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos de publicidad " +e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void insertar() throws Exception {
        try {
            pub.setIdPublicidad(this.cmbPublicidad.getSelectedIndex());
            pub.setFoto(fis);
            pub.setUrl(txtUrl.getText());
            if (tblPublicidad.getRowCount() == 10) {
                daopub.modificarPublicidad(pub);
            } else {
                daopub.insertarPublicidad(pub);
            }
            mostrar();
            limpiar();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos de la publicidad " + e,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiar()
    {
        btnAgregar.setEnabled(true);
        cmbPublicidad.setSelectedIndex(0);
        cmbPublicidad.setEnabled(true);
        txtUrl.setText("");
        lblFoto.setIcon(null);
        this.fis = null;
        tblPublicidad.clearSelection();
    }
    
    public void llenarTabla() 
    {
        int fila = this.tblPublicidad.getSelectedRow();
        if (fila > -1) 
        {
            String id = tblPublicidad.getValueAt(fila, 0).toString();
            cmbPublicidad.setSelectedIndex(Integer.parseInt(this.tblPublicidad.getValueAt(fila, 0).toString()));
            txtUrl.setText(String.valueOf(this.tblPublicidad.getValueAt(fila,1)));
            try {
                CustomImageIcon imagen = daopub.getFoto(Integer.parseInt(id));
                lblFoto.setIcon(imagen);
                lblFoto.updateUI();
            } catch (Exception ex) {
                Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            cmbPublicidad.setEnabled(false);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cmbPublicidad = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtUrl = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnSeleccionar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPublicidad = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel9.setText("PUBLICIDAD");

        cmbPublicidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleecione --", "Panel 1", "Panel 2", "Panel 3", "Panel 4", "Panel 5", "Panel 6", "Panel 7", "Panel 8", "Panel 9", "Panel 10" }));

        jLabel10.setText("Panel de Publicidad");

        jLabel11.setText("URL");

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSeleccionarMouseClicked(evt);
            }
        });

        jLabel12.setText("Foto");

        lblFoto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));

        tblPublicidad.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPublicidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPublicidadMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPublicidad);

        btnAgregar.setText("Agregar");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(291, 291, 291))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPublicidad, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel10)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel12)
                        .addGap(31, 31, 31))
                    .addComponent(btnSeleccionar))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addGap(53, 53, 53)
                .addComponent(btnCancelar)
                .addGap(67, 67, 67)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPublicidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeleccionar)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnAgregar))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        try {
            if (btnAgregar.isEnabled()) {
                insertar();
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        limpiar();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnSeleccionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSeleccionarMouseClicked
        try {
            JFileChooser se = new JFileChooser();
            se.setFileFilter(filter);
            
            int estado = se.showOpenDialog(null);
            if (estado == JFileChooser.APPROVE_OPTION) {
                fis = new FileInputStream(se.getSelectedFile());
                longitudBytes = (int) se.getSelectedFile().length();

                Image icono = ImageIO.read(se.getSelectedFile()).getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT);
                lblFoto.setIcon(new ImageIcon(icono));
                lblFoto.updateUI();

            }
            else {
                fis = new FileInputStream(se.getSelectedFile());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar la foto " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSeleccionarMouseClicked

    private void tblPublicidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPublicidadMouseClicked
        llenarTabla();
    }//GEN-LAST:event_tblPublicidadMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> cmbPublicidad;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTable tblPublicidad;
    private javax.swing.JTextField txtUrl;
    // End of variables declaration//GEN-END:variables
}
