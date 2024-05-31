/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AirplaneBS_package;

import buisness_package.BusinessInterface;
import buisness_package.ButtonHandler;
import buisness_package.ButtonInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mskim
 */
public class AirplaneBS extends JPanel implements BusinessInterface,ButtonInterface{
    
    
    private Airplane_information airplaneInfo;
    private JComboBox airline;
    private JComboBox departure_area;
    private JComboBox arrival_area;
    private JComboBox seat_type;
    private ButtonGroup route_group;
    private JRadioButton round_trip;
    private JRadioButton one_way;
    private JTextField airplane_cost;
    private JTextField airplane_register;
    private String file;
    private JLabel file_name_label;
     
    private JComboBox search_combo;
    private String search_combo_text;
    private JTextField search_text;
    private String text;
    private int row;
    
    private JButton searchBT;
    private DefaultTableModel airplane_model;
    private JTable airplane_table;
    private JScrollPane airplane_scrollpane;
    private int column;
    private int lineCount;
    
    
    
    public AirplaneBS(){
        
        
        super(new BorderLayout());
        
        
        String [] airline_combo = {"::항공사::","제주 항공","대한 항공","에어 부산"};
        airline = new JComboBox<>(airline_combo);
        String [] departure_area_combo = {"::출발 ::","김포","제주","부산","대구","여수","광주","인천"};
        departure_area = new JComboBox<>(departure_area_combo);
        String [] arrival_area_combo= {"::출발 ::","김포","제주","부산","대구","여수","광주","인천"};
        arrival_area = new JComboBox<>(arrival_area_combo);
        String [] seat_type_combo = {"::좌석::", "이코노미","비즈니스","일등석"};
        seat_type = new JComboBox<>(seat_type_combo);
        round_trip = new JRadioButton("왕복");
        one_way = new JRadioButton("편도");
        airplane_cost = new JTextField(10);
        airplane_register = new JTextField(2);
        
        file = "airplane_business_textfile.txt";
        
        airplaneInfo = new Airplane_information(
                (String) airline.getSelectedItem(),
                (String) departure_area.getSelectedItem(),
                (String) arrival_area.getSelectedItem(),
                (String) seat_type.getSelectedItem(),
                round_trip.isSelected()? "왕복" : "",
                one_way.isSelected()? "편도" : "",
                airplane_cost.getText(),
                airplane_register.getText());
                
        route_group = new ButtonGroup();
        
        
  
        
    }
    
    @Override
    public JPanel createPanel(ButtonInterface buttonInterface) {
        
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(null);
    
    // 이미지를 표시할 라벨
    JLabel airplane_image_label = new JLabel();
    ImageIcon airplane_list_Icon = new ImageIcon("airplane_list.png");
    airplane_image_label.setIcon(airplane_list_Icon);
    airplane_image_label.setBounds(18,1,80,80);
    mainPanel.add(airplane_image_label);

    // 입력 패널 추가
    JPanel inputPanel = createInputPanel();
    inputPanel.setBounds(15, 75, 655, 300);
    mainPanel.add(inputPanel);

    // 검색 패널 추가
    JPanel searchPanel = searchPanel();
    searchPanel.setBounds(15, 400, 655, 50);
    mainPanel.add(searchPanel);

    // 센터 패널 추가
    
    JPanel centerPanel = centerPanel();
    centerPanel.setBounds(15, 450, 655, 230);
    mainPanel.add(centerPanel);

    // 하단 패널 추가
    JPanel southPanel = southPanel(buttonInterface);
    southPanel.setBounds(150, 690, 400, 50);
    mainPanel.add(southPanel);


    return mainPanel;
        
    }
    
