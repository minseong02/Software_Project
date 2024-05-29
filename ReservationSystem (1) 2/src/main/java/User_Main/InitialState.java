package User_Main;

import javax.swing.JOptionPane;

public class InitialState implements ReservationState {

    @Override
    public void cancelReservation(ReservationEntry entry) {
        int response = JOptionPane.showConfirmDialog(
                JavaNaver.layeredPane,
                "정말 취소하시겠습니까?",
                "예약 취소 확인",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            entry.setState(new CancelledState());
            ReservationInquiryPage.cancelReservation(entry.getReservationNumber());
        }
    }

    @Override
    public void completeReservation(ReservationEntry entry) {
        entry.setState(new CompletedState());
        ReservationInquiryPage.showRatingDialog(entry.getReservationNumber());
    }
}
