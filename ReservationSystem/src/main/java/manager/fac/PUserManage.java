/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager.fac;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 원채연
 */
public class PUserManage extends PManage {
    private String file = "File/UserInfo.txt";

    public PUserManage(String file) {
        super(file);
    }
    
    public PUserManage() {    }

    @Override
  protected String[] parseData(String line) {
        String[] dataRow = line.split("\\|");
        String[] parsedData = new String[4];
        parsedData[0] = dataRow[0];
        parsedData[1] = dataRow[2];
        parsedData[2] = dataRow[3];
        parsedData[3] = dataRow[5];
        return parsedData;
  }
  
    @Override
    protected void addDataToModel(DefaultTableModel model, String[] data) {
        model.addRow(data);
    }
}