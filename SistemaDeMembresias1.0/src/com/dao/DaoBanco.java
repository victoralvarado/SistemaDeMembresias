
package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesBanco;
import com.modelo.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *Nombre de la clase: DaoBanco
 *Fecha: 14/11/2020
 *CopyRigth:
 *Version:0.1
 * @author 
 */
public class DaoBanco extends Conexion implements OperacionesBanco {

    @Override
    public List<Banco> mostrarBancos() throws Exception {
         List<Banco>listaBanco;
        listaBanco = new ArrayList();
        ResultSet res;
        
        try
        {
            this.conectar();
            String sql="select * from banco";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            res= pre.executeQuery();
            
            while(res.next())
            {
                Banco ba = new Banco();
                ba.setIdBanco(res.getInt("idBanco"));
                ba.setNombre(res.getString("nombre"));
                listaBanco.add(ba);
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
        return listaBanco;
    }

    @Override
    public void insertarBanco(Banco ba) throws Exception {
        try
        {
            this.conectar();
            String sql="insert into banco values(?,?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, ba.getIdBanco());
            pre.setString(2, ba.getNombre());
            pre.executeUpdate();   
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al insertar"+
                    e.getMessage());
        }
    }

    @Override
    public void modificarBanco(Banco ba) throws Exception {
        try
        {
            this.conectar();
            String sql="update banco set nombre=? where idBanco=?";
             PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, ba.getNombre());
            pre.setInt(2, ba.getIdBanco());
            pre.executeUpdate();
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al modificar"+
                    e.getMessage());
        }
    }

    @Override
    public void eliminarBanco(Banco ba) throws Exception {
        try
        {
            this.conectar();
            String sql = "delete from banco where idBanco=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, ba.getIdBanco());
            pre.executeUpdate();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al eliminar"+
                    e.getMessage());
        }
    }
    
    public Banco getBanco(int codigoBanco) throws Exception {
        Banco ba = new Banco();
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select * from banco where idBanco = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, codigoBanco);
            rs = pre.executeQuery();
            while(rs.next())
            {
                ba.setIdBanco(rs.getInt("idBanco"));
                ba.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
        return ba;
    }
    
}
