/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reserve.file;

import User_Main.JavaNaver;
import reserve.data.UserData;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author regin
 */

public class JoinUser implements CreateInterface {
    
ArrayList<String> readInfo = new ArrayList<>();
//ArrayList<UserData> userInfo = new ArrayList<>();
private List<UserData> userInfo;
private static JoinUser instance;

public JoinUser(List<UserData> userInfo){
    this.userInfo = userInfo;
}

private JoinUser(){
    userInfo = new ArrayList<>();
}

public static JoinUser getInstance(){
    if(instance == null){
        instance = new JoinUser();
    }
    return instance;
}

private String line;
private String UserFile = "File/UserInfo.txt";
private int index = 0;

    @Override
    public void fRead() {
        try(BufferedReader fileread = new BufferedReader(new InputStreamReader(new FileInputStream(UserFile), "UTF-8"))) {
            index = 1;
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(UserFile, true), "UTF-8"));
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
            userInfo.add(new UserData(str[0], str[1], str[2], str[3], str[4], str[5]));
        }
    }
    
    public List<UserData> returnUserInfo() throws IOException {
        return userInfo;
    }
    
    
    @Override
    public boolean login(String ID, String PW) {
        if(ID.isEmpty() || PW.isEmpty()){
            return false;
        }
        
        for (UserData user : userInfo) {
            if(user.getID().equals(ID) && user.getPW().equals(PW)){
                return true;
            }
        }
         return false;
    }

    @Override
    public void LoginSuccess() {
        JOptionPane.showMessageDialog(null, "로그인 되었습니다.");


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setLocationRelativeTo(null);
        JavaNaver.openReservationSystem(frame);// 지도로 이동
        frame.setVisible(true);
    }
}
