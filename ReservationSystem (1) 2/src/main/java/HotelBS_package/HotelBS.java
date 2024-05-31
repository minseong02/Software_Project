/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package HotelBS_package;
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
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author mskim
 */
public class HotelBS extends JPanel implements BusinessInterface,ButtonInterface{
    
    
    private HotelBS_information hotelInfo; // HotelBS_information 클래스의 인스턴스 생성
    private JTextField hotel_name ;
    private JComboBox hotel_area;
    private JTextField detailed_address;
    private JComboBox num_guest;
    private JRadioButton breakfast_yes;
    private JRadioButton breakfast_no;
    private ButtonGroup breakfast_group;
    private JRadioButton suit_room;
    private JRadioButton deluxe_room;
    private JRadioButton standard_room;
    private ButtonGroup room_group;
    private JTextField hotel_cost ;
    private JTextField hotel_register;
   
    private String file;
    private JComboBox search_combo;
    private String search_combo_text;
    private JTextField search_text;
    private String text;
    private JLabel file_name_label;
    private JButton searchBT;
    
    
    
    private List<HotelMemento> deleteHotel;
    private DefaultTableModel hotel_model;
    private JScrollPane hotel_scrollpane;
    private JTable hotel_table;
    
    
    private int row;
    private int lineCount;
    private int column;
    
    
    private HotelCaretaker caretaker;
   

    
    public HotelBS(){
        super(new BorderLayout());
        hotel_name = new JTextField(20);
        String[] hotel_area_combo = {"::시/도::","서울특별시","부산광역시","대구광역시","인천광역시","광주광역시","대전광역시","울산광역시","세종특별자치시","경기도","강원도","충청북도","충청남도","전라남도","전라북도","경상남도","경상북도","제주특별자치도"};
        hotel_area = new JComboBox<>(hotel_area_combo);
        detailed_address = new JTextField(50);
        String[] num_guest_combo = {"::인원 수::","2명","3명","4명"};
        num_guest = new JComboBox<>(num_guest_combo);
        breakfast_yes = new JRadioButton("O");
        breakfast_no = new JRadioButton("X");
        breakfast_group = new ButtonGroup();
        suit_room = new JRadioButton("스위트룸");
        deluxe_room = new JRadioButton("디럭스룸");
        standard_room = new JRadioButton("스탠다드룸");
        room_group = new ButtonGroup();
        hotel_cost = new JTextField(15);
        hotel_register = new JTextField(2);
        
        
        file = "hotel_business_textfile.txt";
       

        
       hotelInfo = new HotelBS_information(
        hotel_name.getText(),
                (String) hotel_area.getSelectedItem(),
    detailed_address.getText(),
                (String) num_guest.getSelectedItem(),
                breakfast_yes.isSelected() ? "O" : "X",
                breakfast_no.isSelected() ? "X" : "O",
                suit_room.isSelected() ? "스위트룸" : "",
                deluxe_room.isSelected() ? "디럭스룸" : "",
                standard_room.isSelected() ? "스탠다드룸" : "",
        hotel_cost.getText(),
     hotel_register.getText());
       
        search_combo = new JComboBox();
        
        search_text = new JTextField();
       

        
 
       caretaker = new HotelCaretaker();
        
        
  
    }
    
    @Override
    public JPanel createPanel(ButtonInterface buttonInterface) {
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
         // 이미지를 표시할 JLabel
        JLabel hotel_image_label = new JLabel();
        ImageIcon hotel_list_Icon = new ImageIcon("hotel_list.png");
        hotel_image_label.setIcon(hotel_list_Icon);
        hotel_image_label.setBounds(18,1,80,80);
        mainPanel.add(hotel_image_label);
        
        // 입력 패널 추가
        JPanel inputPanel = InputPanel();
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
    
    private JPanel InputPanel(){
        
        // 상단 패널 //
        JPanel input_panel = new JPanel();
        input_panel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"입력"));
        input_panel.setLayout(new GridLayout(5,4,10,15));

        // 호텔 이름 입력
        input_panel.add(new JLabel("호텔 이름"));
        hotel_name.setText(hotelInfo.getHotelName());
        input_panel.add(hotel_name);
       
