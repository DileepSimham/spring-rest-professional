package com.infosys.infyride.service;

import com.infosys.infyride.dto.CancelBookingDTO;
import com.infosys.infyride.dto.RideDTO;
import com.infosys.infyride.exception.InfyRideException;

public interface InfyRideService {

    String getEstimatedFare(String pickupLocation, String dropLocation) throws InfyRideException;

    String bookRide(RideDTO rideDTO) throws InfyRideException;

    String updateRide(int rideId,String newPickupLocation) throws InfyRideException;

    String cancelRide(int rideId, CancelBookingDTO cancelBookingDTO) throws InfyRideException;
}
