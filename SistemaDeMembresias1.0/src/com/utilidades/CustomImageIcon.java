package com.utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Nombre de la clase: CustomImageIcon
 * Fecha: 18-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class CustomImageIcon extends ImageIcon {

    public CustomImageIcon(String filename) {
        super(filename);
    }; 

    public CustomImageIcon(Image image) {
        super(image);
    }

    public CustomImageIcon(URL location) {
        super(location);
    }
  
    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(Color.white);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
        if (getImageObserver() == null) {
            g.drawImage(
                    getImage(),
                    0,
                    0,
                    c.getWidth(),
                    c.getHeight(),
                    c
            );
        } else {
            g.drawImage(
                    getImage(),
                    0,
                    0,
                    c.getWidth(),
                    c.getHeight(),
                    getImageObserver()
            );
        }
    }
}
