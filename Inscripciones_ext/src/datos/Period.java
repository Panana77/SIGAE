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
public class Period 
{
    private int idPeriod;
    private String period;
    public Period(){
    }

    public Period(int idPeriod)
    {
        this.idPeriod = idPeriod;
    }
    public Period(String period)
    {
        this.period = period;
    }
    
    public Period(int id, String period)
    {
        this.idPeriod = idPeriod;
        this.period = period;
    }
    
    public void setPeriod(String period)
    {
        this.period = period;
    }
    
    public String getPeriod()
    {
        return this.period;
    }
    
    public int getidPeriod()
    {
        return this.idPeriod;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Period{idPeriod=").append(idPeriod);
        sb.append(", period=").append(period);
        sb.append('}');
        return sb.toString();
    }
    
    
}
