/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarBS_package;
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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
public class CarBS extends JPanel implements BusinessInterface,ButtonInterface{
    
    private CarBS_information carInfo;
    private JTextField car_company;
    private JComboBox rental_time;
    private JTextField car_cost;
    private JComboBox vehicle_type;
    private JRadioButton gasoline;
    private JRadioButton diesel;
    private JRadioButton electricity;
    private JRadioButton high_pass_yes;
    private JRadioButton high_pass_no;
    private JTextField register;
    
    private JComboBox search_combo;
    private JTextField search_text;
    private JButton searchBT;
    private DefaultTableModel car_model;
    private JTable car_table;
    private JScrollPane car_scrollpane;
    private String file;
    private String data;
    private String search_combo_text;
    private String text;
    private int row;
    private int lineCount;
    private JLabel file_name_label;
    
    
    public CarBS(){
        super(new BorderLayout());
        
        car_company = new JTextField(20);
        String[]rental_time_combo={"::대여 시간::","12시간","24시간","30시간","36시간","42시간","48시간"};
        rental_time = new JComboBox<>(rental_time_combo);
        car_cost = new JTextField(10);
        String[]vehicle_type_combo = {"::차종::","모닝","레이","아반떼","쏘나타","그랜저","쏘렌토","스타리아","아이오닉","코나","레이"};
        vehicle_type = new JComboBox<>(vehicle_type_combo);
        gasoline = new JRadioButton("휘발유");
        diesel = new JRadioButton("경유");
        electricity = new JRadioButton("전기");
        high_pass_yes = new JRadioButton("O");
        high_pass_no = new JRadioButton("X");
        register = new JTextField(2);
        
        carInfo = new CarBS_information( car_company.getText(),
                (String)rental_time.getSelectedItem(),
                car_cost.getText(),
                (String)vehicle_type.getSelectedItem(),
                gasoline.isSelected() ? "휘발유" : "",
                diesel.isSelected() ? "경유" : "",
                electricity.isSelected() ? "전기" : "",
                high_pass_yes.isSelected() ? "O" : "X",
                high_pass_no.isSelected() ? "X" : "O",
                register.getText()
                
        );
        
        file = "car_business_textfile.txt";
        
     
    }
    
