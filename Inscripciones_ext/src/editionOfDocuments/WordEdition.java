/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editionOfDocuments;

/**
 *
 * @author Panana
 */
import com.mysql.cj.conf.PropertyKey;
import datos.Career;
import datos.CareerDAO;
import datos.Student;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static org.apache.poi.hssf.util.HSSFColor.AQUA.index;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import panels.InscriptionsPanel;

public class WordEdition {

    public WordEdition() {
    }
    
    /**
     * 
     * @param fileName Nombre del archivo
     * @param day Dia actual
     * @param month Mes actual
     * @param year Año actual
     * @param isSelective Dar a cononer si el estudiante pertenecio en el selectivo de su actividad
     * @param tradeNumber Numero del oficio
     * @param text Texto que ira como mensaje en el credito
     * @throws IOException 
     */
    public void createCreditsFormat(String fileName, int day, String month, int year, boolean isSelective, int tradeNumber, String text) throws IOException
    {
        FileInputStream file = new FileInputStream("src/documents/Formato_de_creditos.docx");
        XWPFDocument doc = new XWPFDocument(file);

        //Creación de parrafos
        
        //Lugar y fecha
        createParagraph(doc,ParagraphAlignment.RIGHT, TextAlignment.AUTO, true, "Montserrat Medium", 10, "Cancún, Quintana Roo, " + day + "/" + month + "/" + year, 0);
        //Conteo de creditos expedidos
        createParagraph(doc,ParagraphAlignment.RIGHT, TextAlignment.AUTO, false,"Montserrat Medium", 10, "OFICIO No. DAE/ " + tradeNumber +"/" + year, 1);
        //Nombre
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, true,"Montserrat ExtraBold", 10, "DIANELA SHANDERINE GARCIA HERRERA", 0);
        //Puesto
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, true,"Montserrat ExtraBold", 10, "JEFE DEL DEPTO. DE SERVICIOS ESCOLARES", 2);
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, false,"Montserrat ExtraBold", 10, "PRESENTE", 0);
        
        //Colocar el texto del credito
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, false,"Montserrat", 9, text, 2);
        createParagraph(doc,ParagraphAlignment.DISTRIBUTE, TextAlignment.AUTO, false,"Montserrat", 9, "Se extiende la presente en la Ciudad de Cancún, Quintana Roo, a los " + day +" días del mes de " + month + " del " + year + ".", 4);
        
        //Quien envia el reporte
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, true,"Montserrat ExtraBold", 10, "A T E N T A M E N T E", 0);
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, false,"Montserrat ExtraLight", 8, "Excelencia en Educación Tecnológica®", 0);
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, false,"Montserrat ExtraLight", 8, "Cultural, Cívica y Tecnológica para la Superación de Mexico", 5);

        
        //Jefe del departamenteo
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, true,"Montserrat ExtraBold", 10, "ALEJANDRO FLORES REYES", 0);
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, true,"Montserrat ExtraBold", 10, "JEFE DEL DEPTO. DE ACTIVIDADES EXTRAESCOLARES", 6);
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, false,"Montserrat Medium", 8, "Minutario", 0);
        createParagraph(doc,ParagraphAlignment.LEFT, TextAlignment.AUTO, false,"Montserrat Medium", 8, "RMC/VMOC/AFR/imif*", 0);

        //Guardar el archivo
        FileOutputStream out = new FileOutputStream(new File("Formatos/Creditos/" + fileName + ".docx"));
        doc.write(out);
        file.close();
        doc.close();
        
    }
    /**Crear archivo de estadisticas
     * 
     * @param fileName Nombre del archivo
     * @param period nombre del periodo
     * @param activityName nombre de las actividades
     * @param activityCount conteo de alumnos registrados en las actividades
     * @param day dia actual
     * @param month mes actual
     * @param year año actual
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void createStatisticsFormat(String fileName, String period, List<String> activityName, List<String> activityCount, String day, String month, String year) throws FileNotFoundException, IOException
    {
        //Creamos objeto para abrir la plantilla de estadisticas
        FileInputStream file = new FileInputStream("src/documents/Formato_de_estadisticas.docx");
        XWPFDocument doc = new XWPFDocument(file);

        //Parrafos de titulos
        //Diseñar el titulo que es el nombre de la institución
        createParagraph(doc,ParagraphAlignment.CENTER, TextAlignment.TOP, true, "Arial", 14, "INSTITUTO TECNOLÓGICO DE CANCUN", 0);
        //Diseñar el subtitulo que es el nombre de la subdireccion
        createParagraph(doc, ParagraphAlignment.CENTER, TextAlignment.TOP, true, "Arial", 14, "Subdirección de Planeación y Vinculación", 0);
        //Diseñar el nombre del departamento
        createParagraph(doc, ParagraphAlignment.CENTER, TextAlignment.TOP, true, "Arial", 11, "DEPARTAMENTO DE ACTIVIDADES EXTRAESCOLARES", 0);
        //Diseñar los datos a rellenar
        createParagraph(doc, ParagraphAlignment.CENTER, TextAlignment.TOP, true, "Arial", 11, "Reporte de alumnos inscritos", 0);
        createParagraph(doc, ParagraphAlignment.CENTER, TextAlignment.TOP, true, "Arial", 11, "Periodo: " + period, 2);

        //Creacion de la tabla
        XWPFTable table = doc.createTable(1, 2);
        //Colocar el texto de cabecera de la tabla y estilos
        createTableHeader(table, 0, true, "Arial", 12, "Actividades");
        createTableHeader(table, 1, true, "Arial", 12, "Conteo");
        //Total de la suma de todo el conteo
        int total = 0;
        int j = 0;
        //agregar las columnas a la tabla asi como la información
        for(int i = 1; i < activityName.size()+1; i++)
        {
            table.createRow();
            table.getRow(i).getCell(0).setText(activityName.get(j));
            table.getRow(i).getCell(1).setText(activityCount.get(j));
            total += Integer.valueOf(activityCount.get(j));
            
            table.getRow(i).getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            table.getRow(i).getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            j++;
        }
        
        //Agreagar columna de conteo total
        table.createRow();
        table.getRow(activityName.size()+1).getCell(0).setText("Total");
        table.getRow(activityName.size()+1).getCell(1).setText("" + total);
        //Agregar parrafo de fecha de elaboración
        createParagraph(doc, ParagraphAlignment.LEFT, TextAlignment.AUTO, false, "Arial", 11, "", 1);
        createParagraph(doc, ParagraphAlignment.LEFT, TextAlignment.AUTO, false, "Arial", 11, "Fecha de elaboración: " + day + "-" + month + "-" + year, 0);
        
        //Guardar el archivo
        FileOutputStream out = new FileOutputStream(new File("Formatos/Estadisticas/" + fileName + ".docx"));
        doc.write(out);
        file.close();
        doc.close();
    }
    
    /** Crear archivo de formatos de calidad
     * 
     * @param students Pasar lita de tipo Student
     * @param fileName Nombre al archivo
     * @param careerName Nombre de la carrera
     * @throws InvalidFormatException
     * @throws IOException 
     */
    public void createQualityFormat(List<Student> students, String fileName, String careerName, String activityType) throws InvalidFormatException, IOException {
        //Creamos objeto para abrir la plantilla de formatos de calidad
        FileInputStream file = new FileInputStream("../src/documents/Formato_de_calidad.docx");
        XWPFDocument doc = new XWPFDocument(file);

       //Parrafos de titulos
        //Diseñar el titulo que es el nombre de la institución
        createParagraph(doc,ParagraphAlignment.CENTER, TextAlignment.TOP, true, "Arial", 14, "INSTITUTO TECNOLÓGICO DE CANCUN", 0);
        //Diseñar el subtitulo que es el nombre de la subdireccion
        createParagraph(doc, ParagraphAlignment.CENTER, TextAlignment.TOP, true, "Arial", 14, "Subdirección de Planeación y Vinculación", 0);
        //Diseñar el nombre del departamento
        createParagraph(doc, ParagraphAlignment.CENTER, TextAlignment.TOP, true, "Arial", 11, "DEPARTAMENTO DE ACTIVIDADES EXTRAESCOLARES", 0);
        //Diseñar los datos a rellenar
        createParagraph(doc, ParagraphAlignment.CENTER, TextAlignment.TOP, true, "Arial", 11, "OFICINA DE PROMOCIÓN _______"+ activityType +"________", 0);
        createParagraph(doc, ParagraphAlignment.CENTER, TextAlignment.TOP, true, "Arial", 11, "ACTIVIDAD________" + students.get(0).getActivity() + "_________", 2);

       //Creacion de la tabla
        XWPFTable table = doc.createTable(1, 6);
        //Colocar el texto de cabecera de la tabla y estilos
        createTableHeader(table, 0, true, "Arial", 12, "NO.");
        createTableHeader(table, 1, true, "Arial", 12, "NOMBRE");
        createTableHeader(table, 2, true, "Arial", 12, "CONTROL");
        createTableHeader(table, 3, true, "Arial", 12, "ESP.");
        createTableHeader(table, 4, true, "Arial", 12, "SEM.");
        createTableHeader(table, 5, true, "Arial", 12, "OBSERVACIONES");
        //indice
        int index = 1;
        //Crear todas las filas de la tabla
        for (int i = 0; i < students.size(); i++) {
            table.createRow();
        }
        //Agregamos los datos a la tabla
        for (Student student : students) {
            table.getRow(index).getCell(0).setText(String.valueOf(index));
            table.getRow(index).getCell(1).setText(student.getLastName() + " " + student.getName());
            table.getRow(index).getCell(2).setText(String.valueOf(student.getControlNumber()));
            table.getRow(index).getCell(3).setText(careerName);
            table.getRow(index).getCell(4).setText(String.valueOf(student.getSemester()));
            index++;
        }

        //Parrafo de fecha
        createParagraph(doc, ParagraphAlignment.LEFT, TextAlignment.AUTO, false, "Arial", 11, " ", 0);
        createParagraph(doc, ParagraphAlignment.LEFT, TextAlignment.AUTO, false, "Arial", 11, "          Lugar y fecha ", 5);
        
        //Parrafo para firmas 
        createParagraph(doc, ParagraphAlignment.CENTER, TextAlignment.TOP, false, "Arial", 11, "     Promotor (a) Cultural       Titular de Oficina de Promoción Cultural          Titular de Departamento", 0);
        createParagraph(doc, ParagraphAlignment.RIGHT, TextAlignment.TOP, false, "Arial", 11, "Actividades Extraescolares", 5);
        
        //Crear titulo de inscructivo
        createParagraph(doc, ParagraphAlignment.CENTER, TextAlignment.CENTER, true, "Arial", 12, "INSTRUCTIVO DE LLENADO", 2);
        //Crear tabla de inscructivo
        XWPFTable instructiveTable = doc.createTable(13, 2);
        //Crear Cabecera de la tabla de inscructivo
        createTableHeader(instructiveTable, 0, true, "Arial", 12, "Número");
        createTableHeader(instructiveTable, 1, true, "Arial", 12, "Descripción");
        //Añadir contenido a la tabla
        instructiveTable.getRow(1).getCell(0).setText("1");
        instructiveTable.getRow(2).getCell(0).setText("2");
        instructiveTable.getRow(3).getCell(0).setText("3");
        instructiveTable.getRow(4).getCell(0).setText("4");
        instructiveTable.getRow(5).getCell(0).setText("5");
        instructiveTable.getRow(6).getCell(0).setText("6");
        instructiveTable.getRow(7).getCell(0).setText("7");
        instructiveTable.getRow(8).getCell(0).setText("8");
        instructiveTable.getRow(9).getCell(0).setText("9");
        instructiveTable.getRow(10).getCell(0).setText("10  ");
        instructiveTable.getRow(11).getCell(0).setText("11");
        instructiveTable.getRow(12).getCell(0).setText("12");
        instructiveTable.getRow(1).getCell(1).setText("Anotar cultural o deportiva, según corresponda");
        instructiveTable.getRow(2).getCell(1).setText("Anotar el nombre de la actividad cultural y/o deportiva  que el estudiante haya cursado.");
        instructiveTable.getRow(3).getCell(1).setText("Anotar el número consecutivo que corresponda.");
        instructiveTable.getRow(4).getCell(1).setText("Anotar el apellido paterno, materno y nombre(s) del Estudiante.");
        instructiveTable.getRow(5).getCell(1).setText("Anotar el número de control del Estudiante      ");
        instructiveTable.getRow(6).getCell(1).setText("Anotar el identificador de la Carrera, que esté cursando el Estudiante.");
        instructiveTable.getRow(7).getCell(1).setText("Anotar el semestre escolar que ha cursado el Estudiante.");
        instructiveTable.getRow(8).getCell(1).setText("Anotar observaciones.");
        instructiveTable.getRow(9).getCell(1).setText("Anotar lugar donde se encuentre el plantel y la fecha de emisión del documento.");
        instructiveTable.getRow(10).getCell(1).setText("Nombre y Firma del Promotor (a) Cultural.");
        instructiveTable.getRow(11).getCell(1).setText("Nombre y Firma del Titular de Oficina de Promoción Cultural.");
        instructiveTable.getRow(12).getCell(1).setText("Nombre y Firma del Titular del Departamento de Actividades Extraescolares.");
        //Guardar el archivo
        FileOutputStream out = new FileOutputStream(new File("Formatos/Formatos de calidad/" + fileName + ".docx"));
        doc.write(out);
        file.close();
        doc.close();
    }

    /**Crear cabecera de una tabla
     * 
     * @param table Tabla a rellenar
     * @param column Numero de columna a modificar
     * @param isBold Colocar negritas al texto
     * @param font Tipo de fuente de texto
     * @param fontSize Tamaño de la fuente de texto
     * @param text Texto en la celda
     */
    private void createTableHeader(XWPFTable table, int column, boolean isBold, String font, int fontSize, String text) {
        XWPFTableRow row = table.getRow(0);
        XWPFTableCell cell = row.getCell(column);
        XWPFParagraph p = cell.getParagraphArray(0);
        p.setAlignment(ParagraphAlignment.CENTER);
        p.setVerticalAlignment(TextAlignment.TOP);
        XWPFRun cellRun = p.createRun();
        //Colocar el texto en la celda
        cellRun.setText(text);
        //Colocar las negritas en el texto de la celda
        cellRun.setBold(isBold);
        //Establecer tamaño de texto en la celda
        cellRun.setFontSize(fontSize);
        //Colcar el tipo de fuente del texto en la celda
        cellRun.setFontFamily(font);
    }

    /**Crear un parrafo
     * 
     * @param doc Objeto de documento Word
     * @param aligment Tipo de alineación del texto
     * @param verticalAligment Tipo de alineación vertical del texto
     * @param isBold Colocar negritas al texto
     * @param font Tipo de fuente de texto
     * @param fontSize Tamaño de la fuente de texto
     * @param text Texto del parrafo
     * @param BreakNumber Numero de saltos de linea al final del parrafo
     */
    private void createParagraph(XWPFDocument doc, ParagraphAlignment aligment,TextAlignment verticalAligment, boolean isBold, String font, int fontSize, String text, int BreakNumber) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setAlignment(aligment);
        paragraph.setVerticalAlignment(verticalAligment);
        XWPFRun run = paragraph.createRun();
        //Colocar los estilos
        run.setBold(isBold);
        run.setFontSize(fontSize);
        run.setFontFamily(font);
        //Insertar el texto
        run.setText(text);
        
        //Dar un determinado numero de saltos de linea
        for(int i = 0; i <BreakNumber; i++)
        {
            run.addBreak();
        }
    }
}
