package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesUsuario;
import com.modelo.Usuario;
import com.vistas.FrmAdministracion;
import com.vistas.FrmPrincipal;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void insertarUsuario(Usuario u) throws Exception {
        try {
            this.conectar();
            String sql = "insert into usuario values(?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, u.getIdUsuario());
            pre.setString(2, u.getEmail());
            pre.setString(3, u.getNombre());
            pre.setString(4, u.getApellido());
            pre.setInt(5, u.getTipoUsuario());
            pre.setString(6, u.getPassword());
            pre.setInt(7, u.getEstado());
            pre.setBinaryStream(8, u.getFoto());
            pre.setString(9, u.getUltimoLogin());
            pre.setString(10, u.getFecha());
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
            String sql = "update  usuario set email = ?, nombre = ?, apellido = ?, TipoUsuario = ?, password = ?,"
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

    @Override
    public boolean login(Usuario us) throws Exception {
        ResultSet rs;
        boolean estado = false;
        int nivel = 0;
        FrmAdministracion adm= new FrmAdministracion();
        FrmPrincipal suscrip =  new FrmPrincipal();
        try {
            this.conectar();
            String sql = "select * from usuario where  email= ? && password = ?";
            PreparedStatement pre = this.getCon().prepareCall(sql);
            pre.setString(1, us.getEmail());
            pre.setString(2, us.getPassword());
            rs = pre.executeQuery();
            while (rs.next()) {
                estado = true;
                nivel = rs.getInt("tipoUsuario");
            }
            if (estado) {
                if (nivel == 1) {
                    //Administrador
                    adm.setVisible(true);
                }
                if(nivel == 2){
                    //Editor
                    adm.setVisible(true);
                }
                if (nivel == 3) {
                    //Suscriptor
                    suscrip.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "UserName y/o password incorrectos",
                        "Login", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return estado;
    }
    
}
