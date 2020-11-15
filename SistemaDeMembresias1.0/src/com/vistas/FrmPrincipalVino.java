package com.vistas;

import com.dao.DaoCarrito;
import com.dao.DaoCategoria;
import com.dao.DaoMarca;
import com.dao.DaoProducto;
import com.dao.DaoPublicidad;
import com.dao.DaoVino;
import com.modelo.Carrito;
import com.modelo.Producto;
import com.modelo.Vino;
import com.utilidades.CustomImageIcon;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 * Nombre de la clase: FrmPrincipalLicor
 Fecha: 19-10-2020
 Versión: 1.0
 CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmPrincipalVino extends javax.swing.JInternalFrame {
    DaoProducto daop = new DaoProducto();
    Producto prod = new Producto();
    Vino vi = new Vino();
    DaoVino daovi = new DaoVino();
    DaoCategoria daoc = new DaoCategoria();
    DaoMarca daom = new DaoMarca();
    JLabel lbl = new JLabel();
    Producto p = new Producto();
    DaoCarrito daocar = new DaoCarrito();
    Carrito car = new Carrito();
    int id1 = 0;
    int id2 = 0;
    int id3 = 0;
    int id4 = 0;
    int id5 = 0;
    int id6 = 0;
    int id7 = 0;
    int id8 = 0;
    int id9 = 0;
    int id10 = 0;
    int id11 = 0;
    int id12 = 0;
    int nst = 0;
    
    DaoPublicidad daopu = new DaoPublicidad();
    String urlp3 = "";
    String urlp4 = "";
    String urlp5 = "";
    String urlp6 = "";
    
    public FrmPrincipalVino() {
        initComponents();
    }

    public FrmPrincipalVino(int idSuscriptor) {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        try {
            cargarVino();
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipalVino.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl.setText(String.valueOf(idSuscriptor));
        cargarPublicidad();
    }
    
    public void cargarPublicidad() {
        urlp3 = daopu.getUrl(3);
        urlp4 = daopu.getUrl(4);
        urlp5 = daopu.getUrl(5);
        urlp6 = daopu.getUrl(6);
        try {
            CustomImageIcon imagenp3 = daopu.getFoto(3);
            lblPublicidad3.setIcon(imagenp3);
            lblPublicidad3.updateUI();
            
            CustomImageIcon imagenp4 = daopu.getFoto(4);
            lblPublicidad4.setIcon(imagenp4);
            lblPublicidad4.updateUI();
            
            CustomImageIcon imagenp5 = daopu.getFoto(5);
            lblPublicidad5.setIcon(imagenp5);
            lblPublicidad5.updateUI();
            
            CustomImageIcon imagenp6 = daopu.getFoto(6);
            lblPublicidad6.setIcon(imagenp6);
            lblPublicidad6.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipalInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarVino() throws Exception{
         id1 = daovi.getIdVino(1).getIdProducto();
         id2 = daovi.getIdVino(2).getIdProducto();
         id3 = daovi.getIdVino(3).getIdProducto();
         id4 = daovi.getIdVino(4).getIdProducto();
         id5 = daovi.getIdVino(5).getIdProducto();
         id6 = daovi.getIdVino(6).getIdProducto();
         id7 = daovi.getIdVino(7).getIdProducto();
         id8= daovi.getIdVino(8).getIdProducto();
         id9 = daovi.getIdVino(9).getIdProducto();
         id10 = daovi.getIdVino(10).getIdProducto();
         id11 = daovi.getIdVino(11).getIdProducto();
         id12 = daovi.getIdVino(12).getIdProducto();
         
         NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
         try {
             
            CustomImageIcon imagen1 = daop.getImagen(id1);
            p1.setIcon(imagen1);
            p1.updateUI();
            p1.setToolTipText(daop.info("descripcion", id1));
            nom1.setText(daop.info("nombre", id1));
            nom1.setToolTipText(daop.info("nombre", id1));
            precio1.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id1))));

            if (Integer.parseInt(daop.info("stock", id1)) == 0) {
                sp1.setEnabled(false);
                p1.setText("AGOTADO");
                btn1.setEnabled(false);
            } else {
                p1.setText("");
                btn1.setEnabled(true);
                sp1.setEnabled(true);
            }
            
            CustomImageIcon imagen2 = daop.getImagen(id2);
            p2.setIcon(imagen2);
            p2.updateUI();
            p2.setToolTipText(daop.info("descripcion", id2));
            nom2.setText(daop.info("nombre", id2));
            nom2.setToolTipText(daop.info("nombre", id2));
            precio2.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id2))));

            if (Integer.parseInt(daop.info("stock", id2)) == 0) {
                sp2.setEnabled(false);
                p2.setText("AGOTADO");
                btn2.setEnabled(false);
            } else {
                p2.setText("");
                btn2.setEnabled(true);
                sp2.setEnabled(true);
            }
            
            CustomImageIcon imagen3 = daop.getImagen(id3);
            p3.setIcon(imagen3);
            p3.updateUI();
            p3.setToolTipText(daop.info("descripcion", id3));
            nom3.setText(daop.info("nombre", id3));
            nom3.setToolTipText(daop.info("nombre", id3));
            precio3.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id3))));

            if (Integer.parseInt(daop.info("stock", id3)) == 0) {
                sp3.setEnabled(false);
                p3.setText("AGOTADO");
                btn3.setEnabled(false);
            } else {
                p3.setText("");
                btn3.setEnabled(true);
                sp3.setEnabled(true);
            }
            CustomImageIcon imagen4 = daop.getImagen(id4);
            p4.setIcon(imagen4);
            p4.updateUI();
            p4.setToolTipText(daop.info("descripcion", id4));
            nom4.setText(daop.info("nombre", id4));
            nom4.setToolTipText(daop.info("nombre", id4));
            precio4.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id4))));

            if (Integer.parseInt(daop.info("stock", id4)) == 0) {
                sp4.setEnabled(false);
                p4.setText("AGOTADO");
                btn4.setEnabled(false);
            } else {
                p4.setText("");
                btn4.setEnabled(true);
                sp4.setEnabled(true);
            }
            
            CustomImageIcon imagen5 = daop.getImagen(id5);
            p5.setIcon(imagen5);
            p5.updateUI();
            p5.setToolTipText(daop.info("descripcion", id5));
            nom5.setText(daop.info("nombre", id5));
            nom5.setToolTipText(daop.info("nombre", id5));
            precio5.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id5))));

            if (Integer.parseInt(daop.info("stock", id5)) == 0) {
                sp5.setEnabled(false);
                p5.setText("AGOTADO");
                btn5.setEnabled(false);
            } else {
                p5.setText("");
                btn5.setEnabled(true);
                sp5.setEnabled(true);
            }
            
            CustomImageIcon imagen6 = daop.getImagen(id6);
            p6.setIcon(imagen6);
            p6.updateUI();
            p6.setToolTipText(daop.info("descripcion", id6));
            nom6.setText(daop.info("nombre", id6));
            nom6.setToolTipText(daop.info("nombre", id6));
            precio6.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id6))));

            if (Integer.parseInt(daop.info("stock", id6)) == 0) {
                sp6.setEnabled(false);
                p6.setText("AGOTADO");
                btn6.setEnabled(false);
            } else {
                p6.setText("");
                btn6.setEnabled(true);
                sp6.setEnabled(true);
            }
            
            CustomImageIcon imagen7 = daop.getImagen(id7);
            p7.setIcon(imagen7);
            p7.updateUI();
            p7.setToolTipText(daop.info("descripcion", id7));
            nom7.setText(daop.info("nombre", id7));
            nom7.setToolTipText(daop.info("nombre", id7));
            precio7.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id7))));

            if (Integer.parseInt(daop.info("stock", id7)) == 0) {
                sp7.setEnabled(false);
                p7.setText("AGOTADO");
                btn7.setEnabled(false);
            } else {
                p7.setText("");
                btn7.setEnabled(true);
                sp7.setEnabled(true);
            }
            
            CustomImageIcon imagen8 = daop.getImagen(id8);
            p8.setIcon(imagen8);
            p8.updateUI();
            p8.setToolTipText(daop.info("descripcion", id8));
            nom8.setText(daop.info("nombre", id8));
            nom8.setToolTipText(daop.info("nombre", id8));
            precio8.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id8))));

            if (Integer.parseInt(daop.info("stock", id8)) == 0) {
                sp8.setEnabled(false);
                p8.setText("AGOTADO");
                btn8.setEnabled(false);
            } else {
                p8.setText("");
                btn8.setEnabled(true);
                sp8.setEnabled(true);
            }
            
            CustomImageIcon imagen9 = daop.getImagen(id9);
            p9.setIcon(imagen9);
            p9.updateUI();
            p9.setToolTipText(daop.info("descripcion", id9));
            nom9.setText(daop.info("nombre", id9));
            nom9.setToolTipText(daop.info("nombre", id9));
            precio9.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id9))));

            if (Integer.parseInt(daop.info("stock", id9)) == 0) {
                sp9.setEnabled(false);
                p9.setText("AGOTADO");
                btn9.setEnabled(false);
            } else {
                p9.setText("");
                btn9.setEnabled(true);
                sp9.setEnabled(true);
            }
            
            CustomImageIcon imagen10 = daop.getImagen(id10);
            p10.setIcon(imagen10);
            p10.updateUI();
            p10.setToolTipText(daop.info("descripcion", id10));
            nom10.setText(daop.info("nombre", id10));
            nom10.setToolTipText(daop.info("nombre", id10));
            precio10.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id10))));

            if (Integer.parseInt(daop.info("stock", id10)) == 0) {
                sp10.setEnabled(false);
                p10.setText("AGOTADO");
                btn10.setEnabled(false);
            } else {
                p10.setText("");
                btn10.setEnabled(true);
                sp10.setEnabled(true);
            }
            
            CustomImageIcon imagen11 = daop.getImagen(id11);
            p11.setIcon(imagen11);
            p11.updateUI();
            p11.setToolTipText(daop.info("descripcion", id11));
            nom11.setText(daop.info("nombre", id11));
            nom11.setToolTipText(daop.info("nombre", id11));
            precio11.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id11))));

            if (Integer.parseInt(daop.info("stock", id11)) == 0) {
                sp11.setEnabled(false);
                p11.setText("AGOTADO");
                btn11.setEnabled(false);
            } else {
                p11.setText("");
                btn11.setEnabled(true);
                sp11.setEnabled(true);
            }
            
            CustomImageIcon imagen12 = daop.getImagen(id12);
            p12.setIcon(imagen12);
            p12.updateUI();
            p12.setToolTipText(daop.info("descripcion", id12));
            nom12.setText(daop.info("nombre", id12));
            nom12.setToolTipText(daop.info("nombre", id12));
            precio12.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id12))));

            if (Integer.parseInt(daop.info("stock", id12)) == 0) {
                sp12.setEnabled(false);
                p12.setText("AGOTADO");
                btn12.setEnabled(false);
            } else {
                p12.setText("");
                btn12.setEnabled(true);
                sp12.setEnabled(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipalInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarStock(int nstock, int idProducto) {
        p.setStock(nstock);
        p.setIdProducto(idProducto);
        daop.modificarStock(p);
    }
    
    public void cargarCar(JButton btn, JSpinner spi, int id ){
        if (btn.isEnabled()) {
            int sp = Integer.parseInt(String.valueOf(spi.getValue()));
            nst = daop.stock(id) - sp;
            if (nst < 0) {
                JOptionPane.showMessageDialog(this, "Hay " + daop.stock(id) + " en estock");
                spi.setValue(1);
            } else {
                modificarStock(nst, id);
                car.setIdProducto(id);
                car.setCantidad(Integer.parseInt(spi.getValue().toString()));
                car.setIdSuscriptor(Integer.parseInt(lbl.getText()));
                try {
                    daocar.insertarCarrito(car);
                } catch (Exception ex) {
                    Logger.getLogger(FrmPnlProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
                spi.setValue(1);
            }
        }
    }
    
    public void cargarUrl(String url) {
        if (url.trim().length() > 0) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                System.err.println(e);
            }
        } else {
            System.out.println("No hay link");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        p1 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        sp1 = new javax.swing.JSpinner();
        nom1 = new javax.swing.JLabel();
        precio1 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        p2 = new javax.swing.JLabel();
        btn2 = new javax.swing.JButton();
        sp2 = new javax.swing.JSpinner();
        precio2 = new javax.swing.JLabel();
        nom2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        p4 = new javax.swing.JLabel();
        btn4 = new javax.swing.JButton();
        sp4 = new javax.swing.JSpinner();
        nom4 = new javax.swing.JLabel();
        precio4 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        p5 = new javax.swing.JLabel();
        btn5 = new javax.swing.JButton();
        sp5 = new javax.swing.JSpinner();
        nom5 = new javax.swing.JLabel();
        precio5 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        p3 = new javax.swing.JLabel();
        btn3 = new javax.swing.JButton();
        sp3 = new javax.swing.JSpinner();
        nom3 = new javax.swing.JLabel();
        precio3 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        p6 = new javax.swing.JLabel();
        btn6 = new javax.swing.JButton();
        sp6 = new javax.swing.JSpinner();
        nom6 = new javax.swing.JLabel();
        precio6 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        p7 = new javax.swing.JLabel();
        btn7 = new javax.swing.JButton();
        sp7 = new javax.swing.JSpinner();
        nom7 = new javax.swing.JLabel();
        precio7 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        p8 = new javax.swing.JLabel();
        btn8 = new javax.swing.JButton();
        sp8 = new javax.swing.JSpinner();
        nom8 = new javax.swing.JLabel();
        precio8 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        p10 = new javax.swing.JLabel();
        btn10 = new javax.swing.JButton();
        sp10 = new javax.swing.JSpinner();
        nom10 = new javax.swing.JLabel();
        precio10 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        p9 = new javax.swing.JLabel();
        btn9 = new javax.swing.JButton();
        sp9 = new javax.swing.JSpinner();
        nom9 = new javax.swing.JLabel();
        precio9 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        p12 = new javax.swing.JLabel();
        btn12 = new javax.swing.JButton();
        sp12 = new javax.swing.JSpinner();
        nom12 = new javax.swing.JLabel();
        precio12 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        p11 = new javax.swing.JLabel();
        btn11 = new javax.swing.JButton();
        sp11 = new javax.swing.JSpinner();
        nom11 = new javax.swing.JLabel();
        precio11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblPublicidad4 = new javax.swing.JLabel();
        lblPublicidad3 = new javax.swing.JLabel();
        btnp3 = new javax.swing.JButton();
        btnp4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblPublicidad5 = new javax.swing.JLabel();
        btnp5 = new javax.swing.JButton();
        lblPublicidad6 = new javax.swing.JLabel();
        btnp6 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 250, 250)));
        setTitle("VINOS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(null);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p1.setBackground(new java.awt.Color(255, 255, 255));
        p1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p1.setForeground(new java.awt.Color(255, 0, 0));
        p1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn1.setBackground(new java.awt.Color(115, 198, 182));
        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        sp1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom1.setText("$00.0");

        precio1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio1.setText("$00.0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sp1)
                                    .addComponent(btn1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nom1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(precio1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p2.setBackground(new java.awt.Color(255, 255, 255));
        p2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p2.setForeground(new java.awt.Color(255, 0, 0));
        p2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn2.setBackground(new java.awt.Color(115, 198, 182));
        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        sp2.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        precio2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio2.setText("$00.0");

        nom2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom2.setText("$00.0");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sp2)
                            .addComponent(btn2, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(precio2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nom2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nom2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p4.setBackground(new java.awt.Color(255, 255, 255));
        p4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p4.setForeground(new java.awt.Color(255, 0, 0));
        p4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn4.setBackground(new java.awt.Color(115, 198, 182));
        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        sp4.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom4.setText("$00.0");

        precio4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio4.setText("$00.0");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(p4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sp4)
                            .addComponent(btn4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nom4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(nom4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p5.setBackground(new java.awt.Color(255, 255, 255));
        p5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p5.setForeground(new java.awt.Color(255, 0, 0));
        p5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn5.setBackground(new java.awt.Color(115, 198, 182));
        btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        sp5.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom5.setText("$00.0");

        precio5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio5.setText("$00.0");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(p5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sp5)
                            .addComponent(btn5, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nom5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nom5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p3.setBackground(new java.awt.Color(255, 255, 255));
        p3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p3.setForeground(new java.awt.Color(255, 0, 0));
        p3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn3.setBackground(new java.awt.Color(115, 198, 182));
        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        sp3.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom3.setText("$00.0");

        precio3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio3.setText("$00.0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sp3)
                            .addComponent(btn3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nom3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nom3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p6.setBackground(new java.awt.Color(255, 255, 255));
        p6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p6.setForeground(new java.awt.Color(255, 0, 0));
        p6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn6.setBackground(new java.awt.Color(115, 198, 182));
        btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        sp6.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom6.setText("$00.0");

        precio6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio6.setText("$00.0");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(nom6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(precio6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(p6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel18Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(sp6)
                                .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(nom6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p7.setBackground(new java.awt.Color(255, 255, 255));
        p7.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p7.setForeground(new java.awt.Color(255, 0, 0));
        p7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn7.setBackground(new java.awt.Color(115, 198, 182));
        btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        sp7.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom7.setText("$00.0");

        precio7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio7.setText("$00.0");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(p7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sp7)
                            .addComponent(btn7, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nom7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(nom7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p8.setBackground(new java.awt.Color(255, 255, 255));
        p8.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p8.setForeground(new java.awt.Color(255, 0, 0));
        p8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn8.setBackground(new java.awt.Color(115, 198, 182));
        btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        sp8.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom8.setText("$00.0");

        precio8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio8.setText("$00.0");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(p8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sp8)
                            .addComponent(btn8, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nom8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(nom8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p10.setBackground(new java.awt.Color(255, 255, 255));
        p10.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p10.setForeground(new java.awt.Color(255, 0, 0));
        p10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn10.setBackground(new java.awt.Color(115, 198, 182));
        btn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });

        sp10.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom10.setText("$00.0");

        precio10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio10.setText("$00.0");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(p10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sp10)
                            .addComponent(btn10, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nom10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(nom10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p9.setBackground(new java.awt.Color(255, 255, 255));
        p9.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p9.setForeground(new java.awt.Color(255, 0, 0));
        p9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn9.setBackground(new java.awt.Color(115, 198, 182));
        btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        sp9.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom9.setText("$00.0");

        precio9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio9.setText("$00.0");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(p9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sp9)
                            .addComponent(btn9, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nom9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(nom9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p12.setBackground(new java.awt.Color(255, 255, 255));
        p12.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p12.setForeground(new java.awt.Color(255, 0, 0));
        p12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn12.setBackground(new java.awt.Color(115, 198, 182));
        btn12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });

        sp12.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom12.setText("$00.0");

        precio12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio12.setText("$00.0");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sp12)
                            .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nom12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(nom12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p11.setBackground(new java.awt.Color(255, 255, 255));
        p11.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        p11.setForeground(new java.awt.Color(255, 0, 0));
        p11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn11.setBackground(new java.awt.Color(115, 198, 182));
        btn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });

        sp11.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        nom11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nom11.setText("$00.0");

        precio11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        precio11.setText("$00.0");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(p11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sp11)
                            .addComponent(btn11, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nom11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(nom11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(precio11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(248, 249, 249));

        lblPublicidad4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPublicidad4.setText("PUBLICIDAD");
        lblPublicidad4.setFocusable(false);
        lblPublicidad4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblPublicidad4.setOpaque(true);

        lblPublicidad3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPublicidad3.setText("PUBLICIDAD");
        lblPublicidad3.setFocusable(false);
        lblPublicidad3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblPublicidad3.setOpaque(true);

        btnp3.setBackground(new java.awt.Color(248, 196, 113));
        btnp3.setText("Visitar");
        btnp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnp3ActionPerformed(evt);
            }
        });

        btnp4.setBackground(new java.awt.Color(248, 196, 113));
        btnp4.setText("Visitar");
        btnp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnp4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnp4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnp3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPublicidad3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPublicidad4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPublicidad3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnp3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPublicidad4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnp4)
                .addGap(17, 17, 17))
        );

        jPanel5.setBackground(new java.awt.Color(248, 249, 249));

        lblPublicidad5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPublicidad5.setText("PUBLICIDAD");
        lblPublicidad5.setFocusable(false);
        lblPublicidad5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblPublicidad5.setOpaque(true);

        btnp5.setBackground(new java.awt.Color(248, 196, 113));
        btnp5.setText("Visitar");
        btnp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnp5ActionPerformed(evt);
            }
        });

        lblPublicidad6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPublicidad6.setText("PUBLICIDAD");
        lblPublicidad6.setFocusable(false);
        lblPublicidad6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblPublicidad6.setOpaque(true);

        btnp6.setBackground(new java.awt.Color(248, 196, 113));
        btnp6.setText("Visitar");
        btnp6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnp6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnp6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnp5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPublicidad5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPublicidad6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPublicidad5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnp5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPublicidad6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnp6)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        cargarCar(btn1, sp1, id1);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        cargarCar(btn2, sp2, id2);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        cargarCar(btn4, sp4, id4);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        cargarCar(btn5, sp5, id5);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        cargarCar(btn3, sp3, id3);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        cargarCar(btn6, sp6, id6);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        cargarCar(btn7, sp7, id7);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        cargarCar(btn8, sp8, id8);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        cargarCar(btn10, sp10, id10);
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        cargarCar(btn9, sp9, id9);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        cargarCar(btn12, sp12, id12);
    }//GEN-LAST:event_btn12ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        cargarCar(btn11, sp11, id11);
    }//GEN-LAST:event_btn11ActionPerformed

    private void btnp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnp3ActionPerformed
        cargarUrl(urlp3);
    }//GEN-LAST:event_btnp3ActionPerformed

    private void btnp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnp4ActionPerformed
        cargarUrl(urlp4);
    }//GEN-LAST:event_btnp4ActionPerformed

    private void btnp5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnp5ActionPerformed
        cargarUrl(urlp5);
    }//GEN-LAST:event_btnp5ActionPerformed

    private void btnp6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnp6ActionPerformed
        cargarUrl(urlp6);
    }//GEN-LAST:event_btnp6ActionPerformed
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnp3;
    private javax.swing.JButton btnp4;
    private javax.swing.JButton btnp5;
    private javax.swing.JButton btnp6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblPublicidad3;
    private javax.swing.JLabel lblPublicidad4;
    private javax.swing.JLabel lblPublicidad5;
    private javax.swing.JLabel lblPublicidad6;
    private javax.swing.JLabel nom1;
    private javax.swing.JLabel nom10;
    private javax.swing.JLabel nom11;
    private javax.swing.JLabel nom12;
    private javax.swing.JLabel nom2;
    private javax.swing.JLabel nom3;
    private javax.swing.JLabel nom4;
    private javax.swing.JLabel nom5;
    private javax.swing.JLabel nom6;
    private javax.swing.JLabel nom7;
    private javax.swing.JLabel nom8;
    private javax.swing.JLabel nom9;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p10;
    private javax.swing.JLabel p11;
    private javax.swing.JLabel p12;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    private javax.swing.JLabel p4;
    private javax.swing.JLabel p5;
    private javax.swing.JLabel p6;
    private javax.swing.JLabel p7;
    private javax.swing.JLabel p8;
    private javax.swing.JLabel p9;
    private javax.swing.JLabel precio1;
    private javax.swing.JLabel precio10;
    private javax.swing.JLabel precio11;
    private javax.swing.JLabel precio12;
    private javax.swing.JLabel precio2;
    private javax.swing.JLabel precio3;
    private javax.swing.JLabel precio4;
    private javax.swing.JLabel precio5;
    private javax.swing.JLabel precio6;
    private javax.swing.JLabel precio7;
    private javax.swing.JLabel precio8;
    private javax.swing.JLabel precio9;
    private javax.swing.JSpinner sp1;
    private javax.swing.JSpinner sp10;
    private javax.swing.JSpinner sp11;
    private javax.swing.JSpinner sp12;
    private javax.swing.JSpinner sp2;
    private javax.swing.JSpinner sp3;
    private javax.swing.JSpinner sp4;
    private javax.swing.JSpinner sp5;
    private javax.swing.JSpinner sp6;
    private javax.swing.JSpinner sp7;
    private javax.swing.JSpinner sp8;
    private javax.swing.JSpinner sp9;
    // End of variables declaration//GEN-END:variables

}
