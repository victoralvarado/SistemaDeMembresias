
package com.utilidades;

/**
 * Nombre de la clase: ComboItem
 * Fecha: 19/10/2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Luna-
 */
public class ComboItem {
    private int value;
    private String label;

    public ComboItem() {
    }

    public ComboItem(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
    
    
}
