/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBS_package;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author mskim
 */
public class HotelBS_information { 
    
    private JTextField hotel_name;// 호텔 이름
    private JComboBox<String> hotel_area_combo; // 지역 콤보 박스
    private JTextField detailed_address; // 상세 주소
    private JComboBox<String> num_guest_combo; // 투숙객 수 콤보 박스
    private JRadioButton breakfast_yes; // 조식O
    private JRadioButton breakfast_no; // 조식X
    private JRadioButton suit_room; // 스위트룸
    private JRadioButton deluxe_room; // 디럭스룸
    private JRadioButton standard_room; // 스탠다드룸
    private JTextField hotel_cost_text; // 숙박 비용
    private JTextField hotel_register; // 등록 여부
    
    
    
    public HotelBS_information(String[] hotel_area, String[] num_guest){ 
        
        hotel_name = new JTextField();
        hotel_area_combo = new JComboBox<String>(hotel_area);
        detailed_address = new JTextField();
        num_guest_combo = new JComboBox<String>(num_guest);
        //num_guest_combo.addItem("::인원 수::");
        breakfast_yes = new JRadioButton("O");
        breakfast_no = new JRadioButton("X");
        suit_room = new JRadioButton("스위트룸");
        deluxe_room = new JRadioButton("디럭스룸");
        standard_room = new JRadioButton("스탠다드");
        hotel_cost_text = new JTextField();
        hotel_register = new JTextField();
        
    }
    
    // getter 메서드
    
 
    
    public JTextField getHotelName(){
       return hotel_name;
    }
    
    public JComboBox<String> getHotelArea(){ 
        return hotel_area_combo;
    }
    
    public JTextField getDetailedAddress(){
        return detailed_address;
    }
    
    public JComboBox<String> getNumGuest(){
        return num_guest_combo;
    }
    
    public JRadioButton getBreakfastYes() {
        return breakfast_yes;
    }
    
    public JRadioButton getBreakfastNo() {
        return breakfast_no;
    }
    
   public JRadioButton getSuitRoom() {
        return suit_room;
    } 
   
   public JRadioButton getDeluxeRoom() {
        return deluxe_room;
    }
   
   public JRadioButton getStandardRoom() {
        return standard_room;
    }
   
   public JTextField getHotelCost() {
        return hotel_cost_text;
    }

    public JTextField getHotelRegistration() {
        return hotel_register;
    }

    

    
    // setter 메서드
    
     
    public void setHotelName(JTextField hotel_name) {
        this.hotel_name = hotel_name;
    }

     public void setHotelArea(JComboBox<String> hotel_area_combo) {
        this.hotel_area_combo = hotel_area_combo;
    }

    public void setDetailedAddress(JTextField detailed_address) {
        this.detailed_address = detailed_address;
    }

    public void setNumGuests(JComboBox<String> num_guest_combo) {
        this.num_guest_combo=num_guest_combo;
    }

    public void setBreakfast(JRadioButton breakfast) {
        if (breakfast.equals(breakfast_yes)) {
            this.breakfast_yes=breakfast;
        } else if (breakfast.equals(breakfast_no)) {
            this.breakfast_no = breakfast;
        }
    }

    public void setRoomType(JRadioButton roomType) {
        if (roomType.equals(suit_room)){
            this.suit_room=roomType;
        }
        else if(roomType.equals(deluxe_room)){
            this.deluxe_room=roomType;
        }
        else if(roomType.equals(standard_room)){
            this.standard_room=roomType;
        }
}


    public void setHotelCost(JTextField hotel_cost_text) {
        this.hotel_cost_text = hotel_cost_text;
    }

    public void setRegistration(JTextField hotel_register) {
        this.hotel_register = hotel_register;
    }

    

    
    
}

