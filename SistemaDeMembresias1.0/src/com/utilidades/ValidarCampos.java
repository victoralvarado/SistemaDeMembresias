
package com.utilidades;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 * Nombre de la clase: ValidarCampos
 * Fecha: 19/10/2020
 * Versión: 1.0
 * CopyRight: ITCA-FEPADE
 * @author Luna-
 */
public class ValidarCampos {

    public ValidarCampos() {
    }
    
    public void numbersOnly(KeyEvent evt) {
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }
    
    public void wordssOnly(KeyEvent evt) {
        if (!Character.isLetter(evt.getKeyChar()) && evt.getKeyChar() != KeyEvent.VK_SPACE) {
            evt.consume();
        }
    }
    
    public void numbersAndPoint(KeyEvent evt, JTextField textField) {
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.') {
            evt.consume();
        }
        if (evt.getKeyChar()=='.' && textField.getText().contains(".")) {
            evt.consume();
        }
    }
}
