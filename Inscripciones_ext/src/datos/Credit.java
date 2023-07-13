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
public class Credit 
{
    private int idCredit;
    private int idStudent;
    
    public Credit(){
    }
    
    public Credit(int idCredit)
    {
        this.idCredit = idCredit;
    }
    public Credit(int idCredit, int idStudent)
    {
        this.idCredit = idCredit;
        this.idStudent = idStudent;
    }
    
    public void setIdStudent(int idStudent)
    {
        this.idStudent = idStudent;
    }
    
    public int getIdStudent()
    {
        return idStudent;
    }
    
    public int getIdCredit()
    {
        return idCredit;
    }
    
    public Object[] getCreditsData()
    {
      return new Object[]{String.valueOf(this.idCredit), String.valueOf(this.idStudent)};
    }

    @Override
    public String toString() {
        return "Credit{" + "idCredit=" + idCredit + ", idStudent=" + idStudent + '}';
    }
    
    
}
