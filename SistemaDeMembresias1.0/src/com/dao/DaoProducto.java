package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesProducto;
import com.modelo.Producto;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setIdMarca(rs.getInt("idMarca"));
                p.setStock(rs.getInt("stock"));
                p.setPrecioCompra(rs.getDouble("precioCompra"));
                p.setPrecioVenta(rs.getDouble("precioVenta"));
                p.setFecha(rs.getString("fecha"));
                lst.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
        return lst;
    }

    @Override
    public void insertarProducto(Producto p) throws Exception {
        try {
            this.conectar();
            String sql = "insert into departamento values(?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, p.getIdProducto());
            pre.setInt(2, p.getIdCategoria());
            pre.setString(3, p.getNombre());
            pre.setString(4, p.getDescripcion());
            pre.setInt(5, p.getIdMarca());
            pre.setBinaryStream(6, p.getImagen());
            pre.setInt(7, p.getStock());
            pre.setDouble(8, p.getPrecioCompra());
            pre.setDouble(9, p.getPrecioVenta());
            pre.setString(10, p.getFecha());
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
    public void modificarProducto(Producto p) throws Exception {
        try {
            this.conectar();
            String sql = "update producto set idCategoria = ?, nombre = ?, descripcion = ?, "
                    + "idMarca = ?, imagen = ?, stock = ?, precioCompra = ?, precioVenta = ?, "
                    + "fecha = ? where idProducto = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, p.getIdCategoria());
            pre.setString(2, p.getNombre());
            pre.setString(3, p.getDescripcion());
            pre.setInt(4, p.getIdMarca());
            pre.setBinaryStream(5, p.getImagen());
            pre.setInt(6, p.getStock());
            pre.setDouble(7, p.getPrecioCompra());
            pre.setDouble(8, p.getPrecioVenta());
            pre.setString(9, p.getFecha());
            pre.setInt(10, p.getIdProducto());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato modificado correctamente",
                    "Modificar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
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
        }
        finally {
            this.desconectar();
        }
    }
    
}
