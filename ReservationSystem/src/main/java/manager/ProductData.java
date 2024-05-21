/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * @author PARKSOHYUN
 */
public class ProductData {
    private List<String[]> dataRows = new ArrayList<>();
    
    public void loadDataFromTextFile(String filename, String delimiter) {
        //테이블에 불러올 파일의 데이터 값 읽기
        dataRows.clear();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"CP949")); 
            //한글 인코딩을 위해 CP949 사용함
            String line;
            while((line = br.readLine()) != null) {
                String[] rowData = line.split(Pattern.quote(delimiter));
                dataRows.add(rowData);
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }     
    } 
    //row 데이터 값을 받아옴
    public List<String[]> getDataRows() {
        return dataRows;
    }
    
    public void addRow(String[] rowData) {
        for(String data : rowData) {
            System.out.print(data + " ");
        }
        System.out.println();
    }
    
    public void processData() {
        ProductData manager = new ProductData();           
    }
}
