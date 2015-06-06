/* 
 * Copyright (C) 2015 Luis Chávez
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.luischavez.levsym.modelos;

import com.github.luischavez.levsym.modulos.funcion.Log;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Luis
 */
public class JTextFieldOnlyNum extends JTextField {

    private static final int INTEGER = 0;
    private static final int DECIMAL = 1;

    public JTextFieldOnlyNum() {
        this.setInputVerifier(new InputVerifier() {

            @Override
            public boolean verify(JComponent input) {
                String txt = ((JTextField) input).getText();
                try {
                    Double.parseDouble(txt);
                    return true;
                } catch (Exception e) {
                    ((JTextField) input).setText("");
                    Log.SaveLog(e.toString());
                    return false;
                }
            }
        });

    }

}
