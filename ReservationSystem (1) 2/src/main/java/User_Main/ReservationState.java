package User_Main;

public interface ReservationState {
    void cancelReservation(ReservationEntry entry);
    void completeReservation(ReservationEntry entry);
}
