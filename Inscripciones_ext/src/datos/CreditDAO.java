/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import static datos.StudentDAO.SQL_INSERT;
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
public class CreditDAO 
{
     //Consultas
    public static final String SQL_SELECT = "SELECT * FROM credit";
    public static final String SQL_INSERT = "INSERT INTO credit (idstudent) VALUE (?)";
    
    //Metodo para consultar registros de la base de datos
     public List<Credit> select()
    {
        //Declarar variables locales para la conexion de la bd
       Connection conect = null;
       PreparedStatement _preparedStatement = null;
       ResultSet _resultSet = null;
       Credit credit = null;
       List<Credit> credits = new ArrayList<>();
       
        try {
            conect = ConectionToDB.getConnection();
            _preparedStatement = conect.prepareStatement(SQL_SELECT);
            _resultSet = _preparedStatement.executeQuery();
            
            //Recuperar todos los datos y almacenarlos en una lista
            while(_resultSet.next())
            {
                //almacenar los datos en el objeto credit
                credit = new Credit(_resultSet.getInt("idcredit"), _resultSet.getInt("idstudent"));
                
                //pasar los datos del objeto _student a la lista students
                credits.add(credit);              
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
        
       return credits; 
    }
    
    //Metodo para insertar registros a la base de datos
    public int insert(Credit credit)
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
            _prepareStatement.setInt(1, credit.getIdStudent());

            registers = _prepareStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"ERROR IN CLASS CreditDAO \n" + ex);
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

}
