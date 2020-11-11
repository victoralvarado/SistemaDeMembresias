package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesUsuario;
import com.modelo.Usuario;
import com.utilidades.CustomImageIcon;
import com.vistas.FrmAdministracion;
import com.vistas.FrmPrincipal;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            if (u.getIdUsuario() == 1) {
                JOptionPane.showMessageDialog(null, "No se puede eliminar el administrador principal",
                        "Eliminar", JOptionPane.WARNING_MESSAGE);
            } else {
                this.conectar();
                String sql = "delete from  usuario where idUsuario = ?;";
                PreparedStatement pre = this.getCon().prepareStatement(sql);
                pre.setInt(1, u.getIdUsuario());
                pre.executeUpdate();
                JOptionPane.showMessageDialog(null, "Datos eliminados correctamente",
                        "Eliminar", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }

    @Override
    public boolean login(Usuario us) {
        ResultSet rs;
        boolean estado = false;
        int nivel = 0;
        int es = 0;
        FrmAdministracion adm= new FrmAdministracion();
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
        try {
            this.conectar();
            String sql = "select * from usuario where  email= ? && password = MD5(?)";
            PreparedStatement pre = this.getCon().prepareCall(sql);
            pre.setString(1, us.getEmail());
            pre.setString(2, us.getPassword());
            rs = pre.executeQuery();
            while (rs.next()) {
                estado = true;
                es = rs.getInt("estado");
                nivel = rs.getInt("tipoUsuario");
            }
            switch (es) {
                case 1:
                    if (nivel == 1) {
                        //Administrador
                        String sqlM = "update usuario set ultimoLogin = ? where email = ?;";
                        PreparedStatement preM = this.getCon().prepareStatement(sqlM);
                        preM.setString(1, String.valueOf(hourdateFormat.format(date)));
                        preM.setString(2, us.getEmail());
                        preM.executeUpdate();
                        adm.show();
                    }
                    if (nivel == 2) {
                        //Editor
                        String sqlM = "update usuario set ultimoLogin = ? where email = ?;";
                        PreparedStatement preM = this.getCon().prepareStatement(sqlM);
                        preM.setString(1, String.valueOf(hourdateFormat.format(date)));
                        preM.setString(2, us.getEmail());
                        preM.executeUpdate();
                        adm.show();
                    }
                    if (nivel == 3) {
                        //Suscriptor
                        String sqlM = "update usuario set ultimoLogin = ? where email = ?;";
                        PreparedStatement preM = this.getCon().prepareStatement(sqlM);
                        preM.setString(1, String.valueOf(hourdateFormat.format(date)));
                        preM.setString(2, us.getEmail());
                        preM.executeUpdate();
                        FrmPrincipal suscrip = new FrmPrincipal(us.getEmail(), consultarId(us.getEmail()));
                        suscrip.show();
                    }
                    break;
                case 2:
                    if (nivel == 1 || nivel == 2) {
                        JOptionPane.showMessageDialog(null, "Su cuenta se encuentra inactiva.\n"
                                + "Comuniquese con el administrador para volver a activarla",
                                "Login", JOptionPane.WARNING_MESSAGE);
                    }
                    if (nivel == 3) {
                        //Suscriptor
                    }
                    break;
                default:
                    estado = false;
                    JOptionPane.showMessageDialog(null, "Correo y/o password incorrectos",
                            "Login", JOptionPane.WARNING_MESSAGE);
                    break;
            }
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error login " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return estado;
    }
    
    public int consultarId(String email) throws SQLException{
        ResultSet rs;
        int id = 0;
        try {
            String sql2 = "select idSuscriptor from suscriptor where email= ?";
            PreparedStatement pre = this.getCon().prepareCall(sql2);
            pre.setString(1, email);
            rs = pre.executeQuery();
            while (rs.next()) {
                id = rs.getInt("idSuscriptor");
            }
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Error login idSuscriptor " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
    
}
