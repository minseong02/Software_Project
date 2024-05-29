/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */

package manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import reserve.login.Login;

public class ManagerMode extends JFrame implements ActionListener { 
    
    private JPanel productPanel;
    private JPanel buttonPanel;
    private JRadioButton btn1;
    private JRadioButton btn2;
    private JRadioButton btn3;
    private ProductTablePanel productTablePanel;
    private ProductData productData;
    private String filename;
    private String delimiter;
    private Map<String, List<String[]>> previousData;
    
    private CommandInvokerWithUndo commandInvoker;
    
    private String panelName;
    
    public ManagerMode(String panelName) { 
    	
    	this.panelName = (panelName != null) ? panelName : "Hotel_Panel";
        
        //기본 frame 설정(managerModeFrame)
        setTitle("관리자 모드");       
        getContentPane().setBackground(Color.white);    
        setSize(700, 800); // JFrame 크기 설정
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        productData = new ProductData();
        productTablePanel = new ProductTablePanel(new String[]{}, productData);
        commandInvoker = new CommandInvokerWithUndo();
        previousData = new HashMap<>();
        
        //filename, delimiter 초기화
        this.filename = getFileNameForPanel(this.panelName);
        this.delimiter = "|";
        
        //버튼 패널 초기화
        showButtonPanel();    
        setVisible(true);
    }
    


    private void showButtonPanel() {
        //buttonPanel 설정
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setPreferredSize(new Dimension(700,800));
        
        // JPanel을 JFrame의 CENTER에 추가
        add(buttonPanel, BorderLayout.CENTER);
        
        //buttonPanel에 회원 관리 버튼 설정
        JButton memberButton = new JButton("회원 관리");
        memberButton.addActionListener(this);
        memberButton.setBounds(250,200,200,150); // 버튼 크기 설정
        memberButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        memberButton.setBackground(Color.WHITE);
        buttonPanel.add(memberButton);
        
        //buttonPanel에 상품 관리 버튼 설정
        JButton productButton = new JButton("상품 관리");
        productButton.addActionListener(this);
        productButton.setBounds(250,400,200,150); // 버튼 크기 설정
        productButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        productButton.setBackground(Color.WHITE);
        buttonPanel.add(productButton);
        
        add(buttonPanel, BorderLayout.CENTER);
        
        //종료 버튼 설정
        JButton exitButton = new JButton("종료");
        exitButton.addActionListener(this);
        exitButton.setBounds(300,650,100,30);
        exitButton.setFont(new Font("맑은 고딕", Font.BOLD,14));
        exitButton.setBackground(Color.WHITE);
        buttonPanel.add(exitButton);
        //로그인 화면으로 이동하는 기능 추가 필요!
        
        //'관리자 모드 화면' 텍스트 추가
        JLabel label = new JLabel("관리자 모드입니다.");
        label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label.setForeground(Color.BLUE);
        label.setBounds(265,100,200,50);
        buttonPanel.add(label);
        
    }
    
    @Override // buttonPanel과 productPanel으로 화면을 전환하기 위해 필요한 부분 
              // 사용자의 클릭 이벤트에 따라 전환됨
              // 수정: 저장 버튼 기능 추가!
              // 2차 수정: 커맨드 패턴 추가!
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Action Command: " + actionCommand);
            
