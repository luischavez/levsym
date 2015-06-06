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
package com.github.luischavez.levsym;

import com.github.luischavez.levsym.modulos.funcion.Log;
import com.github.luischavez.levsym.modulos.funcion.Sql;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Luis
 */
public class Main {

    public static void main(String[] args) {
        Log MiLog = new Log();
        try {
            Sql sql = new Sql();
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Log.SaveLog(ex.toString());
        }

        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.MarinerSkin");

        MainFrame Principal = new MainFrame();
        Principal.setTitle("LevSym");
        Principal.pack();
        Principal.setResizable(true);
        Principal.setVisible(true);
        Image Icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/image/icono.png"));
        Principal.setIconImage(Icon);
    }
}
