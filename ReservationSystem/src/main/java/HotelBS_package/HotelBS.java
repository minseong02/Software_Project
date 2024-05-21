/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package HotelBS_package;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*; 
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author mskim
 */
public class HotelBS extends JPanel{
    
    private HotelBS_information hotelInfo; // HotelBS_information 클래스의 인스턴스 생성
    private String file;
    private DefaultTableModel hotel_model;
    private JScrollPane hotel_scrollpane;
    private JTable hotel_table;
    private String data;
    private JComboBox search_combo;
    private JTextField search_text;
    private String search_combo_text;
    private String text;
    private JComboBox hotel_area_combo;
    private JComboBox num_guest_combo;
    private int row;
    
    public HotelBS(){
        super(new BorderLayout());
        
        file = "hotel_buisness_textfile.txt";
        search_combo = new JComboBox();
        search_text = new JTextField();
        hotel_area_combo = new JComboBox();
        num_guest_combo = new JComboBox();
        
        String[] hotel_area = {"::시/도::","서울특별시","부산광역시","대구광역시","인천광역시","광주광역시","대전광역시","울산광역시","세종특별자치시","경기도","강원도","충청북도","충청남도","전라남도","전라북도","경상남도","경상북도","제주특별자치도"};
        String[] num_guest = {"::인원 수::","2명","3명","4명"};
        hotelInfo = new HotelBS_information(hotel_area, num_guest); // 인스턴스 초기화
        
        
        
        
        hotel_list();
    }
    
