package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesVenta;
import com.modelo.Usuario;
import com.modelo.Venta;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
* Nombre de la clase: DaoVenta
* Fecha: 10-11-2020 
* CopyRigh: ITCA-FEPADE
* Versión: 1.0
* Autor: Roberto Aguillón
*/
public class DaoVenta extends Conexion implements OperacionesVenta{

    @Override
    public List<Venta> mostrarVenta() throws Exception {
        ResultSet rs;
        List<Venta> vent = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from venta;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Venta v = new Venta();
                v.setIdVenta(rs.getInt("idVenta"));
                v.setTotal(rs.getFloat("total"));
                v.setMetodoPago(rs.getString("metodoPago"));
                v.setFechaVenta(rs.getString("fecha"));                
                vent.add(v);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return vent;
    }

    @Override
    public void insertarVenta(Venta v) throws Exception {
        try {
            this.conectar();
            String sql = "insert into venta values(?,?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, v.getIdVenta());
            pre.setFloat(2, v.getTotal());
            pre.setString(3, v.getMetodoPago());
            pre.setString(4, v.getFechaVenta());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente",
                    "Insertar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
    }

    @Override
    public void modificarVenta(Venta v) throws Exception {
        try {
            this.conectar();
            String sql = "update  venta set total = ?, metodoPago = ?, fecha = ? where idVenta = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setFloat(1, v.getTotal());
            pre.setString(2, v.getMetodoPago());
            pre.setString(3, v.getFechaVenta());
            pre.setInt(4, v.getIdVenta());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos modificados correctamente",
                    "Modificar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminarVenta(Venta v) throws Exception {
        try {
            this.conectar();
            String sql = "delete from  venta where idVenta = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, v.getIdVenta());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos eliminados correctamente",
                    "Eliminar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }
    

}
