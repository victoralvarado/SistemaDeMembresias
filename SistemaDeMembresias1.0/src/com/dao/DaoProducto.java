package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesProducto;
import com.modelo.Producto;
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
                p.setTipo(rs.getString("tipo"));
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
    public List<Producto> mostrarVL(String tipo) throws Exception {
        ResultSet rs;
        List<Producto> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from producto where tipo = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, tipo);
            rs = pre.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setIdMarca(rs.getInt("idMarca"));
                p.setTipo(rs.getString("tipo"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioVenta(rs.getDouble("precioVenta"));
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
            String sql = "insert into producto(idCategoria, idMarca, tipo, nombre, descripcion, imagen, stock, precioVenta, fecha) values(?,?,?,?,?,?,?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, p.getIdCategoria());
            pre.setInt(2, p.getIdMarca());
            pre.setString(3, p.getTipo());
            pre.setString(4, p.getNombre());
            pre.setString(5, p.getDescripcion());
            pre.setBinaryStream(6, p.getImagen());
            pre.setInt(7, p.getStock());
            pre.setDouble(8, p.getPrecioVenta());
            pre.setString(9, p.getFecha());
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
            if (p.getImagen() == null) {
                String sql = "update producto set idCategoria = ?, idMarca = ?, tipo = ?, nombre = ?, descripcion = ?, "
                        + "stock = ?, precioVenta = ?, "
                        + "fecha = ? where idProducto = ?;";
                PreparedStatement pre = this.getCon().prepareStatement(sql);
                pre.setInt(1, p.getIdCategoria());
                pre.setInt(2, p.getIdMarca());
                pre.setString(3, p.getTipo());
                pre.setString(4, p.getNombre());
                pre.setString(5, p.getDescripcion());
                pre.setInt(6, p.getStock());
                pre.setDouble(7, p.getPrecioVenta());
                pre.setString(8, p.getFecha());
                pre.setInt(9, p.getIdProducto());
                pre.executeUpdate();
            } else {
                String sql = "update producto set idCategoria = ?, idMarca = ?, tipo = ?, nombre = ?, descripcion = ?, "
                        + "imagen = ?, stock = ?, precioVenta = ?, "
                        + "fecha = ? where idProducto = ?;";
                PreparedStatement pre = this.getCon().prepareStatement(sql);
                pre.setInt(1, p.getIdCategoria());
                pre.setInt(2, p.getIdMarca());
                pre.setString(3, p.getTipo());
                pre.setString(4, p.getNombre());
                pre.setString(5, p.getDescripcion());
                pre.setBinaryStream(6, p.getImagen());
                pre.setInt(7, p.getStock());
                pre.setDouble(8, p.getPrecioVenta());
                pre.setString(9, p.getFecha());
                pre.setInt(10, p.getIdProducto());
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
            String sql = "select imagen from producto where idProducto = ?;";
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
    
    public Producto getProducto(int codigoProducto) throws Exception {
        Producto pr = new Producto();
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select * from producto where idProducto = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, codigoProducto);
            rs = pre.executeQuery();
            while(rs.next())
            {
                pr.setIdProducto(rs.getInt("idProducto"));
                pr.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
        return pr;
    }
    
    
    public String info(String campo, int codigo) {
        String consulta ="";
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select "+campo+" from producto where idProducto = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, codigo);
            rs = pre.executeQuery();
            while(rs.next())
            {
                consulta = rs.getString(campo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            this.desconectar();
        }
        return consulta;
    }
    
    public List<Producto> buscarV(String por) throws Exception {
        ResultSet rs;
        List<Producto> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from producto where tipo = 'Vino' && nombre like ? or precioVenta like ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, por);
            pre.setString(2, por);
            rs = pre.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setIdMarca(rs.getInt("idMarca"));
                p.setTipo(rs.getString("tipo"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioVenta(rs.getDouble("precioVenta"));
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
    
    public List<Producto> buscarVP(double inicio, double fin) throws Exception {
        ResultSet rs;
        List<Producto> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from producto where precioVenta Between ? And ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setDouble(1, inicio);
            pre.setDouble(2, fin);
            rs = pre.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setIdMarca(rs.getInt("idMarca"));
                p.setTipo(rs.getString("tipo"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioVenta(rs.getDouble("precioVenta"));
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
    
    public int stock(int idProducto) {
        int stockP = 0;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select stock from producto where idProducto = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idProducto);
            rs = pre.executeQuery();
            while (rs.next()) {                
                stockP = rs.getInt("stock");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return stockP;
        
    }
    
    public void modificarStock(Producto p) {
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "update producto set stock = ? where idProducto = ?;";
                PreparedStatement pre = this.getCon().prepareStatement(sql);
                pre.setInt(1, p.getStock());
                pre.setInt(2, p.getIdProducto());
                pre.executeUpdate();
                JOptionPane.showMessageDialog(null, "Producto agregado al carrito",
                    "Carrito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        
    }
}
