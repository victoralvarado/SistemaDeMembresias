package com.dao;

import com.conexion.Conexion;
import com.modelo.Licor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoLicor
 * Fecha: 08-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class DaoLicor extends Conexion{
    public List<Licor> mostrarLicor() throws Exception {
        ResultSet rs;
        List<Licor> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from licor;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {                
                Licor li= new Licor();
                li.setIdLicor(rs.getInt("idLicor"));
                li.setIdProducto(rs.getInt("idProducto"));
                lst.add(li);
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
    
    public void insertarLicor(Licor li) throws Exception {
        try {
            this.conectar();
            String sql = "insert into licor values(?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,li.getIdLicor());
            pre.setInt(2,li.getIdProducto());
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
    
    public void modificarLicor(Licor li) throws Exception {
        try {
            this.conectar();
            String sql = "update licor set idProducto = ? where idLicor = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,li.getIdProducto());
            pre.setInt(2,li.getIdLicor());
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
    
    public void eliminarLicor(Licor li) throws Exception {
        try {
            this.conectar();
            String sql = "delete from licor  where idLicor = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,li.getIdLicor());
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
    
    public Licor getIdLicor(int idLicor) {
        Licor li = new Licor();
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select * from licor where idLicor = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idLicor);
            rs = pre.executeQuery();
            while(rs.next())
            {
                li.setIdLicor(rs.getInt("idLicor"));
                li.setIdProducto(rs.getInt("idProducto"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
        return li;
    }
}
