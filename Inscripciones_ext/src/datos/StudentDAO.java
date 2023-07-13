/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.*;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection.*;
import java.sql.Statement.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Panana
 */
public class StudentDAO 
{
    //comandos de la base de datos
    public static final String SQL_SELECT = "SELECT * FROM student";
    public static final String SQL_INSERT = "INSERT INTO student (name, lastname, age, idsex, idcareer, ctrlNumber, semester, activity_name, idperiod, email, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_DELETE = "DELETE FROM student WHERE idstudent = ?";
    public static final String SQL_UPDATE = "UPDATE student SET name = ?, lastname = ?, age = ?, idsex = ?, semester = ?, idcareer = ?, activity_name = ?, ctrlNumber = ?, idperiod = ?, email = ?, phoneNumber = ? WHERE idstudent = ?";
//metodo para hacer un select de la base de datos
    public List<Student> select()
    {
        //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       Student _student = null;
       List<Student> students = new ArrayList<>();
       
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_SELECT);
            _resultSet = _preparedStatement.executeQuery();
            
            //Recuperar todos los datos y almacenarlos en una lista
            while(_resultSet.next())
            {
                //almacenar los datos en el objeto _student
                _student = new Student(_resultSet.getInt("idstudent"), _resultSet.getString("name"), _resultSet.getString("lastname"), _resultSet.getInt("age"),
                _resultSet.getString("idsex"), _resultSet.getInt("semester"), _resultSet.getString("idcareer"), _resultSet.getString("activity_name"),
                _resultSet.getInt("ctrlNumber"), _resultSet.getInt("idperiod"), _resultSet.getString("email"), _resultSet.getString("phoneNumber"));
                
                //pasar los datos del objeto _student a la lista students
                students.add(_student);              
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
        
       return students; 
    }
    
