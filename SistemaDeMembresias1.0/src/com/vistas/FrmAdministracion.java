package com.vistas;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;


/**
 * Nombre de la clase: FrmAdministracion
 * Fecha: 17-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmAdministracion extends javax.swing.JFrame {
    
    public FrmAdministracion() {
        initComponents();
        //Iniciar maximixado FrmAdministracion
        this.setExtendedState(MAXIMIZED_BOTH);
    }
    
    public void abrirFrm(JInternalFrame frm) {
        //Agregar instancias de sus formularios
        FrmProducto producto = new FrmProducto();
        FrmCarrito carrito = new FrmCarrito();
        FrmCategoria categoria=new FrmCategoria();
        FrmMarca marca = new FrmMarca();
        FrmPortada portada = new FrmPortada();
        FrmUsuario usuario = new FrmUsuario();
        FrmTipoSuscriptor tiposuscriptor = new FrmTipoSuscriptor();
        FrmProductoInicio productoI =new FrmProductoInicio();
        FrmVino vino = new FrmVino();
        FrmLicor licor = new FrmLicor();
        FrmPublicidad publicidad = new FrmPublicidad();
        FrmCobertura cobertura= new FrmCobertura();
        FrmSuscripcionBronce bronce = new FrmSuscripcionBronce();
        FrmSuscripcionOro oro = new FrmSuscripcionOro();
        FrmSuscripcionPlata plata = new FrmSuscripcionPlata();
        FrmEnvioProducto envios = new FrmEnvioProducto();
        FrmBanco  banco = new FrmBanco();
        if (!jInternalFramesAbiertos()) {
            mostrarFrm(frm);
        }
        else {
            deskPnlContenedor.removeAll();
            producto.setVisible(false);
            carrito.setVisible(false);
            categoria.setVisible(false);
            marca.setVisible(false);
            productoI.setVisible(false);
            portada.setVisible(false);
            usuario.setVisible(false);
            tiposuscriptor.setVisible(false);
            vino.setVisible(false);
            publicidad.setVisible(false);
            licor.setVisible(false);
            cobertura.setVisible(false);
            bronce.setVisible(false);
            oro.setVisible(false);
            plata.setVisible(false);
            banco.setVisible(false);
            envios.setVisible(true);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deskPnlContenedor = new javax.swing.JDesktopPane();
        menuPrincipal = new javax.swing.JMenuBar();
        btnMenu = new javax.swing.JMenu();
        btnProducto = new javax.swing.JMenuItem();
        btnCarrito = new javax.swing.JMenuItem();
        btnCategoria = new javax.swing.JMenuItem();
        btnMarca = new javax.swing.JMenuItem();
        btnUsuario = new javax.swing.JMenuItem();
        btnTipoSuscriptor = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        btnProductosI = new javax.swing.JMenuItem();
        btnPortada = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnVino = new javax.swing.JMenuItem();
        btnLicor = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnPublicidad = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btnCobertura = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        btnOro = new javax.swing.JMenuItem();
        btnPlata = new javax.swing.JMenuItem();
        btnBronce = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        btnEnvios = new javax.swing.JMenuItem();
        btnCerrarSesion = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        deskPnlContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 2));

        javax.swing.GroupLayout deskPnlContenedorLayout = new javax.swing.GroupLayout(deskPnlContenedor);
        deskPnlContenedor.setLayout(deskPnlContenedorLayout);
        deskPnlContenedorLayout.setHorizontalGroup(
            deskPnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1314, Short.MAX_VALUE)
        );
        deskPnlContenedorLayout.setVerticalGroup(
            deskPnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        btnMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/mantenimiento.png"))); // NOI18N
        btnMenu.setText("MANTENIMIENTO");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/producto.png"))); // NOI18N
        btnProducto.setText("Producto");
        btnProducto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });
        btnMenu.add(btnProducto);

        btnCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btnCarrito.setText("Carrito");
        btnCarrito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarritoActionPerformed(evt);
            }
        });
        btnMenu.add(btnCarrito);

        btnCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/categoria.png"))); // NOI18N
        btnCategoria.setText("Categoria");
        btnCategoria.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });
        btnMenu.add(btnCategoria);

        btnMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/marca.png"))); // NOI18N
        btnMarca.setText("Marca");
        btnMarca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcaActionPerformed(evt);
            }
        });
        btnMenu.add(btnMarca);

        btnUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/usuario.png"))); // NOI18N
        btnUsuario.setText("Usuario");
        btnUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });
        btnMenu.add(btnUsuario);

        btnTipoSuscriptor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/tiposus.png"))); // NOI18N
        btnTipoSuscriptor.setText("Tipo Suscriptor");
        btnTipoSuscriptor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTipoSuscriptor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoSuscriptorActionPerformed(evt);
            }
        });
        btnMenu.add(btnTipoSuscriptor);

        menuPrincipal.add(btnMenu);

        jMenu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/tablas.png"))); // NOI18N
        jMenu1.setText("TABLAS");
        menuPrincipal.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/inicio.png"))); // NOI18N
        jMenu2.setText("INICIO");

        btnProductosI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/producto.png"))); // NOI18N
        btnProductosI.setText("Productos");
        btnProductosI.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProductosI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosIActionPerformed(evt);
            }
        });
        jMenu2.add(btnProductosI);

        btnPortada.setText("Portada");
        btnPortada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPortadaActionPerformed(evt);
            }
        });
        jMenu2.add(btnPortada);

        menuPrincipal.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/wine_freezer (1).png"))); // NOI18N
        jMenu3.setText("PRODUCTOS");

        btnVino.setText("Vino");
        btnVino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVinoActionPerformed(evt);
            }
        });
        jMenu3.add(btnVino);

        btnLicor.setText("Licor");
        btnLicor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLicorActionPerformed(evt);
            }
        });
        jMenu3.add(btnLicor);

        menuPrincipal.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/advertising.png"))); // NOI18N
        jMenu4.setText("PUBLICIDAD");

        btnPublicidad.setText("Plubicidad");
        btnPublicidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicidadActionPerformed(evt);
            }
        });
        jMenu4.add(btnPublicidad);

        menuPrincipal.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/route_map.png"))); // NOI18N
        jMenu5.setText("COBERTURA");

        btnCobertura.setText("Cobertura");
        btnCobertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoberturaActionPerformed(evt);
            }
        });
        jMenu5.add(btnCobertura);

        menuPrincipal.add(jMenu5);

        jMenu6.setText("PRODUCTO SUCRIPCION");

        btnOro.setText("Oro");
        btnOro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOroActionPerformed(evt);
            }
        });
        jMenu6.add(btnOro);

        btnPlata.setText("Plata");
        btnPlata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlataActionPerformed(evt);
            }
        });
        jMenu6.add(btnPlata);

        btnBronce.setText("Bronce");
        btnBronce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBronceActionPerformed(evt);
            }
        });
        jMenu6.add(btnBronce);

        menuPrincipal.add(jMenu6);

        jMenu8.setText("ENVIOS");

        btnEnvios.setText("Envios");
        btnEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviosActionPerformed(evt);
            }
        });
        jMenu8.add(btnEnvios);

        menuPrincipal.add(jMenu8);

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/exit.png"))); // NOI18N
        btnCerrarSesion.setText("CERRAR SESION");
        btnCerrarSesion.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseClicked(evt);
            }
        });
        menuPrincipal.add(btnCerrarSesion);

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(deskPnlContenedor)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deskPnlContenedor)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        //Instancia formulario
        FrmProducto producto = new FrmProducto();
        //Invocacion de metodo para abrir formulario
        abrirFrm(producto);
    }//GEN-LAST:event_btnProductoActionPerformed

    private void btnCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarritoActionPerformed
        FrmCarrito carrito = new FrmCarrito();
        abrirFrm(carrito);
    }//GEN-LAST:event_btnCarritoActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        FrmCategoria categoria=new FrmCategoria();
        abrirFrm(categoria);
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void btnMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarcaActionPerformed
        FrmMarca marca = new FrmMarca();
        abrirFrm(marca);
    }//GEN-LAST:event_btnMarcaActionPerformed

    private void btnProductosIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosIActionPerformed
        FrmProductoInicio productoI =new FrmProductoInicio();
        abrirFrm(productoI);
    }//GEN-LAST:event_btnProductosIActionPerformed

    private void btnPortadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPortadaActionPerformed
        FrmPortada portada = new FrmPortada();
        abrirFrm(portada);
    }//GEN-LAST:event_btnPortadaActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        FrmUsuario usuario = new FrmUsuario();
        abrirFrm(usuario);
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void btnTipoSuscriptorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoSuscriptorActionPerformed
        FrmTipoSuscriptor tiposuscriptor = new FrmTipoSuscriptor();
        abrirFrm(tiposuscriptor);
    }//GEN-LAST:event_btnTipoSuscriptorActionPerformed

    private void btnVinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVinoActionPerformed
        FrmVino vino = new FrmVino();
        abrirFrm(vino);
    }//GEN-LAST:event_btnVinoActionPerformed

    private void btnCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseClicked
        FrmLogin login = new FrmLogin();
        login.show();
        this.hide();
    }//GEN-LAST:event_btnCerrarSesionMouseClicked

    private void btnPublicidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublicidadActionPerformed
        FrmPublicidad publicidad = new FrmPublicidad();
        abrirFrm(publicidad);
    }//GEN-LAST:event_btnPublicidadActionPerformed

    private void btnLicorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLicorActionPerformed
        FrmLicor licor = new FrmLicor();
        abrirFrm(licor);
    }//GEN-LAST:event_btnLicorActionPerformed

    private void btnCoberturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCoberturaActionPerformed
        FrmCobertura cobertura= new FrmCobertura();
        abrirFrm(cobertura);
    }//GEN-LAST:event_btnCoberturaActionPerformed

    private void btnBronceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBronceActionPerformed
        FrmSuscripcionBronce bronce = new FrmSuscripcionBronce();
        abrirFrm(bronce);
    }//GEN-LAST:event_btnBronceActionPerformed

    private void btnOroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOroActionPerformed
        FrmSuscripcionOro oro = new FrmSuscripcionOro();
        abrirFrm(oro);
    }//GEN-LAST:event_btnOroActionPerformed

    private void btnPlataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlataActionPerformed
        FrmSuscripcionPlata plata = new FrmSuscripcionPlata();
        abrirFrm(plata);
    }//GEN-LAST:event_btnPlataActionPerformed

    private void btnEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviosActionPerformed
        FrmEnvioProducto envios = new FrmEnvioProducto();
        abrirFrm(envios);
    }//GEN-LAST:event_btnEnviosActionPerformed



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
            java.util.logging.Logger.getLogger(FrmAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdministracion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnBronce;
    private javax.swing.JMenuItem btnCarrito;
    private javax.swing.JMenuItem btnCategoria;
    private javax.swing.JMenu btnCerrarSesion;
    private javax.swing.JMenuItem btnCobertura;
    private javax.swing.JMenuItem btnEnvios;
    private javax.swing.JMenuItem btnLicor;
    private javax.swing.JMenuItem btnMarca;
    private javax.swing.JMenu btnMenu;
    private javax.swing.JMenuItem btnOro;
    private javax.swing.JMenuItem btnPlata;
    private javax.swing.JMenuItem btnPortada;
    private javax.swing.JMenuItem btnProducto;
    private javax.swing.JMenuItem btnProductosI;
    private javax.swing.JMenuItem btnPublicidad;
    private javax.swing.JMenuItem btnTipoSuscriptor;
    private javax.swing.JMenuItem btnUsuario;
    private javax.swing.JMenuItem btnVino;
    private javax.swing.JDesktopPane deskPnlContenedor;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar menuPrincipal;
    // End of variables declaration//GEN-END:variables

}
