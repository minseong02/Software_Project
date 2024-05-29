/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HotelBS_package;

/**
 *
 * @author mskim
 */
public class HotelOriginator {
    
    /*
    
    private HotelBS_information hotelInfo;
    private HotelMemento memento;
    
    public void setHotelInformation(HotelBS_information hotelInfo){ // 현재 상태 설정
        this.hotelInfo = hotelInfo;
    }

    
    public HotelMemento createMemento() { // 현재 상태를 기반으로 메멘토 객체 생성하고 반환
        return new HotelMemento(
            hotelInfo.getHotelName(),
            hotelInfo.getHotelArea(),
            hotelInfo.getDetailedAddress(),
            hotelInfo.getNumGuest(),
            hotelInfo.getBreakfastYes(),
            hotelInfo.getSuitRoom(),
            hotelInfo.getHotelCost(),
         hotelInfo.getHotelRegistration()
        
        );
    }
    
    public void restoreMemento(HotelMemento memento){ // 저장된 상태를 받아와서 현재 상태로 복원
        hotelInfo.setHotelName(memento.getHotelName());
        hotelInfo.setHotelArea(memento.getHotelArea());
        hotelInfo.setDetailedAddress(memento.getDetailedAddress());
        hotelInfo.setNumGuests(memento.getNumGuest());
        hotelInfo.setBreakfast(memento.getBreakfast());
        hotelInfo.setRoomType(memento.getRoomType());
        hotelInfo.setHotelCost(memento.getHotelCost());
        hotelInfo.setRegistration(memento.getHotelRegister());
        
    }
*/
    
    private String[] delete_row;
    
 
    public void setDeleteRow(String[] row){
        this. delete_row = row;
    }
    
    public Object[] getDeleteRow(){
        return this.delete_row;
    }
   
    
    public HotelMemento createMemento(){
        return new HotelMemento(delete_row);
    }
    
    public void restoreMemento(HotelMemento memento){
        this.delete_row = memento.getRow();
    }
    
    
}
