/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Panana
 */
public class Student 
{
   private int idStudent;
   private String name;
   private String lastName;
   private int age;
   private String sex;
   private int semester;  
   private String idCarrera;
   private String Activity;
   private int controlNumber;
   private int idPeriod;
   private String email;
   private String phoneNumber;
   
   public Student(){
   }
   
   public Student(int idStudent)
   {
       this.idStudent = idStudent;
   }
   
   public Student(String name, String lastName, int age, String sex, int semester, String carrera, String Activity, int controlNumber,int idPeriod, String email, String phoneNumber)
   {
       this.name = name;
       this.lastName = lastName;
       this.age = age;
       this.sex = sex;
       this.semester = semester;
       this.idCarrera = carrera;
       this.Activity = Activity;
       this.controlNumber = controlNumber;
       this.idPeriod = idPeriod;
       this.email = email;
       this.phoneNumber = phoneNumber;
   }

    public Student(int idStudent, String name, String lastName, int age, String sex, int semester, String carrera, String Activity, int controlNumber ,int idPeriod,  String email, String phoneNumber) 
    {
        this.idStudent = idStudent;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.semester = semester;
        this.idCarrera = carrera;
        this.Activity = Activity;
        this.controlNumber = controlNumber;
        this.idPeriod = idPeriod;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

   public void setName(String name)
   {
       this.name = name;
   }
   
   public void setLastName(String lastName)
   {
       this.lastName = lastName;
   }
   
   public void setAge(int age)
   {
       this.age = age;
   }
   
   public void setSex(String sex)
   {
       this.sex = sex;
   }
   
   public void setSemester(int semester)
   {
       this.semester = semester;
   }
   
   public void setIdCarrera(String idCarrera)
   {
       this.idCarrera = idCarrera;
   }
   
   public void setActivity(String activity)
   {
       this.Activity = activity;
   }
   
   public void setControlNumber(int controlnumber)
   {
       this.controlNumber = controlnumber;
   }
   
   public void setPhoneNumber(String phoneNumber)
   {
       this.phoneNumber = phoneNumber;
   }
   
   public void setEmail(String email)
   {
       this.email = email;
   }
   
   public void setIdPeriod(int idPeriod)
   {
       this.idPeriod = idPeriod;
   }
   public int getID()
   {
       return this.idStudent;
   }
   
   public String getName()
   {
       return this.name;
   }
   
   public String getLastName()
   {
       return this.lastName;
   }
   
   public int getAge()
   {
       return this.age;
   }
   
   public String getSex()
   {
       return this.sex;
   }
   
   public int getSemester()
   {
       return this.semester;
   }
   
   public String getIdCarrera()
   {
       return this.idCarrera;
   }
   
   public String getActivity()
   {
       return this.Activity;
   }
   
   public int getControlNumber()
   {
       return this.controlNumber;
   }

   public String getPhoneNumber()
   {
       return this.phoneNumber;
   }
   
   public String getEmail()
   {
       return this.email;
   }
   
   public int getIdPeriod()
   {
       return this.idPeriod;
   }
   
   public Object[] getStudentsData()
   {
      return new Object[]{String.valueOf(this.idStudent), String.valueOf(this.name), String.valueOf(this.lastName), String.valueOf(this.age), String.valueOf(this.sex), String.valueOf(this.idCarrera), String.valueOf(this.controlNumber), String.valueOf(this.semester), String.valueOf(this.Activity), String.valueOf(this.idPeriod)};
   }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{idStudent=").append(idStudent);
        sb.append(", name=").append(name);
        sb.append(", lastName=").append(lastName);
        sb.append(", age=").append(age);
        sb.append(", sex=").append(sex);
        sb.append(", semester=").append(semester);
        sb.append(", carrera=").append(idCarrera);
        sb.append(", Activity=").append(Activity);
        sb.append(", controlNumber=").append(controlNumber);
        sb.append(", PhoneNumber=").append(phoneNumber);
        sb.append(", Email=").append(email);
        sb.append(", idPeriod=").append(idPeriod);
        sb.append('}');
        return sb.toString();
    }

   
}
