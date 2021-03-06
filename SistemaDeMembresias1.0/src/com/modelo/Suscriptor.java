package com.modelo;

/**
 * Nombre de la clase: Suscriptor
 * Fecha: 09-10-2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author andrea rosales
 */
public class Suscriptor{
    
    private int idSuscriptor;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private String genero;
    private int tipoSuscriptor;
    private String tiempoSus;
    private double costoSus;
    private String fechaNacimiento;
    private double totalCompra;
    private String fecha;

    public Suscriptor() {
    }

    public Suscriptor(int idSuscriptor, String nombre, String apellido, String email, String telefono, String direccion, String genero, int tipoSuscriptor, String tiempoSus, double costoSus, String fechaNacimiento, double totalCompra, String fecha) {
        this.idSuscriptor = idSuscriptor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.genero = genero;
        this.tipoSuscriptor = tipoSuscriptor;
        this.tiempoSus = tiempoSus;
        this.costoSus = costoSus;
        this.fechaNacimiento = fechaNacimiento;
        this.totalCompra = totalCompra;
        this.fecha = fecha;
    }

   

    public int getIdSuscriptor() {
        return idSuscriptor;
    }

    public void setIdSuscriptor(int idSuscriptor) {
        this.idSuscriptor = idSuscriptor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getTipoSuscriptor() {
        return tipoSuscriptor;
    }

    public void setTipoSuscriptor(int tipoSuscriptor) {
        this.tipoSuscriptor = tipoSuscriptor;
    }

    public String getTiempoSus() {
        return tiempoSus;
    }

    public void setTiempoSus(String tiempoSus) {
        this.tiempoSus = tiempoSus;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCostoSus() {
        return costoSus;
    }

    public void setCostoSus(double costoSus) {
        this.costoSus = costoSus;
    }
    
}
