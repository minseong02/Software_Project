/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarBS_package;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
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
public class CarBS extends JPanel{
    
    private CarBS_information carInfo;
    private JTextField car_company;
    private JComboBox rental_time_combo;
    private JTextField car_cost;
    private JComboBox vehicle_type_combo;
    private JRadioButton gasoline;
    private JRadioButton diesel;
    private JRadioButton electricity;
    private JRadioButton yes;
    private JRadioButton no;
    private JTextField register;
    private JComboBox search_combo;
    private JTextField search_text;
    private JButton searchBT;
    private DefaultTableModel car_model;
    private JTable car_table;
    private JScrollPane car_scrollpane;
    private String file;
    
    
    
    

    
    public CarBS(){
        super(new BorderLayout());
        
        String[]rental_time={"::대여 시간::","12시간","24시간","30시간","36시간","42시간","48시간"};
        String[]vehicle_type = {"::차종::","모닝","레이","아반떼","쏘나타","그랜저","쏘렌토","스타리아","아이오닉","코나","레이"};
        
        carInfo = new CarBS_information(rental_time,vehicle_type);
        
        vehicle_type_combo = new JComboBox();
        file = "car_business_textfile";
       
        
        rentalcar_list();
    }
    
    private void rentalcar_list(){
        
        setLayout(null);
        
        // 이미지를 표시할 JLabel//
        JLabel hotel_image_label = new JLabel();
        ImageIcon hotel_list_Icon = new ImageIcon("car_list.png");
        hotel_image_label.setIcon(hotel_list_Icon);
        hotel_image_label.setBounds(18,1,80,80);
        add(hotel_image_label);
        
        // 상단 패널 //
        JPanel input_panel = new JPanel();
        input_panel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"입력"));
        input_panel.setLayout(new GridLayout(5,4,10,15));
    
        
        input_panel.add(new JLabel("자동차 회사"));
        car_company = carInfo.getCarCompany();
        input_panel.add(car_company);
        
        input_panel.add(new JLabel("대여 시간"));
        JPanel rental_time_Panel = new JPanel();
        rental_time_Panel.setLayout(new GridLayout(1, 2));
        rental_time_combo = carInfo.getRentalTime();
        rental_time_Panel.add(rental_time_combo);
        input_panel.add(rental_time_Panel);
        
        
        input_panel.add(new JLabel("비용"));
        JPanel car_costPanel = new JPanel();
        car_costPanel.setLayout(new GridLayout(1, 2));
        car_cost = carInfo.getCarCost();
        car_costPanel.add(car_cost);
        car_costPanel.add(new JLabel(" 원"));
        input_panel.add(car_costPanel);
        
        input_panel.add(new JLabel("차종"));
        JPanel vehicle_type_Panel = new JPanel();
        vehicle_type_Panel.setLayout(new GridLayout(1, 2));
        vehicle_type_combo = carInfo.getVehicleType();
        input_panel.add(vehicle_type_combo);
        
        // 모닝, 레이, 아반떼, 쏘나타, 그랜저 : 휘발유
        // 쏘렌토, 스타리아 : 휘발유
        // 아이오닉, 코나, 레이 : 전기
        
        input_panel.add(new JLabel("연료"));
        JPanel oil_Panel = new JPanel();
        oil_Panel.setLayout(new GridLayout(1,3));
        ButtonGroup oil_Group = new ButtonGroup();
        gasoline = carInfo.getGasoline();
        diesel = carInfo.getDiesel();
        electricity = carInfo.getElectricity();
        
        oil_Group.add(gasoline);
        oil_Group.add(diesel);
        oil_Group.add(electricity);
        
        oil_Panel.add(gasoline);
        oil_Panel.add(diesel);
        oil_Panel.add(electricity);
        
        input_panel.add(oil_Panel);
        
        
        input_panel.add(new JLabel("하이패스")); // 하이패스 유무
        JPanel high_pass_Panel = new JPanel();
        high_pass_Panel.setLayout(new GridLayout(1,2));
        ButtonGroup high_pass_Group = new ButtonGroup();
        yes = carInfo.getYes();
        no = carInfo.getNo();
        
        high_pass_Group.add(yes);
        high_pass_Group.add(no);
        
        high_pass_Panel.add(yes);
        high_pass_Panel.add(no);
        
        input_panel.add(high_pass_Panel);
        
        
        input_panel.add(new JLabel("등록 여부"));
        register = carInfo.getCarRegister();
        input_panel.add(register);
        
        // 사진 등록
        input_panel.add(new JLabel("렌터카 대표 사진"));
        JButton btnOpen = new JButton("사진 등록");
        input_panel.add(btnOpen);
        
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog file_open = new FileDialog((JFrame) SwingUtilities.getWindowAncestor(CarBS.this), "사진 등록", FileDialog.LOAD);
                file_open.setVisible(true);
                
                String path = file_open.getDirectory();
            }
        });
        
        
        input_panel.setBounds(15,75,655,300);
        add(input_panel);
        
        
        // 검색 패널 //
        JPanel search_panel = new JPanel();
        
        // 콤보 박스
        String [] search = {"비즈니스 넘버", "자동차 회사"};
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
        JPanel car_list_panel = new JPanel(new BorderLayout());
        String header[] = {"비즈니스 넘버", "자동차 회사", "대여시간","비용","차종","연료","하이패스","등록 여부"};
        String contents[][]={};
        
        car_model = new DefaultTableModel(contents, header);
        car_table = new JTable(car_model);
        car_scrollpane = new JScrollPane(car_table);
        car_list_panel.add(car_scrollpane, BorderLayout.CENTER);

        car_list_panel.setBounds(15,450,655,230);
        add(car_list_panel);
        
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
        south_panel.add(deleteBT);
        south_panel.add(okBT);
        
        
        south_panel.setBounds(150,690,400,50);
        add(south_panel);
        
        addBT.addActionListener(new ActionListener(){ // 추가 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                add(car_table);
            }
            
        });
        
        
        
        
        
        
        
    }
    
    /*
    private void add(JTable car_table){
        try{
            Path filepath = Path.get(file);
            long lineCount = Files.lines(filepath).count()+1;
            
            String carcompany = carInfo.getCarCompany().getText();
        }
    }
*/
    
}

