package reserve.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class MainPage {

    public static void main(String[] args) {
        JFrame frame = new JFrame("메인 페이지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("여행 예약 시스템에 오신 것을 환영합니다!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        JButton proceedButton = new JButton("예약 시스템으로 이동");
        proceedButton.setFont(new Font("Serif", Font.PLAIN, 20));
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JavaNaver.openReservationSystem(frame);
            }
        });

        mainPanel.add(proceedButton, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