    //Metodo para insertar registros a la base de datos
    public int insert(Student _student)
    {
        //Declaración de variables locales para la conexion de la base de datos
        Connection conect = null;
        PreparedStatement _prepareStatement = null;
        int registers = 0;
        try {
            //Conectar a la base de datos
            conect = ConectionToDB.getConnection();
            _prepareStatement = conect.prepareStatement(SQL_INSERT);
            
            //Insertar los datos del estudiante a la tabla
            //_prepareStatement.setInt(1, _student.getID());
            _prepareStatement.setString(1, _student.getName().toUpperCase());
            _prepareStatement.setString(2, _student.getLastName().toUpperCase());
            _prepareStatement.setInt(3, _student.getAge());
            _prepareStatement.setString(4, _student.getSex());
            _prepareStatement.setString(5, _student.getIdCarrera());
            _prepareStatement.setInt(6, _student.getControlNumber());
            _prepareStatement.setInt(7, _student.getSemester());    
            _prepareStatement.setString(8, _student.getActivity());
            _prepareStatement.setInt(9, _student.getIdPeriod());
            _prepareStatement.setString(10, _student.getEmail());
            _prepareStatement.setString(11, _student.getPhoneNumber());
            registers = _prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERROR IN CLASS StudentDAO \n" + ex);
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

    //Metodo para actualizar registros de la base de datos
    public int update(Student _student, int id)
    {
        Connection _connection = null;
        PreparedStatement _preparedStatement = null;
        int registers = 0;
        
        try {
            
            _connection = ConectionToDB.getConnection();
            _preparedStatement = _connection.prepareStatement(SQL_UPDATE);
            
            _preparedStatement.setString(1, _student.getName());
            _preparedStatement.setString(2, _student.getLastName());
            _preparedStatement.setInt(3, _student.getAge());
            _preparedStatement.setString(4, _student.getSex());
            _preparedStatement.setInt(5, _student.getSemester());
            _preparedStatement.setString(6, _student.getIdCarrera());
            _preparedStatement.setString(7, _student.getActivity());
            _preparedStatement.setLong(8, _student.getControlNumber());
            _preparedStatement.setInt(9, _student.getIdPeriod());
            _preparedStatement.setString(10, _student.getEmail());
            _preparedStatement.setString(11, _student.getPhoneNumber());
            _preparedStatement.setInt(12, id);
            
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
    //Metodo para borrar registros en base a su id
    public int delete(int id)
    {
         Connection _connection = null;
        PreparedStatement _preparedStatement = null;
        int registers = 0;
        
        try
        {
             _connection = ConectionToDB.getConnection();
            _preparedStatement = _connection.prepareStatement(SQL_DELETE);
            _preparedStatement.setInt(1, id);
            
            registers = _preparedStatement.executeUpdate();
        }
        catch(SQLException ex)
        {
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
    
    public List<Student> customSelect(String orderBy, String filterBy, String filter,int idPeriod)
    {
          //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       Student _student = null;
       List<Student> students = new ArrayList<>();
       String SQL_CUSTOM_SELECT = "";
       //Consulta personalizada 
       //Comprobar que tipo de selección de periodo se indica
       if(idPeriod != 0)
       {
           //En caso de que no sea 0 filtrar registros conforme al periodo
            SQL_CUSTOM_SELECT = "SELECT * FROM student WHERE idperiod = "+ idPeriod + " AND " + filterBy + " LIKE('" + filter + "%') ORDER BY " + orderBy;
       }
       else
       {
           //En caso de ser cero no filtrar registros bajo ningun periodo 
            SQL_CUSTOM_SELECT = "SELECT * FROM student WHERE "+ filterBy + " LIKE('" + filter + "%') ORDER BY " + orderBy;

       }
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_CUSTOM_SELECT);
            _resultSet = _preparedStatement.executeQuery();
            
            //Recuperar todos los datos y almacenarlos en una lista
            while(_resultSet.next())
            {
                //almacenar los datos en el objeto _student
                _student = new Student(_resultSet.getInt("idstudent"), _resultSet.getString("name"), _resultSet.getString("lastname"), _resultSet.getInt("age"),
                _resultSet.getString("idsex"), _resultSet.getInt("semester"), _resultSet.getString("idcareer"), _resultSet.getString("activity_name"),
                _resultSet.getInt("ctrlNumber"), _resultSet.getInt("idperiod"), _resultSet.getString("email"), _resultSet.getString("phoneNumber"));
                
                //pasar los datos del objeto _student a la lista students
                students.add(_student);              
            }
            
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex);
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
        
       return students; 
    }
    
    public List<Student> customSelect(String orderBy, String filterBy, String filterBy2,  String filter, String filter2, int idPeriod)
    {
       //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       Student _student = null;
       List<Student> students = new ArrayList<>();
       
       //Consulta personalizada
       String SQL_CUSTOM_SELECT = "SELECT * FROM student WHERE idperiod = " + idPeriod + " AND " + filterBy + " LIKE('" + filter + "%') AND " + filterBy2 + " LIKE('" + filter2 + "%') ORDER BY " + orderBy;
       
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_CUSTOM_SELECT);
            _resultSet = _preparedStatement.executeQuery();
            
            //Recuperar todos los datos y almacenarlos en una lista
            while(_resultSet.next())
            {
                //almacenar los datos en el objeto _student
                _student = new Student(_resultSet.getInt("idstudent"), _resultSet.getString("name"), _resultSet.getString("lastname"), _resultSet.getInt("age"),
                _resultSet.getString("idsex"), _resultSet.getInt("semester"), _resultSet.getString("idcareer"), _resultSet.getString("activity_name"),
                _resultSet.getInt("ctrlNumber"), _resultSet.getInt("idperiod"), _resultSet.getString("email"), _resultSet.getString("phoneNumber"));
                
                //pasar los datos del objeto _student a la lista students
                students.add(_student);              
            }
            
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex);
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
        
       return students; 
    }
    
    /** Regresar el total de estudiantes registrados en base a la actividad
     * 
     * @param activity Cadena donde se pasa el nombre de una actividad
     * @return Valor entero del total de estudiantes registrados
     */
    public static int selectCount(String activity)
    {
       //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       //Consulta personalizada
       String SQL_COUNT_SELECT = "SELECT COUNT(*) FROM student WHERE activity_name = \"" + activity +"\"";
       int countActivity = 0;
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_COUNT_SELECT);
            _resultSet = _preparedStatement.executeQuery();
            
            //Recuperar todos los datos y almacenarlos en una lista
            while(_resultSet.next())
            {
                //Guardar el conteo total
                countActivity = _resultSet.getInt(1);
            }
            
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex);
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
        return countActivity;
    }
    /**Regresa el conteo total de los alumnos registrados en alguna actividad
     * 
     * @param idPeriod id del periodo
     * @param activity Nombre de la actividad
     * @return 
     */
    public static int selectCount(int idPeriod, String activity)
    {
               //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       //Consulta personalizada
       String SQL_COUNT_SELECT = "SELECT COUNT(*) FROM student WHERE activity_name = \"" + activity +"\" AND idperiod = " + idPeriod;
       int countActivity = 0;
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_COUNT_SELECT);
            _resultSet = _preparedStatement.executeQuery();
            
            //Recuperar todos los datos y almacenarlos en una lista
            while(_resultSet.next())
            {
                //Guardar el conteo total
                countActivity = _resultSet.getInt(1);
            }
            
        } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex);
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
        return countActivity;
    }
   // public List<>
}
