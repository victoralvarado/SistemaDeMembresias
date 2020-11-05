package com.dao;

import com.conexion.Conexion;
import com.modelo.Portada;
import com.utilidades.CustomImageIcon;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoPortada
 * Fecha: 04-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class DaoPortada extends Conexion{
    public CustomImageIcon getImagen(int id) throws Exception {
        ResultSet rs;
        CustomImageIcon ii = null;
        InputStream is = null;
        try {
            this.conectar();
            String sql = "select imagen from portada where codigo = " + id + ";";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
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
    
    public void insertarPortada(Portada p) throws Exception {
        try {
            this.conectar();
            String sql = "insert into portada(imagen) values(?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setBinaryStream(1, p.getImagen());
             pre.executeUpdate();
             JOptionPane.showMessageDialog(null, "Portada insertada correctamente",
                    "Insertar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
    }
    
    public void modificarPortada(Portada p) throws Exception {
        try {
            this.conectar();
            String sql = "update portada set imagen = ? where codigo = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setBinaryStream(1, p.getImagen());
            pre.setInt(2, p.getCodigo());
             pre.executeUpdate();
             JOptionPane.showMessageDialog(null, "Portada modificada correctamente",
                    "Modificar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
    }
}
