
package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesTipoSucriptor;
import com.modelo.TipoSucriptor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author manza
 */
public class DaoTipoSucriptor extends Conexion implements OperacionesTipoSucriptor{

    @Override
    public List<TipoSucriptor> mostrarTipoSus() throws Exception {
        
        List<TipoSucriptor>listaSus;
        listaSus = new ArrayList();
        ResultSet res;
        
        try
        {
            this.conectar();
            String sql="select * from tipoSus";
            PreparedStatement pre =  this.getCon().prepareStatement(sql);
            res= pre.executeQuery();
            
            while(res.next())
            {
                TipoSucriptor tSus = new TipoSucriptor();
                tSus.setTipoSuscriptor(res.getInt("tipoSuscriptor"));
                tSus.setNombre(res.getString("nombre"));
                tSus.setCosto(res.getDouble("costo"));
                tSus.setDetalle(res.getString("detalle"));
                listaSus.add(tSus);
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al mostrar"+
                    e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
        
        return listaSus;
    }

    @Override
    public void insertarTipoSus(TipoSucriptor tSus) throws Exception {
                try
        {
            this.conectar();
            String sql="insert into tipoSus values(?,?,?,?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, tSus.getTipoSuscriptor());
            pre.setString(2, tSus.getNombre());
            pre.setDouble(3, tSus.getCosto());
            pre.setString(4, tSus.getDetalle());
            pre.executeUpdate();   
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al insertar"+
                    e.getMessage());
        }
    }

    @Override
    public void modificarTipoSus(TipoSucriptor tSus) throws Exception {
        try
        {
            this.conectar();
            String sql="update tipoSus set nombre=?, costo = ?, detalle = ? where tipoSuscriptor=?";
             PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, tSus.getNombre());
            pre.setDouble(2, tSus.getCosto());
            pre.setString(3, tSus.getDetalle());
            pre.setInt(4, tSus.getTipoSuscriptor());
            pre.executeUpdate();
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al modificar"+
                    e.getMessage());
        }
        
    }

    @Override
    public void eliminarTipoSus(TipoSucriptor tSus) throws Exception {
        try {
            this.conectar();
            String sql = "delete from tipoSus where tipoSuscriptor=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, tSus.getTipoSuscriptor());
            pre.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar"
                    + e.getMessage());
        }

    }
    
    public TipoSucriptor getTipoSus(int tipoSuscriptor) {
        TipoSucriptor tSus = new TipoSucriptor();
        ResultSet res = null;
        try {
            this.conectar();
            String sql = "select * from tiposus where tipoSuscriptor = ? ";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, tipoSuscriptor);
            res = pre.executeQuery();
            while (res.next()) {
                tSus.setTipoSuscriptor(res.getInt("tipoSuscriptor"));
                tSus.setNombre(res.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos" + e.getMessage());
        } finally {
            this.desconectar();
        }
        return tSus;
    }
    
    public double getCosto(int id) {
        double costo = 0;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select costo from tiposus where tipoSuscriptor = ? ";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            while (rs.next()) {
                costo = rs.getDouble("costo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            this.desconectar();
        }
        return costo;
        
    }
}
