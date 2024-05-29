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
public class HotelCaretaker { // 메멘토 객체 관리하고 저장. 복원 기능 담당
    
   
    private List<HotelMemento> mementoList; // 메멘토 객체를 저장하기 위한 list
    
    public HotelCaretaker(){
        mementoList = new ArrayList<>();
        
    }
    
    public void save(HotelMemento memento){ // 메멘토 객체를 인자로 받아서 리스트에 추가.현재 상태의 메멘토 객체를 저장하기 위해 사용
        mementoList.add(memento);
    }
    
    public void restoreLastState(HotelOriginator originator, HotelMemento memento) {
        originator.restoreMemento(memento);
    }
    
    HotelMemento undo(){ // 리스트에서 마지막에 저장된 메멘토 객체를 제거하고 반환하는 메서드 : 이전 상태로 복원
        return mementoList.remove(mementoList.size()-1);
    }
    
    
}
