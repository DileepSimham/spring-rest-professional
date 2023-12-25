package com.infosys.infyride.dto;

import com.infosys.infyride.entity.RideEntity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDTO {

    @NotBlank(message = "{ride.pickuplocation.notpresent}")
    private String pickupLocation;

    @NotBlank(message = "{ride.droplocation.notpresent}")
    private String dropLocation;

    @NotNull(message = "{ride.datetime.notpresent}")
    @FutureOrPresent(message = "{ride.datetime.invalid}")
    private LocalDateTime rideDateTime;

    public static RideEntity prepareRideEntity(RideDTO rideDTO) {
        RideEntity rideEntity = new RideEntity();
        rideEntity.setPickupLocation(rideDTO.getPickupLocation());
        rideEntity.setDropLocation(rideDTO.getDropLocation());
        rideEntity.setRideDateTime(rideDTO.getRideDateTime().toString());
        rideEntity.setReasonForCancellation("NA");

        return rideEntity;
    }
}
