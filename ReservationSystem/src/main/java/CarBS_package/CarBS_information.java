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
    
    private JTextField car_company;
    private JComboBox <String> rental_time_combo;
    private JTextField car_cost;
    private JComboBox<String> vehicle_type_combo;
    private JRadioButton gasoline;
    private JRadioButton diesel;
    private JRadioButton electricity;
    private JRadioButton yes;
    private JRadioButton no;
    private JTextField car_register;
    
    
    public CarBS_information(String[] rental_time, String[]vehicle_type){
        car_company = new JTextField();
        rental_time_combo = new JComboBox<String>(rental_time);
        car_cost = new JTextField();
        vehicle_type_combo =new JComboBox<String>(vehicle_type);
        gasoline = new JRadioButton("휘발유");
        diesel = new JRadioButton("경유");
        electricity = new JRadioButton("전기");
        yes = new JRadioButton("O");
        no = new JRadioButton("X");
        car_register = new JTextField();
    }
    
    // getter 메서드
    
    public JTextField getCarCompany(){
        return car_company;
    }
    
    public JComboBox <String> getRentalTime(){
        return rental_time_combo;
    }
    
    public JTextField getCarCost(){
        return car_cost;
    }
    
    public JComboBox<String> getVehicleType(){
        return vehicle_type_combo;
    }
    
    public JRadioButton getGasoline(){
        return gasoline;
    }
    
    public JRadioButton getDiesel(){
        return gasoline;
    }
    
    public JRadioButton getElectricity(){
        return electricity;
    }
    
    public JRadioButton getYes(){
        return yes;
    }
    
    public JRadioButton getNo(){
        return no;
    }
    
    public JTextField getCarRegister(){
        return  car_register;
    }
    
    
    
    // setter 메서드
    
    public void setCarCompany(JTextField car_company){
        this.car_company = car_company;
    }
    
    public void setRentalTime(JComboBox <String> rental_time_combo){
        this.rental_time_combo = rental_time_combo;
    }
    
    public void setCarCost(JTextField car_cost){
        this.car_cost = car_cost;
    }
    
    public void setVehicleType(JComboBox<String> vehicle_type_combo){
        this.vehicle_type_combo = vehicle_type_combo;
    }
    
    public void setOil(JRadioButton oil){
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
    
    public void setHighPass(JRadioButton highpass){
        if(highpass.equals(yes)){
            this.yes = highpass;
        }
        else if(highpass.equals(no)){
            this.no=highpass;
        }
    }
    
    public void setCarRegister(JTextField car_register){
        this.car_register = car_register;
    }
        
}
