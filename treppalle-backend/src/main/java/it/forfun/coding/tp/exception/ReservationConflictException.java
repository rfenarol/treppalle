package it.forfun.coding.tp.exception;

public class ReservationConflictException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = "Reservation conflict!";

    public ReservationConflictException() {
        super(DEFAULT_MESSAGE);
    }

    public ReservationConflictException(String message) {
        super(message);
    }
}
