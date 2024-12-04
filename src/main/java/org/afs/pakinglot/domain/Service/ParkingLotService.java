package org.afs.pakinglot.domain.Service;

import org.afs.pakinglot.domain.Car;

import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.Repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.getAll();
    }
}