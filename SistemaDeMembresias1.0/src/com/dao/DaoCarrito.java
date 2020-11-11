package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesCarrito;
import com.modelo.Carrito;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoCarrito
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author ever vasquez
 */
public class DaoCarrito extends Conexion implements OperacionesCarrito{

    @Override
    public List<Carrito> mostrarCarrito() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarCarrito(Carrito car) throws Exception {
        try {
            this.conectar();
            String sql = "insert into carrito(idSuscriptor,idProducto, cantidad) values(?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, car.getIdSuscriptor());
            pre.setInt(2, car.getIdProducto());
            pre.setInt(3, car.getCantidad());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se agrego el producto al carrito",
                    "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            desconectar();
        }
    }
    
    public int contarProdCar(int idSuscriptor) {
        ResultSet rs;
        int filas = 0;
        try {
            this.conectar();
            String sql = "select count(*) from carrito where idSuscriptor = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idSuscriptor);
            rs = pre.executeQuery();
            while (rs.next()) {
                filas = rs.getInt("count(*)");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al contar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            desconectar();
        }
        return filas;
    }

    @Override
    public void modificarCarrito(Carrito car) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarCarrito(Carrito car) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
