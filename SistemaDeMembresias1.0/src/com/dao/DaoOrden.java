
package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesOrden;
import com.modelo.Orden;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoOrden
 * Fecha: 19/10/2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Luna-
 */

public class DaoOrden extends Conexion implements OperacionesOrden{

    @Override
    public List<Orden> mostrarOrden() throws Exception {
        ResultSet rs;
        List<Orden> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from orden;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {                
                Orden ord= new Orden();
                ord.setIdOrden(rs.getInt("idOrden"));
                ord.setIdSuscriptor(rs.getInt("idSuscriptor"));
                ord.setIdDetalle(rs.getInt("idDetalle"));
                ord.setTotal(rs.getFloat("total"));
                ord.setIdEnvio(rs.getInt("idEnvio"));
                lst.add(ord);
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
    public void insertarOrden(Orden ord) throws Exception {
        try {
            this.conectar();
            String sql = "insert into orden(idSuscriptor, idDetalle, total, idEnvio)  values(?,?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,ord.getIdSuscriptor());
            pre.setInt(2,ord.getIdDetalle());
            pre.setFloat(3,ord.getTotal());
            pre.setInt(4,ord.getIdEnvio());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente",
                    "Insertar", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            this.desconectar();
        }
    }

    @Override
    public void modificarOrden(Orden ord) throws Exception {
        try {
            this.conectar();
            String sql = "update orden set idSuscriptor=?, idDetalle=?, total=?, idEnvio=?, where idCategoria=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);;
            pre.setInt(1,ord.getIdSuscriptor());
            pre.setInt(2,ord.getIdDetalle());
            pre.setFloat(3,ord.getTotal());
            pre.setInt(4,ord.getIdEnvio());
            pre.setInt(5,ord.getIdOrden());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato modificado correctamente",
                    "Modificar", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            this.desconectar();
        }
    }

    @Override
    public void eliminarOrden(Orden ord) throws Exception {
        try {
            this.conectar();
            String sql = "delete from orden where idOrden=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,ord.getIdOrden());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato eliminado correctamente",
                    "Eliminar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally 
        {
            this.desconectar();
        }
    }
    
}
