package com.vistas;

import com.dao.DaoProducto;
import com.utilidades.CustomImageIcon;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victor alvarado
 */
public class FrmPnlProducto extends javax.swing.JPanel {

    DaoProducto daop = new DaoProducto();
    public FrmPnlProducto() {
        initComponents();
    }
    public void cargarProd(String id, String tooltip, String nombre, double precio, int stock) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        p1.setToolTipText(tooltip);
        lblNombre.setText(nombre);
        lblNombre.setToolTipText(nombre);
        lblPrecio.setText(String.valueOf(nf.format(precio)));
        try {
            CustomImageIcon imagen = daop.getImagen(Integer.parseInt(id));
            p1.setIcon(imagen);
            p1.updateUI();
            if (stock == 0) {
                p1.setText("AGOTADO");
                addCart.setEnabled(false);
            } else {
                p1.setText("");
                addCart.setEnabled(true);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(FrmPnlProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        p1 = new javax.swing.JLabel();
        addCart = new javax.swing.JButton();
        jSpinner4 = new javax.swing.JSpinner();
        lblNombre = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(249, 235, 234));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p1.setBackground(new java.awt.Color(255, 255, 255));
        p1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        p1.setForeground(new java.awt.Color(255, 0, 0));
        p1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1.setToolTipText("<html>Vino procedente de La Rioja Alta,<br>\ntiene un color granate con notas<br>\nvioláceas en el menisco.</html>");
        p1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        addCart.setBackground(new java.awt.Color(192, 57, 43));
        addCart.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        addCart.setForeground(new java.awt.Color(255, 255, 255));
        addCart.setText("Agregar al carrito");
        addCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lblNombre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText("Nombre");
        lblNombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecio.setText("Precio");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(addCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPrecio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addCart)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCart;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel p1;
    // End of variables declaration//GEN-END:variables
}
