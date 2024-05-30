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
    private String panelName;
    private CommandInvokerWithUndo commandInvoker;

    public ManagerMode(String panelName) {

        this.panelName = (panelName != null) ? panelName : "Hotel_Panel";
        // 기본 frame 설정(managerModeFrame)
        setTitle("관리자 모드");
        getContentPane().setBackground(Color.white);
        setSize(700, 800); // JFrame 크기 설정
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 사용되는 객체 초기화
        productData = new ProductData();
        productTablePanel = new ProductTablePanel(new String[]{}, productData);
        commandInvoker = new CommandInvokerWithUndo();

        //filename, delimiter 설정
        this.filename = getFileNameForPanel(this.panelName);
        this.delimiter = "|";

        // 버튼 패널 초기화
        showButtonPanel();
        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
        return button;
    }

    private void addButton(JPanel panel, JButton button) {
        panel.add(button);
    }

    private void showButtonPanel() {
        // buttonPanel 설정
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(700, 800));

        // JPanel을 JFrame의 CENTER에 추가
        add(buttonPanel, BorderLayout.CENTER);

        // 회원 관리 버튼 추가
        JButton memberButton = createButton("회원 관리", 250, 200, 200, 150);
        addButton(buttonPanel, memberButton);

        // 상품 관리 버튼 추가
        JButton productButton = createButton("상품 관리", 250, 400, 200, 150);
        addButton(buttonPanel, productButton);

        // 종료 버튼 추가
        JButton exitButton = createButton("종료", 300, 650, 100, 70);
        addButton(buttonPanel, exitButton);

        // '관리자 모드 화면' 텍스트 추가
        JLabel label = new JLabel("관리자 모드입니다.");
        label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label.setForeground(Color.BLUE);
        label.setBounds(265, 100, 200, 50);
        buttonPanel.add(label);
    }

    @Override // buttonPanel과 productPanel으로 화면을 전환하기 위해 필요한 부분 
    // 사용자의 클릭 이벤트에 따라 전환됨
    // 수정: 저장 버튼 기능 추가!
    // 2차 수정: 종료 버튼 기능 추가!
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Action Command: " + actionCommand);

        if ("상품 관리".equals(actionCommand)) {
            showProductPanel();
        } else if ("이전 화면".equals(actionCommand)) {
            resetButtonPanel();
        } else if ("종료".equals(actionCommand)) {
            dispose();
            new Login().setVisible(true); 
} 
        else if ("저장".equals(actionCommand)) {            
            if (productData != null) {
                // 현재 선택한 테이블의 데이터를 가져옴
                String panelName = productTablePanel.getCurrentPanelName();
                List<String[]> currentData = productTablePanel.getDataForPanel(panelName);
                // 현재 데이터를 사용하여 파일에 저장
                productData.updateFileData(currentData, getFileNameForPanel(panelName), delimiter);
            } else {
                System.err.println("Error: productData is null.");
            }
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

    private JButton createProductButton(String text, ActionListener actionListener, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setBounds(x, y, width, height);
        return button;
    }

    public void showProductPanel() {
        remove(buttonPanel);

        productPanel = new JPanel();
        productPanel.setLayout(null);
        productPanel.setBackground(Color.WHITE);
        productPanel.setBounds(0, 0, 700, 800);

        JButton backbtn = createProductButton("이전 화면", this, 530, 5, 120, 30);
        JButton registerbtn = createProductButton("등록", new ActionListener() {
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
        }, 480, 600, 70, 30);

        JButton deletebtn = createProductButton("삭제", new ActionListener() {
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
        }, 580, 600, 70, 30);

        JButton savebtn = createProductButton("저장", this, 315, 700, 70, 30);
        JButton cancelbtn = createProductButton("취소", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandInvoker.undoLastCommand();
            }
        }, 50, 600, 70, 30);

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

        productTablePanel = new ProductTablePanel(new String[]{}, productData);
        productTablePanel.setBounds(50, 75, 600, 500);

        productPanel.add(backbtn);
        productPanel.add(registerbtn);
        productPanel.add(deletebtn);
        productPanel.add(savebtn);
        productPanel.add(cancelbtn);
        productPanel.add(radioPanel);
        productPanel.add(productTablePanel);

        productTablePanel.settingProducts(btn1, btn2, btn3);

        add(productPanel);
        revalidate();
        repaint();

        if (productData == null) {
            productData = new ProductData();
        }

        btn1.addActionListener(e -> loadDataForPanel("Hotel_Panel"));
        btn2.addActionListener(e -> loadDataForPanel("Airplane_Panel"));
        btn3.addActionListener(e -> loadDataForPanel("Car_Panel"));

        loadDataForPanel("Hotel_Panel");
    }

    private void loadDataForPanel(String panelName) {
        if (productData != null) {
            String filename = getFileNameForPanel(panelName);
            productData.loadDataFromTextFile(filename, delimiter);
            ProductIterator iterator = productData.iterator();
            productTablePanel.updateData(iterator);
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
        // 기본으로 설정된 패널: 호텔 
        String intialPanelName = "Hotel_Panel";
        ManagerMode manager = new ManagerMode(intialPanelName);

        // getFileNameForPanel 메소드 호출
        String fileName = getFileNameForPanel(manager.panelName);
        System.out.println("사용할 파일 이름: " + fileName);

    }
}
