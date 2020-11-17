package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesPersonaExterna;
import com.modelo.PersonaExterna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoPersonaExterna
 * Fecha: 10-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author victor alvarado
 */
public class DaoPersonaExterna extends Conexion implements OperacionesPersonaExterna{

    @Override
    public List<PersonaExterna> mostrarPersonaExterna() throws Exception {
        ResultSet rs;
        List<PersonaExterna> lst = new ArrayList();
        try {
            this.conectar();
            String sql = "select * from personaExterna;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                PersonaExterna pe = new PersonaExterna();
                pe.setIdPersonaExterna(rs.getInt("idPersonaExterna"));
                pe.setNombre(rs.getString("nombre"));
                pe.setDui(rs.getString("dui"));
                pe.setTelefonoMovil(rs.getString("telefonoMovil"));
                pe.setIdSuscriptor(rs.getInt("idSuscriptor"));
                lst.add(pe);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return lst;
    }

    @Override
    public void insertarPersonaExterna(PersonaExterna pe) throws Exception {
        try {
            this.conectar();
            String sql = "insert into personaExterna(nombre, dui, telefonoMovil, idSuscriptor) values(?,?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, pe.getNombre());
            pre.setString(2, pe.getDui());
            pre.setString(3, pe.getTelefonoMovil());
            pre.setInt(4, pe.getIdSuscriptor());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente",
                    "Insertar", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void modificarPersonaExterna(PersonaExterna pe) throws Exception {
        try {
            this.conectar();
            String sql = "update  personaExterna set nombre = ?, dui = ?, telefonoMovil = ?, idSuscriptor = ? where idPersonaExerna = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, pe.getNombre());
            pre.setString(2, pe.getDui());
            pre.setString(3, pe.getTelefonoMovil());
            pre.setInt(4, pe.getIdSuscriptor());
            pre.setInt(5, pe.getIdPersonaExterna());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato modificado correctamente",
                    "Modificar", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminarPersonaExterna(PersonaExterna pe) throws Exception {
        try {
            this.conectar();
            String sql = "delete from  personaExterna where idPersonaExerna = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, pe.getIdPersonaExterna());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato eliminado correctamente",
                    "Eliminar", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }
    
    public int getIdPersonaExterna(int idSuscriptor) {
        int id = 0;
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "Select idPersonaExterna from personaExterna where idSuscriptor = ?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idSuscriptor);
            rs = pre.executeQuery();
            while (rs.next()) {                
                id = rs.getInt("idPersonaExterna");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error idPE"
                    + e.getMessage());
        } finally {
            this.desconectar();
        }
        return id;
    }
}