    @Override
    public JPanel createPanel(ButtonInterface buttonInterface) {
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        // 이미지를 표시할 JLabel//
        JLabel hotel_image_label = new JLabel();
        ImageIcon hotel_list_Icon = new ImageIcon("car_list.png");
        hotel_image_label.setIcon(hotel_list_Icon);
        hotel_image_label.setBounds(18,1,80,80);
        mainPanel.add(hotel_image_label);
        
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
    
        
        input_panel.add(new JLabel("자동차 회사"));
        car_company.setText(carInfo.getCarCompany());
        input_panel.add(car_company);
        
        input_panel.add(new JLabel("대여 시간"));
        JPanel rental_time_Panel = new JPanel();
        rental_time_Panel.setLayout(new GridLayout(1, 2));
        rental_time.setSelectedItem(carInfo.getRentalTime());
        rental_time_Panel.add(rental_time);
        input_panel.add(rental_time_Panel);
        
        
        input_panel.add(new JLabel("비용"));
        JPanel car_costPanel = new JPanel();
        car_costPanel.setLayout(new GridLayout(1, 2));
        car_cost.setText(carInfo.getCarCost());
        car_costPanel.add(car_cost);
        car_costPanel.add(new JLabel(" 원"));
        input_panel.add(car_costPanel);
        
        input_panel.add(new JLabel("차종"));
        JPanel vehicle_type_Panel = new JPanel();
        vehicle_type_Panel.setLayout(new GridLayout(1, 2));
        vehicle_type.setSelectedItem(carInfo.getVehicleType());
        input_panel.add(vehicle_type);
        
        // 모닝, 레이, 아반떼, 쏘나타, 그랜저 : 휘발유
        // 쏘렌토, 스타리아 : 휘발유
        // 아이오닉, 코나, 레이 : 전기
        
        input_panel.add(new JLabel("연료"));
        JPanel oil_Panel = new JPanel();
        oil_Panel.setLayout(new GridLayout(1,3));
        ButtonGroup oil_Group = new ButtonGroup();
        
        
        oil_Group.add(gasoline);
        oil_Group.add(diesel);
        oil_Group.add(electricity);
        if("휘발유".equals(carInfo.getGasoline())){
            gasoline.setSelected(true);
        }
        else if("경유".equals(carInfo.getDiesel())){
            diesel.setSelected(true);
        }
        else if("전기".equals(carInfo.getElectricity())){
            electricity.setSelected(true);
        }

        
        oil_Panel.add(gasoline);
        oil_Panel.add(diesel);
        oil_Panel.add(electricity);
        
        input_panel.add(oil_Panel);
        
        
        input_panel.add(new JLabel("하이패스")); // 하이패스 유무
        JPanel high_pass_Panel = new JPanel();
        high_pass_Panel.setLayout(new GridLayout(1,2));
        ButtonGroup high_pass_Group = new ButtonGroup();
   
        high_pass_Group.add(high_pass_yes);
        high_pass_Group.add(high_pass_no);
        
        high_pass_Group.add(high_pass_yes);
        high_pass_Group.add(high_pass_no);
        if ("O".equals(carInfo.getYes())){
           high_pass_yes.setSelected(true);
        }
        else if("X".equals(carInfo.getNo())){
            high_pass_no.setSelected(true);
        }
        
        high_pass_Panel.add(high_pass_yes);
        high_pass_Panel.add(high_pass_no);
        
        input_panel.add(high_pass_Panel);
        
        
        input_panel.add(new JLabel("등록 여부"));
        register.setText(carInfo.getCarRegister());
        input_panel.add(register);
        
        // 사진 등록
        input_panel.add(new JLabel("렌터카 대표 사진"));
        JPanel photoPanel = new JPanel();
        photoPanel.setLayout(new GridLayout(3,1));
        JButton btnOpen = new JButton("사진 열기");
        file_name_label= new JLabel();
        JButton btnSave = new JButton("사진 저장");
        photoPanel.add(btnOpen);
        photoPanel.add(file_name_label);
        photoPanel.add(btnSave);
        input_panel.add(photoPanel);
        
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog file_open = new FileDialog((JFrame) SwingUtilities.getWindowAncestor(CarBS.this), "사진 등록", FileDialog.LOAD);
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
                FileDialog file_save = new FileDialog((JFrame) SwingUtilities.getWindowAncestor(CarBS.this), "사진 저장", FileDialog.SAVE);
                file_save.setVisible(true);
                
                String path = file_save.getDirectory(); // 파일 경로
                String file_name = file_save.getFile(); // 파일 이름
                
                File file = new File(path);
                BufferedWriter writer = null;
                
                try{
                    writer = new BufferedWriter(new FileWriter(file+"/"+file_name));
                    writer.write("저장된 파일");
                    writer.flush();
                    
                    JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(CarBS.this), "사진이 저장되었습니다.");
                    
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
        String [] search = {"전체 조회","자동차 회사"};
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
        
        JPanel car_list_panel = new JPanel(new BorderLayout());
        String header[] = {"비즈니스 넘버", "사업자 유저넘버","자동차 회사", "대여시간","비용","차종","연료","하이패스","등록 여부"};
        String contents[][]={};
        
        car_model = new DefaultTableModel(contents, header);
        car_table = new JTable(car_model);
        car_scrollpane = new JScrollPane(car_table);
        car_list_panel.add(car_scrollpane, BorderLayout.CENTER);
        
        return car_list_panel;
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
                search(car_table);
            }
        });
        
        modifyBT.addActionListener(new ActionListener(){ // 수정버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                modify(car_table);
            }
        });
        
        addBT.addActionListener(new ActionListener(){ // 추가 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                add(car_table);
            }
            
        });
        
        
        deleteBT.addActionListener(new ActionListener(){ // 삭제 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                delete(car_table);
            }
            
        });
        
        okBT.addActionListener(e->buttonInterface.onOk());
        
        return south_panel;
        
    }
    

    private void add(JTable car_table){
        
        try{
            
            String car_company_text = car_company.getText();
            String rental_time_text =(String) rental_time.getSelectedItem();
            String car_cost_text = car_cost.getText();
            String vehicle_type_text = (String)vehicle_type.getSelectedItem();
            String oil ="";
            if(gasoline.isSelected()){
                oil="휘발유";
            }
            else if(diesel.isSelected()){
                oil="경유";
            }
            else if(electricity.isSelected()){
                oil="전기";
            }
            
            String high_pass = "";
            if(high_pass_yes.isSelected()){
                high_pass="O";
            }
            else if(high_pass_no.isSelected()){
                high_pass="X";
            }
            
            String car_registration = register.getText();
            
            
            if (car_company_text.isEmpty() || rental_time_text.isEmpty() || car_cost_text.isEmpty() || vehicle_type_text.isEmpty() || oil.isEmpty() || high_pass.isEmpty() || car_registration.isEmpty()){
                JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            lineCount=0;
            car_model = (DefaultTableModel) car_table.getModel();
            String type = "C";
            
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                if (reader.readLine() == null) {
                lineCount = 1; // 파일이 비어 있는 경우 lineCount를 1로 초기화
                }
                else {
                    lineCount = (int) reader.lines().count() + 1; // 파일에 데이터가 있는 경우 현재 라인 수 + 1
                }
            }catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "파일 읽기 오류.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            
            String bs_num = type+lineCount;
            
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
            
            
            
            car_model.addRow(new Object[]{bs_num, bs_user_num ,car_company_text,rental_time_text,car_cost_text,vehicle_type_text,oil,high_pass,car_registration});
            
            
                   
            // 파일에 데이터 추가하기
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(bs_num + "|" + bs_user_num + "|" + car_company_text + "|" + rental_time_text + "|" +car_cost_text + "|" + vehicle_type_text + "|" + oil + "|" + high_pass + "|" + car_registration+"\n");
            writer.close();
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void search(JTable car_table){
        
        // 사용자가 선택한 검색 기준과 입력한 텍스트 가져오기
        search_combo_text = (String)search_combo.getSelectedItem();
        text = search_text.getText();
    
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            car_model = (DefaultTableModel) car_table.getModel();
            car_model.setRowCount(0); // 테이블 초기화
            
        
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (search_combo_text.equals("전체 조회") || 
                    (search_combo_text.equals("자동차 회사") && data.length >= 3 && data[2].equals(text))) {
                    car_model.addRow(data);
                    
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "파일 읽기 오류.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void modify(JTable car_table){
  
        
        row = car_table.getSelectedRow();
        
        String new_oil="";
        if(gasoline.isSelected()){
            new_oil = "휘발유";
        }
        else if(diesel.isSelected()){
            new_oil = "경유";
        }
        else if(electricity.isSelected()){
            new_oil = "전기";
        }
        
        String new_high_pass="";
        if(high_pass_yes.isSelected()){
            new_high_pass = "O";
        }
        else if(high_pass_no.isSelected()){
            new_high_pass = "X";
        }
        
        if(row!=-1){
            
            car_model = (DefaultTableModel) car_table.getModel();
            
            String bs_num = (String) car_model.getValueAt(row, 0);
            String bs_user_num = (String) car_model.getValueAt(row, 1);
            
            
            String[] new_car_input = {
                car_company.getText(),
                (String)rental_time.getSelectedItem(),
                car_cost.getText(),
                (String)vehicle_type.getSelectedItem(),
                new_oil,
                new_high_pass,
                register.getText()
            };
            
            
            for (int i = 0; i < new_car_input.length; i++) {
                if (new_car_input[i] == null || new_car_input[i].isEmpty()) { // 수정된 정보가 없으면
                    new_car_input[i] = (String) car_model.getValueAt(row, i + 2); // 현재 값을 유지
                }
                car_model.setValueAt(new_car_input[i], row, i + 2);
            }
            
            car_model.setValueAt(bs_num, row, 0);
            car_model.setValueAt(bs_user_num, row, 1);
            
            // 파일에 저장
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < car_model.getRowCount(); i++) {
                    StringBuilder row_data = new StringBuilder();
                    for (int j = 0; j < car_model.getColumnCount(); j++) {
                        Object value = car_model.getValueAt(i, j);
                        row_data.append(value != null ? value.toString() : "");
                        if (j < car_model.getColumnCount() - 1) {
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
    
    private void delete(JTable car_table){
        
        row=car_table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "삭제할 행을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        car_model = (DefaultTableModel) car_table.getModel();
        car_model.removeRow(row);
    
      
        for (int i = 0; i < car_model.getRowCount(); i++) {
            car_model.setValueAt("A" + (i + 1), i, 0);
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < car_model.getRowCount(); i++) {
                List<String> row_data = new ArrayList<>();
                for (int j = 0; j < car_model.getColumnCount(); j++) {
                    row_data.add(car_model.getValueAt(i, j).toString());
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
        buttonhandler.onOk();}

    @Override
    public void onBack() {
        ButtonHandler buttonhandler = new ButtonHandler();
        buttonhandler.onBack();
    }

   

   
  
}
