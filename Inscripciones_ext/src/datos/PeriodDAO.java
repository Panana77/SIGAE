/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import static datos.StudentDAO.SQL_SELECT;
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
public class PeriodDAO 
{
    public static final String SQL_SELECT = "SELECT * FROM period";
    public List<Period> select()
    {
        //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       Period _period = null;
       List<Period> periods = new ArrayList<>();
       
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_SELECT);
            _resultSet = _preparedStatement.executeQuery();
            
            //Recuperar todos los datos y almacenarlos en una lista
            while(_resultSet.next())
            {
                //almacenar los datos en el objeto _period
                _period = new Period(_resultSet.getInt("idperiod"), _resultSet.getString("period_name"));
                
                //pasar los datos del objeto _student a la lista periods
                periods.add(_period);              
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
        
       return periods; 
    }
    /**Devolver el id del periodo en base a su nombre
     * 
     * @param period nombre del periodo
     * @return Valor entero del id del periodo
     */
    public static int select_idPeriod(String period)
    {
       //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       int idPeriod = 0;
       List<Student> students = new ArrayList<>();
       Student student = null;
       String SQL_SELECT_IDPERIOD = "SELECT idperiod FROM period WHERE period_name = \"" + period + "\"";
       System.out.println("\"hola\"");
       try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_SELECT_IDPERIOD);
            _resultSet = _preparedStatement.executeQuery();
            
            //Recuperar todos los datos y almacenarlos en una lista
            while(_resultSet.next())
            {
                //almacenar el idperiod en un entero
                student = new Student();
                student.setIdPeriod(_resultSet.getInt("idperiod"));
                students.add(student);
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
        
       return student.getIdPeriod(); 
    }
    
    /**Devolver el nombre del periodo en base a su id
     * 
     * @param idPeriod id del periodo
     * @return cadena del periodo
     */
     public static String select_Period_name(int idPeriod)
    {
       //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       String period = "";
       String SQL_SELECT_PERIOD = "SELECT period_name FROM period WHERE idperiod = \"" + idPeriod + "\"";
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_SELECT_PERIOD);
            _resultSet = _preparedStatement.executeQuery();
            
            while(_resultSet.next())
            {
                //almacenar el nombre del periodo en una cadena
                period = _resultSet.getString("period_name");
                             
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
        
       return period; 
    }
}
