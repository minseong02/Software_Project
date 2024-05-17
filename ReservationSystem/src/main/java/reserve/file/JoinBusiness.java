/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reserve.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import reserve.data.BusinessData;

/**
 *
 * @author 민석
 */

public class JoinBusiness implements CreateInterface {
    
ArrayList<String> readInfo = new ArrayList<>();
ArrayList<BusinessData> businessInfo = new ArrayList<>();

private String line;
private String BusinessFile = "C:\\ReservationSystem\\File\\BusinessInfo.txt";

    @Override
    public void fRead() {
        try {
            BufferedReader fileread = new BufferedReader(new FileReader(BusinessFile));
                while ((line = fileread.readLine())!=null ){
                    readInfo.add(line);
                }
                fileread.close();
        }
        
        catch(FileNotFoundException a) { 
            a.printStackTrace(); 
            System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요. ");   
        }
        
        catch(IOException e ) { 
            e.printStackTrace();  
        }
    }
    
    @Override
    public void fWrite(String a) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(BusinessFile,true));
        bw.write(a); 
        bw.flush(); 
        bw.close();
    }

    @Override
    public void sPlite() {
        String line;
        System.out.println(readInfo.size());
        for(int i = 0 ; i <readInfo.size(); i ++){
            line  = readInfo.get(i);
            String[] str = line.split("\\|"); 
            businessInfo.add(new BusinessData(str[0], str[1], str[2], str[3], str[4], str[5]));
        }
    }
    
    public ArrayList<BusinessData> returnBusinessInfo() throws IOException {
        return businessInfo;
    }
}
