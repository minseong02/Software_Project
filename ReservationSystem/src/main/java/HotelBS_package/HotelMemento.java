/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBS_package;

/**
 *
 * @author mskim
 */

//삭제된 호텔 정보를 HotelMemento 객체에 저장합니다.
//HotelMemento 객체를 deleteHotel 리스트에 추가합니다.


public class HotelMemento { // HotelBS_information 클래스의 상태를 저장하고 해당 상태를 반환
    
    /*
    private final String hotel_name;
    private final String hotel_area;
    private final String detailed_address;
    private final String num_guest;
    private final String breakfast;
    private final String room_type;
    private final String hotel_cost;
    private final String hotel_register;

    public HotelMemento(String hotel_name, String hotel_area, String detailed_address, String num_guest, String breakfast, String room_type, String hotel_cost, String hotel_register) {
        this.hotel_name = hotel_name;
        this.hotel_area = hotel_area;
        this.detailed_address = detailed_address;
        this.num_guest = num_guest;
        this.breakfast = breakfast;
        this.room_type = room_type;
        this.hotel_cost = hotel_cost;
        this.hotel_register = hotel_register;
    }

    public String getHotelName() {
        return hotel_name;
    }

    public String getHotelArea() {
        return hotel_area;
    }

    public String getDetailedAddress() {
        return detailed_address;
    }

    public String getNumGuest() {
        return num_guest;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public String getRoomType() {
        return room_type;
    }

    public String getHotelCost() {
        return hotel_cost;
    }

    public String getHotelRegister() {
        return hotel_register;
    }

*/
    
    private final String[] row;
    
    public HotelMemento(String[] row){
        this.row = row;
    }
    
    public String[] getRow(){
        return row;
    }
    
}

