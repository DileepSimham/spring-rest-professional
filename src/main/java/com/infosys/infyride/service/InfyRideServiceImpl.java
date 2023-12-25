package com.infosys.infyride.service;

import com.infosys.infyride.dto.CancelBookingDTO;
import com.infosys.infyride.dto.RideDTO;
import com.infosys.infyride.entity.FareEntity;
import com.infosys.infyride.entity.RideEntity;
import com.infosys.infyride.exception.InfyRideException;
import com.infosys.infyride.repository.FareRepository;
import com.infosys.infyride.repository.RideRepository;
import com.infosys.infyride.utilities.InfyRideConstants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@PropertySource("classpath:ValidationMessages.properties")
public class InfyRideServiceImpl implements InfyRideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private FareRepository fareRepository;

    @Autowired
    private Environment environment;

    private static final String CANCELLED = "CANCELLED";


    @Override
    public String getEstimatedFare(String pickupLocation, String dropLocation) throws InfyRideException {

        FareEntity fareEntity = fareRepository.getByPickupLocationIgnoreCaseAndDropLocationIgnoreCase(pickupLocation, dropLocation);

        if (fareEntity == null) {
            throw new InfyRideException(InfyRideConstants.INFYRIDE_PICKUPTODROPLOCATION_NOT_FOUND.toString());
        }

        return environment.getProperty(InfyRideConstants.INFYRIDE_GETESTIMATED_FARE_SUCCESS.toString())+" "+
                fareEntity.getFare();
    }

    @Override
    public String bookRide(RideDTO rideDTO) throws InfyRideException {
        FareEntity fareDetails = fareRepository.getByPickupLocationIgnoreCaseAndDropLocationIgnoreCase(rideDTO.getPickupLocation(), rideDTO.getDropLocation());

        if (fareDetails == null) {
            throw new InfyRideException(InfyRideConstants.INFYRIDE_PICKUPTODROPLOCATION_NOT_FOUND.toString());
        }

        RideEntity rideEntity = RideDTO.prepareRideEntity(rideDTO);
        rideEntity.setStatus("BOOKED");
        rideEntity.setTotalFare(fareDetails.getFare());
        RideEntity savedRideEntity = rideRepository.save(rideEntity);

        return environment.getProperty(InfyRideConstants.INFYRIDE_BOOKING_SUCCESS.toString()
        )+  savedRideEntity.getTotalFare();
    }

    @Override
    @Transactional
    public String updateRide(int rideId, String newPickupLocation) throws InfyRideException {

        Optional<RideEntity> rideEntityOpt = rideRepository.findById(rideId);
        if (!rideEntityOpt.isPresent())
            throw new InfyRideException(InfyRideConstants.INFYRIDE_RIDEID_NOT_FOUND.toString());

        RideEntity rideEntity = rideEntityOpt.get();

        if (rideEntity.getStatus().equals("COMPLETED"))
            throw new InfyRideException(InfyRideConstants.INFYRIDE_UPDATE_RIDE_ALREADY_COMPLETED.toString());

        if (rideEntity.getStatus().equals(CANCELLED))
            throw new InfyRideException(InfyRideConstants.INFYRIDE_UPDATE_RIDE_ALREADY_CANCELLED.toString());

        newPickupLocation = newPickupLocation.trim();
        if (rideEntity.getPickupLocation().equalsIgnoreCase(newPickupLocation))
            throw new InfyRideException(InfyRideConstants.INFYRIDE_OLDANDNEWPICKUPLOCATION_SAME.toString());

        FareEntity fareEntity = fareRepository.getByPickupLocationIgnoreCaseAndDropLocationIgnoreCase(newPickupLocation, rideEntity.getDropLocation());


        if (fareEntity == null)
            throw new InfyRideException(InfyRideConstants.INFYRIDE_PICKUPTODROPLOCATION_NOT_FOUND.toString());

        rideEntity.setPickupLocation(fareEntity.getPickupLocation());
        rideEntity.setTotalFare(fareEntity.getFare());

        return environment.getProperty(InfyRideConstants.INFYRIDE_UPDATE_SUCCESS1.toString()) + fareEntity.getPickupLocation() + " " +
                environment.getProperty(InfyRideConstants.INFYRIDE_UPDATE_SUCCESS2.toString())+  rideEntity.getTotalFare();
    }

    @Override
    @Transactional
    public String cancelRide(int rideId, CancelBookingDTO cancelBookingDTO) throws InfyRideException {
        Optional<RideEntity> rideEntityOpt = rideRepository.findById(rideId);

        if (!rideEntityOpt.isPresent())
            throw new InfyRideException(InfyRideConstants.INFYRIDE_RIDEID_NOT_FOUND.toString());

        RideEntity rideEntity = rideEntityOpt.get();

        if (rideEntity.getStatus().equals("COMPLETED"))
            throw new InfyRideException(InfyRideConstants.INFYRIDE_CANCEL_RIDE_ALREADY_COMPLETED.toString());

        if (rideEntity.getStatus().equals(CANCELLED))
            throw new InfyRideException(InfyRideConstants.INFYRIDE_CANCEL_RIDE_ALREADY_CANCELLED.toString());

        rideEntity.setStatus(CANCELLED);
        rideEntity.setReasonForCancellation(cancelBookingDTO.getReasonForCancellation().trim());


        return environment.getProperty(InfyRideConstants.INFYRIDE_CANCEL_SUCCESS.toString());
    }
}
