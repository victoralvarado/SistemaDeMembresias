package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private Connection con;

    public Connection getCon() {
        return con;
    }
    
    public boolean conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdsistemademembresias?", "root", "Itca123!");
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al conectar "+e.getMessage(),"Error",0);
            return false;
        }
    }
}