/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buisness_package;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author mskim
 */
public class HotelBS extends JPanel{
    public HotelBS(){
        super(new BorderLayout());
        hotel_list();
    }
    
    void hotel_list(){
        
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
        
   
        input_panel.add(new JLabel("비즈니스 넘버"));
        JTextField  hotel_BS_num = new JTextField();
        input_panel.add(hotel_BS_num);


        input_panel.add(new JLabel("호텔 이름"));
        JTextField hotel_name = new JTextField();
        input_panel.add(hotel_name);
       
        // 지역 콤보 박스
        input_panel.add(new JLabel("지역"));
        String [] hotel_area = {"::시/도::","서울특별시","부산광역시","대구광역시","인천광역시","광주광역시","대전광역시","울산광역시","세종특별자치시","경기도","강원도","충청북도","충청남도","전라남도","전라북도","경상남도","경상북도","제주특별자치도"};
        JComboBox hotel_area_combo = new JComboBox(hotel_area);
        input_panel.add(hotel_area_combo);
        
        // 주소 입력
        input_panel.add(new JLabel("상세 주소"));
        JTextField detailed_address = new JTextField();
        input_panel.add(detailed_address);
       
        // 투숙객 수 콤보 박스
        input_panel.add(new JLabel("투숙객 수"));
        String [] num_guest = {"::인원 수::","2명","3명","4명"};
        JComboBox num_guest_combo = new JComboBox(num_guest);
        input_panel.add(num_guest_combo);
       
        input_panel.add(new JLabel("조식여부"));
        JPanel breakfast_panel = new JPanel();
        ButtonGroup breakfastTypeGroup = new ButtonGroup();
        breakfast_panel.setLayout(new GridLayout(1, 2));
        JRadioButton breakfast_yes = new JRadioButton("O");
        JRadioButton breakfast_no = new JRadioButton("X");
        
        breakfastTypeGroup.add(breakfast_yes);
        breakfastTypeGroup.add(breakfast_no);
        
        breakfast_panel.add(breakfast_yes);
        breakfast_panel.add(breakfast_no);
        
        input_panel.add(breakfast_panel);
        

        input_panel.add(new JLabel("룸 타입"));
        JPanel roomTypePanel = new JPanel();
        ButtonGroup roomTypeGroup = new ButtonGroup();
        roomTypePanel.setLayout(new GridLayout(3, 1));
        JRadioButton suit_room = new JRadioButton("스위트룸");
        JRadioButton deluxe_room = new JRadioButton("디럭스룸");
        JRadioButton standard_room = new JRadioButton("스탠다드룸");
    

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
        JTextField hotel_cost_text = new JTextField();
        hotel_costPanel.add(hotel_cost_text);
        hotel_costPanel.add(new JLabel(" 원"));
        input_panel.add(hotel_costPanel);
        
        
       
        input_panel.add(new JLabel("등록 여부"));
        JTextField register = new JTextField();
        input_panel.add(register);
        
        // 사진 등록
        input_panel.add(new JLabel("호텔 대표 사진"));
        JTextField hotel_photo = new JTextField();
        input_panel.add(hotel_photo);
       
       
        input_panel.setBounds(15,75,655,300);
        add(input_panel);
       
       
        // 검색 패널 //

        JPanel search_panel = new JPanel();
        //search_panel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"검색"));
      
        // 콤보 박스
        String [] search = {"비즈니스 넘버", "호텔 이름"};
        JComboBox search_combo = new JComboBox(search);
        search_panel.add(search_combo);
        
        // 검색 텍스트 필드
        JTextField search_text = new JTextField(30);
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
        
        DefaultTableModel hotel_model = new DefaultTableModel(contents, header);
        JTable hotel_table = new JTable(hotel_model);
        JScrollPane hotel_scrollpane = new JScrollPane(hotel_table);
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
        
        addBT.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 파일 경로 설정
                    String filePath = "hotel_buisness_textfile.txt";

                     // 사용자가 입력한 데이터 가져오기
                    String hotelNum = hotel_BS_num.getText();
                    String hotelName = hotel_name.getText();
                    String area = hotel_area_combo.getSelectedItem().toString();
                    String address = detailed_address.getText();
                    String guestNum = num_guest_combo.getSelectedItem().toString();
                    String breakfast = breakfast_yes.isSelected() ? "O" : "X";
                    String roomType = "";
                    if (suit_room.isSelected()) {
                        roomType = "스위트룸";
                    } else if (deluxe_room.isSelected()) {
                        roomType = "디럭스룸";
                    } else if (standard_room.isSelected()) {
                        roomType = "스탠다드룸";
                    }
                    String cost = hotel_cost_text.getText();
                    //String photo = hotel_photo.getText();
                    String registration = register.getText();

                    
                    String data = hotelNum + "|" + hotelName + "|" + area + "|" + address + "|" + guestNum + "|" + breakfast + "|" + roomType + "|" + cost + "|"  + registration + "\n";

                    // 파일에 데이터 추가하기
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                    writer.write(data);
                    writer.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
        });

        
    
        south_panel.add(deleteBT);
        south_panel.add(okBT);
        
        
        south_panel.setBounds(150,690,400,50);
        add(south_panel);

}
        
    
}
