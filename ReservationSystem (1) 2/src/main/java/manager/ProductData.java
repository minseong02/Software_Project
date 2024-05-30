/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
        try (
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"))) {             
            String line;
            while((line = br.readLine()) != null) {
                String[] rowData = line.split(Pattern.quote(delimiter));
                dataRows.add(rowData);
            }
            
        } catch (IOException e){
            e.printStackTrace();
        }     
    } 
    //row 데이터 값을 받아옴
    public List<String[]> getDataRows() {
        return dataRows;
    }
    
    public void addRow(String[] rowData) {
        dataRows.add(rowData);
        for(String data : rowData) {
            System.out.print(data + " ");
        }
        System.out.println();
    }
    
    
    //수정한 데이터를 저장하는 메소드
    public void saveDataToFile(String filename, String delimiter) {
        try (
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"))) {
            for (String[] row : dataRows) {
                bw.write(String.join(delimiter, row));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setDataRows(List<String[]> dataRows) {
        this.dataRows = dataRows;
    }
    
    public void updateFileData(List<String[]> currentData, String filename, String delimiter) {
    	setDataRows(currentData);
    	saveDataToFile(filename, delimiter);
    }
    
    public ProductIterator iterator() {
        return new ProductDataIterator(dataRows);
    }
}

