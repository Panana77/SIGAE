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
public class ActivityDAO 
{
    public static final String SQL_SELECT = "SELECT * FROM activity";
    public static final String SQL_INSERT = "INSERT INTO activity (activity_name, idactivity_type, registration_limit) VALUES (?, ?, ?)";
    public static final String SQL_UPDATE_LIMIT = "UPDATE activity SET registration_limit = ? where activity_name = ?";
    public static final String SQL_SELECT_LIMIT_REGISTRATION = "SELECT (registration_limit) FROM activity WHERE activity_name = ?";
    
    //Metodo para obtener los valores del limite de registro de alumnos
    public static int selectLimitRegistration(String activity)
    {
          //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       int limitRegistration  = 0;
       
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_SELECT_LIMIT_REGISTRATION);
            _preparedStatement.setString(1, activity);
            _resultSet = _preparedStatement.executeQuery();
           
            //Recuperar todos los datos y almacenarlos en la variable entera
            while(_resultSet.next())
            {
                //almacenar los datos en la variable registration_limit
                limitRegistration = _resultSet.getInt("registration_limit");            
            }            
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex);
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
        
       return limitRegistration;
    }

    //Metodo para obtener los valores de los registros
    public List<Activity> select()
    {
         //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       Activity _activity = null;
       List<Activity> activities = new ArrayList<>();
       
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_SELECT);
            _resultSet = _preparedStatement.executeQuery();
            
            //Recuperar todos los datos y almacenarlos en una lista
            while(_resultSet.next())
            {
                //almacenar los datos en el objeto _career
                _activity = new Activity(_resultSet.getString("activity_name"), _resultSet.getInt("idactivity_type"),
                _resultSet.getInt("registration_limit"));       
                //pasar los datos del objeto activity a la lista activities
                activities.add(_activity);              
            }            
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex);
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
        
       return activities;
    }
    //Metodo para insertar valores
    public int insert(Activity activity)
    {
         //Declaración de variables locales para la conexion de la base de datos
        Connection conect = null;
        PreparedStatement _prepareStatement = null;
        int registers = 0;
        try {
            //Conectar a la base de datos
            conect = ConectionToDB.getConnection();
            _prepareStatement = conect.prepareStatement(SQL_INSERT);
            
            //Insertar los datos de la actividad a la tabla
            //_prepareStatement.setInt(1, activity.getID());
            _prepareStatement.setString(1, activity.getActivityName().toUpperCase());
            _prepareStatement.setInt(2, activity.getIdActivityType());
            _prepareStatement.setInt(3, activity.getLimit());
            registers = _prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERROR IN CLASS ActivityDAO \n" + ex);
        }
        
        finally
        {
            try {
                //Cerrar los objetos abiertos, todo en orden inverso
                ConectionToDB.close(_prepareStatement);
                ConectionToDB.close(conect);
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null,ex);
            }
        }  
        return registers;
    }
    
    public int update(Activity _activity, String activityName)
    {
        Connection _connection = null;
        PreparedStatement _preparedStatement = null;
        int registers = 0;
        
        try {
            
            _connection = ConectionToDB.getConnection();
            _preparedStatement = _connection.prepareStatement(SQL_UPDATE_LIMIT);
            
            _preparedStatement.setInt(1, _activity.getLimit());
            _preparedStatement.setString(2, activityName);

            
            registers = _preparedStatement.executeUpdate();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
        
        finally
        {
            try {
                  ConectionToDB.close(_preparedStatement);
                  ConectionToDB.close(_connection);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
          
        }
        
        return registers;
    }
}