    private JPanel createInputPanel(){
        JPanel input_panel = new JPanel();
        input_panel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"입력"));
        input_panel.setLayout(new GridLayout(4,4,10,15));
        
        // 항공사
        input_panel.add(new JLabel("항공사"));
        airline.setSelectedItem(airplaneInfo.getAirline());
        input_panel.add(airline);
        
        // 출발 지역
        input_panel.add(new JLabel("출발 지역"));
        departure_area.setSelectedItem(airplaneInfo.getDepartureArea());
        input_panel.add(departure_area);
        
        // 도착 지역
        input_panel.add(new JLabel("도착 지역"));
        arrival_area.setSelectedItem(airplaneInfo.getArrivalArea());
        input_panel.add(arrival_area);
        
        // 좌석 타입
        input_panel.add(new JLabel("좌석 타입"));
        seat_type.setSelectedItem(airplaneInfo.getSeatType());
        input_panel.add(seat_type);
         
        // 왕복 편도 선택
        input_panel.add(new JLabel("왕복 / 편도"));
        JPanel route_panel = new JPanel();
        route_panel.setLayout(new GridLayout(1, 2));

        route_group.add(round_trip);
        route_group.add(one_way);
        if("왕복".equals(airplaneInfo.getRoundTrip())){
            round_trip.setSelected(true);
        }
        else if("편도".equals(airplaneInfo.getOneWay())){
            one_way.setSelected(true);
        }
        
        route_panel.add(round_trip);
        route_panel.add(one_way);
        
        input_panel.add(route_panel);
        
        
        
        // 비용
        input_panel.add(new JLabel("항공 비용"));
        JPanel costPanel = new JPanel();
        costPanel.setLayout(new GridLayout(1, 2));
        airplane_cost.setText(airplaneInfo.getAirplaneCost());
        costPanel.add(airplane_cost);
        costPanel.add(new JLabel(" 원"));
        input_panel.add(costPanel);
        
        // 등록 여부
        input_panel.add(new JLabel("등록 여부"));
        airplane_register.setText(airplaneInfo.getAirplaneRegister());
        input_panel.add(airplane_register);
        
        // 사진 등록
        input_panel.add(new JLabel("항공 대표 사진"));
        JPanel photoPanel = new JPanel();
        photoPanel.setLayout(new GridLayout(3,1));
        JButton btnOpen = new JButton("사진 열기");
        file_name_label= new JLabel();
        JButton btnSave = new JButton("사진 저장");
        photoPanel.add(btnOpen);
        photoPanel.add(file_name_label);
        photoPanel.add(btnSave);
        input_panel.add(photoPanel);
        
        btnOpen.addActionListener(new ActionListener(){ // 사진등록 버튼을 누르면 다운로드 폴더로 들어가서 사진을 선택후 
            @Override
            public void actionPerformed(ActionEvent e) {
               FileDialog file_open = new FileDialog((JFrame) SwingUtilities.getWindowAncestor(AirplaneBS.this), "사진 열기", FileDialog.LOAD);
               file_open.setVisible(true);
                
                String path = file_open.getDirectory(); // 파일 경로
                String file_name = file_open.getFile(); // 파일 이름
                
                if(path!=null && file_name!=null){
                    file_name_label.setText("경로 : "+path+file_name);
                }
            }
            
        });
        
        btnSave.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog file_save = new FileDialog((JFrame) SwingUtilities.getWindowAncestor(AirplaneBS.this), "사진 저장", FileDialog.SAVE);
                file_save.setVisible(true);
                
                String path = file_save.getDirectory(); // 파일 경로
                String file_name = file_save.getFile(); // 파일 이름
                
                File file = new File(path);
                BufferedWriter writer = null;
                
                try{
                    writer = new BufferedWriter(new FileWriter(file+"/"+file_name));
                    writer.write("저장된 파일");
                    writer.flush();
                    
                    JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(AirplaneBS.this), "사진이 저장되었습니다.");
                    
                    writer.close();
                    
                } catch(IOException e1){
                    e1.printStackTrace();
                }
            }
            
        });
        

        
        return input_panel;
    }
    
    private JPanel searchPanel(){
        
        JPanel search_panel = new JPanel();
        
        // 콤보 박스
        String [] search = {"전체 조회","항공사"};
        search_combo = new JComboBox(search);
        search_panel.add(search_combo);
        
        // 검색 텍스트 필드
        search_text = new JTextField(30);
        search_panel.add(search_text);
        
        // 조회 버튼
        searchBT = new JButton("조회");
        search_panel.add(searchBT);
        
        return search_panel;
   
    }
    
    private JPanel centerPanel(){

        JPanel airplane_center_panel = new JPanel(new BorderLayout());
 
        String header[] = {"비즈니스 넘버","사업자 유저넘버", "항공사","출발지역","도착지역","좌석타입","왕복/편도","항공 비용","등록 여부"};
        String contents[][]={};
        
        airplane_model = new DefaultTableModel(contents, header);
        airplane_table = new JTable(airplane_model);
        airplane_scrollpane = new JScrollPane(airplane_table);
        airplane_center_panel.add(airplane_scrollpane, BorderLayout.CENTER);

        return airplane_center_panel;
        
    }
    
    private JPanel southPanel(ButtonInterface buttonInterface){
        
        
        
        JPanel south_panel = new JPanel();
        south_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
       
        
        
        JButton backBT = new JButton("이전"); 
        JButton modifyBT = new JButton("수정");
        JButton addBT = new JButton("추가");
        JButton deleteBT = new JButton("삭제");
        JButton okBT = new JButton("확인");
        
        south_panel.add(backBT);
        south_panel.add(modifyBT);
        south_panel.add(addBT);
        south_panel.add(deleteBT);
        south_panel.add(okBT);
        
        backBT.addActionListener(e->buttonInterface.onBack());
        
        searchBT.addActionListener(new ActionListener(){ // 조회 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                search(airplane_table);
            }
        });
        
        
        modifyBT.addActionListener(new ActionListener(){ // 수정버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                modify(airplane_table);
            }
        });
        
        
        addBT.addActionListener(new ActionListener(){ // 추가 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                add(airplane_table);
            }
            
        });
        
        
        
        
        deleteBT.addActionListener(new ActionListener(){ // 삭제 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                delete(airplane_table);
            }
            
        });
        
        okBT.addActionListener(e->buttonInterface.onOk());
        
        return south_panel;
    }
    
    
    
    private void add(JTable airplane_table)
    {
        try{
            String airline_text = (String) airline.getSelectedItem();
            String departure_area_text = (String)departure_area.getSelectedItem();
            String arrival_area_text = (String)arrival_area.getSelectedItem();
            String seat_type_text = (String)seat_type.getSelectedItem();
            String route = "";
            if(round_trip.isSelected()){
                route="왕복";
            }
            else if(one_way.isSelected()){
                route="편도";
            }
            String airplane_cost_text = airplane_cost.getText();
            String airplane_register_text = airplane_register.getText();
            
            if (airline_text.isEmpty() || departure_area_text.isEmpty() || arrival_area_text.isEmpty() || seat_type_text.isEmpty() || route.isEmpty() || airplane_cost_text.isEmpty() || airplane_register_text.isEmpty()){
                JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            airplane_model = (DefaultTableModel) airplane_table.getModel();
            String type = "A";
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                if (reader.readLine() == null) {
                    lineCount = 1; // 파일이 비어 있는 경우 lineCount를 1로 초기화
                } else {
                    lineCount = (int) reader.lines().count() + 1; // 파일에 데이터가 있는 경우 현재 라인 수 + 1
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "파일 읽기 오류.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String bs_num = type + lineCount;
            
            String bs_user_num = "";
            try (BufferedReader reader = new BufferedReader(new FileReader("File/BSLogin.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    bs_user_num = line;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "파일 읽기 오류.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            
            
            airplane_model.addRow(new Object[]{
                bs_num,
                bs_user_num,
                airline_text,
                departure_area_text,
                arrival_area_text,
                seat_type_text,
                route,
                airplane_cost_text,
                airplane_register_text
            });
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(bs_num + "|" + bs_user_num + "|"+ airline_text +"|" + departure_area_text +"|" + arrival_area_text +"|" + seat_type_text  +"|" + route  +"|" + airplane_cost_text +"|" + airplane_register_text + "\n");
            writer.close();
            
            
        }
         catch (IOException ex) {
            ex.printStackTrace();
            
        }
    }
    
    private void search(JTable airplane_table){
        // 사용자가 선택한 검색 기준과 입력한 텍스트 가져오기
        search_combo_text = (String)search_combo.getSelectedItem();
        text = search_text.getText();
    
        // 파일에서 항공사 정보 읽어오기
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            airplane_model = (DefaultTableModel) airplane_table.getModel();
            airplane_model.setRowCount(0); // 테이블 초기화
            
        
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (search_combo_text.equals("전체 조회") || 
                    (search_combo_text.equals("항공사") || data.length >= 3 && data[2].equals(text))) {
                    airplane_model.addRow(data);
                    
                }
            }
           
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "파일 읽기 오류.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void modify(JTable airplane_Table){
        
        row = airplane_table.getSelectedRow();
        
        String new_route = "";
        if(round_trip.isSelected()){
            new_route = "왕복";
        }
        else if(one_way.isSelected()){
            new_route = "편도";
        }
        
        if(row!=-1){
            
            airplane_model = (DefaultTableModel) airplane_table.getModel();
            
            String bs_num = (String) airplane_model.getValueAt(row, 0);
            String bs_user_num = (String) airplane_model.getValueAt(row, 1);
            
            String[] new_airplane_input = {
                (String)airline.getSelectedItem(),
                (String)departure_area.getSelectedItem(),
                (String)arrival_area.getSelectedItem(),
                (String)seat_type.getSelectedItem(),
                new_route,
                airplane_cost.getText(),
                airplane_register.getText()
            };
            
            
            for (int i = 0; i < new_airplane_input.length; i++) {
                if (new_airplane_input[i] == null || new_airplane_input[i].isEmpty()) { // 수정된 정보가 없으면
                    new_airplane_input[i] = (String) airplane_model.getValueAt(row, i + 2); // 현재 값을 유지
                }
                airplane_model.setValueAt(new_airplane_input[i], row, i + 2);
            }
            
            airplane_model.setValueAt(bs_num, row, 0);
            airplane_model.setValueAt(bs_user_num, row, 1);
            
            // 파일에 저장
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < airplane_model.getRowCount(); i++) {
                    StringBuilder row_data = new StringBuilder();
                    for (int j = 0; j < airplane_model.getColumnCount(); j++) {
                        Object value = airplane_model.getValueAt(i, j);
                        row_data.append(value != null ? value.toString() : "");
                        if (j < airplane_model.getColumnCount() - 1) {
                            row_data.append("|");
                        }
                    }
                    writer.write(row_data.toString() + "\n");

                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void delete(JTable airplane_table) {
        
        row = airplane_table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "삭제할 행을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        airplane_model = (DefaultTableModel) airplane_table.getModel();
        airplane_model.removeRow(row);
    
      
        for (int i = 0; i < airplane_model.getRowCount(); i++) {
            airplane_model.setValueAt("A" + (i + 1), i, 0);
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < airplane_model.getRowCount(); i++) {
                List<String> row_data = new ArrayList<>();
                for (int j = 0; j < airplane_model.getColumnCount(); j++) {
                    row_data.add(airplane_model.getValueAt(i, j).toString());
                }
                writer.write(String.join("|", row_data));
                writer.newLine();
            }
        }
        
        catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void onOk() {
        ButtonHandler buttonhandler = new ButtonHandler();
        buttonhandler.onOk();
    }

    @Override
    public void onBack() {
        ButtonHandler buttonhandler = new ButtonHandler();
        buttonhandler.onBack();
    }
    
  

    
    
}

