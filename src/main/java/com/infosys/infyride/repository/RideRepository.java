package com.infosys.infyride.repository;

import com.infosys.infyride.entity.RideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<RideEntity,Integer> {
}
