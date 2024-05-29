/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBS_package;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author mskim
 */
public class HotelBS_information {  // 메멘토 패턴 Originator
    
    private String hotel_name;// 호텔 이름
    private String hotel_area; // 지역 콤보 박스
    private String detailed_address; // 상세 주소
    private String num_guest; // 투숙객 수 콤보 박스
    private String breakfast_yes; // 조식O
    private String breakfast_no; // 조식X
    private String suit_room; // 스위트룸
    private String deluxe_room; // 디럭스룸
    private String standard_room; // 스탠다드룸
    private String hotel_cost; // 숙박 비용
    private String hotel_register; // 등록 여부
    private HotelMemento hotelmemento; // 호텔메멘토 객체
    private List<HotelMemento> deleteHotel = new ArrayList<>();
    
    
    
    public HotelBS_information(String hotel_name,String hotel_area,String detailed_address,String num_guest,String breakfast_yes,String breakfast_no,String suit_room,String deluxe_room,String standard_room,String hotel_cost,String hotel_register){ 
        
        this.hotel_name = hotel_name;
        this.hotel_area = hotel_area;
        this.detailed_address = detailed_address;
        this.num_guest = num_guest;
        this.breakfast_yes = breakfast_yes;
        this.breakfast_no = breakfast_no;
        this.suit_room = suit_room;
        this.deluxe_room = deluxe_room;
        this.hotel_cost = hotel_cost;
        this.hotel_register = hotel_register;
        
    }
    
    // getter 메서드
    
 
    
    public String getHotelName(){
       return hotel_name;
    }
    
    public String getHotelArea(){ 
        return hotel_area;
    }
    
    public String getDetailedAddress(){
        return detailed_address;
    }
    
    public String getNumGuest(){
        return num_guest;
    }
    
    public String getBreakfastYes() {
        return breakfast_yes;
    }
    
    public String getBreakfastNo() {
        return breakfast_no;
    }
    
   public String getSuitRoom() {
        return suit_room;
    } 
   
   public String getDeluxeRoom() {
        return deluxe_room;
    }
   
   public String getStandardRoom() {
        return standard_room;
    }
   
   public String getHotelCost() {
        return hotel_cost;
    }

    public String getHotelRegistration() {
        return hotel_register;
    }

    
    // setter 메서드
    
     
    public void setHotelName(String hotel_name) {
        this.hotel_name = hotel_name;
    }

     public void setHotelArea(String hotel_area) {
        this.hotel_area = hotel_area;
    }

    public void setDetailedAddress(String detailed_address) {
        this.detailed_address = detailed_address;
    }

    public void setNumGuests(String num_guest) {
        this.num_guest=num_guest;
    }

    public void setBreakfast(String breakfast) {
        if (breakfast.equals(breakfast_yes)) {
            this.breakfast_yes=breakfast;
        } else if (breakfast.equals(breakfast_no)) {
            this.breakfast_no = breakfast;
        }
    }

    public void setRoomType(String roomType) {
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


    public void setHotelCost(String hotel_cost_text) {
        this.hotel_cost = hotel_cost_text;
    }

    public void setRegistration(String hotel_register) {
        this.hotel_register = hotel_register;
    }
    
 
    
    
    /*
    public void HotelMemento creationMemento(Memento memento){  // 현재 호텔 상태를 저장하는 HotelMemento 객체 생성
    // HotelBS_information클래스의 현재 상태를 가져와서 HotelMemento 객체에 저장. 나중에 상태 복원힐 수 있다
    // 오리지네이터의 상태를 캡슐화하
        return new HotelMemento( // HotelMemento의 생성자는 여러 인수를 받으며, 각 인수는 오리지네이터의 현재 상태를 나타낸다.
            hotel_name.getText(),
            (String) hotel_area.getSelectedItem(),
            detailed_address.getText(),
            (String) num_guest.getSelectedItem(),
            breakfast_yes.isSelected() ? "O" : "X",
            suit_room.isSelected() ? "스위트룸" : deluxe_room.isSelected() ? "디럭스룸" : "스탠다드룸",
            hotel_cost.getText(),
            hotel_register.getText()
        );
        
        
    
    } // 이 구조로 작성하는 이유 : 메멘토의 목적 -> 객체의 내부 상태를 저장하고, 이후 필요할 때 이 상태로 복구할 수 있게 하는 것
      //                                          'HotelMemento' 객체는 HotelBS_iinformation 객체의 현재 상태를 저장
    
    
    
    public void deleteInfo(String hotel_name, String hotel_area, String detailed_address, String num_guest, String breakfast, String room_type, String hotel_cost, String hotel_register){ // 삭제된 호텔 정보를 HotelMemento 객체에 저장 
    // 삭제된 호텔 정보를 HotelMemeno 객체에 저장하고, 이를 deleteHotl에 추가
        
    
        HotelMemento memento = new HotelMemento(hotel_name,hotel_area,detailed_address,num_guest,breakfast,room_type,hotel_cost,hotel_register);
        deleteHotel.add(memento);
    }
 
    
    
    
    /*
    public void restoreInfo(HotelMemento memento, JTable hotel_table){
        // 복원된 정보를 테이블에 새로운 행으로 추가하여 화면에 표시
        // deleteHotel 리스트에서 저장된 정보를 추출하여 사용자 인터페이스에 다시 추가
        
        String hotelname = memento.getHotelName();
        String hotelarea = memento.getHotelArea();
        String detailed_address = memento.getDetailedAddress();
        String num_guest = memento.getNumGuest();
        String breakfast = memento.getBreakfast();
        String room_type = memento.getRoomType();
        String hotel_cost = memento.getHotelCost();
        String hotel_register = memento.getHotelRegister();
        // HotelBS 클래스에 있는 테이블에 추가시키고 싶어
        
        DefaultTableModel model = (DefaultTableModel) hotel_table.getModel();
        int rowCount = model.getRowCount() + 1;
        model.addRow(new String[]{
            String.valueOf(rowCount),
            hotelname,
            hotelarea,
            detailed_address,
            num_guest,
            breakfast,
            room_type,
            hotel_cost,
            hotel_register
        });
    
    }
*/
    
    
}

