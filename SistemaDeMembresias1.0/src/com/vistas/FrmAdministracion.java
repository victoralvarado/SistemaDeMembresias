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
        FrmOrden orden=new FrmOrden();
        FrmPortada portada = new FrmPortada();
        FrmUsuario usuario = new FrmUsuario();
        FrmProductoInicio productoI =new FrmProductoInicio();
        if (!jInternalFramesAbiertos()) {
            mostrarFrm(frm);
        }
        else {
            deskPnlContenedor.removeAll();
            producto.setVisible(false);
            carrito.setVisible(false);
            categoria.setVisible(false);
            orden.setVisible(false);
            marca.setVisible(false);
            productoI.setVisible(false);
            portada.setVisible(false);
            usuario.setVisible(false);
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
        jMenu1 = new javax.swing.JMenu();
        btnOrden = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnProductosI = new javax.swing.JMenuItem();
        btnPortada = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        deskPnlContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 2));

        javax.swing.GroupLayout deskPnlContenedorLayout = new javax.swing.GroupLayout(deskPnlContenedor);
        deskPnlContenedor.setLayout(deskPnlContenedorLayout);
        deskPnlContenedorLayout.setHorizontalGroup(
            deskPnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 794, Short.MAX_VALUE)
        );
        deskPnlContenedorLayout.setVerticalGroup(
            deskPnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
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

        menuPrincipal.add(btnMenu);

        jMenu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/tablas.png"))); // NOI18N
        jMenu1.setText("TABLAS");

        btnOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/orden.png"))); // NOI18N
        btnOrden.setText("Ordenes");
        btnOrden.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenActionPerformed(evt);
            }
        });
        jMenu1.add(btnOrden);

        menuPrincipal.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/inicio.png"))); // NOI18N
        jMenu2.setText("INICIO");

        btnProductosI.setText("Productos");
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

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deskPnlContenedor)
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

    private void btnOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenActionPerformed
        FrmOrden orden=new FrmOrden();
        abrirFrm(orden);
    }//GEN-LAST:event_btnOrdenActionPerformed

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
    private javax.swing.JMenuItem btnCarrito;
    private javax.swing.JMenuItem btnCategoria;
    private javax.swing.JMenuItem btnMarca;
    private javax.swing.JMenu btnMenu;
    private javax.swing.JMenuItem btnOrden;
    private javax.swing.JMenuItem btnPortada;
    private javax.swing.JMenuItem btnProducto;
    private javax.swing.JMenuItem btnProductosI;
    private javax.swing.JMenuItem btnUsuario;
    private javax.swing.JDesktopPane deskPnlContenedor;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar menuPrincipal;
    // End of variables declaration//GEN-END:variables

}
