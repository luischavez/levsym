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

import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author Luis
 */
public class Widget {

    private JInternalFrame frame = new JInternalFrame();

    /**
     * Inicia y Estableze el contenido del Widget
     *
     * @param widget Contenedor del widget
     * @see JPanel
     *
     */
    public Widget(JPanel widget) {
        frame.add(widget);
    }

    /**
     * Agrega el Widget a una superficie
     *
     * @param panel la superficie done se va agregar el widget
     * @see JDesktopPane
     *
     */
    public void SetWidget(JDesktopPane panel) {
        panel.add(frame);
    }

    /**
     * Muestra el Widget o lo oculta
     *
     * @param state el estado de la ventana
     *
     */
    public void ShowWidget(Boolean state) {
        frame.show(state);
    }

    /**
     * Estableze las coordenadas del Widget y su tamaño
     *
     * @param x coordenada x del widget
     * @param y coordenada y del widget
     * @param w ancho del widget
     * @param h alto del widget
     *
     */
    public void SetBounds(int x, int y, int w, int h) {
        frame.setBounds(x, y, w, h);
    }

    /**
     * Localizacion del Widget
     *
     * @param x coordenada x del widget
     * @param y coordenada y del widget
     *
     */
    public void SetLocation(int x, int y) {
        frame.setLocation(x, y);
    }

    /**
     * Tamaño del Widget
     *
     * @param w ancho del widget
     * @param h alto del widget
     *
     */
    public void SetSize(int w, int h) {
        frame.setSize(w, h);
    }

    /**
     * Estableze si el Widget puede cerrarse
     *
     * @param state
     *
     */
    public void Closable(Boolean state) {
        frame.setClosable(state);
    }

    /**
     * Estableze si el Widget puede minimizarse
     *
     * @param state
     *
     */
    public void Iconifiable(Boolean state) {
        frame.setIconifiable(state);
    }

    /**
     * Estableze si el widget puede maximizarse
     *
     * @param state
     *
     */
    public void Maximizable(Boolean state) {
        frame.setMaximizable(state);
    }

    /**
     * Color de fondo para el JInternalFrame que contiene el Widget
     *
     * @param c color del fondo del JInternalFrame
     * @see JInternalFrame
     *
     */
    public void SetColor(Color c) {
        frame.setBackground(c);
    }

    /**
     * Habilita la propiedad Focusable
     *
     * @param state
     *
     */
    public void SetFocusable(Boolean state) {
        frame.setFocusable(state);
    }

    /**
     * Obtiene el ancho del Widget
     *
     * @return int
     *
     */
    public int GetW() {
        return frame.getWidth();
    }

    /**
     * Obtiene el alto del Widget
     *
     * @return int
     *
     */
    public int GetH() {
        return frame.getHeight();
    }
}
