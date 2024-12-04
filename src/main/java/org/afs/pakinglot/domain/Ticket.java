package org.afs.pakinglot.domain;

public record Ticket(String plateNumber, int position, int parkingLotId) {
    public int getParkedToParkingLotId() {
        return parkingLotId;
    }
}
