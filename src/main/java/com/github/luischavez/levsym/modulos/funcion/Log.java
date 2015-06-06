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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextArea;

/**
 *
 * @author Luis
 */
public final class Log {

    private static File LogDir = new File(System.getProperty("user.dir") + "/log.txt");

    public Log() {
        if (!LogDir.exists()) {
            try {
                LogDir.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void SaveLog(String txt) {
        FileWriter w = null;
        try {
            w = new FileWriter(LogDir, true);
            w.write(Calendar.getInstance().getTime() + "\n" + txt + "\r\n");
            w.close();
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                w.close();
            } catch (IOException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void OpenLog(JTextArea area) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(LogDir));
            String linea = reader.readLine();
            while (linea != null) {
                area.append(linea + "\n");
                linea = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
