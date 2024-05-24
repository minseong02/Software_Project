/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AirplaneBS_package;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author mskim
 */
public class Airplane_information {
    
    private JComboBox<String> airline_combo;
    private JComboBox<String> departure_area_combo;
    private JComboBox<String> arrival_area_combo;
    private JComboBox<String> seat_type_combo;
    private JRadioButton round_trip;
    private JRadioButton one_way;
    private JTextField airplane_cost_text;
    private JTextField airplane_register;
    
    public Airplane_information(String[] airline, String[] departure_area,String[] arrival_area, String[]seat_type){
        airline_combo = new JComboBox<String>(airline);
        departure_area_combo = new JComboBox<String>(departure_area);
        arrival_area_combo = new JComboBox<String>(arrival_area);
        seat_type_combo = new JComboBox<String>(seat_type);
        round_trip = new JRadioButton("왕복");
        one_way = new JRadioButton("편도");
        airplane_cost_text = new JTextField();
        airplane_register = new JTextField();
    }
    
    public JComboBox<String> getAirline(){
        return airline_combo;
    }
    
    public JComboBox<String> getDepartureArea(){
        return departure_area_combo;
    }
    
    public JComboBox<String> getArrivalArea(){
        return arrival_area_combo;
    }
    
    public JComboBox<String> getSeatType(){
        return seat_type_combo;
    }
    
    public JRadioButton getRoundTrip(){
        return round_trip;
    }
    
    public JRadioButton getOneWay(){
        return one_way;
    }
    
    public JTextField getAirplaneCost(){
        return airplane_cost_text;
    }
    
    public JTextField getAirplaneRegister(){
        return airplane_register;
    }
    
    
    public void setAirline(JComboBox<String> airline_combo){
        this.airline_combo = airline_combo;
    }
    public void setDepartureArea(JComboBox<String> departure_area_combo){
        this.departure_area_combo = departure_area_combo;
    }
    public void setArrivalArea(JComboBox<String> arrival_area_combo){
        this.arrival_area_combo = arrival_area_combo;
    }
    public void setSeatType(JComboBox<String> seat_type_combo){
        this.seat_type_combo = seat_type_combo;
    }
    public void setRoundTrip(JRadioButton round_trip){
        this.round_trip = round_trip;
    }
    public void setOneWay(JRadioButton one_way){
        this.one_way = one_way;
    }
    public void setAirplaneCost(JTextField airplane_cost_text){
        this.airplane_cost_text = airplane_cost_text; 
    }
    public void setAirplaneRegister(JTextField airplane_register){
        this.airplane_register = airplane_register;
    }
    
}
