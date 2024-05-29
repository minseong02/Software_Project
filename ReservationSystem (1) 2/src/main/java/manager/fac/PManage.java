/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager.fac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 원채연
 */
abstract class PManage {
    private File file;

    public PManage(String file) {
        this.file = new File(file);
    }
    
    public PManage() {    }

    public final void loadInfo(DefaultTableModel model, JTable table) {
        List<String> lines = readFile();
        for (String line : lines) {
            String[] parsedData = parseData(line);
            addDataToModel(model, parsedData);
        }
    }

    protected void deleteInfo(DefaultTableModel model, int selectedRow) {
        List<String> lines = readFile();
        List<String> filteredLines = filterLines(lines, selectedRow);
        writeFile(filteredLines);
        removeRowFromModel(model, selectedRow);
    }

    // 파일에서 데이터를 읽어오는 메서드
    private List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String temp;
            while ((temp = br.readLine()) != null) {
                lines.add(temp);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    //선택한 행을 제외한 데이터를 필터링
    private List<String> filterLines(List<String> lines, int selectedRow) {
        List<String> filteredLines = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (i != selectedRow) {
                filteredLines.add(lines.get(i));
            }
        }
        return filteredLines;
    }
    
    //데이터를 파일에 다시 씀
    private void writeFile(List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            for (String row : lines) {
                bw.write(row);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //테이블에서 행 제거
    private void removeRowFromModel(DefaultTableModel model, int selectedRow) {
    model.removeRow(selectedRow);
}


    // 데이터를 파싱하여 필요한 정보를 추출하는 메서드
    abstract String[] parseData(String line);
    // 파싱된 데이터를 테이블 모델에 추가하는 메서드
    abstract void addDataToModel(DefaultTableModel model, String[] data);

}
