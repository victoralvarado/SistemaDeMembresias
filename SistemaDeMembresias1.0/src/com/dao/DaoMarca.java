package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesMarca;
import com.modelo.Marca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoMarca
 * Fecha: 09-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author andrea rosales
 */
public class DaoMarca extends Conexion implements OperacionesMarca {

    @Override
    public List<Marca> mostrarMarca() throws Exception {
        
        List<Marca>listaMarca;
        listaMarca = new ArrayList();
        ResultSet res;
        
        try
        {
            this.conectar();
            String sql="select * from marca";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            res= pre.executeQuery();
            
            while(res.next())
            {
                Marca mar = new Marca();
                mar.setIdMarca(res.getInt("idMarca"));
                mar.setNombre(res.getString("nombre"));
                listaMarca.add(mar);
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
        
        return listaMarca;
        
    }

    @Override
    public void insertarMarca(Marca mar) throws Exception {
       
        try
        {
            this.conectar();
            String sql="insert into marca values(?,?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, mar.getIdMarca());
            pre.setString(2, mar.getNombre());
            pre.executeUpdate();   
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al insertar"+
                    e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
    }

    @Override
    public void modificarMarca(Marca mar) throws Exception {
        
        try
        {
            this.conectar();
            String sql="update marca set nombre=? where idMarca=?";
             PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, mar.getNombre());
            pre.setInt(2, mar.getIdMarca());
            pre.executeUpdate();
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al modificar"+
                    e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
    }

    @Override
    public void eliminarMarca(Marca mar) throws Exception {
        
        try
        {
            this.conectar();
            String sql = "delete from marca where idMarca=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, mar.getIdMarca());
            pre.executeUpdate();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al eliminar"+
                    e.getMessage());
        }
        finally
        {
            this.desconectar();
        }
    }
    
    
}
