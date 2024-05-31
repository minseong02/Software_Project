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
  
    private final String[] row;
    
    public HotelMemento(String[] row){
        this.row = row;
    }
    
    public String[] getRow(){
        return row;
    }
    
}

