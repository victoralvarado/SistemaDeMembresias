package com.modelo;

/**
* Nombre de la clase: Venta
* Fecha: 10-09-2020 
* CopyRigh: ITCA-FEPADE
* Versión: 1.0
* Autor: Roberto Aguillón
*/

public class Venta {
    private int idVenta;
    private float total;
    private String metodoPago;
    private String fechaVenta;

    public Venta() {
    }

    public Venta(int idProducto, float total, String metodoPago, String fechaVenta) {
        this.idVenta = idProducto;
        this.total = total;
        this.metodoPago = metodoPago;
        this.fechaVenta = fechaVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    
    
}
