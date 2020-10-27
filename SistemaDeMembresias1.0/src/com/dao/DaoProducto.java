package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesProducto;
import com.modelo.Producto;
import com.utilidades.CustomImageIcon;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoProducto
 * Fecha: 09-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class DaoProducto extends Conexion implements OperacionesProducto{

    @Override
    public List<Producto> mostrarProducto() throws Exception {
        ResultSet rs;
        List<Producto> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from producto;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setIdMarca(rs.getInt("idMarca"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setStock(rs.getInt("stock"));
                p.setPrecioVenta(rs.getDouble("precioVenta"));
                p.setFecha(rs.getString("fecha"));
                lst.add(p);
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
    public void insertarProducto(Producto p) throws Exception {
        try {
            this.conectar();
            String sql = "insert into producto(idCategoria, idMarca,nombre, descripcion, imagen, stock, precioVenta, fecha) values(?,?,?,?,?,?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, p.getIdCategoria());
            pre.setInt(2, p.getIdCategoria());
            pre.setString(3, p.getNombre());
            pre.setString(4, p.getDescripcion());
            pre.setBinaryStream(5, p.getImagen());
            pre.setInt(6, p.getStock());
            pre.setDouble(7, p.getPrecioVenta());
            pre.setString(8, p.getFecha());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente",
                    "Insertar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void modificarProducto(Producto p) throws Exception {
        try {
            this.conectar();
            String sql = "update producto set idCategoria = ?, nombre = ?, descripcion = ?, "
                    + "imagen = ?, stock = ?, precioVenta = ?, "
                    + "fecha = ? where idProducto = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, p.getIdCategoria());
            pre.setString(2, p.getNombre());
            pre.setString(3, p.getDescripcion());
            pre.setBinaryStream(4, p.getImagen());
            pre.setInt(5, p.getStock());
            pre.setDouble(6, p.getPrecioVenta());
            pre.setString(7, p.getFecha());
            pre.setInt(8, p.getIdProducto());
            pre.executeUpdate();
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
    public void eliminarProducto(Producto p) throws Exception {
        try {
            this.conectar();
            String sql = "delete from producto where idProducto = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, p.getIdProducto());
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
    
    @Override
    public CustomImageIcon getImagen(int id) throws Exception {
        ResultSet rs;
        CustomImageIcon ii = null;
        InputStream is = null;
        try {
            this.conectar();
            String sql = "select imagen from producto where idProducto = " + id + ";";
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
}
