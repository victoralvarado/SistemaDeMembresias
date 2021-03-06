package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesCarrito;
import com.modelo.Carrito;
import com.sun.glass.events.KeyEvent;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoCarrito
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Aguillon, Alvarado, Luna, Rosales y Vasquez
 */
public class DaoCarrito extends Conexion implements OperacionesCarrito{

    @Override
    public List<Carrito> mostrarCarrito() throws Exception {
        List<Carrito> lst = new ArrayList();
        ResultSet rs;
        try {
            this.conectar();
            String sql = "select * from carrito;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Carrito ca = new Carrito();
                ca.setIdCarrito(rs.getInt("idCarrito"));
                ca.setIdSuscriptor(rs.getInt("idSuscriptor"));
                ca.setIdProducto(rs.getInt("idProducto"));
                ca.setCantidad(rs.getInt("cantidad"));
                lst.add(ca);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return lst;
    }
    
    @Override
    public List<Carrito> mostrarCarritoId(int idSuscriptor) throws Exception {
        List<Carrito> lst = new ArrayList();
        ResultSet rs;
        try {
            this.conectar();
            String sql = "select * from carrito where idSuscriptor = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idSuscriptor);
            rs = pre.executeQuery();
            while (rs.next()) {
                Carrito ca = new Carrito();
                ca.setIdCarrito(rs.getInt("idCarrito"));
                ca.setIdSuscriptor(rs.getInt("idSuscriptor"));
                ca.setIdProducto(rs.getInt("idProducto"));
                ca.setCantidad(rs.getInt("cantidad"));
                lst.add(ca);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return lst;
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
            Robot robot = new Robot();
            JOptionPane.showMessageDialog(null, "Se agrego el producto al carrito",
                    "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_ESCAPE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            desconectar();
        }
    }
    
    @Override
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
    public void eliminarCarrito(Carrito car) throws Exception {
        try {
            this.conectar();
            String sql = "delete from carrito where idCarrito=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, car.getIdCarrito());
            pre.executeUpdate();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public void eliminarTodo(int idSuscriptor) {
        try {
            this.conectar();
            String sql = "delete from carrito where idSuscriptor = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idSuscriptor);
            pre.executeUpdate();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            desconectar();
        }
    }

}
