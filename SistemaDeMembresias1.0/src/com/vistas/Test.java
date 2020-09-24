package com.vistas;

import com.conexion.Conexion;
import javax.swing.JOptionPane;

public class Test {
    public static void main(String[] args) {
        Conexion con = new Conexion();
        JOptionPane.showMessageDialog(null, "Conecto? "+con.conectar());
    }
}