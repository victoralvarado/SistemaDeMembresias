package com.dao;

import com.conexion.Conexion;
import com.modelo.Vino;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoVino
 * Fecha: 07-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class DaoVino extends Conexion{
    public List<Vino> mostrarVino() throws Exception {
        ResultSet rs;
        List<Vino> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from vino;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {                
                Vino vi= new Vino();
                vi.setIdVino(rs.getInt("idVino"));
                vi.setIdProducto(rs.getInt("idProducto"));
                lst.add(vi);
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
    
    public void insertarVino(Vino vi) throws Exception {
        try {
            this.conectar();
            String sql = "insert into vino values(?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,vi.getIdVino());
            pre.setInt(2,vi.getIdProducto());
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
    
    public void modificarVino(Vino vi) throws Exception {
        try {
            this.conectar();
            String sql = "update vino set idProducto = ? where idVino = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,vi.getIdProducto());
            pre.setInt(2,vi.getIdVino());
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
    
    public void eliminarVino(Vino vi) throws Exception {
        try {
            this.conectar();
            String sql = "delete from vino  where idVino = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,vi.getIdVino());
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
    
    public Vino getIdVino(int idVino) {
        Vino vi = new Vino();
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select * from vino where idVino = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idVino);
            rs = pre.executeQuery();
            while(rs.next())
            {
                vi.setIdVino(rs.getInt("idVino"));
                vi.setIdProducto(rs.getInt("idProducto"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
        return vi;
    }
}
