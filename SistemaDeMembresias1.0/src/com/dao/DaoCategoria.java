package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesCategoria;
import com.modelo.Categoria;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoCategoria
 * Fecha: 09-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author adrian luna
 */
public class DaoCategoria extends Conexion implements OperacionesCategoria{

    @Override
    public List<Categoria> mostrarCategoria() throws Exception {
        ResultSet rs;
        List<Categoria> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from categoria;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {                
                Categoria cat= new Categoria();
                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setCategoria(rs.getString("categoria"));
                lst.add(cat);
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
    public void insertarCategoria(Categoria cat) throws Exception {
        try {
            this.conectar();
            String sql = "insert into categoria values(?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,cat.getIdCategoria());
            pre.setString(2,cat.getCategoria());
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
    public void modificarCategoria(Categoria cat) throws Exception {
        try {
            this.conectar();
            String sql = "update categoria set categoria=?, where idCategoria=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1,cat.getIdCategoria());
            pre.setString(2,cat.getCategoria());
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
    public void eliminarCategoria(Categoria cat) throws Exception {
        try {
            this.conectar();
            String sql = "delete from categoria where idCategoria=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, cat.getIdCategoria());
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
