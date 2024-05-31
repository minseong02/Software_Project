/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buisness_package;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import reserve.login.Login;

/**
 *
 * @author mskim
 */
public class ButtonHandler implements ButtonInterface{

    @Override
    public void onOk() {
        
       JOptionPane.showMessageDialog(null, "완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
        Component component = SwingUtilities.getRoot((Component) null);
        if (component instanceof Window) {
            Window window = (Window) component;
            window.dispose();
        }
    
    }

    @Override
    public void onBack() {
        Login loginWindow = new Login();
        loginWindow.setVisible(true);
        
    }
    
}
