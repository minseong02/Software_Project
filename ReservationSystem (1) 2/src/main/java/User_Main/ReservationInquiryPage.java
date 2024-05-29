package User_Main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class ReservationInquiryPage {

    public static void showReservationInquiryPage() {
        JPanel currentPanel = new JPanel();
        currentPanel.setLayout(null);
        currentPanel.setBounds(0, 0, 700, 800);
        for (Component component : JavaNaver.layeredPane.getComponents()) {
            currentPanel.add(component);
        }
        JavaNaver.pageStack.push(currentPanel); // Push current page to stack

        JPanel reservationPanel = new JPanel();
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));
        reservationPanel.setBounds(0, 0, 700, 800);

        JScrollPane scrollPane = new JScrollPane(reservationPanel);
        scrollPane.setBounds(0, 50, 700, 750);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the back button to the top left corner
        JButton backButton = new JButton("뒤로");
        backButton.setBounds(10, 10, 80, 30);
        backButton.addActionListener(e -> JavaNaver.goBack());
        JavaNaver.layeredPane.add(backButton, JLayeredPane.PALETTE_LAYER);

        JLabel reservationLabel = new JLabel("예약 내역");
        reservationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        reservationPanel.add(reservationLabel);

        // Read reservation details from the file
        boolean hasReservations = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("reservation_details.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    JPanel entryPanel = createReservationEntryPanel(line);
                    reservationPanel.add(entryPanel);
                    hasReservations = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!hasReservations) {
            JLabel noReservationsLabel = new JLabel("예약 내역이 없습니다.");
            noReservationsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            reservationPanel.add(noReservationsLabel);
        }

        JavaNaver.layeredPane.add(scrollPane, JLayeredPane.PALETTE_LAYER);
        JavaNaver.layeredPane.revalidate();
        JavaNaver.layeredPane.repaint();
    }

    private static JPanel createReservationEntryPanel(String reservationDetails) {
        String[] labels = {
                "예약 번호: ", "예약 날짜: ", "고객 이름: ", "고객 ID: ", "호텔 비즈니스 넘버: ",
                "호텔 이름: ", "지역: ", "상세 주소: ", "투숙객 수: ", "조식 여부: ",
                "룸 타입: ", "숙박 비용: ", "등록 여부: ", "항공 예약 번호: ", "항공사: ",
                "출발 지역: ", "도착 지역: ", "좌석 타입: ", "왕복/편도: ", "항공 비용: ",
                "항공 등록 여부: ", "렌터카 예약 번호: ", "렌터카 회사: ", "이용 시간: ",
                "렌터카 비용: ", "차량 종류: ", "연료 종류: ", "렌터카 보험 여부: ", "렌터카 등록 여부: "
        };
        String[] details = reservationDetails.split("/");
        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));
        entryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        entryPanel.setPreferredSize(new Dimension(680, 30 * (details.length + 2))); // Adjust the height dynamically

        for (int i = 0; i < details.length; i++) {
            JPanel detailPanel = new JPanel();
            detailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel(labels[i]);
            JLabel detailLabel = new JLabel(details[i]);
            detailPanel.add(label);
            detailPanel.add(detailLabel);
            entryPanel.add(detailPanel);
        }

        // Add the cancel and complete buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton cancelButton = new JButton("예약 취소");
        cancelButton.addActionListener(e -> cancelReservation(details[0])); // Use reservation number for identification
        buttonPanel.add(cancelButton);

        JButton completeButton = new JButton("이용 완료");
        completeButton.addActionListener(e -> completeReservation(details[0])); // Use reservation number for identification
        buttonPanel.add(completeButton);

        entryPanel.add(buttonPanel);

        return entryPanel;
    }

    static void cancelReservation(String reservationNumber) {
        // Show a confirmation dialog before deleting the reservation
        int response = JOptionPane.showConfirmDialog(
                JavaNaver.layeredPane,
                "정말 취소하시겠습니까?",
                "예약 취소 확인",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            try {
                List<String> reservations = new ArrayList<>();
                try (BufferedReader reader = new BufferedReader(new FileReader("reservation_details.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.startsWith(reservationNumber)) {
                            reservations.add(line);
                        }
                    }
                }
                try (PrintWriter writer = new PrintWriter(new FileWriter("reservation_details.txt"))) {
                    for (String reservation : reservations) {
                        writer.println(reservation);
                    }
                }
                JOptionPane.showMessageDialog(null, "예약 " + reservationNumber + " 취소되었습니다.");
                JavaNaver.goBack(); // Go back to refresh the page
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "예약 취소 중 오류가 발생했습니다.");
            }
        }
    }

    private static void completeReservation(String reservationNumber) {
        // Implement the completion logic here
        showRatingDialog(reservationNumber);
    }

    public static void showRatingDialog(String reservationNumber) {
        JDialog ratingDialog = new JDialog();
        ratingDialog.setTitle("별점 주기");
        ratingDialog.setSize(300, 200);
        ratingDialog.setLocationRelativeTo(null);

        JPanel ratingPanel = new JPanel();
        ratingPanel.setLayout(new BoxLayout(ratingPanel, BoxLayout.Y_AXIS));

        JLabel ratingLabel = new JLabel("별점을 선택하세요:");
        ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ratingPanel.add(ratingLabel);

        JPanel starsPanel = new JPanel();
        starsPanel.setLayout(new FlowLayout());
        ButtonGroup starGroup = new ButtonGroup();

        for (int i = 1; i <= 5; i++) {
            JRadioButton starButton = new JRadioButton(i + "★");
            starButton.setActionCommand(String.valueOf(i));
            starGroup.add(starButton);
            starsPanel.add(starButton);
        }
        ratingPanel.add(starsPanel);

        JButton submitButton = new JButton("제출");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> {
            String selectedRating = starGroup.getSelection().getActionCommand();
            JOptionPane.showMessageDialog(ratingDialog, "별점 " + selectedRating + "점을 주셨습니다.");
            ratingDialog.dispose();
            JOptionPane.showMessageDialog(null, "예약 " + reservationNumber + " 완료되었습니다.");
            recordRating(reservationNumber, selectedRating);
            JavaNaver.goBack();
        });
        ratingPanel.add(submitButton);

        ratingDialog.add(ratingPanel);
        ratingDialog.setVisible(true);
    }

    private static void recordRating(String reservationNumber, String rating) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("ratings.txt", true))) {
            writer.println("예약 번호: " + reservationNumber + " - 별점: " + rating);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
