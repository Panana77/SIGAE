/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Panana
 */
public class CareerDAO 
{
    //Consultas
    public static final String SQL_SELECT = "SELECT * FROM career";

    public List<Career> select()
    {
         //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       Career _career = null;
       List<Career> careers = new ArrayList<>();
       
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_SELECT);
            _resultSet = _preparedStatement.executeQuery();
            
            //Recuperar todos los datos y almacenarlos en una lista
            while(_resultSet.next())
            {
                //almacenar los datos en el objeto _career
                _career = new Career(_resultSet.getString("idcareer"), _resultSet.getString("career_name"));       
                //pasar los datos del objeto _career a la lista careers
                careers.add(_career);              
            }            
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null,"ERROR IN CLASS CareerDAO \n" + ex);
        }
        //Cerrar la conexion de la base de datos
        finally
         {
                try
                {
                    ConectionToDB.close(_resultSet);
                    ConectionToDB.close(_preparedStatement);
                    ConectionToDB.close(conect);
                }
                catch(SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        
       return careers;
    }
    
     public static String select_Career_name(String idCareer)
    {
       //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       String careerName = "";
       String SQL_SELECT_CAREER_NAME = "SELECT career_name FROM career WHERE idcareer = \"" + idCareer + "\"";
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_SELECT_CAREER_NAME);
            _resultSet = _preparedStatement.executeQuery();
            
            while(_resultSet.next())
            {
                //almacenar el nombre del periodo en una cadena
                careerName = _resultSet.getString("career_name");
                             
            }
            
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null,"ssfff"+ ex);
        }
        //Cerrar la coneccion de la base de datos
        finally
         {
                try
                {
                    ConectionToDB.close(_resultSet);
                    ConectionToDB.close(_preparedStatement);
                    ConectionToDB.close(conect);
                }
                catch(SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        
       return careerName; 
    }
}
