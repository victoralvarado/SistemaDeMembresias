package com.vistas;

import com.dao.DaoCarrito;
import com.dao.DaoPortada;
import com.dao.DaoProducto;
import com.dao.DaoProductoInicio;
import com.dao.DaoPublicidad;
import com.modelo.Carrito;
import com.modelo.Producto;
import com.modelo.ProductoInicio;
import com.utilidades.CargarImagen;
import com.utilidades.CustomImageIcon;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 * Nombre de la clase: FrmPrincipalInicio
 Fecha: 19-10-2020
 Versión: 1.0
 CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmPrincipalInicio extends javax.swing.JInternalFrame {
    ImageIcon fot;
    Image img;
    Icon iconoEsca;
    DaoProducto daop = new DaoProducto();
    DaoProductoInicio daopi = new DaoProductoInicio();
    ProductoInicio pi = new ProductoInicio();
    DaoPortada daopt = new DaoPortada();
    JLabel lbl = new JLabel();
    Producto p = new Producto();
    DaoCarrito daoc = new DaoCarrito();
    Carrito car = new Carrito();
    int id1 = 0;
    int id2 = 0;
    int id3 = 0;
    int id4 = 0;
    int nst = 0;
    
    DaoPublicidad daopu = new DaoPublicidad();
    String urlp1 = "";
    String urlp2 = "";
    public Timer tim = new Timer();
    public FrmPrincipalInicio() {
        initComponents();
    }
    
    public FrmPrincipalInicio(int idSuscriptor) {
        initComponents();
        ((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        cargarProducto();
        lbl.setText(String.valueOf(idSuscriptor));
        cargarPublicidad();
    }
    
    public TimerTask tarea1 = new TimerTask() {
            @Override
            public void run() {
                mostrarP();
            }
        };
    
    public void cargarPublicidad() {
        urlp1 = daopu.getUrl(1);
        urlp2 = daopu.getUrl(2);
        try {
            CustomImageIcon imagenp1 = daopu.getFoto(1);
            lblPublicidad1.setIcon(imagenp1);
            lblPublicidad1.updateUI();
            
            CustomImageIcon imagenp2 = daopu.getFoto(2);
            lblPublicidad2.setIcon(imagenp2);
            lblPublicidad2.updateUI();
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipalInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarProducto() {
        id1 = daopi.getIdPrducto(1).getIdProducto();
        id2 = daopi.getIdPrducto(2).getIdProducto();
        id3 = daopi.getIdPrducto(3).getIdProducto();
        id4 = daopi.getIdPrducto(4).getIdProducto();

        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

        try {
            CustomImageIcon imagen1 = daop.getImagen(id1);
            p1.setIcon(imagen1);
            p1.updateUI();
            p1.setToolTipText(daop.info("descripcion", id1));
            lblNombre.setText(daop.info("nombre", id1));
            lblNombre.setToolTipText(daop.info("nombre", id1));
            lblPrecio.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id1))));

            if (Integer.parseInt(daop.info("stock", id1)) == 0) {
                p1.setText("AGOTADO");
                btn1.setEnabled(false);
            } else {
                p1.setText("");
                btn1.setEnabled(true);
            }
            CustomImageIcon imagen2 = daop.getImagen(id2);
            p2.setIcon(imagen2);
            p2.updateUI();
            p2.setToolTipText(daop.info("descripcion", id2));
            lblNombre1.setText(daop.info("nombre", id2));
            lblNombre1.setToolTipText(daop.info("nombre", id2));
            lblPrecio1.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id2))));
            if (Integer.parseInt(daop.info("stock", id2)) == 0) {
                p2.setText("AGOTADO");
                btn2.setEnabled(false);
            } else {
                p2.setText("");
                btn2.setEnabled(true);
            }
            CustomImageIcon imagen3 = daop.getImagen(id3);
            p3.setIcon(imagen3);
            p3.updateUI();
            p3.setToolTipText(daop.info("descripcion", id3));
            lblNombre2.setText(daop.info("nombre", id3));
            lblNombre2.setToolTipText(daop.info("nombre", id3));
            lblPrecio2.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id3))));
            if (Integer.parseInt(daop.info("stock", id3)) == 0) {
                p3.setText("AGOTADO");
                btn3.setEnabled(false);
            } else {
                p3.setText("");
                btn3.setEnabled(true);
            }
            CustomImageIcon imagen4 = daop.getImagen(id4);
            p4.setIcon(imagen4);
            p4.updateUI();
            p4.setToolTipText(daop.info("descripcion", id4));
            lblNombre3.setText(daop.info("nombre", id4));
            lblNombre3.setToolTipText(daop.info("nombre", id4));
            lblPrecio3.setText(nf.format(Integer.valueOf(daop.info("precioVenta", id4))));
            if (Integer.parseInt(daop.info("stock", id4)) == 0) {
                p4.setText("AGOTADO");
                btn4.setEnabled(false);
            } else {
                p4.setText("");
                btn4.setEnabled(true);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmPrincipalInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarP() {
        try {
            CustomImageIcon imagen = daopt.getImagen(1);
            ImageIcon imagenF = new ImageIcon(imagen.getImage().getScaledInstance(lblPortada.getWidth(), lblPortada.getHeight(), Image.SCALE_SMOOTH));
            lblPortada.setIcon(imagenF);
            lblPortada.repaint();
        } catch (Exception ex) {
            Logger.getLogger(FrmPortada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarStock(int nstock, int idProducto) {
        p.setStock(nstock);
        p.setIdProducto(idProducto);
        daop.modificarStock(p);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInicio = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        p1 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        sp1 = new javax.swing.JSpinner();
        lblNombre = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        p2 = new javax.swing.JLabel();
        btn2 = new javax.swing.JButton();
        sp2 = new javax.swing.JSpinner();
        lblPrecio1 = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        p3 = new javax.swing.JLabel();
        btn3 = new javax.swing.JButton();
        sp3 = new javax.swing.JSpinner();
        lblNombre2 = new javax.swing.JLabel();
        lblPrecio2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        p4 = new javax.swing.JLabel();
        btn4 = new javax.swing.JButton();
        sp4 = new javax.swing.JSpinner();
        lblPrecio3 = new javax.swing.JLabel();
        lblNombre3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblPublicidad1 = new javax.swing.JLabel();
        btnVisitar1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lblPublicidad2 = new javax.swing.JLabel();
        btnVisitar2 = new javax.swing.JButton();
        lblPortada = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 250, 250)));
        setTitle("INICIO");
        setAutoscrolls(true);

        pnlInicio.setBackground(new java.awt.Color(213, 245, 227));
        pnlInicio.setAutoscrolls(true);
        pnlInicio.setPreferredSize(new java.awt.Dimension(1308, 531));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p1.setBackground(new java.awt.Color(255, 255, 255));
        p1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        p1.setForeground(new java.awt.Color(255, 0, 0));
        p1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        sp1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lblNombre.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText("Nombre");
        lblNombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecio.setText("Precio");
        lblPrecio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrecio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn1)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p2.setBackground(new java.awt.Color(255, 255, 255));
        p2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        p2.setForeground(new java.awt.Color(255, 0, 0));
        p2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        sp2.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lblPrecio1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecio1.setText("Precio");
        lblPrecio1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblNombre1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblNombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre1.setText("Nombre");
        lblNombre1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(sp2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrecio1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrecio1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn2)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p3.setBackground(new java.awt.Color(255, 255, 255));
        p3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        p3.setForeground(new java.awt.Color(255, 0, 0));
        p3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        sp3.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lblNombre2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblNombre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre2.setText("Nombre");
        lblNombre2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblPrecio2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecio2.setText("Precio");
        lblPrecio2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(lblPrecio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btn3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(26, 26, 26))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(sp3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(60, 60, 60)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNombre2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrecio2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn3)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        p4.setBackground(new java.awt.Color(255, 255, 255));
        p4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        p4.setForeground(new java.awt.Color(255, 0, 0));
        p4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        p4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        sp4.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lblPrecio3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecio3.setText("Precio");
        lblPrecio3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblNombre3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblNombre3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre3.setText("Nombre");
        lblNombre3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(p4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sp4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecio3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombre3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrecio3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn4)
                .addContainerGap())
        );

        lblPublicidad1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPublicidad1.setText("PUBLICIDAD");
        lblPublicidad1.setFocusable(false);
        lblPublicidad1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblPublicidad1.setOpaque(true);

        btnVisitar1.setText("Visitar");
        btnVisitar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVisitar1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVisitar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPublicidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPublicidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVisitar1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPublicidad2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPublicidad2.setText("PUBLICIDAD");

        btnVisitar2.setText("Visitar");
        btnVisitar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVisitar2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblPublicidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnVisitar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPublicidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVisitar2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPortada.setBackground(new java.awt.Color(0, 51, 51));
        lblPortada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPortada.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        lblPortada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlInicioLayout = new javax.swing.GroupLayout(pnlInicio);
        pnlInicio.setLayout(pnlInicioLayout);
        pnlInicioLayout.setHorizontalGroup(
            pnlInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInicioLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPortada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInicioLayout.setVerticalGroup(
            pnlInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInicioLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblPortada, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        if (btn2.isEnabled()) {
            int sp = Integer.parseInt(String.valueOf(sp2.getValue()));
            nst = daop.stock(id2) - sp;
            if (nst < 0) {
                JOptionPane.showMessageDialog(this, "Hay " + daop.stock(id1) + " en estock");
                sp2.setValue(1);
            } else {
                modificarStock(nst, id2);
                car.setIdProducto(id2);
                car.setCantidad(Integer.parseInt(sp2.getValue().toString()));
                car.setIdSuscriptor(Integer.parseInt(lbl.getText()));
                try {
                    daoc.insertarCarrito(car);
                } catch (Exception ex) {
                    Logger.getLogger(FrmPnlProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
                sp2.setValue(1);
            }
        }
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        if (btn3.isEnabled()) {
            int sp = Integer.parseInt(String.valueOf(sp3.getValue()));
            nst = daop.stock(id3) - sp;
            if (nst < 0) {
                JOptionPane.showMessageDialog(this, "Hay " + daop.stock(id3) + " en estock");
                sp3.setValue(1);
            } else {
                modificarStock(nst, id3);
                car.setIdProducto(id3);
                car.setCantidad(Integer.parseInt(sp3.getValue().toString()));
                car.setIdSuscriptor(Integer.parseInt(lbl.getText()));
                try {
                    daoc.insertarCarrito(car);
                } catch (Exception ex) {
                    Logger.getLogger(FrmPnlProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
                sp3.setValue(1);
            }
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        if (btn4.isEnabled()) {
            int sp = Integer.parseInt(String.valueOf(sp4.getValue()));
            nst = daop.stock(id4) - sp;
            if (nst < 0) {
                JOptionPane.showMessageDialog(this, "Hay " + daop.stock(id4) + " en estock");
                sp4.setValue(1);
            } else {
                modificarStock(nst, id4);
                car.setIdProducto(id4);
                car.setCantidad(Integer.parseInt(sp4.getValue().toString()));
                car.setIdSuscriptor(Integer.parseInt(lbl.getText()));
                try {
                    daoc.insertarCarrito(car);
                } catch (Exception ex) {
                    Logger.getLogger(FrmPnlProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
                sp4.setValue(1);
            }
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        if (btn1.isEnabled()) {
            int sp = Integer.parseInt(String.valueOf(sp1.getValue()));
            nst = daop.stock(id1) - sp;
            if (nst < 0) {
                JOptionPane.showMessageDialog(this, "Hay " + daop.stock(id1) + " en estock");
                sp1.setValue(1);
            } else {
                modificarStock(nst, id1);
                car.setIdProducto(id1);
                car.setCantidad(Integer.parseInt(sp1.getValue().toString()));
                car.setIdSuscriptor(Integer.parseInt(lbl.getText()));
                try {
                    daoc.insertarCarrito(car);
                } catch (Exception ex) {
                    Logger.getLogger(FrmPnlProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
                sp1.setValue(1);
            }
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btnVisitar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVisitar1MouseClicked
        try {
                Desktop.getDesktop().browse(new URI(urlp1));
        } catch (IOException | URISyntaxException e) {
            System.err.println("No hay link: " + e);
        }
    }//GEN-LAST:event_btnVisitar1MouseClicked

    private void btnVisitar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVisitar2MouseClicked
        try {
                Desktop.getDesktop().browse(new URI(urlp2));
        } catch (IOException | URISyntaxException e) {
            System.err.println("No hay link: " + e);
        }
    }//GEN-LAST:event_btnVisitar2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btnVisitar1;
    private javax.swing.JButton btnVisitar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombre2;
    private javax.swing.JLabel lblNombre3;
    private javax.swing.JLabel lblPortada;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblPrecio1;
    private javax.swing.JLabel lblPrecio2;
    private javax.swing.JLabel lblPrecio3;
    private javax.swing.JLabel lblPublicidad1;
    private javax.swing.JLabel lblPublicidad2;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    private javax.swing.JLabel p4;
    private javax.swing.JPanel pnlInicio;
    private javax.swing.JSpinner sp1;
    private javax.swing.JSpinner sp2;
    private javax.swing.JSpinner sp3;
    private javax.swing.JSpinner sp4;
    // End of variables declaration//GEN-END:variables

}