        // 지역 콤보 박스
        input_panel.add(new JLabel("지역"));
        hotel_area.setSelectedItem(hotelInfo.getHotelArea());
        input_panel.add(hotel_area);
        
        // 주소 입력
        input_panel.add(new JLabel("상세 주소"));
        detailed_address.setText(hotelInfo.getDetailedAddress());
        input_panel.add(detailed_address);
       
        // 투숙객 수 콤보 박스
        input_panel.add(new JLabel("투숙객 수"));
        num_guest.setSelectedItem(hotelInfo.getNumGuest());
        input_panel.add(num_guest);
       
        
        input_panel.add(new JLabel("조식여부"));
        JPanel breakfast_panel = new JPanel();
        breakfast_panel.setLayout(new GridLayout(1, 2));
        
        breakfast_group.add(breakfast_yes);
        breakfast_group.add(breakfast_no);
        if ("O".equals(hotelInfo.getBreakfastYes())){
            breakfast_yes.setSelected(true);
        }
        else if("X".equals(hotelInfo.getBreakfastNo())){
            breakfast_no.setSelected(true);
        }
        
        breakfast_panel.add(breakfast_yes);
        breakfast_panel.add(breakfast_no);
        
        input_panel.add(breakfast_panel);
        

        input_panel.add(new JLabel("룸 타입"));
        JPanel roomTypePanel = new JPanel();
        roomTypePanel.setLayout(new GridLayout(3, 1));
    
        room_group.add(suit_room);
        room_group.add(deluxe_room);
        room_group.add(standard_room);
        if ("스위트룸".equals(hotelInfo.getSuitRoom())){
            suit_room.setSelected(true);
        }
        else if("디럭스룸".equals(hotelInfo.getDeluxeRoom())){
            deluxe_room.setSelected(true);
        }
        else if("스탠다드룸".equals(hotelInfo.getStandardRoom())){
            standard_room.setSelected(true);
        }

        roomTypePanel.add(suit_room);
        roomTypePanel.add(deluxe_room);
        roomTypePanel.add(standard_room);
        input_panel.add(roomTypePanel);
        
       
        input_panel.add(new JLabel("숙박 비용"));
        JPanel hotel_costPanel = new JPanel();
        hotel_costPanel.setLayout(new GridLayout(1, 2));
        hotel_cost.setText(hotelInfo.getHotelCost());
        hotel_costPanel.add(hotel_cost);
        hotel_costPanel.add(new JLabel(" 원"));
        input_panel.add(hotel_costPanel);

       
        input_panel.add(new JLabel("등록 여부"));
        hotel_register.setText(hotelInfo.getHotelRegistration());
        input_panel.add(hotel_register);
        
        
        // 폴더에서 사진 선택
        input_panel.add(new JLabel("호텔 대표 사진"));
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
                FileDialog file_open = new FileDialog((JFrame) SwingUtilities.getWindowAncestor(HotelBS.this), "사진 등록", FileDialog.LOAD);
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
                FileDialog file_save = new FileDialog((JFrame) SwingUtilities.getWindowAncestor(HotelBS.this), "사진 저장", FileDialog.SAVE);
                file_save.setVisible(true);
                
                String path = file_save.getDirectory(); // 파일 경로
                String file_name = file_save.getFile(); // 파일 이름
                
                File file = new File(path);
                BufferedWriter writer = null;
                
                try{
                    writer = new BufferedWriter(new FileWriter(file+"/"+file_name));
                    writer.write("저장된 파일");
                    writer.flush();
                    
                    JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(HotelBS.this), "사진이 저장되었습니다.");
                    
