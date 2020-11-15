package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesBronce;
import com.modelo.Bronce;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
* Nombre de la clase: DaoBronce
* Fecha: 11-12-2020 
* CopyRigh: ROKE
* Versión: 1.0
* Autor: Roberto Aguillón
*/
public class DaoBronce extends Conexion implements OperacionesBronce{

    @Override
    public List<Bronce> mostrarSuscipcionBronce() throws Exception {
        ResultSet rs;
        List<Bronce> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from bronce";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {                
                Bronce b= new Bronce();
                b.setIdBronce(rs.getInt("idOro"));
                b.setIdProducto(rs.getInt("idProducto"));     
                lst.add(b);
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

    @Override
    public void insertarSuscipcionBronce(Bronce b) throws Exception {
        try {
            this.conectar();
            String sql = "insert into bronce (idProducto) values (?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,b.getIdProducto());
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

    @Override
    public void modificarSuscipcionBronce(Bronce b) throws Exception {
        try {
            this.conectar();
            String sql = "update bronce set idProducto = ? where idBronce = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,b.getIdProducto());
            pre.setInt(2,b.getIdBronce());
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

    @Override
    public void eliminarSuscipcionBronce(Bronce b) throws Exception {
        try {
            this.conectar();
            String sql = "delete from bronce where idBronce = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,b.getIdBronce());
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

    @Override
    public Bronce getIdProducto(int idBronce) {
        Bronce pr = new Bronce();
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select * from bronce where idBronce = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idBronce);
            rs = pre.executeQuery();
            while(rs.next())
            {
                pr.setIdBronce(rs.getInt("idBronce"));
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
