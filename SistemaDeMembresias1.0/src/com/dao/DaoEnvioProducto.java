package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesEnvioProducto;
import com.modelo.EnvioProducto;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoEnvioProducto
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author ever vasquez
 */
public class DaoEnvioProducto extends Conexion implements OperacionesEnvioProducto{

    @Override
    public List<EnvioProducto> mostrarEnvioProducto() throws Exception {
        ResultSet rs;
        List<EnvioProducto> envio = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from envioProducto;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                EnvioProducto enp = new EnvioProducto();
                enp.setIdEnvio(rs.getInt("idEnvio"));
                enp.setIdSuscriptor(rs.getInt("idSuscriptor"));
                enp.setIdPersonaExterna(rs.getInt("idPersonaExterna"));
                enp.setFechaEnvio(rs.getString("fechaEnvio"));
                enp.setIdProducto(rs.getInt("idProducto"));
                enp.setDetalleEnvio(rs.getString("detalleEnvio"));
                enp.setEstado(rs.getInt("estado"));
                enp.setIdCobertura(rs.getInt("idCobertura"));
                envio.add(enp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return envio;
    }

   
    @Override
    public void insertarProducto(EnvioProducto env) throws Exception {
       try {
            this.conectar();
            String sql = "insert into envioProducto (idSuscriptor,idPersonaExterna,fechaEnvio,idProducto,detalleEnvio,estado,idCobertura) values(?,?,?,?,?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, env.getIdSuscriptor());
            pre.setInt(2, env.getIdPersonaExterna());
            pre.setString(3, env.getFechaEnvio());
            pre.setInt(4, env.getIdProducto());
            pre.setString(5, env.getDetalleEnvio());
            pre.setInt(6, env.getEstado());
            pre.setInt(7, env.getIdCobertura());
            pre.executeUpdate();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
    }

    @Override
    public void modificarProducto(EnvioProducto env) throws Exception {
        try {
            this.conectar();
            String sql = "update envioProducto  set idSuscriptor=?,  idPersonaExterna=?, fechaEnvio=?, idProducto=?, detalleEnvio=?, estado=?, idCobertura=? where idEnvio = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, env.getIdSuscriptor());
            pre.setInt(2, env.getIdPersonaExterna());
            pre.setString(4, env.getFechaEnvio());
            pre.setInt(4, env.getIdProducto());
            pre.setString(5, env.getDetalleEnvio());
            pre.setInt(6, env.getEstado());
            pre.setInt(7, env.getIdCobertura());
            pre.setInt(5, env.getIdEnvio());
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
    
    public void modificarFechaEstado(EnvioProducto env) throws Exception {
        try {
            this.conectar();
            String sql = "update envioProducto  set fechaEnvio=?, estado=? where idEnvio = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, env.getFechaEnvio());
            pre.setInt(2, env.getEstado());
            pre.setInt(3, env.getIdEnvio());
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
    public void eliminarProducto(EnvioProducto env) throws Exception {
        try {
            this.conectar();
            String sql = "delete from envioProducto where idEnvio = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, env.getIdEnvio());
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
