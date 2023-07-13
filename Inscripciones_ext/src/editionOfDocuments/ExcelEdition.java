/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editionOfDocuments;

import com.mysql.cj.conf.PropertyKey;
import datos.CareerDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import datos.Student;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 *
 * @author Panana
 */
public class ExcelEdition 
{
    public ExcelEdition(){
    }
    
    //Metodo para crear una lista en un archivo excel
    public void createList(List<Student> students, String fileName)
    {     
        //Objeto para crear archivo excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Crear una pestaña para el archivo excel
        XSSFSheet sheet = workbook.createSheet("Lista");
        
        try
        {
            //Crear fila para los encabezados
            Row encabezado = sheet.createRow(0);
            //Crear las celdas para colocar los nombres de los encabezados
            encabezado.createCell(0).setCellValue("No.");
            encabezado.createCell(1).setCellValue("Apellido");
            encabezado.createCell(2).setCellValue("Nombre");
            encabezado.createCell(3).setCellValue("Edad");
            encabezado.createCell(4).setCellValue("Sexo");
            encabezado.createCell(5).setCellValue("Carrera");
            encabezado.createCell(6).setCellValue("No. de Control");
            encabezado.createCell(7).setCellValue("Semestre");
            encabezado.createCell(8).setCellValue("Actividad");
            encabezado.createCell(9).setCellValue("Email");
            encabezado.createCell(10).setCellValue("Telefono");
            
            //Colocar los registros de la tabla en el archivo excel
            int j = 1;
            for(Student student : students)
            {
                //Crear la fila
                Row row = sheet.createRow(j);
                //Asignar los valores a las celdas
               row.createCell(0).setCellValue(j);
                row.createCell(1).setCellValue(student.getLastName());
                row.createCell(2).setCellValue(student.getName());
                row.createCell(3).setCellValue(student.getAge());
                row.createCell(4).setCellValue(student.getSex());
                row.createCell(5).setCellValue(student.getIdCarrera());
                row.createCell(6).setCellValue(student.getControlNumber());
                row.createCell(7).setCellValue(student.getSemester());
                row.createCell(8).setCellValue(student.getActivity());
                row.createCell(9).setCellValue(student.getEmail());
                row.createCell(10).setCellValue(student.getPhoneNumber());
                
                j++;
            }
            
            
            //Crear archivo excel
            FileOutputStream fileout = new FileOutputStream("Formatos/Listas/" + fileName + ".xlsx");
            workbook.write(fileout);
            fileout.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelEdition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelEdition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo para modificar un archivo excel 
    public void modifyList(List<Student> students, String FileName, String teacherName, String period, String schedule, String activityName) throws IOException
    {
        try
        {
            //contador de registros
            int count = 0;
            //Iterador de las filas
            int rowCount = 11;
            //numero de pestañas en el excel
            int sheetCount = 0;
            //Buscar el archivo a modificar
            FileInputStream file = new FileInputStream("src/documents/FORMATO INSCRIPCION ALUMNOS NVO INGRESO.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //Localizar la pestaña a modiicar
            
            int i = 0;
            while (i < students.size()) 
            {
                XSSFSheet sheet = workbook.getSheetAt(sheetCount);
                for (int j = 0; j < 20; j++) 
                {
                    Student student = new Student();
                    student = students.get(i);
                    //Aumentar la iteracion de count
                    count++;
                    rowCount++;
                    //Obtener la fila del archivo
                    XSSFRow row = sheet.getRow(rowCount);
                    //Si la fila es null entonces se crea
                    if (row == null) {
                        row = sheet.createRow(rowCount);
                    }
                    //Obtener la celda
                    XSSFCell cell1 = row.createCell(1);
                    XSSFCell cell2 = row.createCell(2);
                    XSSFCell cell3 = row.createCell(3);
                    XSSFCell cell4 = row.createCell(4);
                    XSSFCell cell5 = row.createCell(5);
                    XSSFCell cell6 = row.createCell(6);
                    //Condiciones para crear las celdas en caso de que sean nulas
                    if (cell1 == null) 
                    {
                        cell1 = row.createCell(1);
                    }
                    if (cell2 == null) 
                    {
                        cell2 = row.createCell(2);
                    }
                    if (cell3 == null) 
                    {
                        cell3 = row.createCell(3);
                    }
                    if (cell4 == null) 
                    {
                        cell4 = row.createCell(4);
                    }
                    if(cell5 == null)
                    {
                        cell5 = row.createCell(5);
                    }
                    if(cell6 == null)
                    {
                        cell6 = row.createCell(6);
                    }

                    cell1.setCellValue(count);
                    cell2.setCellValue(student.getLastName() + " " + student.getName());
                    cell3.setCellValue(student.getControlNumber());
                    cell4.setCellValue(CareerDAO.select_Career_name(student.getIdCarrera()));
                    cell5.setCellValue(student.getSemester());
                    cell6.setCellValue(student.getSex());

                    i++;
                    if(i >= students.size())
                    {
                        break;
                    }
                }

                XSSFRow row1 = sheet.getRow(6);
                if (row1 == null) 
                {
                    row1 = sheet.createRow(6);
                }
                XSSFCell cellActivity = row1.createCell(1);
                if (cellActivity == null) 
                {
                    cellActivity = row1.createCell(1);
                }
                cellActivity.setCellValue("ACTIVIDAD: " + activityName);
                XSSFCell cellPeriod = row1.createCell(4);
                if (cellPeriod == null) 
                {
                    cellPeriod = row1.createCell(4);
                }
                cellPeriod.setCellValue("PERIODO: " + period);

                XSSFRow row2 = sheet.getRow(8);
                if (row2 == null) 
                {
                    row2 = sheet.createRow(1);
                }
                XSSFCell cellTeacher = row2.getCell(1);
                if (cellTeacher == null) 
                {
                    cellTeacher = row2.createCell(1);
                }
                cellTeacher.setCellValue("PROFESOR: " + teacherName);
                XSSFCell cellSchedule = row2.createCell(4);
                if (cellSchedule == null) 
                {
                    cellSchedule = row2.createCell(4);
                }
                cellSchedule.setCellValue("HORARIO: " + schedule);

                sheetCount++;
                rowCount = 11;
            }
            file.close();
            
            FileOutputStream output = new FileOutputStream("Formatos/Listas/" + FileName + ".xlsx");
            workbook.write(output);
            output.close();
        }
        catch(FileNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
