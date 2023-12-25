package com.infosys.infyride.repository;

import com.infosys.infyride.entity.FareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRepository extends JpaRepository<FareEntity, Integer> {

    FareEntity getByPickupLocationIgnoreCaseAndDropLocationIgnoreCase(String pickupLocation,String dropLocation);
}