    private void hotel_list(){
        
        setLayout(null);
        
        // 이미지를 표시할 JLabel
        JLabel hotel_image_label = new JLabel();
        ImageIcon hotel_list_Icon = new ImageIcon("hotel_list.png");
        hotel_image_label.setIcon(hotel_list_Icon);
        hotel_image_label.setBounds(18,1,80,80);
        add(hotel_image_label);
        
    
        // 상단 패널 //
        JPanel input_panel = new JPanel();
        input_panel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"입력"));
        input_panel.setLayout(new GridLayout(5,4,10,15));


        input_panel.add(new JLabel("호텔 이름"));
        JTextField hotel_name = hotelInfo.getHotelName();
        input_panel.add(hotel_name);
       
        // 지역 콤보 박스
        input_panel.add(new JLabel("지역"));
        hotel_area_combo =  hotelInfo.getHotelArea();
        input_panel.add(hotel_area_combo);
        
        // 주소 입력
        input_panel.add(new JLabel("상세 주소"));
        JTextField detailed_address = hotelInfo.getDetailedAddress();
        input_panel.add(detailed_address);
       
        // 투숙객 수 콤보 박스
        input_panel.add(new JLabel("투숙객 수"));
        num_guest_combo = hotelInfo.getNumGuest();
        input_panel.add(num_guest_combo);
       
        
        input_panel.add(new JLabel("조식여부"));
        JPanel breakfast_panel = new JPanel();
        ButtonGroup breakfastTypeGroup = new ButtonGroup();
        breakfast_panel.setLayout(new GridLayout(1, 2));
        JRadioButton breakfast_yes = hotelInfo.getBreakfastYes();
        JRadioButton breakfast_no = hotelInfo.getBreakfastNo();
        
        breakfastTypeGroup.add(breakfast_yes);
        breakfastTypeGroup.add(breakfast_no);
        
        breakfast_panel.add(breakfast_yes);
        breakfast_panel.add(breakfast_no);
        
        input_panel.add(breakfast_panel);
        

        input_panel.add(new JLabel("룸 타입"));
        JPanel roomTypePanel = new JPanel();
        ButtonGroup roomTypeGroup = new ButtonGroup();
        roomTypePanel.setLayout(new GridLayout(3, 1));
        JRadioButton suit_room = hotelInfo.getSuitRoom();
        JRadioButton deluxe_room = hotelInfo.getDeluxeRoom();
        JRadioButton standard_room = hotelInfo.getStandardRoom();
    

        // 각 라디오 버튼을 그룹에 추가
        roomTypeGroup.add(suit_room);
        roomTypeGroup.add(deluxe_room);
        roomTypeGroup.add(standard_room);

        roomTypePanel.add(suit_room);
        roomTypePanel.add(deluxe_room);
        roomTypePanel.add(standard_room);
        input_panel.add(roomTypePanel);
        
       
        input_panel.add(new JLabel("숙박 비용"));
        JPanel hotel_costPanel = new JPanel();
        hotel_costPanel.setLayout(new GridLayout(1, 2));
        JTextField hotel_cost_text = hotelInfo.getHotelCost();
        hotel_costPanel.add(hotel_cost_text);
        hotel_costPanel.add(new JLabel(" 원"));
        input_panel.add(hotel_costPanel);
        
        
       
        input_panel.add(new JLabel("등록 여부"));
        JTextField register = hotelInfo.getHotelRegistration();
        input_panel.add(register);
        
        // 폴더에서 사진 선택
        input_panel.add(new JLabel("호텔 대표 사진"));
        JButton btnOpen = new JButton("사진 등록");
        input_panel.add(btnOpen);
        
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog file_open = new FileDialog((JFrame) SwingUtilities.getWindowAncestor(HotelBS.this), "사진 등록", FileDialog.LOAD);
                file_open.setVisible(true);
                
                String path = file_open.getDirectory();
            }
        });
        
       
        input_panel.setBounds(15,75,655,300);
        add(input_panel);
       
       
        // 검색 패널 //

        JPanel search_panel = new JPanel();
        //search_panel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"검색"));
      
        // 콤보 박스
        String [] search = {"전체 조회","비즈니스 넘버", "호텔 이름"};
        search_combo = new JComboBox(search);
        search_panel.add(search_combo);
        
        // 검색 텍스트 필드
        search_text = new JTextField(30);
        search_panel.add(search_text);
        
        // 조회 버튼
        JButton searchBT = new JButton("조회");
        search_panel.add(searchBT);
        
        
        
        
        search_panel.setBounds(15,400,655,50);
        add(search_panel);


       
        // 센터 패널 //
        JPanel hotel_list_panel = new JPanel(new BorderLayout());
        //center_panel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"목록"));
        String header[] = {"비즈니스 넘버", "호텔 이름", "지역", "상세 주소","투숙객 수", "조식 여부", "룸 타입", "숙박 비용","등록 여부"};
        String contents[][]={};
        
        hotel_model = new DefaultTableModel(contents, header);
        hotel_table = new JTable(hotel_model);
        hotel_scrollpane = new JScrollPane(hotel_table);
        hotel_list_panel.add(hotel_scrollpane, BorderLayout.CENTER);

        hotel_list_panel.setBounds(15,450,655,230);
        add(hotel_list_panel);
       
       
        // 하단 패널
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
        
        addBT.addActionListener(new ActionListener(){ // 추가 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                add(hotel_table);
            }
            
        });
        
        searchBT.addActionListener(new ActionListener(){ // 조회 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                search(hotel_table);
            }
        });
        
        
        modifyBT.addActionListener(new ActionListener(){ // 수정버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                modify(hotel_table);
            }
        });
        
        deleteBT.addActionListener(new ActionListener(){ // 삭제 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                delete(hotel_table);
            }
            
        });
        
        okBT.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ok(hotel_table);
            }
            
        });
            
        south_panel.add(deleteBT);
        south_panel.add(okBT);
       
        south_panel.setBounds(150,690,400,50);
        add(south_panel);
    }
    
    private void add(JTable hotel_table){
        try {
            
            // 입력한 데이터 가져오기
            Path filepath = Paths.get(file);
            long lineCount = Files.lines(filepath).count()+1;
            
            String hotelname = hotelInfo.getHotelName().getText();
            String hotelarea = (String) hotelInfo.getHotelArea().getSelectedItem();
            String hotel_detaild_address = hotelInfo.getDetailedAddress().getText();
            String hotel_guestnum = (String) hotelInfo.getNumGuest().getSelectedItem();
            String breakfast = hotelInfo.getBreakfastYes().isSelected() ? "O" : "X";
            String roomType = "";
            if (hotelInfo.getSuitRoom().isSelected()) {
                roomType = "스위트룸";
            } else if (hotelInfo.getDeluxeRoom().isSelected()) {
                roomType = "디럭스룸";
            } else if (hotelInfo.getStandardRoom().isSelected()) {
                roomType = "스탠다드룸";
            }
            String hotelcost = hotelInfo.getHotelCost().getText();
            String hotel_registration = hotelInfo.getHotelRegistration().getText();

            
            if (hotelname.isEmpty() || hotelarea.isEmpty() || hotel_detaild_address.isEmpty() || hotel_guestnum.isEmpty() || breakfast.isEmpty() ||roomType.isEmpty() || hotelcost.isEmpty() || hotel_registration.isEmpty()) {
                JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            } 
            

            data = lineCount + "|" + hotelname + "|" + hotelarea + "|" + hotel_detaild_address + "|" + hotel_guestnum + "|" + breakfast + "|" + roomType + "|" + hotelcost + "|" + hotel_registration;

            // 파일에 데이터 추가하기
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(data+"\n");
            writer.close();
            
            
            DefaultTableModel model = (DefaultTableModel) hotel_table.getModel();
            model.addRow(new String[]{String.valueOf(lineCount), hotelname, hotelarea, hotel_detaild_address, hotel_guestnum, breakfast, roomType, hotelcost, hotel_registration});


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    private void search(JTable hotel_table){
        
        search_combo_text = (String)search_combo.getSelectedItem();
        text = search_text.getText();
                
        try{
                    
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            DefaultTableModel model = (DefaultTableModel) hotel_table.getModel();
            model.setRowCount(0); // 테이블 초기화
                    
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (search_combo_text == null) { // 검색 콤보 상자가 선택되지 않았을 경우 전체 조회로 처리
                    model.addRow(data);
                }
                else if(search_combo_text.equals("전체 조회")){
                    model.addRow(data);
                }
                else if (search_combo_text.equals("비즈니스 넘버") && data[0].equals(text)) {
                    model.addRow(data); 
                }
                else if (search_combo_text.equals("호텔 이름") && data[1].equals(text)) {
                    model.addRow(data);
                }
            }
            
            reader.close();
                
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void modify(JTable hotel_table){
        row = hotel_table.getSelectedRow();
                
        if(row!=-1){
         
            String new_hotel_name = hotelInfo.getHotelName().getText();
            String new_hotelarea = hotel_area_combo.getSelectedItem() != null ? (String) hotel_area_combo.getSelectedItem() : "";
            String new_hotel_detaild_address = hotelInfo.getDetailedAddress().getText();
            String new_hotel_guestnum = num_guest_combo.getSelectedItem() != null ? (String) num_guest_combo.getSelectedItem() : "";
            String new_breakfast = "";
            if (hotelInfo.getBreakfastYes().isSelected()) {
                new_breakfast = "O";
            } else if (hotelInfo.getBreakfastNo().isSelected()) {
                new_breakfast = "X";
            }
            String new_roomType = "";
            if (hotelInfo.getSuitRoom().isSelected()) {
                new_roomType = "스위트룸";
            } else if (hotelInfo.getDeluxeRoom().isSelected()) {
                new_roomType = "디럭스룸";
            } else if (hotelInfo.getStandardRoom().isSelected()) {
                new_roomType = "스탠다드룸";
            }
            String new_hotelcost = hotelInfo.getHotelCost().getText();
            String new_hotel_registration = hotelInfo.getHotelRegistration().getText();
                    
            // 기존 값 가져오기
            String old_hotel_num = (String) hotel_model.getValueAt(row, 0);
            String old_hotel_name = (String) hotel_model.getValueAt(row, 1);
            String old_hotelarea = (String) hotel_model.getValueAt(row, 2);
            String old_hotel_detaild_address = (String) hotel_model.getValueAt(row, 3);
            String old_hotel_guestnum = (String) hotel_model.getValueAt(row, 4);
            String old_breakfast = (String) hotel_model.getValueAt(row, 5);
            String old_roomType = (String) hotel_model.getValueAt(row, 6);
            String old_hotelcost = (String) hotel_model.getValueAt(row, 7);
            String old_hotel_registration = (String) hotel_model.getValueAt(row, 8);
                    
            
            if (new_hotel_name.isEmpty()) {
                new_hotel_name = old_hotel_name;
            }
            else if (new_hotelarea.isEmpty()) {
                new_hotelarea = old_hotelarea;
            }
            else if (new_hotel_detaild_address.isEmpty()) {
                new_hotel_detaild_address = old_hotel_detaild_address;
            }
            else if (new_hotel_guestnum.isEmpty()) {
                new_hotel_guestnum = old_hotel_guestnum;
            }
            else if (new_breakfast.isEmpty()) {
                new_breakfast = old_breakfast;
            }
            else if (new_roomType.isEmpty()) {
                new_roomType = old_roomType;
            }
            else if (new_hotelcost.isEmpty()) {
                new_hotelcost = old_hotelcost;
            }
            else if (new_hotel_registration.isEmpty()) {
                new_hotel_registration = old_hotel_registration;
            }
            
            // 기존 값 복구
            //hotel_model.setValueAt(new_hotel_num, row, 0);
            hotel_model.setValueAt(new_hotel_name, row, 1);
            hotel_model.setValueAt(new_hotelarea, row, 2);
            hotel_model.setValueAt(new_hotel_detaild_address, row, 3);
            hotel_model.setValueAt(new_hotel_guestnum, row, 4);
            hotel_model.setValueAt(new_breakfast, row, 5);
            hotel_model.setValueAt(new_roomType, row, 6);
            hotel_model.setValueAt(new_hotelcost, row, 7);
            hotel_model.setValueAt(new_hotel_registration, row, 8);
        }
    }
    
    
    private void delete(JTable hotel_table){
        row = hotel_table.getSelectedRow(); // 삭제된 행의 인덱스
        hotel_model.removeRow(row); // 열 삭제
        
        for (int i = row; i < hotel_model.getRowCount(); i++) { // 현재 테이블 모델의 행 수 반환
            hotel_model.setValueAt(i + 1, i, 0); // 비즈니스 넘버 재할당 : i번째 행의 비즈니스 넘버를 i+1로 수정하여 번호를 한칸씩 앞당기기!!
        }
    }
    
    private void ok(JTable hotel_table){
        
        JOptionPane.showMessageDialog(this, "완료되었습니다.", "알림", JOptionPane.ERROR_MESSAGE);
        Window window = SwingUtilities.getWindowAncestor(this);
        window.dispose();
    }
         
}
    

