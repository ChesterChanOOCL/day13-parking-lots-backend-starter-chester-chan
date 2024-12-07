package org.afs.pakinglot.domain.Repository;

import org.afs.pakinglot.domain.ParkingLot;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ParkingLotRepository {
    private final List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingLotRepository() {
        parkingLots.add(new ParkingLot(1, "The Plaza Park", 9, Arrays.asList(
                Arrays.asList("EV-1234", "EV-5678", "EV-91011"),
                Arrays.asList("EV-1213", "", "EV-1617"),
                Arrays.asList("EV-1819", "EV-2021", "")
        )));
        parkingLots.add(new ParkingLot(2, "City Mall Garage", 12, Arrays.asList(
                Arrays.asList("EV-2425", "", "EV-2829"),
                Arrays.asList("EV-3031", "EV-3233", ""),
                Arrays.asList("EV-3637", "", "EV-4041")
        )));
        parkingLots.add(new ParkingLot(3, "Office Tower Parking", 9, Arrays.asList(
                Arrays.asList("", "EV-4445", "EV-4647"),
                Arrays.asList("EV-4849", "", "EV-5253"),
                Arrays.asList("EV-5455", "EV-5657", "")
        )));
    }

    public List<ParkingLot> getAll() {
        return parkingLots;
    }
}