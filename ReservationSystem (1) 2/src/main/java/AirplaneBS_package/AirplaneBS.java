/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AirplaneBS_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class AirplaneBS extends JPanel{
    
    private Airplane_information airplaneInfo;
    private JComboBox airline_combo;
    private JComboBox departure_area_combo;
    private JComboBox arrival_area_combo;
    private JComboBox seat_type_combo;
    private ButtonGroup route_group;
    private JRadioButton round_trip;
    private JRadioButton one_way;
    private JTextField airplane_cost_text;
    private JTextField airplane_register;
    private JComboBox search_combo;
    private JTextField search_text;
    private JButton searchBT;
    private DefaultTableModel airplane_model;
    private JTable airplane_table;
    private JScrollPane airplane_scrollpane;
    
    
    public AirplaneBS(){
        
        super(new BorderLayout());
        
        String [] airline = {"::항공사::","제주 항공","대한 항공","에어 부산"};
        String [] departure_area = {"::출발 ::","김포","제주","부산","대구","여수","광주","인천"};
        String [] arrival_area= {"::출발 ::","김포","제주","부산","대구","여수","광주","인천"};
        String [] seat_type = {"::좌석::", "이코노미","비즈니스","일등석"};
        
        airplaneInfo = new Airplane_information(airline, departure_area,arrival_area, seat_type);
        airline_combo = new JComboBox();
        departure_area_combo = new JComboBox();
        arrival_area_combo = new JComboBox();
        seat_type_combo = new JComboBox();
        route_group = new ButtonGroup();
        round_trip = new JRadioButton();
        one_way = new JRadioButton();
        airplane_cost_text = new JTextField();
        airplane_register = new JTextField();
        search_combo = new JComboBox();
        
        
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
        input_panel.setLayout(new GridLayout(4,4,10,15));
        
        // 항공사
        input_panel.add(new JLabel("항공사"));
        airline_combo = airplaneInfo.getAirline();
        input_panel.add(airline_combo);
        
        // 출발 지역
        input_panel.add(new JLabel("출발 지역"));
        departure_area_combo = airplaneInfo.getDepartureArea();
        input_panel.add(departure_area_combo);
        
        // 도착 지역
        input_panel.add(new JLabel("도착 지역"));
        arrival_area_combo = airplaneInfo.getArrivalArea();
        input_panel.add(arrival_area_combo);
        
        // 좌석 타입
        input_panel.add(new JLabel("좌석 타입"));
        seat_type_combo = airplaneInfo.getSeatType();
        input_panel.add(seat_type_combo);
         
        // 왕복 편도 선택
        input_panel.add(new JLabel("왕복 / 편도"));
        JPanel route_panel = new JPanel();
        route_panel.setLayout(new GridLayout(1, 2));
        
        round_trip = airplaneInfo.getRoundTrip();
        one_way =airplaneInfo.getOneWay();
        
        route_group.add(round_trip);
        route_group.add(one_way);
        
        route_panel.add(round_trip);
        route_panel.add(one_way);
        
        input_panel.add(route_panel);
        
        
        
        // 비용
        input_panel.add(new JLabel("항공 비용"));
        JPanel costPanel = new JPanel();
        costPanel.setLayout(new GridLayout(1, 2));
        airplane_cost_text = airplaneInfo.getAirplaneCost();
        costPanel.add(airplane_cost_text);
        costPanel.add(new JLabel(" 원"));
        input_panel.add(costPanel);
        
        
        
        // 등록 여부
        input_panel.add(new JLabel("등록 여부"));
        airplane_register = airplaneInfo. getAirplaneRegister();
        input_panel.add(airplane_register);
        
        // 사진 등록
        input_panel.add(new JLabel("항공 대표 사진"));
        JButton btnOpen = new JButton("사진 등록");
        input_panel.add(btnOpen);
        
        btnOpen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               FileDialog file_open = new FileDialog((JFrame) SwingUtilities.getWindowAncestor(AirplaneBS.this), "사진 등록", FileDialog.LOAD);
               file_open.setVisible(true);
                
                String path = file_open.getDirectory();
            }
            
        });
        
        input_panel.setBounds(15,75,655,300);
        add(input_panel);
        
        
        // 검색 패널 //
        JPanel search_panel = new JPanel();
        
        // 콤보 박스
        String [] search = {"비즈니스 넘버", "항공사"};
        search_combo = new JComboBox(search);
        search_panel.add(search_combo);
        
        // 검색 텍스트 필드
        search_text = new JTextField(30);
        search_panel.add(search_text);
        
        // 조회 버튼
        searchBT = new JButton("조회");
        search_panel.add(searchBT);
        
        
        search_panel.setBounds(15,400,655,50);
        add(search_panel);
        
        
        // 센터 패널 //
        JPanel airplane_center_panel = new JPanel(new BorderLayout());
 
        String header[] = {"비즈니스 넘버", "항공사","출발지역","도착지역","좌석타입","왕복/편도","항공 비용","등록 여부"};
        String contents[][]={};
        
        airplane_model = new DefaultTableModel(contents, header);
        airplane_table = new JTable(airplane_model);
        airplane_scrollpane = new JScrollPane(airplane_table);
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
        
        /*
        addBT.addActionListener(new ActionListener(){ // 추가 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                add(airplane_table);
            }
            
        });
        
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
         
        
        deleteBT.addActionListener(new ActionListener(){ // 삭제 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                delete(airplane_table);
            }
            
        });
        
        okBT.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ok(airplane_table);
            }
            
        });

        
*/
    }
}




