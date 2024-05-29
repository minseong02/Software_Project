/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reserve.file;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author 민석
 */
public class LoginType implements CreateInterface{
    private final List<CreateInterface> strategies;
    
    public LoginType(List<CreateInterface> strategies){
        this.strategies = strategies;
    }
    
    @Override
    public void fRead() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void fWrite(String input) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void sPlite() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean login(String ID, String PW) {
        for(CreateInterface strategy : strategies){
            if(strategy.login(ID,PW)){
                strategy.LoginSuccess();
                return true;
            }
        }
        return false;
    }

    @Override
    public void LoginSuccess() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
