package com.dao;

import com.conexion.Conexion;
import com.interfaces.OperacionesCobertura;
import com.modelo.Cobertura;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
* Nombre de la clase: DaoCobertura
* Fecha: 11-13-2020 
* CopyRigh: Ever-Alexander
* Versión: 1.0
* Autor: Alexander-Garcia
*/
public class DaoCobertura extends Conexion implements OperacionesCobertura{

    @Override
    public List<Cobertura> mostrarCobertura() throws Exception {
        List<Cobertura> lst = new ArrayList();
        ResultSet rs;
        try {
            this.conectar();
            String sql = "select * from cobertura;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Cobertura cob = new Cobertura();
                cob.setIdCobertura(rs.getInt("idCobertura"));
                cob.setDepartamento(rs.getString("departamento"));
                cob.setMunicipio(rs.getString("municipio"));
                lst.add(cob);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return lst;
    }
    
    public List<Cobertura> mostrarDepartamento() throws Exception {
        List<Cobertura> lst = new ArrayList();
        ResultSet rs;
        try {
            this.conectar();
            String sql = "select DISTINCT departamento from cobertura;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                Cobertura cob = new Cobertura();
                cob.setDepartamento(rs.getString("departamento"));
                lst.add(cob);
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
    public void insertarCobertura(Cobertura cob) throws Exception {
        try {
            this.conectar();
            String sql = "insert into cobertura values(?,?,?);";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, cob.getIdCobertura());
            pre.setString(2, cob.getDepartamento());
            pre.setString(3, cob.getMunicipio());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente",
                    "Insertar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            this.desconectar();
        }
    }

    @Override
    public void modificarCobertura(Cobertura cob) throws Exception {
        try {
            this.conectar();
            String sql = "update cobertura set departamento = ?, municipio= ? where idCobertura = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, cob.getDepartamento());
            pre.setString(2, cob.getMunicipio());
            pre.setInt(3, cob.getIdCobertura());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos modificados correctamente",
                    "Modificar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminarCobertura(Cobertura cob) throws Exception {
       try {
            this.conectar();
            String sql = "delete from cobertura where idCobertura = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, cob.getIdCobertura());
            pre.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos eliminados correctamente",
                    "Eliminar", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
    }
    
    
    public Cobertura getDepartamento(int idCobertura){
        Cobertura co = new Cobertura();
        ResultSet rs = null;
        try {
            this.conectar();
            String sql = "select departamento from cobertura where idCobertura = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, idCobertura);
            rs = pre.executeQuery();
            while(rs.next())
            {
                co.setDepartamento(rs.getString("departamento"));
            }
        } catch (SQLException e) {
        }
        return co;
    }
    
    public List<Cobertura> mostrarMunicipio(String departamento) throws Exception {
        List<Cobertura> lst = new ArrayList();
        ResultSet rs;
        try {
            this.conectar();
            String sql = "select municipio from cobertura where departamento = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, departamento);
            rs = pre.executeQuery();
            while (rs.next()) {
                Cobertura cob = new Cobertura();
                cob.setMunicipio(rs.getString("municipio"));
                lst.add(cob);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return lst;
    }
    
    public int getIdCobertura(String municipio) throws Exception {
        int idCobertura = 0;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "select idCobertura from cobertura where municipio = ?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, municipio);
            rs = pre.executeQuery();
            while (rs.next()) {
                idCobertura = rs.getInt("idCobertura");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            this.desconectar();
        }
        return idCobertura;
    }
    
}
