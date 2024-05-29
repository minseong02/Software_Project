/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private ManagerMode managerMode;
    private ProductTablePanel hotelPanel;
    private ProductTablePanel airplanePanel;
    private ProductTablePanel carPanel;
    private ProductData productData;
    private String filename;
    private String delimiter;
    private ProductTablePanel productTablePanel;
    
    private Map<String, Integer> registrationColumnIndexMap = new HashMap<>();
    private Map<String, JTable> tableMap = new HashMap<>();
    private Map<String, List<String[]>> previousData = new HashMap<>();
    
    
    public ProductTablePanel(String[] columns, ProductData productData) {
        this.columns = columns;
        this.productData = productData; // ManagerMode에서 생성한 ProductData 객체를 받아옴 
        this.managerMode = managerMode;
        this.filename = filename;
        this.delimiter = delimiter;
        
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
        
        this.hotelPanel = createAndLoadPanel("Hotel_Panel", new String[]{"비즈니스 넘버", "호텔 명", "지역", "상세 주소", "투숙객 수", "조식 여부", "룸 타입", "숙박 비용", "등록 여부"}, productData, "hotel_business_textfile.txt");
        this.airplanePanel = createAndLoadPanel("Airplane_Panel", new String[]{"비즈니스 넘버", "항공사", "출발 지역", "도착 지역", "좌석 타입", "왕복/편도", "항공권 가격", "등록 여부"}, productData, "airplane_business_textfile.txt");
        this.carPanel = createAndLoadPanel("Car_Panel", new String[]{"비즈니스 넘버", "자동차 회사", "대여 시간", "비용", "차종", "연료", "하이패스", "등록 여부"}, productData, "car_business_textfile.txt");

        registrationColumnIndexMap.put("Hotel_Panel", 8);
        registrationColumnIndexMap.put("Airplane_Panel", 7);
        registrationColumnIndexMap.put("Car_Panel", 7);
        
        tableMap.put("Hotel_Panel", hotelPanel.getProductTable());
        tableMap.put("Airplane_Panel", airplanePanel.getProductTable());
        tableMap.put("Car_Panel", carPanel.getProductTable());       
        
        btn1.addActionListener(e -> cardLayout.show(cardPanel, "Hotel_Panel"));
        btn2.addActionListener(e -> cardLayout.show(cardPanel, "Airplane_Panel"));
        btn3.addActionListener(e -> cardLayout.show(cardPanel, "Car_Panel"));
    }


    private ProductTablePanel createAndLoadPanel(String panelName, String[] columns, ProductData productData, String filePath) {
        ProductTablePanel panel = new ProductTablePanel(columns, productData);
        panel.setName(panelName);
        productData.loadDataFromTextFile(filePath, "|");
        panel.updateData(productData.getDataRows());
        cardPanel.add(panel, panelName);
        tableMap.put(panelName, panel.productTable);
        return panel;
    }    
    
    
    public ProductData getProductData() {
        return productData;
    }

    public String getFilename() {
        return filename;
    }

    public String getDelimiter() {
        return delimiter;
    }
    
    
    public JTable getTable(String panelName) {
        return tableMap.get(panelName);
    }
    
    public JTable getProductTable() {
        return this.productTable;
    }

    
    public int getRegistrationColumnIndex(String panelName) {
        Integer index = registrationColumnIndexMap.get(panelName);
        
        if (index == null) {
            System.out.println("Registration column index not found for panel: " + panelName);
            return -1;
        }
        System.out.println("Registration column index for panel " + panelName + ": " + index);
        return index;
    }    
        
    public JTable getSelectedTable() {
        String currentPanelName = getCurrentPanelName();
            if (currentPanelName != null) {
                return tableMap.get(currentPanelName);
            }
            return null;
    }
       
    public String getCurrentPanelName() {
        //테이블이 세 개이므로 등록 및 삭제를 할 때,
        //지금 선택하고 있는 테이블의 종류(호텔인지, 항공인지, 렌터카인지)를 확인함
        for (Component comp : cardPanel.getComponents()) {
            if (comp.isVisible() && comp instanceof ProductTablePanel) {
                String panelName = comp.getName();
                if (panelName != null && !panelName.isEmpty()) {
                    return panelName;
                }
            }
        }
        System.out.println("No current panel found.");
        return null;
    }

    public void registerProduct(String panelName) {

        Command registerCommand = new RegisterCommand(this, filename, delimiter);
        CommandInvokerWithUndo commandInvoker = new CommandInvokerWithUndo();
        commandInvoker.setCommand(registerCommand);
        commandInvoker.executeCommand();
        
    }

    public void deleteSelected(String panelName) {
        JTable targetTable = tableMap.get(panelName);
        if (targetTable != null) {
            int selectedRow = targetTable.getSelectedRow();
            if (selectedRow != -1) {
                Command deleteCommand = new DeleteCommand((DefaultTableModel) targetTable.getModel(), selectedRow, productData, productTablePanel);
                CommandInvokerWithUndo commandInvoker = new CommandInvokerWithUndo();
                commandInvoker.setCommand(deleteCommand);
                commandInvoker.executeCommand();
            }
        }
    }

    public List<String[]> getDataForPanel(String panelName) {
        JTable targetTable = tableMap.get(panelName);
        List<String[]> data = new ArrayList<>();
        if(targetTable != null) {
            DefaultTableModel model = (DefaultTableModel) targetTable.getModel();
            int columnCount = model.getColumnCount();
            for (int i = 0; i < model.getRowCount(); i++) {
                String[] rowData = new String[columnCount];
                for(int j=0; j < columnCount; j++) {
                    rowData[j] = (String) model.getValueAt(i,j);
                }
                data.add(rowData);
            }
        }
        return data;
    }
   
    public void savePreviousData(String panelName) {
        List<String[]> data = getDataForPanel(panelName);
        previousData.put(panelName, new ArrayList<>(data));
    }

    public void restorePreviousData(String panelName) {
        List<String[]> data = previousData.get(panelName);
        if (data != null) {
            updateData(data);
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