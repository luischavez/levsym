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
package com.github.luischavez.levsym.modulos.funcion;

import javax.swing.JTextField;

/**
 *
 * @author Luis
 */
public class Text {

    /**
     * Comprueba si un JTextField
     * esta vacio
     * @param Field el JTextField a comprobar
     * @return Boolean
     * @see JTextField
     **/
    public Boolean StateField(JTextField Field){
        if(Field.getText().isEmpty()){
                return false;
        }
        return true;
    }

}
