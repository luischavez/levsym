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

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author Luis
 */
public class ClosableInternalFrame extends JInternalFrame{

    public ClosableInternalFrame(Boolean Closable, Boolean Iconifiable, Boolean Resizable){
        this.setClosable(Closable);
        this.setIconifiable(Iconifiable);
        this.setResizable(Resizable);
        this.addInternalFrameListener(new InternalFrameAdapter(){
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                e.getInternalFrame().getContentPane().removeAll();
                e.getInternalFrame().getContentPane().requestFocusInWindow();
                // collect garbage
                Runtime runtime = Runtime.getRuntime();
                runtime.gc();
            }
        });
    }

}
