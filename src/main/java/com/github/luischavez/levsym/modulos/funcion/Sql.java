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

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Luis
 */
public final class Sql {

    private static String Host;
    private static String BD;
    private static String Usuario;
    private static String Contraseña;
    public static Connection connection = null;
    private static Statement Instruccion = null;
    private static PreparedStatement InstruccionStmt = null;
    
    public Sql() throws IOException, SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        GetConfig config = new GetConfig();
        this.Host=config.Get("HOST");
        this.BD=config.Get("BD");
        this.Usuario=config.Get("USER");
        this.Contraseña=config.Get("PASS");
        connection = SqlConect();
    }

    /**
     * Estableze la configuracion para la conexion
     * @param Host ip o direccion del host
     * @param BD nombre de la base de datos
     * @param Usuario nombre del usuario
     * @param Contraseña la contraseña del usuario
     **/
    public static void SetSettings(String Host, String BD, String Usuario, String Contraseña){
        Sql.Host=Host;
        Sql.BD=BD;
        Sql.Usuario=Usuario;
        Sql.Contraseña=Contraseña;
    }

    /**
     * Crea una nueva conexion
     * @return Connection la conexion de la base de datos
     * @throws SQLException 
     **/
    public static Connection SqlConect() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://" + Host + "/" + BD, Usuario, Contraseña);
        return connection;
    }

    /**
     * Crea una nueva conexion comatible con statements
     * @return Connection la conexion de la base de datos
     * @throws SQLException
     * @see Connection
     * @see DriverManager
     **/
    public static Connection SqlConectStmts() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://" + Host + "/" + BD + "?useServerPrepStmts=true", Usuario, Contraseña);
        return connection;
    }

    /**
     * Obtiene el ResultSet a partir de una conexion existente
     * y una sentencia sql
     * @param Conexion la conexion a utilizar
     * @param Key la sentencia a ejecutar
     * @return ResultSet los resultados de la conexion
     * @throws SQLException
     * @see ResultSet
     * @see Statement
     **/
    public static ResultSet SqlQuery(Connection Conexion, String Key) throws SQLException{
        Instruccion = Conexion.createStatement();
        if(Instruccion.execute(Key)){
            return Instruccion.getResultSet();
        }
        return null;
    }

    /**
     * Retorna un PreparedStatement a partir de una conexion existente
     * y una sentencia sql
     * @param Conexion la conexion a utilizar
     * @param Key la sentencia a ejecutar
     * @return PreparedStatement
     * @throws SQLException
     * @see PreparedStatement
     **/
    public static PreparedStatement SqlQueryStmts(Connection Conexion, String Key) throws SQLException{
        InstruccionStmt = Conexion.prepareStatement(Key);
        return InstruccionStmt;
    }

    /**
     * Verifica si la conexion es valida y sigue activa
     * @return Boolean
     * @exception SQLException
     * @see Connection
     **/
    public static Boolean IsActive() throws SQLException{
        if(connection != null && connection.isValid(10000)){
            return true;
        }else{
            return false;
        }            
    }

    /**
     * Crea una nueva conexion
     * @return Boolean
     * @exception SQLException
     * @see Connection
     **/
    public static Boolean Conect() throws SQLException{
        connection = SqlConect();
        if(!connection.isClosed()){
            return true;
        }else{
            return false;
        }            
    }

    /**
     * Elimina la conexion actual
     * @return Boolean
     * @exception SQLException
     * @see Connection
     **/
    public static Boolean Disconect() throws SQLException{
        connection.close();
        if(connection.isClosed()){
            return true;
        }else{
            return false;
        }
    }
    public static void Clear() throws SQLException{
        /**if(Instruccion != null){
            Instruccion.clearBatch();
            Instruccion.clearWarnings();
            Instruccion.close();
            /*connection.clearWarnings();
            connection.commit();
            connection.rollback();
            System.out.println("Clear Statements");
        }
        
        if(InstruccionStmt != null){
            InstruccionStmt.clearBatch();
            InstruccionStmt.clearParameters();
            InstruccionStmt.clearWarnings();
            InstruccionStmt.close();
            /*connection.clearWarnings();
            connection.commit();
            connection.rollback();
            System.out.println("Clear Statements");
        }**/
        
    }

}