                    writer.close();
                    
                } catch(IOException e1){
                    e1.printStackTrace();
                }
            }
            
        });
        
       return input_panel;
    }
    
    private JPanel searchPanel(){
        
        // 검색 패널 //

        JPanel search_panel = new JPanel();
      
        // 콤보 박스
        String [] search = {"전체 조회","호텔 이름"};
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
        
        // 센터 패널 //
        JPanel hotel_list_panel = new JPanel(new BorderLayout());
        String header[] = {"비즈니스 넘버", "사업자 유저넘버", "호텔 이름", "지역", "상세 주소","투숙객 수", "조식 여부", "룸 타입", "숙박 비용","등록 여부"};
        String contents[][]={};
        
        hotel_model = new DefaultTableModel(contents, header);
        hotel_table = new JTable(hotel_model);
        hotel_scrollpane = new JScrollPane(hotel_table);
        hotel_list_panel.add(hotel_scrollpane, BorderLayout.CENTER);
        
        return hotel_list_panel;
    }
    
    private JPanel southPanel(ButtonInterface buttonInterface){
        // 하단 패널
        JPanel south_panel = new JPanel();
        south_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 7, 2));
       
        JButton backBT = new JButton("이전");
        JButton modifyBT = new JButton("수정");
        JButton addBT = new JButton("추가");
        JButton deleteBT = new JButton("삭제");
        JButton okBT = new JButton("확인");
        JButton undoBT = new JButton("복구");
        
        
        south_panel.add(backBT);
        south_panel.add(modifyBT);
        south_panel.add(addBT);
        south_panel.add(deleteBT);
        south_panel.add(okBT);
        south_panel.add(undoBT);
        
        
        backBT.addActionListener(e->buttonInterface.onBack());
        
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
        
        
        addBT.addActionListener(new ActionListener(){ // 추가 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                add(hotel_table);
            }
            
        });
        
        
        
        
        deleteBT.addActionListener(new ActionListener(){ // 삭제 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                delete(hotel_table);
            }
            
        });
       
            
        undoBT.addActionListener(new ActionListener(){ //  복원 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                undo(caretaker,hotel_table);
            }
            
        });
        
        okBT.addActionListener(e->buttonInterface.onOk());
        
        return south_panel;
    }
    
    
    private void add(JTable hotel_table){
        
        try{
            String hotel_name_text = hotel_name.getText();
            String hotelarea_text =(String) hotel_area.getSelectedItem();
            String detaild_address_text = detailed_address.getText();
            String guestnum_text = (String) num_guest.getSelectedItem();
            String breakfast = breakfast_yes.isSelected() ? "O" : "X";
            String roomtype = "";
            if (suit_room.isSelected()) {
                roomtype = "스위트룸";
            } else if (deluxe_room.isSelected()) {
                roomtype = "디럭스룸";
            } else if (standard_room.isSelected()) {
                roomtype = "스탠다드룸";
            }
            String hotel_cost_text = hotel_cost.getText();
            String hotel_register_text = hotel_register.getText();

            
            if (hotel_name_text.isEmpty() || hotelarea_text.isEmpty() || detaild_address_text.isEmpty() || guestnum_text.isEmpty() || breakfast.isEmpty() ||roomtype.isEmpty() || hotel_cost_text.isEmpty() || hotel_register_text.isEmpty()) {
                JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            hotel_model = (DefaultTableModel) hotel_table.getModel();
            String type = "H";
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
            
            
            
            hotel_model.addRow(new Object[]{bs_num,
                bs_user_num,
                hotel_name_text,
                hotelarea_text,
                detaild_address_text,
                guestnum_text,
                breakfast,
                roomtype,
                hotel_cost_text,
                hotel_register_text});


            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(bs_num + "|" + bs_user_num + "|" + hotel_name_text + "|" + hotelarea_text + "|" + detaild_address_text + "|" + guestnum_text + "|" + breakfast + "|" + roomtype + "|" + hotel_cost_text + "|" + hotel_register_text + "\n");
            writer.close();
            
            

        } catch (IOException ex) {
            ex.printStackTrace();
            
        }
        
        
    }
    
    private void search(JTable hotel_table){
        // 사용자가 선택한 검색 기준과 입력한 텍스트 가져오기
        search_combo_text = (String)search_combo.getSelectedItem();
        text = search_text.getText();
    
       
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            hotel_model = (DefaultTableModel) hotel_table.getModel();
            hotel_model.setRowCount(0); // 테이블 초기화
            
        
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (search_combo_text.equals("전체 조회") || 
                    (search_combo_text.equals("호텔 이름") && data.length >= 3 && data[2].equals(text))) {
                    hotel_model.addRow(data);
                   
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "파일 읽기 오류.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   
    
    private void modify(JTable hotel_table){
        row = hotel_table.getSelectedRow();
        
       String new_breakfast = "";
        if (breakfast_yes.isSelected()) {
            new_breakfast = "O";
        } else if (breakfast_no.isSelected()) {
            new_breakfast = "X";
        }
        
        String new_roomtype = "";
        if (suit_room.isSelected()) {
            new_roomtype = "스위트룸";
        } else if (deluxe_room.isSelected()) {
            new_roomtype = "디럭스룸";
        } else if (standard_room.isSelected()) {
            new_roomtype = "스탠다드룸";
        }

                
        if(row!=-1){ // 만약 선택된 행이 있다면
            
            hotel_model = (DefaultTableModel) hotel_table.getModel();
            
            String bs_num = (String) hotel_model.getValueAt(row, 0);
            String bs_user_num = (String) hotel_model.getValueAt(row, 1);
            
            String[] new_hotel_input={ // 각각의 새로운(수정돤) 정보를 배열로 저장
                hotel_name.getText(),
                (String) hotel_area.getSelectedItem(),
                detailed_address.getText(),
                (String) num_guest.getSelectedItem(),
                new_breakfast,
                new_roomtype,
                hotel_cost.getText(),
                hotel_register.getText()
            };
         
            
            for (int i = 0; i < new_hotel_input.length; i++) {
                if (new_hotel_input[i] == null || new_hotel_input[i].isEmpty()) { // 수정된 정보가 없으면
                    new_hotel_input[i] = (String) hotel_model.getValueAt(row, i + 2); // 현재 값을 유지
                }
                hotel_model.setValueAt(new_hotel_input[i], row, i + 2);
            }
            
            hotel_model.setValueAt(bs_num, row, 0);
            hotel_model.setValueAt(bs_user_num, row, 1);
            
            // 파일에 저장
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < hotel_model.getRowCount(); i++) {
                    StringBuilder row_data = new StringBuilder();
                    for (int j = 0; j < hotel_model.getColumnCount(); j++) {
                        Object value = hotel_model.getValueAt(i, j);
                        row_data.append(value != null ? value.toString() : "");
                        if (j < hotel_model.getColumnCount() - 1) {
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
    
        
    
    private void delete(JTable hotel_table){
        
        
        row = hotel_table.getSelectedRow(); // 삭제된 행의 인덱스
        
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "삭제할 행을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        hotel_model = (DefaultTableModel) hotel_table.getModel();
        HotelOriginator originator = new HotelOriginator();

        column = hotel_model.getColumnCount();
        String[] row_data = new String[column];
        for(int i=0; i<column; i++){
             Object value = hotel_model.getValueAt(row, i);
             if(value!=null){
                 row_data[i] = value.toString(); // 타입변환
             }
             else{
                 row_data[i] = "";
             }
        }

       
        originator.setDeleteRow(row_data); // 삭제된 데이터를 오리지네이터에 저장
        
        HotelMemento memento = originator.createMemento(); // 메멘토 생성
        caretaker.save(memento); // 케이테이커에 메멘토 저장
        
        
        hotel_model.removeRow(row); // 열 삭제
        
        for (int i = row; i < hotel_model.getRowCount(); i++) { // 현재 테이블 모델의 행 수 반환
            hotel_model.setValueAt("H" + (i + 1), i, 0); // 비즈니스 넘버 재할당 : i번째 행의 비즈니스 넘버를 i+1로 수정하여 번호를 한칸씩 앞당기기!!
            
        }
       
    }
    
    
    
    private void undo(HotelCaretaker caretaker, JTable hotel_table){ // 복구 메서드
        
        hotel_model = (DefaultTableModel) hotel_table.getModel();
        
        HotelOriginator originator = new HotelOriginator();
        HotelMemento memento = caretaker.undo(); // 케어테이커에서 undo 메멘토 들고옴
        
        originator.restoreMemento(memento);
        Object[] restore_row = originator.getDeleteRow();
        
        hotel_model.addRow(restore_row);
        
        // 비즈니스 번호 순서대로 변경
        column = hotel_model.getRowCount();
        for(int i=0; i<column; i++){
            hotel_model.setValueAt("H" + (i + 1), i, 0);
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
   
