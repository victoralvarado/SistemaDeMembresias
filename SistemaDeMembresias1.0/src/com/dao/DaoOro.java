
package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesOro;
import com.modelo.Oro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

 /**
 * Nombre de la clase: DaoOro
 * Fecha: 09-11-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Andrea Rosales
 */
public class DaoOro extends Conexion implements OperacionesOro {

    @Override
    public List<Oro> mostrarSusOro() throws Exception {
        ResultSet rs;
        List<Oro> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from oro";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {                
                Oro or= new Oro();
                or.setIdOro(rs.getInt("idOro"));
                or.setIdProducto(rs.getInt("idProducto"));     
                lst.add(or);
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
    public void insertarSusOro(Oro p) throws Exception {
        try {
            this.conectar();
            String sql = "insert into oro(idProducto) values (?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,p.getIdProducto());
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
    public void modificarSusOro(Oro p) throws Exception {
        try {
            this.conectar();
            String sql = "update oro set idProducto = ? where idOro = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,p.getIdProducto());
            pre.setInt(2,p.getIdOro());
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
    public void eliminarSusOro(Oro p) throws Exception {
        try {
            this.conectar();
            String sql = "delete from oro  where idOro = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,p.getIdOro());
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
    public Oro getIdPrducto(int idOro) {
        Oro pr = new Oro();
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select * from oro where idOro = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idOro);
            rs = pre.executeQuery();
            while(rs.next())
            {
                pr.setIdOro(rs.getInt("idOro"));
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
