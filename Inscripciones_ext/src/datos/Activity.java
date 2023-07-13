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
public class Activity 
{
    private String activityName;
    private int idActivityType;
    private int limit; //Cupo limite
    public Activity(){
    }
    
    public Activity(String activityName)
    {
        this.activityName = activityName;
    }
    
    public Activity(int limit)
    {
        this.limit = limit;
    }
    
    public Activity(String activityName, int idActivityType)
    {
        this.activityName = activityName;
        this.idActivityType = idActivityType;
    }
    
    public Activity(String activityName, int idActivityType, int limit)
    {
        this.activityName = activityName;
        this.idActivityType = idActivityType;
        this.limit = limit;
    }
    
    public void setLimit(int limit)
    {
        this.limit = limit;
    }
    
    public int getLimit()
    {
        return limit;
    }
    
    public String getActivityName()
    {
        return activityName;
    }
    
    public void setIdActivityType(int idActivityTipe)
    {
        this.idActivityType = idActivityTipe;
    }
    
    public int getIdActivityType()
    {
        return idActivityType;
    }
    
    public Object[] getActivityData()
    {
        return new Object[]{String.valueOf(this.activityName), String.valueOf(this.idActivityType), String.valueOf(this.limit)};
    }

    @Override
    public String toString() {
        return "Activity{" + "activityName=" + activityName + ", idActivityType=" + idActivityType + "limit"+ limit + '}';
    }
    
    
}
