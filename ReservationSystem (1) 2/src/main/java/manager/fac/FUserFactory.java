/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager.fac;
/**
 *
 * @author 원채연
 */
public class FUserFactory extends FManageFactory {

    @Override
    PManage createmanage(String file) {
        return new PUserManage(file);
    }
    
}
