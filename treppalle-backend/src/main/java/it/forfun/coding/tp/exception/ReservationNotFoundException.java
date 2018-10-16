package it.forfun.coding.tp.exception;

public class ReservationNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = "Reservation not found!";

    public ReservationNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ReservationNotFoundException(String message) {
        super(message);
    }
}
