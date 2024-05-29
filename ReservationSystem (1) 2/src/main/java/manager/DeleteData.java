/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

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

/**
 *
 * @author 원채연
 */
public class DeleteData {
    public void deleteData(int selectedRow,File file) {
    // txt 파일에서 데이터 삭제하기
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int rowIndex = 0;
            while ((line = br.readLine()) != null) {
                if (rowIndex != selectedRow) {
                    lines.add(line);
                }
                rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

// 삭제된 데이터로 파일 다시 쓰기
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"))){
            for (String row : lines) {
                bw.write(row);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
