package User_Main;

public class CompletedState implements ReservationState {

    @Override
    public void cancelReservation(ReservationEntry entry) {
        // Cannot cancel a completed reservation
    }

    @Override
    public void completeReservation(ReservationEntry entry) {
        // Already completed
    }
}
