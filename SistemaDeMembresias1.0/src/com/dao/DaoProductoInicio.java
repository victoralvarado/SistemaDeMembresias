package com.dao;

import com.conexion.Conexion;
import com.modelo.ProductoInicio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoProductoInicio
 * Fecha: 01-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class DaoProductoInicio extends Conexion{
    public List<ProductoInicio> mostrarPInicio() throws Exception {
        ResultSet rs;
        List<ProductoInicio> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from productoInicio;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {                
                ProductoInicio pi= new ProductoInicio();
                pi.setCodigo(rs.getInt("codigo"));
                pi.setIdProducto(rs.getInt("idProducto"));
                lst.add(pi);
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
    
    public void insertarPInicio(ProductoInicio pi) throws Exception {
        try {
            this.conectar();
            String sql = "insert into productoInicio values(?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,pi.getCodigo());
            pre.setInt(2,pi.getIdProducto());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente",
                    "Insertar", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            this.desconectar();
        }
    }
    
    public void modificarPInicio(ProductoInicio pi) throws Exception {
        try {
            this.conectar();
            String sql = "update productoInicio set idProducto = ? where codigo = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,pi.getIdProducto());
            pre.setInt(2,pi.getCodigo());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos modificados correctamente",
                    "Modificar", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Modificar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            this.desconectar();
        }
    }
    
    public void eliminarPInicio(ProductoInicio pi) throws Exception {
        try {
            this.conectar();
            String sql = "delete from productoInicio  where codigo = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,pi.getCodigo());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos eliminados correctamente",
                    "Eliminar", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            this.desconectar();
        }
    }
    
    public ProductoInicio getIdPrducto(int codigo) {
        ProductoInicio pr = new ProductoInicio();
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select * from productoInicio where codigo = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, codigo);
            rs = pre.executeQuery();
            while(rs.next())
            {
                pr.setCodigo(rs.getInt("codigo"));
                pr.setIdProducto(rs.getInt("idProducto"));
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
}
