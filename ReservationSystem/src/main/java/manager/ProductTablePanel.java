/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;


/**
 *
 * @author PARKSOHYUN
 */

public class ProductTablePanel extends JPanel implements Updatetablemodel  {
    private JTable productTable;
    private DefaultTableModel model;
    private String[] columns;
    private JPanel productPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    ProductTablePanel hotelPanel;
    ProductTablePanel airplanePanel;
    ProductTablePanel carPanel;
    
    private Map<String, Integer> registrationColumnIndexMap = new HashMap<>();
    private Map<String, JTable> tableMap = new HashMap<>();

    public ProductTablePanel(String[] columns) {
        this.columns = columns;
        initPanels();
    }
        
    
    public void initPanels() {
        setLayout(new BorderLayout());
        createProductTable();
                
        //스크롤 가능
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        //ProductPanel에 cardPanel 추가
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        cardPanel.add(scrollPane, "MainTable"); 
        //"MainTable"은 실제로 사용되는 메소드, 인자값은 아님
        this.add(cardPanel, BorderLayout.CENTER);
    }
    
    
    private void createProductTable() {
        model = new DefaultTableModel(columns, 0);
     
        // JTable 만들기, 위치 설정 
        productTable = new JTable(model);
        productTable.setPreferredScrollableViewportSize(new Dimension(400, 500));
        productTable.setFillsViewportHeight(true);     
    }
    
    
    public void settingProducts(JRadioButton btn1,JRadioButton btn2,JRadioButton btn3) {
        //호텔, 항공, 관리자 세 개의 테이블 구성
        String[] hotelColumns = {"비즈니스 넘버", "호텔 명", "지역", "상세 주소", 
            "투숙객 수", "조식 여부", "룸 타입", "숙박 비용", "등록 여부"};
        String[] airlineColumns = {"비즈니스 넘버", "항공사", "출발 지역", "도착 지역",
            "좌석 타입", "왕복/편도", "항공권 가격", "등록 여부"};
        String[] rentalcarColumns = {"비즈니스 넘버", "자동차 회사", "대여 시간", "비용",
            "차종", "연료", "하이패스", "등록 여부"};    
        
       //생성된 테이블을 패널에 부착 
        hotelPanel = new ProductTablePanel(hotelColumns);
        airplanePanel = new ProductTablePanel(airlineColumns);
        carPanel = new ProductTablePanel (rentalcarColumns);
        
        //패널 이름 설정
        hotelPanel.setName("Hotel_Panel");
        airplanePanel.setName("Airplane_Panel");
        carPanel.setName("Car_Panel");
        
        ProductData productData = new ProductData();
        
        //파일을 읽어옴. 파일 경로와 구분자를 작성함
        productData.loadDataFromTextFile("C:\\ReservationSystem\\hotel_buisness_textfile.txt", "|");                           
        hotelPanel.updateData(productData.getDataRows());
        
        productData.loadDataFromTextFile("C:\\ReservationSystem\\airplane_buisness_textfile.txt", "|");      
        airplanePanel.updateData(productData.getDataRows());
        
        productData.loadDataFromTextFile("C:\\ReservationSystem\\car_buisness_textfile.txt", "|");
        carPanel.updateData(productData.getDataRows());
                         
        //cardLayout을 사용함
        cardPanel.add(hotelPanel, "Hotel_Panel");
        cardPanel.add(airplanePanel, "Airplane_Panel");
        cardPanel.add(carPanel,"Car_Panel");
        
        //tableMap에 패널 넣기
        tableMap.put("Hotel_Panel", hotelPanel.productTable);
        tableMap.put("Airplane_Panel", airplanePanel.productTable);
        tableMap.put("Car_Panel", carPanel.productTable);
        
        registrationColumnIndexMap.put("Hotel_Panel", 8);
        registrationColumnIndexMap.put("Airplane_Panel", 7);
        registrationColumnIndexMap.put("Car_Panel", 7);
        
        //버튼을 누르면 cardLayout에 수직으로 배치된 테이블이 나옴
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"Hotel_Panel");
            }
        });
 
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"Airplane_Panel");
            }
        });
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"Car_Panel");
            }
        });                
    }
    
    public String getCurrentPanelName() {
        for(Component comp : cardPanel.getComponents()) {
            if(comp.isVisible() && comp instanceof JPanel) {
                return ((JPanel) comp).getName();
            }
        }
        return null;
    }
    
    public void registerProduct(String panelName) {
        JTable targetTable = tableMap.get(panelName);
        int registrationColumnIndex = registrationColumnIndexMap.get(panelName);
        
        if(targetTable != null && registrationColumnIndex != -1) {
            int selectedRow = targetTable.getSelectedRow();
            if(selectedRow != -1) {
                String currentStatus = (String) targetTable.getModel().getValueAt(
                    selectedRow, registrationColumnIndex);                        
                if("X".equals(currentStatus)) {
                    targetTable.getModel().setValueAt("O", selectedRow, registrationColumnIndex);
                }
                else if ("O".equals(currentStatus)) {
                    targetTable.getModel().setValueAt("X", selectedRow, registrationColumnIndex);
                }
            }
        }
    }
    public void deleteSelected(String panelName) {
        JTable targetTable = tableMap.get(panelName);
        
        if (targetTable != null) {
            int selectedRow = targetTable.getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) targetTable.getModel();
                model.removeRow(selectedRow);
            } 
        }
    }
    
    @Override
    public void updateData(List<String[]> data) {
        model.setRowCount(0);
        
        for(String[] rowData : data) {
            model.addRow(rowData);
        }
    }
}
      

