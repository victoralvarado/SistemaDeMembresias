package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesOrdenDetalle;
import com.modelo.OrdenDetalle;
import com.modelo.Venta;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
* Nombre de la clase: DaoOrdenDetalle
* Fecha: 10-11-2020 
* CopyRigh: ITCA-FEPADE
* Versión: 1.0
* Autor: Roberto Aguillón
*/
public class DaoOrdenDetalle extends Conexion implements OperacionesOrdenDetalle{

    @Override
    public List<OrdenDetalle> mostrarDetalle() throws Exception {
        ResultSet rs;
        List<OrdenDetalle> od = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from ordenDetalle;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                OrdenDetalle o = new OrdenDetalle();
                o.setIdDetalle(rs.getInt("idDetalle"));
                o.setCantidad(rs.getInt("cantidad"));
                o.setPrecio(rs.getFloat("precio"));             
                od.add(o);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return od;
    }

    @Override
    public void insertarDetalle(OrdenDetalle o) throws Exception {
        try {
            this.conectar();
            String sql = "insert into ordenDetalle values(?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, o.getIdDetalle());
            pre.setInt(2, o.getCantidad());
            pre.setFloat(3, o.getPrecio());
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
    public void modificarDetalle(OrdenDetalle o) throws Exception {
        try {
            this.conectar();
            String sql = "update  ordenDetalle set cantidad = ?, precio = ? where idDetalle = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, o.getCantidad());
            pre.setFloat(2, o.getPrecio());
            pre.setInt(3, o.getIdDetalle());
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
    public void eliminarDetalle(OrdenDetalle o) throws Exception {
        try {
            this.conectar();
            String sql = "delete from  ordenDetalle where idDetalle = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, o.getIdDetalle());
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
