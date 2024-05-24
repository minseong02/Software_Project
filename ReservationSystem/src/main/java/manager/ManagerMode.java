/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */

package manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerMode extends JFrame implements ActionListener { 
    
    private JPanel productPanel;
    private JPanel buttonPanel;
    private JRadioButton btn1;
    private JRadioButton btn2;
    private JRadioButton btn3;
    private ProductTablePanel productTablePanel;
   


    public ManagerMode() { 
        //기본 frame 설정(managerModeFrame)
        setTitle("관리자 모드");       
        getContentPane().setBackground(Color.white);    
        setSize(700, 800); // JFrame 크기 설정
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        //버튼 패널 초기화
        showButtonPanel();    
        setVisible(true);
    }

    private void showButtonPanel() {
        //buttonPanel 설정
        buttonPanel = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(150);
        layout.setVgap(150);
        buttonPanel.setLayout(layout); 
        buttonPanel.setBackground(Color.white);
        
        // JPanel을 JFrame의 CENTER에 추가
        add(buttonPanel, BorderLayout.CENTER);
        
        //buttonPanel에 회원 관리 버튼 설정
        JButton memberButton = new JButton("회원 관리");
        memberButton.addActionListener(this);
        memberButton.setPreferredSize(new Dimension(200,150)); // 버튼 크기 설정
        memberButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        memberButton.setBackground(Color.WHITE);
        buttonPanel.add(memberButton);
        
        //buttonPanel에 상품 관리 버튼 설정
        JButton productButton = new JButton("상품 관리");
        productButton.addActionListener(this);
        productButton.setPreferredSize(new Dimension(200, 150)); // 버튼 크기 설정
        productButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        productButton.setBackground(Color.WHITE);
        buttonPanel.add(productButton);
        
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    @Override // buttonPanel과 productPanel으로 화면을 전환하기 위해 필요한 부분 
              // 사용자의 클릭 이벤트에 따라 전환됨
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        if("상품 관리".equals(actionCommand)) {
            showProductPanel();
        }
        else if("이전 화면".equals(actionCommand)) {
            resetButtonPanel();
        }
        else if("회원 관리".equals(actionCommand)) {
        A_MemberManage AU = new A_MemberManage();
        AU.setVisible(true);
        setVisible(false);
        dispose();
        }
        
    }
    
    private void resetButtonPanel() {
        //화면 전환 시 필요한 추가 패널
        remove(productPanel);
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
                productTablePanel.registerProduct(panelName);
            }
        });
        registerbtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        registerbtn.setBackground(Color.BLACK);
        registerbtn.setForeground(Color.WHITE);
        registerbtn.setBounds(500, 600, 70, 30);
        
        productPanel.add(registerbtn);
        
        //삭제 버튼
        JButton deletebtn = new JButton("삭제");
        deletebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String panelName = productTablePanel.getCurrentPanelName();
                productTablePanel.deleteSelected(panelName);
            }
        });
        deletebtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        deletebtn.setBackground(Color.BLACK);
        deletebtn.setForeground(Color.WHITE);
        deletebtn.setBounds(600, 600, 70, 30);
        
        productPanel.add(deletebtn);       
        
       
        // 라디오 버튼 설정 (위치, 크기..등 미구현)
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
        
        productTablePanel = new ProductTablePanel(new String[]{});
        productTablePanel.setBounds(50, 75, 600, 500);
        productPanel.add(productTablePanel, BorderLayout.CENTER);
        
        productTablePanel.settingProducts(btn1, btn2, btn3);
        
        add(productPanel);
        revalidate();
        repaint();
        
    }
    public static void main(String[] args) {
        ManagerMode frame = new ManagerMode();
    }
}


