/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author PARKSOHYUN
 */
public class ProductDataIterator implements ProductIterator {
    // ConcreteIterator 클래스 구현
    private List<String[]> dataRows;
    private int position;
            
    public ProductDataIterator(List<String[]> dataRows) {
        this.dataRows = dataRows;
        this.position = 0;
    }        
            
    @Override
    public boolean hasNext() {
        return position < dataRows.size();
    }

    @Override
    public String[] next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
        }
        String[] rowData = dataRows.get(position);
        position++;
        return rowData;
    }    
 }

