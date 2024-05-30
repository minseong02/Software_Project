/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;

/**
 *
 * @author PARKSOHYUN
 */
public class RegisterCommand implements Command {
    private ProductTablePanel productTablePanel;
    private String panelName;
    private int selectedRow;
    private String originalStatus;
    private String newStatus;
    private String filename;
    private String delimiter;
    private ProductData productData;
      
    
    public RegisterCommand(ProductTablePanel productTablePanel, String filename, String delimiter) {
        this.productTablePanel = productTablePanel;
        this.panelName = productTablePanel.getCurrentPanelName();
        this.selectedRow = productTablePanel.getSelectedTable().getSelectedRow();
        this.originalStatus = (String) productTablePanel.getSelectedTable().getModel().getValueAt(
                selectedRow, productTablePanel.getRegistrationColumnIndex(panelName));
        this.newStatus = "O"; // 등록 여부를 "O"로 변경한다고 가정
        this.filename = filename;
        this.delimiter = delimiter;
        this.productData = productTablePanel.getProductData();
        
    }

    @Override
    public void execute() {        
        JTable targetTable = productTablePanel.getTable(panelName);
        int registrationColumnIndex = productTablePanel.getRegistrationColumnIndex(panelName);
        
        //선택한 테이블의 등록 여부를 변경함
        if(targetTable != null && registrationColumnIndex != -1) {
                String currentStatus = (String) targetTable.getModel().getValueAt(
                    selectedRow, registrationColumnIndex);                                         
                if("X".equals(currentStatus)) {
                    targetTable.getModel().setValueAt("O", selectedRow, registrationColumnIndex);
                   
                 // 파일 데이터 갱신
                    List<String[]> currentData = productTablePanel.getDataForPanel(panelName);
                    productData.updateFileData(currentData, filename, delimiter);
                }
            
        } 
    }

    @Override
    public void undo() {
        JTable targetTable = productTablePanel.getTable(panelName);
        int registrationColumnIndex = productTablePanel.getRegistrationColumnIndex(panelName);

        if (targetTable != null && registrationColumnIndex != -1 && selectedRow != -1) {
            targetTable.getModel().setValueAt(originalStatus, selectedRow, registrationColumnIndex);
            
            // 파일 데이터 갱신
            List<String[]> currentData = productTablePanel.getDataForPanel(panelName);
            productData.updateFileData(currentData, filename, delimiter);
        } 
    }
}
