package User_Main;

public class CancelledState implements ReservationState {

    @Override
    public void cancelReservation(ReservationEntry entry) {
        // Already cancelled
    }

    @Override
    public void completeReservation(ReservationEntry entry) {
        // Cannot complete a cancelled reservation
    }
}
