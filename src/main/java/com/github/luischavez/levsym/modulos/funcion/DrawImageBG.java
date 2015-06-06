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

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.border.Border;

public class DrawImageBG implements Border {

    private BufferedImage image;

    /**
     * Inicia y almazena la imagen
     *
     * @param image la imagen a dibujar
     * @see BufferedImage
     *
     */
    public DrawImageBG(BufferedImage image) {
        this.image = image;
    }

    /**
     * Dibuja la imagen
     *
     * @param c el componente
     * @param g nueva instancia de Graphics
     * @param x coordenada x
     * @param y coordenada y
     * @param width ancho de la imagen
     * @param height alto de la imagen
     * @see Graphics
     *
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        int x0 = x + (width - image.getWidth()) / 2;
        int y0 = y + (height - image.getHeight()) / 2;
        g.drawImage(image, x0, y0, null);
    }

    /**
     * Obtiene un nuevo Insets vacio
     *
     * @param c el componente
     * @return Insets
     * @see Insets
     *
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    /**
     * Estableze que es un borde opaco
     *
     * @return boolean
     *
     */
    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
