package org.afs.pakinglot.controller;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/park")
    public String parkCar(@RequestBody Car car) {
        return parkingService.parkCar(car);
    }

    @GetMapping("/fetch/{ticket}")
    public Car fetchCar(@PathVariable String ticket) {
        return parkingService.fetchCar(ticket);
    }
}