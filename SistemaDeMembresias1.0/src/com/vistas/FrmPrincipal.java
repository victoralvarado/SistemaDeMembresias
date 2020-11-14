package com.vistas;

import com.dao.DaoCarrito;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Nombre de la clase: FrmPrincipal
 * Fecha: 19-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmPrincipal extends javax.swing.JFrame {
    ImageIcon fot;
    Image img;
    Icon iconoEsca;
    String frm;
    java.util.Timer timer2 = new java.util.Timer();
    java.util.Timer timer3 = new java.util.Timer();
    java.util.Timer timer4 = new java.util.Timer();
    DaoCarrito daoc = new DaoCarrito();
    int n1 = 0;
    public FrmPrincipal() {
        initComponents();
    }
    public FrmPrincipal(String email, int idSuscriptor) {
        initComponents();
        lblIdSuscriptor.setText(String.valueOf(idSuscriptor));
        btnBuscar.setVisible(false);
        //Iniciar maximixado FrmAdministracion
        this.setExtendedState(MAXIMIZED_BOTH);
        btnInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 2));
        FrmPrincipalInicio inicio = new FrmPrincipalInicio(Integer.parseInt(lblIdSuscriptor.getText()));
        logo();
        abrirFrm(inicio);
        lblLoading.setVisible(false);
        lblUsuario.setText(email);
        lblUsuario.setToolTipText(email);
        lblIdSuscriptor.setVisible(false);
        timer2.schedule(tarea, 1000,100);
        lblCopyright.setText(" Copyright 2020-2020 Amantes del Vino y Licores");
        if (inicio.isVisible()) {
            inicio.tim.cancel();
            inicio.tim = new java.util.Timer();
            inicio.tim.schedule(inicio.tarea1, 400);
        }
    }
    
    TimerTask tarea = new TimerTask() {
        @Override
        public void run() {
            int n = daoc.contarProdCar(Integer.parseInt(lblIdSuscriptor.getText()));
            btnCarrito.setText(String.valueOf(n));
        }
    };
    
    public final void logo() {
        fot = new ImageIcon(getClass().getResource("/com/media/logo.png"));
        img = fot.getImage().getScaledInstance(218, 58, Image.SCALE_SMOOTH);
        iconoEsca = new ImageIcon(img);
        lblLogo.setIcon(iconoEsca);
        lblLogo.repaint();
    }
    
    public void abrirFrm(JInternalFrame frm) {
        //Agregar instancias de sus formularios
        int id = Integer.parseInt(lblIdSuscriptor.getText());
        FrmPrincipalInicio inicio = new FrmPrincipalInicio(id);
        FrmPrincipalVino vino = new FrmPrincipalVino(id);
        FrmPrincipalLicor  licor = new FrmPrincipalLicor(id);
        FrmBuscarLicores  licores = new FrmBuscarLicores(id);
        FrmBuscarVinos vinos = new FrmBuscarVinos(id);
        FrmCarrito carrito = new FrmCarrito(id);
        if (!jInternalFramesAbiertos()) {
            mostrarFrm(frm);
        }
        else {
            deskPnlContenedor.removeAll();
            inicio.setVisible(false);
            vino.setVisible(false);
            licor.setVisible(false);
            carrito.setVisible(false);
            licores.setVisible(false);
            vinos.setVisible(false);
            deskPnlContenedor.repaint();
            mostrarFrm(frm);
        }
    }
    
    public void mostrarFrm(JInternalFrame frm) {
        deskPnlContenedor.add(frm);
        //Mostar formulario centrado
        Dimension desktopSize = deskPnlContenedor.getSize();
        Dimension FrameSize = frm.getSize();
        frm.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        //Fin mostrar formulario centrado
        frm.setVisible(true);
        try {
            frm.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean jInternalFramesAbiertos() {
        // Este arreglo almacena todos los JInternalFrames que esten abierto en el jDesktopPane.
        JInternalFrame[] frmActivo = deskPnlContenedor.getAllFrames(); 
        // Creamos un ciclo for para recorrer nuestro arreglo utilizando la propiedad length de nuestro arreglo.
        for (int i = 0; i < frmActivo.length; i++) {
            // Validamos con un if si nuestro arreglo en la posición i es igual al JInternalFrame que esta activo en 
            //el jDesktopPane, si es igual devolverá true.
            if (frmActivo[i] == deskPnlContenedor.getSelectedFrame()) {
                return true;
            }
        }
        return false;
    }
    
    public void cerrar() {
        System.exit(0);
    }
    
    Timer timer = new Timer(4000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            menuUsuario.setVisible(false);
        }
    });
    
    public void cargando() {
        lblLoading.setVisible(true);
        lblLoading.repaint();
        TimerTask tarea1 = new TimerTask() {
                @Override
                public void run() {
                    
                    switch (n1) {
                        case 0:
                            n1++;
                            lblLoading.setText("C");
                            break;
                        case 1:
                            n1++;
                            lblLoading.setText("CA");
                            break;
                        case 2:
                            lblLoading.setText("CAR");
                            n1++;
                            break;
                        case 3:
                            lblLoading.setText("CARG");
                            n1++;
                            break;
                        case 4:
                            lblLoading.setText("CARGA");
                            n1++;
                            break;
                        case 5:
                            lblLoading.setText("CARGAN");
                            n1++;
                            break;
                        case 6:
                            lblLoading.setText("CARGAND");
                            n1++;
                            break;
                        case 7:
                            lblLoading.setText("CARGANDO");
                            n1++;
                            break;
                        case 8:
                            lblLoading.setText("");
                            n1 = 0;
                            break;
                        default:
                            break;
                    }
                }
            };
        timer4.cancel();
        timer4 = new java.util.Timer();
        timer4.schedule(tarea1, 0, 300);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuUsuario = new javax.swing.JPopupMenu();
        menuModificar = new javax.swing.JMenuItem();
        menuCerrarSesion = new javax.swing.JMenuItem();
        pnlPrincipal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnInicio = new javax.swing.JLabel();
        btnVinos = new javax.swing.JLabel();
        btnLicores = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        pnlLogo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        btnCarrito = new javax.swing.JLabel();
        lblIdSuscriptor = new javax.swing.JLabel();
        lblLoading = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        deskPnlContenedor = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        lblCopyright = new javax.swing.JLabel();

        menuUsuario.setInheritsPopupMenu(true);

        menuModificar.setText("Modificar mis datos");
        menuModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModificarActionPerformed(evt);
            }
        });
        menuUsuario.add(menuModificar);

        menuCerrarSesion.setText("Cerrar Secion");
        menuCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCerrarSesionActionPerformed(evt);
            }
        });
        menuUsuario.add(menuCerrarSesion);

        menuUsuario.getAccessibleContext().setAccessibleParent(lblUsuario);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlPrincipal.setBackground(new java.awt.Color(250, 250, 250));
        pnlPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlPrincipal.setAutoscrolls(true);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnInicio.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnInicio.setText(" Inicio ");
        btnInicio.setToolTipText("Inicio");
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnInicioMouseMoved(evt);
            }
        });
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioMouseExited(evt);
            }
        });

        btnVinos.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btnVinos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVinos.setText(" Vinos ");
        btnVinos.setToolTipText("Vinos");
        btnVinos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVinos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVinosMouseClicked(evt);
            }
        });

        btnLicores.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        btnLicores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLicores.setText(" Licores ");
        btnLicores.setToolTipText("Licores");
        btnLicores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLicores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLicoresMouseClicked(evt);
            }
        });

        lblUsuario.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/desplegar.png"))); // NOI18N
        lblUsuario.setText("Usuario@usuario");
        lblUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblUsuario.setIconTextGap(5);
        lblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsuarioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblUsuarioMouseExited(evt);
            }
        });

        pnlLogo.setBackground(new java.awt.Color(245, 245, 245));

        lblLogo.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlLogoLayout = new javax.swing.GroupLayout(pnlLogo);
        pnlLogo.setLayout(pnlLogoLayout);
        pnlLogoLayout.setHorizontalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlLogoLayout.setVerticalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnCarrito.setBackground(new java.awt.Color(204, 204, 204));
        btnCarrito.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCarrito.setForeground(new java.awt.Color(204, 0, 0));
        btnCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btnCarrito.setText("0");
        btnCarrito.setToolTipText("Ver Carrito");
        btnCarrito.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCarrito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCarrito.setName(""); // NOI18N
        btnCarrito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCarritoMouseClicked(evt);
            }
        });

        lblIdSuscriptor.setText("idSuscriptor");

        lblLoading.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblLoading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoading.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(pnlLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(lblIdSuscriptor)
                .addGap(283, 283, 283)
                .addComponent(lblLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInicio)
                .addGap(18, 18, 18)
                .addComponent(btnVinos)
                .addGap(18, 18, 18)
                .addComponent(btnLicores)
                .addGap(18, 18, 18)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnInicio)
                            .addComponent(btnVinos)
                            .addComponent(btnLicores)
                            .addComponent(lblUsuario)
                            .addComponent(btnCarrito)
                            .addComponent(lblIdSuscriptor)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));

        javax.swing.GroupLayout deskPnlContenedorLayout = new javax.swing.GroupLayout(deskPnlContenedor);
        deskPnlContenedor.setLayout(deskPnlContenedorLayout);
        deskPnlContenedorLayout.setHorizontalGroup(
            deskPnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        deskPnlContenedorLayout.setVerticalGroup(
            deskPnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblTitulo.setText("INICIO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(648, 648, 648)
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(638, 638, 638))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnBuscar.setBackground(new java.awt.Color(41, 128, 185));
        btnBuscar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar mas Licores");
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });

        lblCopyright.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblCopyright.setText("jLabel1");
        lblCopyright.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deskPnlContenedor)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(lblCopyright, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(579, 579, 579))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deskPnlContenedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCopyright))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseClicked
        cargando();
        TimerTask tarea2 = new TimerTask() {
            @Override
            public void run() {

                FrmPrincipalInicio inicio = new FrmPrincipalInicio(Integer.parseInt(lblIdSuscriptor.getText()));
                abrirFrm(inicio);
                lblTitulo.setText("INICIO");
                btnBuscar.setVisible(false);
                lblCopyright.setVisible(true);
                lblCopyright.setText(" Copyright 2020-2020 Amantes del Vino y Licores");
                btnInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 2));
                //Borde al color del fondo
                btnVinos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                btnLicores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                btnCarrito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                inicio.tim.cancel();
                inicio.tim = new java.util.Timer();
                inicio.tim.schedule(inicio.tarea1, 0);
                lblLoading.setVisible(false);
            }
        };
        timer3.schedule(tarea2, 1000);
    }//GEN-LAST:event_btnInicioMouseClicked

    private void btnVinosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVinosMouseClicked
        cargando();
        TimerTask tarea2 = new TimerTask() {
            @Override
            public void run() {
                FrmPrincipalVino vino = new FrmPrincipalVino(Integer.parseInt(lblIdSuscriptor.getText()));
                abrirFrm(vino);
                lblTitulo.setText("VINOS");
                btnBuscar.setVisible(true);
                btnBuscar.setText("Buscar mas Vinos");
                lblCopyright.setVisible(false);
                frm = "vinos";
                btnVinos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 2));
                //Borde al color del fondo
                btnInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                btnLicores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                btnCarrito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                lblLoading.setVisible(false);
            }
        };
        timer3.schedule(tarea2, 1000);
    }//GEN-LAST:event_btnVinosMouseClicked

    private void btnLicoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLicoresMouseClicked
        cargando();
        TimerTask tarea2 = new TimerTask() {
            @Override
            public void run() {
                FrmPrincipalLicor licor = new FrmPrincipalLicor(Integer.parseInt(lblIdSuscriptor.getText()));
                abrirFrm(licor);
                lblTitulo.setText("LICORES");
                btnBuscar.setVisible(true);
                btnBuscar.setText("Buscar mas Licores");
                lblCopyright.setVisible(false);
                frm = "licores";
                btnLicores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 2));
                //Borde al color del fondo
                btnInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                btnVinos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                btnCarrito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                lblLoading.setVisible(false);
            }
        };
        timer3.schedule(tarea2, 1000);
    }//GEN-LAST:event_btnLicoresMouseClicked

    private void btnCarritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCarritoMouseClicked
        cargando();
        TimerTask tarea2 = new TimerTask() {
            @Override
            public void run() {
                FrmCarrito carrito = new FrmCarrito(Integer.parseInt(lblIdSuscriptor.getText()));
                abrirFrm(carrito);
                lblTitulo.setText("CARRITO");
                btnBuscar.setVisible(false);
                lblCopyright.setVisible(false);
                btnCarrito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 1));
                //Borde al color del fondo
                btnInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                btnLicores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                btnVinos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                lblLoading.setVisible(false);
            }
        };
        timer3.schedule(tarea2, 1000);
    }//GEN-LAST:event_btnCarritoMouseClicked

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        if (frm.equalsIgnoreCase("licores")) {
            cargando();
            TimerTask tarea2 = new TimerTask() {
                @Override
                public void run() {
                    FrmBuscarLicores licor = new FrmBuscarLicores(Integer.parseInt(lblIdSuscriptor.getText()));
                    abrirFrm(licor);
                    lblTitulo.setText("LICORES");
                    btnBuscar.setVisible(false);
                    lblCopyright.setVisible(true);
                    //Borde al color del fondo
                    btnInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                    btnVinos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                    btnCarrito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                    btnLicores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                    lblLoading.setVisible(false);
                }
            };
            timer3.schedule(tarea2, 1000);
        }
        if (frm.equalsIgnoreCase("vinos")) {
            cargando();
            TimerTask tarea2 = new TimerTask() {
                @Override
                public void run() {
                    FrmBuscarVinos vino = new FrmBuscarVinos(Integer.parseInt(lblIdSuscriptor.getText()));
                    abrirFrm(vino);
                    lblTitulo.setText("VINOS");
                    btnBuscar.setVisible(false);
                    lblCopyright.setVisible(true);
                    //Borde al color del fondo
                    btnInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                    btnLicores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                    btnCarrito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                    btnVinos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 245, 245), 0));
                    lblLoading.setVisible(false);
                }
            };
            timer3.schedule(tarea2, 1000);
        }
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void lblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuarioMouseClicked
        menuUsuario.setLocation(evt.getLocationOnScreen());
        menuUsuario.setVisible(true); 
        timer.start();
    }//GEN-LAST:event_lblUsuarioMouseClicked

    private void btnInicioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseMoved
        
    }//GEN-LAST:event_btnInicioMouseMoved

    private void btnInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseEntered
        
    }//GEN-LAST:event_btnInicioMouseEntered

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        
    }//GEN-LAST:event_btnInicioMouseExited
    
    private void menuModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModificarActionPerformed
        JOptionPane.showMessageDialog(this, "Modificar");
    }//GEN-LAST:event_menuModificarActionPerformed

    private void lblUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuarioMouseEntered
        
    }//GEN-LAST:event_lblUsuarioMouseEntered

    private void lblUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuarioMouseExited
        
    }//GEN-LAST:event_lblUsuarioMouseExited

    private void menuCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCerrarSesionActionPerformed
        menuUsuario.setVisible(false); 
        FrmLogin login = new FrmLogin();
        login.show();
        this.hide();
    }//GEN-LAST:event_menuCerrarSesionActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
       

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel btnCarrito;
    private javax.swing.JLabel btnInicio;
    private javax.swing.JLabel btnLicores;
    private javax.swing.JLabel btnVinos;
    private javax.swing.JDesktopPane deskPnlContenedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblIdSuscriptor;
    public javax.swing.JLabel lblLoading;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuItem menuCerrarSesion;
    private javax.swing.JMenuItem menuModificar;
    private javax.swing.JPopupMenu menuUsuario;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables

}
