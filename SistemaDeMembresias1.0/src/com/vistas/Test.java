package com.vistas;

import com.conexion.Conexion;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: Test
 * Fecha: 24-09-2020
 * Versión: 1.0
 * CopyRight: 
 * @author Victor, Adrian, Andrea, Ever, Roberto
 */

public class Test {
    public static void main(String[] args) {
        Conexion con = new Conexion();
        JOptionPane.showMessageDialog(null, "Conecto? "+con.conectar());
    }
}