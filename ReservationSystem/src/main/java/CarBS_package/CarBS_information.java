/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarBS_package;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author mskim
 */
public class CarBS_information {
    
    private String car_company;
    private String rental_time;
    private String car_cost;
    private String vehicle_type;
    private String gasoline;
    private String diesel;
    private String electricity;
    private String yes;
    private String no;
    private String car_register;
    
    
    public CarBS_information(String car_company,String rental_time,String car_cost,String vehicle_type,String gasoline,String diesel,String electricity,String yes,String no,String car_register){
       this.car_company = car_company;
       this.rental_time = rental_time;
       this.car_cost = car_cost;
       this.vehicle_type = vehicle_type;
       this.gasoline = gasoline;
       this.diesel = diesel;
       this.electricity = electricity;
       this.yes = yes;
       this.no = no;
       this.car_register = car_register;
    }
    
    // getter 메서드
    
    public String getCarCompany(){
        return car_company;
    }
    
    public String getRentalTime(){
        return rental_time;
    }
    
    public String getCarCost(){
        return car_cost;
    }
    
    public String getVehicleType(){
        return vehicle_type;
    }
    
    public String getGasoline(){
        return gasoline;
    }
    
    public String getDiesel(){
        return gasoline;
    }
    
    public String getElectricity(){
        return electricity;
    }
    
    public String getYes(){
        return yes;
    }
    
    public String getNo(){
        return no;
    }
    
    public String getCarRegister(){
        return  car_register;
    }
    
    
    
    // setter 메서드
    
    public void setCarCompany(String car_company){
        this.car_company = car_company;
    }
    
    public void setRentalTime(String rental_time_combo){
        this.rental_time = rental_time;
    }
    
    public void setCarCost(String car_cost){
        this.car_cost = car_cost;
    }
    
    public void setVehicleType(String vehicle_type){
        this.vehicle_type = vehicle_type;
    }
    
    public void setOil(String oil){
        if(oil.equals(gasoline)){
            this.gasoline=oil;
        }
        else if(oil.equals(diesel)){
            this.diesel=oil;
        }
        else if(oil.equals(electricity)){
            this.electricity = oil;
        }
    }
    
    public void setHighPass(String highpass){
        if(highpass.equals(yes)){
            this.yes = highpass;
        }
        else if(highpass.equals(no)){
            this.no=highpass;
        }
    }
    
    public void setCarRegister(String car_register){
        this.car_register = car_register;
    }
        
}
