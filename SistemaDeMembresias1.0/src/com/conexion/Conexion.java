package com.conexion;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: Conexion
 * Fecha: 24-09-2020
 * Versión: 1.0
 * CopyRight: 
 * @author Victor, Adrian, Andrea, Ever, Roberto
 */

public class Conexion {
    private Connection con;

    public Connection getCon() {
        return con;
    }
    
    public boolean conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdsistemademembresias?", "root", "Itca123!");
            //Cambiar la contraseña del usuario root si es diferente
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al conectar "+e.getMessage(),"Error",0);
            return false;
        }
    }
    
    public boolean desconectar() {
        try {
            if(con!=null)
            {
                if(con.isClosed()==false)
                {
                    con.close();
                }
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al desconectar "+e.getMessage(),"Error",0);
            return false;
        }
    }
}