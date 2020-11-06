package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesUsuario;
import com.modelo.Usuario;
import com.utilidades.CustomImageIcon;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoUsuario
 * Fecha: 11-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class DaoUsuario extends Conexion implements OperacionesUsuario{

    @Override
    public List<Usuario> mostrarUsuario() throws Exception {
        ResultSet rs;
        List<Usuario> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from usuario;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setEmail(rs.getString("email"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setTipoUsuario(rs.getInt("tipoUsuario"));
                u.setPassword(rs.getString("password"));
                u.setEstado(rs.getInt("estado"));
                u.setUltimoLogin(rs.getString("ultimoLogin"));
                u.setFecha(rs.getString("fecha"));
                lst.add(u);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return lst;
    }
    
    
    public CustomImageIcon getFoto(int id) {
        ResultSet rs;
        CustomImageIcon ii = null;
        InputStream is = null;
        try {
            this.conectar();
            String sql = "select foto from usuario where idUsuario = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            if (rs.next()) {
                is = rs.getBinaryStream(1);
                if (is != null) {
                    BufferedImage bi = ImageIO.read(is);
                    ii = new CustomImageIcon(bi);
                }
            }
        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar imagen " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return ii;
    }

    @Override
    public void insertarUsuario(Usuario u) throws Exception {
        try {
            this.conectar();
            String sql = "insert into usuario(email,nombre,apellido,tipoUsuario,password,estado,foto,ultimoLogin,fecha) values(?,?,?,?,MD5(?),?,?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, u.getEmail());
            pre.setString(2, u.getNombre());
            pre.setString(3, u.getApellido());
            pre.setInt(4, u.getTipoUsuario());
            pre.setString(5, u.getPassword());
            pre.setInt(6, u.getEstado());
            pre.setBinaryStream(7, u.getFoto());
            pre.setString(8, u.getUltimoLogin());
            pre.setString(9, u.getFecha());
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
    public void modificarUsuario(Usuario u) throws Exception {
        try {
            this.conectar();
            if (u.getFoto() == null) {
                if (u.getPassword() == null) {
                    String sql = "update  usuario set email = ?, nombre = ?, apellido = ?, TipoUsuario = ?,"
                            + "estado = ?, ultimoLogin = ?, fecha = ? where idUsuario = ?;";
                    PreparedStatement pre = this.getCon().prepareStatement(sql);
                    pre.setString(1, u.getEmail());
                    pre.setString(2, u.getNombre());
                    pre.setString(3, u.getApellido());
                    pre.setInt(4, u.getTipoUsuario());
                    pre.setInt(5, u.getEstado());
                    pre.setString(6, u.getUltimoLogin());
                    pre.setString(7, u.getFecha());
                    pre.setInt(8, u.getIdUsuario());
                    pre.executeUpdate();
                } else {
                    String sql = "update  usuario set email = ?, nombre = ?, apellido = ?, TipoUsuario = ?, password = MD5(?),"
                            + "estado = ?, ultimoLogin = ?, fecha = ? where idUsuario = ?;";
                    PreparedStatement pre = this.getCon().prepareStatement(sql);
                    pre.setString(1, u.getEmail());
                    pre.setString(2, u.getNombre());
                    pre.setString(3, u.getApellido());
                    pre.setInt(4, u.getTipoUsuario());
                    pre.setString(5, u.getPassword());
                    pre.setInt(6, u.getEstado());
                    pre.setString(7, u.getUltimoLogin());
                    pre.setString(8, u.getFecha());
                    pre.setInt(9, u.getIdUsuario());
                    pre.executeUpdate();
                }

            } else {
                if (u.getPassword() == null) {
                    String sql = "update  usuario set email = ?, nombre = ?, apellido = ?, TipoUsuario = ?,"
                            + "estado = ?, foto = ?, ultimoLogin = ?, fecha = ? where idUsuario = ?;";
                    PreparedStatement pre = this.getCon().prepareStatement(sql);
                    pre.setString(1, u.getEmail());
                    pre.setString(2, u.getNombre());
                    pre.setString(3, u.getApellido());
                    pre.setInt(4, u.getTipoUsuario());
                    pre.setInt(5, u.getEstado());
                    pre.setBinaryStream(6, u.getFoto());
                    pre.setString(7, u.getUltimoLogin());
                    pre.setString(8, u.getFecha());
                    pre.setInt(9, u.getIdUsuario());
                    pre.executeUpdate();
                } else {
                    String sql = "update  usuario set email = ?, nombre = ?, apellido = ?, TipoUsuario = ?, password = MD5(?),"
                            + "estado = ?, foto = ?, ultimoLogin = ?, fecha = ? where idUsuario = ?;";
                    PreparedStatement pre = this.getCon().prepareStatement(sql);
                    pre.setString(1, u.getEmail());
                    pre.setString(2, u.getNombre());
                    pre.setString(3, u.getApellido());
                    pre.setInt(4, u.getTipoUsuario());
                    pre.setString(5, u.getPassword());
                    pre.setInt(6, u.getEstado());
                    pre.setBinaryStream(7, u.getFoto());
                    pre.setString(8, u.getUltimoLogin());
                    pre.setString(9, u.getFecha());
                    pre.setInt(10, u.getIdUsuario());
                    pre.executeUpdate();
                }
            }
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
    public void eliminarUsuario(Usuario u) throws Exception {
        try {
            this.conectar();
            String sql = "delete from  usuario where idUsuario = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, u.getIdUsuario());
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
