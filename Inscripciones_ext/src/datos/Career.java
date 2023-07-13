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
public class Career {
    private String idCareer;
    private String careerName;
    
    public Career(){
    }
    
    public Career(String idCareer)
    {
        this.idCareer = idCareer;
    }
    
    public Career(String idCareer, String careerName)
    {
        this.idCareer = idCareer;
        this.careerName = careerName;
    }
    
    public void setCareerName(String careerName)
    {
        this.careerName = careerName;
    }
    
    public String getCareerName()
    {
        return careerName;
    }
    
    public String getIdCareer()
    {
        return idCareer;
    }
    
    public Object[] getCareerData()
    {
        return new Object[]{String.valueOf(this.idCareer), String.valueOf(this.careerName)};
    }

    @Override
    public String toString() {
        return "Career{" + "idCareer=" + idCareer + ", careerName=" + careerName + '}';
    }
    
    
}
