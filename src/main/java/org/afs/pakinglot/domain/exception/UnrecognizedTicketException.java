package org.afs.pakinglot.domain.exception;

public class UnrecognizedTicketException extends RuntimeException {
    public UnrecognizedTicketException(String unrecognizedParkingTicket) {
        super("Unrecognized parking ticket.");
    }
}
