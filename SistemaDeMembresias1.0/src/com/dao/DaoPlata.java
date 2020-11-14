
package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesPlata;
import com.modelo.Plata;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

 /**
 * Nombre de la clase: DaoPlata
 * Fecha: 11-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Ever-Garcia
 */
public class DaoPlata extends Conexion implements OperacionesPlata {

    @Override
    public List<Plata> mostrarSuscripPlata() throws Exception {
        ResultSet rs;
        List<Plata> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from plata";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {                
                Plata pl= new Plata();
                pl.setIdPlata(rs.getInt("idPlata"));
                pl.setIdProducto(rs.getInt("idProducto"));     
                lst.add(pl);
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
    public void insertarSuscripPlata(Plata pl) throws Exception {
        try {
            this.conectar();
            String sql = "insert into plata(idProducto) values (?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,pl.getIdProducto());
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
    public void modificarSuscripPlata(Plata pl) throws Exception {
        try {
            this.conectar();
            String sql = "update plata set idProducto = ? where idPlata = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,pl.getIdProducto());
            pre.setInt(2,pl.getIdPlata());
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
    public void eliminarSuscripPlata(Plata pl) throws Exception {
        try {
            this.conectar();
            String sql = "delete from plata  where idPlata = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,pl.getIdPlata());
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
    public Plata getIdPrducto(int idPlata) {
        Plata pl = new Plata();
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select * from plata where idPlata = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idPlata);
            rs = pre.executeQuery();
            while(rs.next())
            {
                pl.setIdPlata(rs.getInt("idPlata"));
                pl.setIdProducto(rs.getInt("idProducto"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
        return pl;
    }

   
}
