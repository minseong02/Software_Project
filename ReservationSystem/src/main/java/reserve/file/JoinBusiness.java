/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reserve.file;

import buisness_package.Business_Choice;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import reserve.data.BusinessData;

/**
 *
 * @author 민석
 */

public class JoinBusiness implements CreateInterface {
    
ArrayList<String> readInfo = new ArrayList<>();
private List<BusinessData> businessInfo;
private static JoinBusiness instance;

public JoinBusiness(List<BusinessData> businessInfo){
    this.businessInfo = businessInfo;
}

private JoinBusiness(){
    businessInfo = new ArrayList<>();
}

public static JoinBusiness getInstance(){
    if(instance == null){
        instance = new JoinBusiness();
    }
    return instance;
}

private String line;
private String BusinessFile = "File/BusinessInfo.txt";
private int index = 0;

    @Override
    public void fRead() {
        index = 1;
        try(BufferedReader fileread = new BufferedReader(new InputStreamReader(new FileInputStream(BusinessFile), "UTF-8"))) {
            while ((line = fileread.readLine())!=null ){
                readInfo.add(line);
                index++;
            }
        }

        catch(FileNotFoundException a) { 
            a.printStackTrace(); 
            System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요. ");   
        }
        
        catch(IOException e ) { 
            e.printStackTrace();  
        }
    }
    
    public int getIndex(){
        return index;
    }
    
    @Override
    public void fWrite(String a) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(BusinessFile, true), "UTF-8"));
        bw.write(a); 
        bw.flush(); 
        bw.close();
    }

    @Override
    public void sPlite() {
        String line;
        for(int i = 0 ; i <readInfo.size(); i ++){
            line  = readInfo.get(i);
            String[] str = line.split("\\|"); 
            businessInfo.add(new BusinessData(str[0], str[1], str[2], str[3], str[4], str[5]));
        }
    }
    
    public List<BusinessData> returnBusinessInfo() throws IOException {
        return businessInfo;
    }

    @Override
    public boolean login(String ID, String PW) {
        if(ID.isEmpty() || PW.isEmpty()){
            return false;
        }
        
        for (BusinessData business : businessInfo) {
            if(business.getID().equals(ID) && business.getPW().equals(PW)){
                return true;
            }
        }
         return false;
    }

    @Override
    public void LoginSuccess() {
        JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
        Business_Choice click = new Business_Choice();
        click.setVisible(true);
    }
}
