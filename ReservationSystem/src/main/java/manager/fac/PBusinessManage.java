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
public class PBusinessManage extends PManage {
    private String file = "File/BusinessInfo.txt";

    public PBusinessManage(String file) {
        super(file);
    }

    public PBusinessManage() {    }

    @Override
    String[] parseData(String line) {
                String[] dataRow = line.split("\\|");
                String[] parsedData = new String[5];
                parsedData[0] = dataRow[0];
                parsedData[1] = dataRow[2];
                parsedData[2] = dataRow[3];
                parsedData[3] = dataRow[4];
                parsedData[4] = dataRow[5];
                return parsedData;
    }

    @Override
    void addDataToModel(DefaultTableModel model, String[] data) {
        model.addRow(data);
    }    
    
}
