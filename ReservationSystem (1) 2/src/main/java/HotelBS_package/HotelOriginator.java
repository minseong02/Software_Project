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
