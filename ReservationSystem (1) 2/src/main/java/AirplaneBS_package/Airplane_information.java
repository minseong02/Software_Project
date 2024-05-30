/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AirplaneBS_package;

/**
 *
 * @author mskim
 */
public class Airplane_information {
    
    private String airline;
    private String departure_area;
    private String arrival_area;
    private String seat_type;
    private String round_trip;
    private String one_way;
    private String airplane_cost;
    private String airplane_register;
    
    public Airplane_information(String airline, String departure_area,String arrival_area,String seat_type,String round_trip,String one_way,String airplane_cost,String airplane_register){
       this.airline = airline;
       this.departure_area = departure_area;
       this.arrival_area = arrival_area;
       this.seat_type = seat_type;
       this.round_trip = round_trip;
       this.one_way =  one_way;
       this.airplane_cost = airplane_cost;
       this.airplane_register = airplane_register;
    }
    
    public String getAirline(){
        return airline;
    }
    
    public String getDepartureArea(){
        return departure_area;
    }
    
    public String getArrivalArea(){
        return arrival_area;
    }
    
    public String getSeatType(){
        return seat_type;
    }
    
    public String getRoundTrip(){
        return round_trip;
    }
    
    public String getOneWay(){
        return one_way;
    }
    
    public String getAirplaneCost(){
        return airplane_cost;
    }
    
    public String getAirplaneRegister(){
        return airplane_register;
    }
    
    
    public void setAirline(String airline){
        this.airline = airline;
    }
    public void setDepartureArea(String departure_area){
        this.departure_area = departure_area;
    }
    public void setArrivalArea(String arrival_area){
        this.arrival_area = arrival_area;
    }
    public void setSeatType(String seat_type){
        this.seat_type = seat_type;
    }
    public void setRoundTrip(String round_trip){
        this.round_trip = round_trip;
    }
    public void setOneWay(String one_way){
        this.one_way = one_way;
    }
    public void setAirplaneCost(String airplane_cost){
        this.airplane_cost = airplane_cost; 
    }
    public void setAirplaneRegister(String airplane_register){
        this.airplane_register = airplane_register;
    }
    
}
