package org.afs.pakinglot;

public class ParkingLotFullException extends RuntimeException {
    public ParkingLotFullException(String message) {
        super(message);
    }
}
