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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Luis
 */
public class GetConfig {
    
    private Properties propiedades = new Properties();

    public GetConfig() throws IOException{
        File f = new File(System.getProperty("user.dir") + "/CONFIG");
        if(!f.exists()){
            f.createNewFile();
        }
        propiedades.load(new FileInputStream(new File(System.getProperty("user.dir") + "/CONFIG")));
    }

    public String Get(String Key){
        if(Contains(Key)){
            return propiedades.getProperty(Key);
        }
        return null;
    }

    public void Set(String Ket, String Value) throws IOException{
        propiedades.put(Ket, Value);
        propiedades.store (new FileOutputStream(System.getProperty("user.dir")+"/CONFIG"), System.getProperty("user.dir")+"/CONFIG");
    }

    public Boolean Contains(String Key){
        return propiedades.containsKey(Key);
    }

}
