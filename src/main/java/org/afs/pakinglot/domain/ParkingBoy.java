package org.afs.pakinglot.domain;

import org.afs.pakinglot.ParkingLotFullException;
import org.afs.pakinglot.domain.exception.UnrecognizedTicketException;
import org.afs.pakinglot.domain.strategies.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    public static final String NO_AVAILABLE_POSITION = "No available position";
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private List<Ticket> ticketList = new ArrayList<>();
    private List<ParkingLot> listOfParkingLots;
    private ParkingStrategy parkingStrategy;


    public ParkingBoy(List<ParkingLot> parkingLotLists, ParkingStrategy parkingStrategy) {
        this.listOfParkingLots = parkingLotLists;
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket park(Car car) {
        ParkingLot selectedLot = parkingStrategy.findParkingLot(listOfParkingLots);
        if (selectedLot != null) {
            Ticket ticket = selectedLot.park(car);
            ticketList.add(ticket);
            return ticket;
        }
        throw new ParkingLotFullException(NO_AVAILABLE_POSITION);
    }

    public List<ParkingLot> getParkingLotList() {
        return this.listOfParkingLots;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
         this.listOfParkingLots = parkingLotList;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public Car fetch(Ticket ticket) {
        if (ticketList.contains(ticket)) {
            Car obtainedCar =  listOfParkingLots.get(ticket.getParkedToParkingLotId()).fetch(ticket);
            ticketList.remove(ticket);
            return obtainedCar;
        } else {
            throw new UnrecognizedTicketException(UNRECOGNIZED_PARKING_TICKET);
        }
    }
}