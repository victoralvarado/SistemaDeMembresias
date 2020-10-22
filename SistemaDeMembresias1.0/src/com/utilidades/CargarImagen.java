package com.utilidades;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * Nombre de la clase: CargarImagen
 * Fecha: 07-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class CargarImagen extends javax.swing.JPanel{
    ImageIcon imagen;
    String nombre;

    public CargarImagen(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void paint(Graphics g) {
        Dimension tamanio = getSize();
        imagen = new ImageIcon(getClass().getResource(nombre));
        g.drawImage(imagen.getImage(), 0, 0, tamanio.width,tamanio.height,null);
        setOpaque(false);
        super.paint(g);
    }
}
