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
import java.util.List;
import javax.swing.JOptionPane;
import manager.ManagerMode;
import reserve.data.ManagerData;
/**
 *
 * @author 민석
 */
public class JoinManager implements CreateInterface {

ArrayList<String> readInfo = new ArrayList<>();
private List<ManagerData> managerInfo;
private static JoinManager instance;

public JoinManager(List<ManagerData> managerInfo){
    this.managerInfo = managerInfo;
}

public JoinManager(){
    managerInfo = new ArrayList<>();
}

public static JoinManager getInstance(){
    if(instance == null){
        instance = new JoinManager();
    }
    return instance;
}

private String line;
private String ManagerFile = "File/ManagerInfo.txt";

    @Override
    public void fRead() {
        try {
            BufferedReader fileread = new BufferedReader(new FileReader(ManagerFile));
                while ((line = fileread.readLine())!=null ){
                    readInfo.add(line);
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

    @Override
    public void fWrite(String a) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(ManagerFile,true));
        bw.write(a); 
        bw.flush(); 
        bw.close();
    }
        
    @Override
    public void sPlite() {String line;
        for(int i = 0 ; i <readInfo.size(); i ++){
            line  = readInfo.get(i);
            String[] str = line.split("\\|"); 
            managerInfo.add(new ManagerData(str[0], str[1]));
        }
    }
    
    public List<ManagerData> returnManagerInfo() throws IOException {
        return managerInfo;
    }

    @Override
    public boolean login(String ID, String PW) {
        if(ID.isEmpty() || PW.isEmpty()){
            return false;
        }
        
        for (ManagerData manager : managerInfo) {
            if(manager.getID().equals(ID) && manager.getPW().equals(PW)){
                return true;
            }
        }
         return false;
    }

    private String panelName;
    
    @Override
    public void LoginSuccess() {
        JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
        ManagerMode click = new ManagerMode(panelName);      
        click.setVisible(true);
    }
}
