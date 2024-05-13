package com.naver;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Java Swing application with two buttons.
 */
public class JavaNaver {

    public static void main(String[] args) {
        // 프레임 생성 및 타이틀 설정
        JFrame frame = new JFrame("Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);  // 크기 설정

        // 버튼 추가
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");

        // 버튼에 액션 리스너 추가
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 1 clicked");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button 2 clicked");
            }
        });

        // 패널에 버튼 추가
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button1);
        panel.add(button2);

        // 프레임에 패널 추가
        frame.add(panel);

        // 프레임 보이기 설정
        frame.setVisible(true);
    }
}
