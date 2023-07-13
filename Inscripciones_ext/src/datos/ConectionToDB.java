/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection.*;
import java.sql.Statement.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Panana
 */
public class ConectionToDB 
{        
    //Variables para la coneccion a la base de datos
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/extraescolares_bd?useSSL=false&useTimezone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    public static String JDBC_USERNAME = "root";
    public static String JDBC_PASSWORD = "R0mnN51A.634";
    //public static String JDBC_URL = "jdbc:mysql://PC-ADMIN:3306/extraescolares_bd?useSSL=false&useTimezone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    //public static String JDBC_USERNAME = "usuario1";
    //public static String JDBC_PASSWORD = "12345678";
    //public static String JDBC_USERNAME = "alejandro";
    //public static String JDBC_PASSWORD = "12345678";
    /**
     * Establecer la conexión con la base de datos
     * @return Regresa objeto de la clase Connection
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException
    {
        Connection conect = null;
        
        try
        {
           conect = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        return conect;   
    }
    /**
     * Cerrar la conexión de base de datos
     * @param _resSet
     * @throws SQLException 
     */
    public static void close(ResultSet _resSet) throws SQLException
    {
        _resSet.close();
    }
    /**
     * Cerrar la conexión de base de datos
     * @param _statement
     * @throws SQLException 
     */
    public static void close(Statement _statement) throws SQLException
    {
        _statement.close();
    }
    
    /**
     * Cerrar la conexión de base de datos
     * @param _preparedStatement
     * @throws SQLException 
     */
    public static void close(PreparedStatement _preparedStatement) throws SQLException
    {
        _preparedStatement.close();
    }
    
    /**
     * Cerrar la conexión de base de datos
     * @param _connection
     * @throws SQLException 
     */
    public static void close(Connection _connection) throws SQLException
    {
        _connection.close();
    }
}
