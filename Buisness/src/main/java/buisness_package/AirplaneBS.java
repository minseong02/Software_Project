/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buisness_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mskim
 */
public class AirplaneBS extends JPanel{
    public AirplaneBS(){
        
        super(new BorderLayout());
        airplane_list();
        
    }
    
    void airplane_list(){
        setLayout(null);
        
        // 이미지를 표시할 라벨
        JLabel airplane_image_label = new JLabel();
        ImageIcon airplane_list_Icon = new ImageIcon("airplane_list.png");
        airplane_image_label.setIcon(airplane_list_Icon);
        airplane_image_label.setBounds(18,1,80,80);
        add(airplane_image_label);
        
        
        // 상단 패널
        JPanel input_panel = new JPanel();
        input_panel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"입력"));
        input_panel.setLayout(new GridLayout(5,4,10,15));
        
        // 항공 비즈니스 넘버
        input_panel.add(new JLabel("비즈니스 넘버"));
        JTextField airplane_BS_num = new JTextField();
        input_panel.add(airplane_BS_num);
        
        // 항공사
        input_panel.add(new JLabel("항공사"));
        String [] airline = {"::항공사::","제주 항공","대한 항공","에어 부산"};
        JComboBox airline_combo = new JComboBox(airline);
        input_panel.add(airline_combo);
        
        // 출발 지역
        input_panel.add(new JLabel("출발 지역"));
        String [] departure_area = {"::출발 ::","김포","제주","부산","대구","여수","광주","인천"};
        JComboBox departure_area_combo = new JComboBox(departure_area);
        input_panel.add(departure_area_combo);
        
        // 도착 지역
        input_panel.add(new JLabel("도착 지역"));
        String [] arrival_area= {"::출발 ::","김포","제주","부산","대구","여수","광주","인천"};
        JComboBox arrival_area_combo = new JComboBox(arrival_area);
        input_panel.add(arrival_area_combo);
        
        // 좌석 타입
        input_panel.add(new JLabel("좌석 타입"));
        String [] seat_type = {"::좌석::", "이코노미","비즈니스","일등석"};
        JComboBox seat_type_combo = new JComboBox(seat_type);
        input_panel.add(seat_type_combo);
         
        // 왕복 편도 선택
        input_panel.add(new JLabel("왕복 / 편도"));
        JPanel route_panel = new JPanel();
        ButtonGroup route_group = new ButtonGroup();
        route_panel.setLayout(new GridLayout(1, 2));
        
        JRadioButton round_trip = new JRadioButton("왕복");
        JRadioButton ond_way = new JRadioButton("편도");
        
        route_group.add(round_trip);
        route_group.add(ond_way);
        
        route_panel.add(round_trip);
        route_panel.add(ond_way);
        
        input_panel.add(route_panel);
        
        
        
        // 비용
        input_panel.add(new JLabel("항공 비용"));
        JPanel costPanel = new JPanel();
        costPanel.setLayout(new GridLayout(1, 2));
        JTextField airplane_cost_text = new JTextField();
        costPanel.add(airplane_cost_text);
        costPanel.add(new JLabel(" 원"));
        input_panel.add(costPanel);
        
        
        
        // 등록 여부
        input_panel.add(new JLabel("등록 여부"));
        JTextField register = new JTextField();
        input_panel.add(register);
        
        // 사진 등록
        input_panel.add(new JLabel("항공 대표 사진"));
        JTextField airplane__photo = new JTextField();
        input_panel.add(airplane__photo);
        
        input_panel.setBounds(15,75,655,300);
        add(input_panel);
        
        
        // 검색 패널 //
        JPanel search_panel = new JPanel();
        
        // 콤보 박스
        String [] search = {"비즈니스 넘버", "항공사"};
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
        JPanel airplane_center_panel = new JPanel(new BorderLayout());
 
        String header[] = {"비즈니스 넘버", "항공사","출발지역","도착지역","좌석타입","왕복/편도","항공 비용","등록 여부"};
        String contents[][]={};
        
        DefaultTableModel airplane_model = new DefaultTableModel(contents, header);
        JTable airplane_table = new JTable(airplane_model);
        JScrollPane airplane_scrollpane = new JScrollPane(airplane_table);
        airplane_center_panel.add(airplane_scrollpane, BorderLayout.CENTER);

        airplane_center_panel.setBounds(15,450,655,230);
        add(airplane_center_panel);
        
        
        // 하단 패널 //
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
        
        
        south_panel.setBounds(150,690,400,50);
        add(south_panel);
         
   
        
        
    }
    
    
}

