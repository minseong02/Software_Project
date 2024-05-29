package User_Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReservationEntry {
    private JPanel reservationEntryPanel;
    private String details;
    private ReservationState state;
    private String reservationNumber;

    private ReservationEntry(Builder builder) {
        this.details = builder.details;
        this.state = builder.state;
        this.reservationNumber = builder.reservationNumber;
        this.reservationEntryPanel = new JPanel();
        this.reservationEntryPanel.setLayout(new BoxLayout(reservationEntryPanel, BoxLayout.Y_AXIS));
        this.reservationEntryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.reservationEntryPanel.setPreferredSize(new Dimension(680, 30 * builder.details.split("/").length)); // Adjust the height dynamically

        JTextArea reservationDetails = new JTextArea(details);
        reservationDetails.setEditable(false);
        reservationDetails.setLineWrap(true);
        reservationDetails.setWrapStyleWord(true);
        reservationDetails.setPreferredSize(new Dimension(660, 250));
        reservationEntryPanel.add(reservationDetails);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton cancelButton = new JButton("예약 취소");
        cancelButton.addActionListener(e -> this.state.cancelReservation(this));
        buttonPanel.add(cancelButton);

        JButton completeButton = new JButton("이용 완료");
        completeButton.addActionListener(e -> this.state.completeReservation(this));
        buttonPanel.add(completeButton);

        reservationEntryPanel.add(buttonPanel);
    }

    public JPanel getReservationEntryPanel() {
        return reservationEntryPanel;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public static class Builder {
        private String details;
        private ReservationState state;
        private String reservationNumber;

        public Builder setDetails(String details) {
            this.details = details;
            return this;
        }

        public Builder setState(ReservationState state) {
            this.state = state;
            return this;
        }

        public Builder setReservationNumber(String reservationNumber) {
            this.reservationNumber = reservationNumber;
            return this;
        }

        public ReservationEntry build() {
            return new ReservationEntry(this);
        }
    }
}