        if ("상품 관리".equals(actionCommand)) {
            showProductPanel();
        }
        else if ("이전 화면".equals(actionCommand)) {
            resetButtonPanel();
        } 
        else if("회원 관리".equals(actionCommand)) {
        A_MemberManage AU = new A_MemberManage();
        AU.setVisible(true);
        setVisible(false);
        dispose();
        }       
        else if ("종료".equals(actionCommand)) {
            dispose();
            new Login().setVisible(true);
        }
        else if ("저장".equals(actionCommand)) {
            if (productData != null) {
                // 현재 선택한 테이블의 데이터를 가져옴
                String panelName = productTablePanel.getCurrentPanelName();
                List<String[]> currentData = productTablePanel.getDataForPanel(panelName);
                
                // 이전 데이터를 사용하여 파일에 저장
                saveDataToFileWithPreviousData(panelName, currentData);                            
            } else {
                System.err.println("Error: productData is null.");
            }
        }                     
    }
    
    // 이전 데이터를 사용하여 파일에 저장하는 메서드
    private void saveDataToFileWithPreviousData(String panelName, List<String[]> currentData) {
       
        // 이전 데이터가 있는 경우에만 실행
        if (previousData.containsKey(panelName)) {
            // 이전 데이터를 가져옴
            List<String[]> previousDataRows = previousData.get(panelName);
            
            // 이전 데이터를 파일에 저장
            String filename = getFileNameForPanel(panelName);
            productData.setDataRows(previousDataRows);
            productData.saveDataToFile(filename, delimiter);
            
            // 현재 데이터를 이전 데이터로 업데이트
            previousData.put(panelName, currentData);
        } else {
            System.err.println("Error: No previous data found for panel: " + panelName);
        }
    }  
    
    
    public void resetButtonPanel() {
        //화면 전환 시 필요한 추가 패널
        if (productPanel != null) {
            remove(productPanel);
        }
        
        add(buttonPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    public void showProductPanel() {
        //사용자한테 보여지는 상품 관리 화면
        remove(buttonPanel);
        
        productPanel = new JPanel();
        productPanel.setLayout(null);
        productPanel.setBackground(Color.WHITE);        
        productPanel.setBounds(0, 0, 700, 800);
        
        //이전 화면 버튼
        JButton backbtn = new JButton("이전 화면");
        backbtn.addActionListener(this);
        backbtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        backbtn.setBackground(Color.BLACK);
        backbtn.setForeground(Color.WHITE);
        backbtn.setBounds(530,5,120,30);
                
        productPanel.add(backbtn);
                
        
        //등록 버튼 
        JButton registerbtn = new JButton("등록");
        registerbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String panelName = productTablePanel.getCurrentPanelName();
                 if (panelName != null) {
                String filename = getFileNameForPanel(panelName);
                Command registerCommand = new RegisterCommand(productTablePanel, filename, delimiter);
                commandInvoker.setCommand(registerCommand);
                commandInvoker.executeCommand();
                } else {
                     System.err.println("Error: No panel selected.");
                 }
            }
        });
        registerbtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        registerbtn.setBackground(Color.BLACK);
        registerbtn.setForeground(Color.WHITE);
        registerbtn.setBounds(480, 600, 70, 30);
        
        productPanel.add(registerbtn);
        
        //삭제 버튼
        JButton deletebtn = new JButton("삭제");
        deletebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JTable targetTable = productTablePanel.getSelectedTable();
            if (targetTable != null) {
                int selectedRow = targetTable.getSelectedRow();
                if (selectedRow != -1) {
                    String panelName = productTablePanel.getCurrentPanelName();
                    if (panelName != null) {
                        String filename = getFileNameForPanel(panelName);
                        Command deleteCommand = new DeleteCommand((DefaultTableModel) targetTable.getModel(), selectedRow, productData, productTablePanel);
                        commandInvoker.setCommand(deleteCommand);
                        commandInvoker.executeCommand();
                    } else {
                        System.err.println("Error: No panel selected.");
                    }
                }
            }
            }
        });
        deletebtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        deletebtn.setBackground(Color.BLACK);
        deletebtn.setForeground(Color.WHITE);
        deletebtn.setBounds(580, 600, 70, 30);
        
        productPanel.add(deletebtn);       
        
        //저장 버튼
        JButton savebtn = new JButton("저장");
        savebtn.addActionListener(this);
        savebtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        savebtn.setBackground(Color.BLACK);
        savebtn.setForeground(Color.WHITE);
        savebtn.setBounds(315, 700, 70, 30);
        
        productPanel.add(savebtn);
        
        //취소 버튼 
        JButton cancelbtn = new JButton("취소");
        cancelbtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               commandInvoker.undoLastCommand();
           }
        });
        cancelbtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        cancelbtn.setBackground(Color.BLACK);
        cancelbtn.setForeground(Color.WHITE);
        cancelbtn.setBounds(50, 600, 70, 30);
       
        productPanel.add(cancelbtn);
        
        // 라디오 버튼 구현
        btn1 = new JRadioButton("호텔");
        btn2 = new JRadioButton("항공");
        btn3 = new JRadioButton("렌터카");
        
        
        Font radioButtonFont = new Font("맑은 고딕", Font.BOLD, 16);
        btn1.setFont(radioButtonFont);
        btn2.setFont(radioButtonFont);
        btn3.setFont(radioButtonFont);
        btn1.setBackground(Color.WHITE);
        btn2.setBackground(Color.WHITE);
        btn3.setBackground(Color.WHITE);
        

        ButtonGroup group = new ButtonGroup();
        group.add(btn1);
        group.add(btn2);
        group.add(btn3);
      
        
        //라디오 패널 설정
        JPanel radioPanel = new JPanel();       
        JLabel label = new JLabel("상품 선택  ");
        label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        
        radioPanel.add(label);
        radioPanel.add(btn1);
        radioPanel.add(btn2);
        radioPanel.add(btn3);
        radioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        radioPanel.setBackground(Color.WHITE);
        
        radioPanel.setBounds(0, 0, 700, 50); 
        productPanel.add(radioPanel);
        
        productTablePanel = new ProductTablePanel(new String[]{}, productData );
        productTablePanel.setBounds(50, 75, 600, 500);
        productPanel.add(productTablePanel, BorderLayout.CENTER);
        
        productTablePanel.settingProducts(btn1, btn2, btn3);
                
        add(productPanel);
        revalidate();
        repaint();
        
        if (productData == null) {
            productData = new ProductData();
        }
        
    // 패널 변경 시 파일 데이터를 로드
    btn1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadDataForPanel("Hotel_Panel");
        }
    });

    btn2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadDataForPanel("Airplane_Panel");
        }
    });

    btn3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadDataForPanel("Car_Panel");
        }
    });

    // 처음 패널 설정
    loadDataForPanel("Hotel_Panel");
    }

    private void loadDataForPanel(String panelName) {
    if (productData != null) {
        String filename = getFileNameForPanel(panelName);
        productData.loadDataFromTextFile(filename, delimiter);
        List<String[]> data = productData.getDataRows();
        productTablePanel.updateData(data);
    } else {
        System.err.println("Error: productData is null.");
    }
}
    
    public static String getFileNameForPanel(String panelName) {
        switch (panelName) {
            case "Hotel_Panel":
                return "hotel_business_textfile.txt";
            case "Airplane_Panel":
                return "airplane_business_textfile.txt";
            case "Car_Panel":
                return "car_business_textfile.txt";
            default:
                throw new IllegalArgumentException("Unknown panel name: " + panelName);
        }
    }
    
    public static void main(String[] args) {
    	String intialPanelName = "Hotel_Panel";
        ManagerMode manager = new ManagerMode(intialPanelName);
       
        // getFileNameForPanel 메소드 호출
        String fileName = getFileNameForPanel(manager.panelName);
        System.out.println("사용할 파일 이름: " + fileName);
        
    }    
}


