package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesPublicidad;
import com.modelo.Publicidad;
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
 * Nombre de la clase: Publicidad
 * Fecha: 08/11/2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author adrian luna
 */

public class DaoPublicidad extends Conexion implements OperacionesPublicidad{

    @Override
    public List<Publicidad> mostrarPublicidad() throws Exception {
        ResultSet rs;
        List<Publicidad> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from publicidad;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {                
                Publicidad pub= new Publicidad();
                pub.setIdPublicidad(rs.getInt("idPublicidad"));
                pub.setUrl(rs.getString("url"));
                lst.add(pub);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally 
        {
            this.desconectar();
        }
        return lst;
    }
    
    public CustomImageIcon getFoto(int id) throws Exception{
        ResultSet rs;
        CustomImageIcon ii = null;
        InputStream is = null;
        try {
            this.conectar();
            String sql = "select foto from publicidad where idPublicidad = ?;";
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
    public void insertarPublicidad(Publicidad pub) throws Exception {
        try {
            this.conectar();
            String sql = "insert into publicidad values(?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,pub.getIdPublicidad());
            pre.setBinaryStream(2,pub.getFoto());
            pre.setString(3,pub.getUrl());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente",
                    "Insertar", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            this.desconectar();
        }
    }

    @Override
    public void modificarPublicidad(Publicidad pub) throws Exception {
        try {
            this.conectar();
            if (pub.getFoto() == null)
            {
                String sql = "update publicidad set url = ? where idPublicidad = ?;";
                PreparedStatement pre = this.getCon().prepareStatement(sql);
                pre.setString(1, pub.getUrl());
                pre.setInt(2, pub.getIdPublicidad());
                pre.executeUpdate();
            }else {
                String sql = "update publicidad set foto=?, url=? where idPublicidad=?;";
                PreparedStatement pre = this.getCon().prepareStatement(sql);
                pre.setBinaryStream(1, pub.getFoto());
                pre.setString(2, pub.getUrl());
                pre.setInt(3, pub.getIdPublicidad());
                pre.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "Dato modificado correctamente",
                    "Modificar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminarPublicidad(Publicidad pub) throws Exception {
        try {
            this.conectar();
            String sql = "delete from publicidad where idPublicidad=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, pub.getIdPublicidad());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato eliminado correctamente",
                    "Eliminar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }
    
    
}
