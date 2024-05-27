/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reserve.file;

import javax.swing.JOptionPane;

/**
 *
 * @author 민석
 */
public class LoginContext {
    private CreateInterface strategy;
    
    public void setLoginStrategy(CreateInterface strategy){
        this.strategy = strategy;
    }
    
    public boolean executeStrategy(String ID, String PW){
        return strategy.login(ID,PW);
    }
}
