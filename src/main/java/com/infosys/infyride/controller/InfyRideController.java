package com.infosys.infyride.controller;

import com.infosys.infyride.dto.CancelBookingDTO;
import com.infosys.infyride.dto.RideDTO;
import com.infosys.infyride.entity.FareEntity;
import com.infosys.infyride.exception.InfyRideException;
import com.infosys.infyride.repository.FareRepository;
import com.infosys.infyride.service.InfyRideService;
import com.infosys.infyride.service.InfyRideServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Validated
public class InfyRideController {

    @Autowired
    private InfyRideServiceImpl infyRideService;

    @GetMapping("/ride/{pickupLocation}/{dropLocation}")
    public String getEstimatedFare(@PathVariable String pickupLocation, @PathVariable String dropLocation) throws InfyRideException {
        return infyRideService.getEstimatedFare(pickupLocation.trim(), dropLocation.trim());
    }


    @PostMapping("/ride")
    public String bookRide(@RequestBody @Valid RideDTO rideDTO) throws InfyRideException {
        return infyRideService.bookRide(rideDTO);
    }


    @PutMapping("/ride/{rideId}/{newPickupLocation}")
    public String updateRide(@PathVariable
                             @Positive(message = "{ride.rideid.invalid}")
                             int rideId, @PathVariable String newPickupLocation) throws InfyRideException {
       return infyRideService.updateRide(rideId,newPickupLocation);

    }

    @DeleteMapping("/ride/{rideId}")
    public String cancelRide(@PathVariable  @Positive(message = "{ride.rideid.invalid}") int rideId, @RequestBody @Valid CancelBookingDTO cancelBookingDTO) throws InfyRideException {
        return infyRideService.cancelRide(rideId,cancelBookingDTO);

    }


}
