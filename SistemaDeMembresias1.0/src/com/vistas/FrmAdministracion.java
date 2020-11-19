package com.vistas;

import com.dao.DaoUsuario;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 * Nombre de la clase: FrmAdministracion
 * Fecha: 17-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class FrmAdministracion extends javax.swing.JFrame {
    DaoUsuario daou = new DaoUsuario();
    JLabel email = new JLabel();
    public FrmAdministracion() {
        initComponents();
        //Iniciar maximixado FrmAdministracion
        this.setExtendedState(MAXIMIZED_BOTH);
    }
    public FrmAdministracion(String email) {
        initComponents();
        //Iniciar maximixado FrmAdministracion
        this.setExtendedState(MAXIMIZED_BOTH);
        if (daou.tipoUsuario(email)== 2) {
            menuMantenimiento.setVisible(false);
            menuCobertura.setVisible(false);
        }
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
        FrmSociosRegistrado socio = new FrmSociosRegistrado();
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
            socio.setVisible(false);
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
        menuMantenimiento = new javax.swing.JMenu();
        btnProducto = new javax.swing.JMenuItem();
        btnCarrito = new javax.swing.JMenuItem();
        btnCategoria = new javax.swing.JMenuItem();
        btnMarca = new javax.swing.JMenuItem();
        btnUsuario = new javax.swing.JMenuItem();
        btnTipoSuscriptor = new javax.swing.JMenuItem();
        menuTablas = new javax.swing.JMenu();
        btnSocios = new javax.swing.JMenuItem();
        btnEnvioT = new javax.swing.JMenuItem();
        menuInicio = new javax.swing.JMenu();
        btnProductosI = new javax.swing.JMenuItem();
        btnPortada = new javax.swing.JMenuItem();
        menuPoductos = new javax.swing.JMenu();
        btnVino = new javax.swing.JMenuItem();
        btnLicor = new javax.swing.JMenuItem();
        menuPublicida = new javax.swing.JMenu();
        btnPublicidad = new javax.swing.JMenuItem();
        menuCobertura = new javax.swing.JMenu();
        btnCobertura = new javax.swing.JMenuItem();
        menuProductoSuscripcion = new javax.swing.JMenu();
        btnOro = new javax.swing.JMenuItem();
        btnPlata = new javax.swing.JMenuItem();
        btnBronce = new javax.swing.JMenuItem();
        menuEnvios = new javax.swing.JMenu();
        btnEnvios = new javax.swing.JMenuItem();
        menuBancos = new javax.swing.JMenu();
        btnBancos = new javax.swing.JMenuItem();
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

        menuMantenimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        menuMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/mantenimiento.png"))); // NOI18N
        menuMantenimiento.setText("MANTENIMIENTO");
        menuMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMantenimientoActionPerformed(evt);
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
        menuMantenimiento.add(btnProducto);

        btnCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/carrito.png"))); // NOI18N
        btnCarrito.setText("Carrito");
        btnCarrito.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarritoActionPerformed(evt);
            }
        });
        menuMantenimiento.add(btnCarrito);

        btnCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/categoria.png"))); // NOI18N
        btnCategoria.setText("Categoria");
        btnCategoria.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });
        menuMantenimiento.add(btnCategoria);

        btnMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/marca.png"))); // NOI18N
        btnMarca.setText("Marca");
        btnMarca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarcaActionPerformed(evt);
            }
        });
        menuMantenimiento.add(btnMarca);

        btnUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/usuario.png"))); // NOI18N
        btnUsuario.setText("Usuario");
        btnUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });
        menuMantenimiento.add(btnUsuario);

        btnTipoSuscriptor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/tiposus.png"))); // NOI18N
        btnTipoSuscriptor.setText("Tipo Suscriptor");
        btnTipoSuscriptor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTipoSuscriptor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoSuscriptorActionPerformed(evt);
            }
        });
        menuMantenimiento.add(btnTipoSuscriptor);

        menuPrincipal.add(menuMantenimiento);

        menuTablas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        menuTablas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/tablas.png"))); // NOI18N
        menuTablas.setText("TABLAS Y REPORTES");

        btnSocios.setText("Socios Registrados");
        btnSocios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSociosActionPerformed(evt);
            }
        });
        menuTablas.add(btnSocios);

        btnEnvioT.setText("Consultas de envios");
        btnEnvioT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnvioTActionPerformed(evt);
            }
        });
        menuTablas.add(btnEnvioT);

        menuPrincipal.add(menuTablas);

        menuInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/inicio.png"))); // NOI18N
        menuInicio.setText("INICIO");

        btnProductosI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/producto.png"))); // NOI18N
        btnProductosI.setText("Productos");
        btnProductosI.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProductosI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosIActionPerformed(evt);
            }
        });
        menuInicio.add(btnProductosI);

        btnPortada.setText("Portada");
        btnPortada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPortadaActionPerformed(evt);
            }
        });
        menuInicio.add(btnPortada);

        menuPrincipal.add(menuInicio);

        menuPoductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/wine_freezer (1).png"))); // NOI18N
        menuPoductos.setText("PRODUCTOS");

        btnVino.setText("Vino");
        btnVino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVinoActionPerformed(evt);
            }
        });
        menuPoductos.add(btnVino);

        btnLicor.setText("Licor");
        btnLicor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLicorActionPerformed(evt);
            }
        });
        menuPoductos.add(btnLicor);

        menuPrincipal.add(menuPoductos);

        menuPublicida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/advertising.png"))); // NOI18N
        menuPublicida.setText("PUBLICIDAD");

        btnPublicidad.setText("Plubicidad");
        btnPublicidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublicidadActionPerformed(evt);
            }
        });
        menuPublicida.add(btnPublicidad);

        menuPrincipal.add(menuPublicida);

        menuCobertura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/route_map.png"))); // NOI18N
        menuCobertura.setText("COBERTURA");

        btnCobertura.setText("Cobertura");
        btnCobertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCoberturaActionPerformed(evt);
            }
        });
        menuCobertura.add(btnCobertura);

        menuPrincipal.add(menuCobertura);

        menuProductoSuscripcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/ProdSuscriptor.png"))); // NOI18N
        menuProductoSuscripcion.setText("PRODUCTO SUCRIPCION");

        btnOro.setText("Oro");
        btnOro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOroActionPerformed(evt);
            }
        });
        menuProductoSuscripcion.add(btnOro);

        btnPlata.setText("Plata");
        btnPlata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlataActionPerformed(evt);
            }
        });
        menuProductoSuscripcion.add(btnPlata);

        btnBronce.setText("Bronce");
        btnBronce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBronceActionPerformed(evt);
            }
        });
        menuProductoSuscripcion.add(btnBronce);

        menuPrincipal.add(menuProductoSuscripcion);

        menuEnvios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/enviosA.png"))); // NOI18N
        menuEnvios.setText("ENVIOS");

        btnEnvios.setText("Envios");
        btnEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviosActionPerformed(evt);
            }
        });
        menuEnvios.add(btnEnvios);

        menuPrincipal.add(menuEnvios);

        menuBancos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/banco.png"))); // NOI18N
        menuBancos.setText("BANCOS");

        btnBancos.setText("Bancos");
        btnBancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBancosActionPerformed(evt);
            }
        });
        menuBancos.add(btnBancos);

        menuPrincipal.add(menuBancos);

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

    private void menuMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMantenimientoActionPerformed
        
    }//GEN-LAST:event_menuMantenimientoActionPerformed

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

    private void btnBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBancosActionPerformed
        FrmBanco banco = new FrmBanco();
        abrirFrm(banco);
    }//GEN-LAST:event_btnBancosActionPerformed

    private void btnSociosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSociosActionPerformed
        FrmSociosRegistrado socio = new FrmSociosRegistrado();
        abrirFrm(socio);
    }//GEN-LAST:event_btnSociosActionPerformed

    private void btnEnvioTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnvioTActionPerformed
        FrmEnviosRealizados env = new FrmEnviosRealizados();
        abrirFrm(env);
    }//GEN-LAST:event_btnEnvioTActionPerformed



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
    private javax.swing.JMenuItem btnBancos;
    private javax.swing.JMenuItem btnBronce;
    private javax.swing.JMenuItem btnCarrito;
    private javax.swing.JMenuItem btnCategoria;
    private javax.swing.JMenu btnCerrarSesion;
    private javax.swing.JMenuItem btnCobertura;
    private javax.swing.JMenuItem btnEnvioT;
    private javax.swing.JMenuItem btnEnvios;
    private javax.swing.JMenuItem btnLicor;
    private javax.swing.JMenuItem btnMarca;
    private javax.swing.JMenuItem btnOro;
    private javax.swing.JMenuItem btnPlata;
    private javax.swing.JMenuItem btnPortada;
    private javax.swing.JMenuItem btnProducto;
    private javax.swing.JMenuItem btnProductosI;
    private javax.swing.JMenuItem btnPublicidad;
    private javax.swing.JMenuItem btnSocios;
    private javax.swing.JMenuItem btnTipoSuscriptor;
    private javax.swing.JMenuItem btnUsuario;
    private javax.swing.JMenuItem btnVino;
    private javax.swing.JDesktopPane deskPnlContenedor;
    private javax.swing.JMenu menuBancos;
    private javax.swing.JMenu menuCobertura;
    private javax.swing.JMenu menuEnvios;
    private javax.swing.JMenu menuInicio;
    private javax.swing.JMenu menuMantenimiento;
    private javax.swing.JMenu menuPoductos;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JMenu menuProductoSuscripcion;
    private javax.swing.JMenu menuPublicida;
    private javax.swing.JMenu menuTablas;
    // End of variables declaration//GEN-END:variables

}
