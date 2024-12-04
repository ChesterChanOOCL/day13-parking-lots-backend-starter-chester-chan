package org.afs.pakinglot.domain.Controller;

import org.afs.pakinglot.domain.Car;

import org.afs.pakinglot.domain.ParkingBoy;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.Service.ParkingLotService;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    private ParkingBoy StandParkingBoy;
    private ParkingBoy SmartParkingBoy;
    private ParkingBoy SuperSmartParkingBoy;
    
    private String[] parkingBoysOptions = {"Standard", "Smart", "SuperSmart"};


    
    
    private ParkingLotController(ParkingLotService parkingLotService){
        this.parkingLotService = parkingLotService;
        List<ParkingLot> listofParkingLots = parkingLotService.getAllParkingLots();
        StandParkingBoy = new ParkingBoy(listofParkingLots, new SequentiallyStrategy());
        SmartParkingBoy = new ParkingBoy(listofParkingLots, new MaxAvailableStrategy());
        SuperSmartParkingBoy = new ParkingBoy(listofParkingLots, new AvailableRateStrategy());
    }

    @GetMapping("/parking-lots")
    public List<ParkingLot> getAll() {
        return parkingLotService.getAllParkingLots();
    }

    @PostMapping("/park/{plateNumber}/{parkingBoy}")
    public ResponseEntity<Ticket> park(@PathVariable String plateNumber, @PathVariable String parkingBoy) {

        System.out.println(parkingBoy);
        if (!Arrays.asList(parkingBoysOptions).contains(parkingBoy)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        for (ParkingLot parkingLot : parkingLotService.getAllParkingLots()) {
            for (Ticket t : parkingLot.getTickets()) {
                if (t.plateNumber().equals(plateNumber)) {
                    throw new IllegalArgumentException("Car is already parked");
                }
            }
        }
        switch (parkingBoy) {
            case "Standard":
                return ResponseEntity.ok(StandParkingBoy.park(new Car(plateNumber)));
            case "Smart":
                return ResponseEntity.ok(SmartParkingBoy.park(new Car(plateNumber)));
            case "SuperSmart":
                return ResponseEntity.ok(SuperSmartParkingBoy.park(new Car(plateNumber)));
        }
        return null;
    }
    //write the fetch function
    @GetMapping("/fetch/{plateNumber}")
    public ResponseEntity<Car> fetch(@PathVariable String plateNumber) {
        for (ParkingLot parkingLot : parkingLotService.getAllParkingLots()) {
            for (Ticket t : parkingLot.getTickets()) {
                if (t.plateNumber().equals(plateNumber)) {
                    return ResponseEntity.ok(parkingLot.fetch(t));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}