package com.infosys.infyride.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ride")
public class RideEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rideid")
    private int rideId;

    @Column(name = "pickuplocation")
    private String pickupLocation;

    @Column(name = "dropLocation")
    private String dropLocation;

    @Column(name = "ridedatetime")
    private String rideDateTime;

    @Column(name = "totalfare")
    private Double totalFare;

    @Column(name = "status")
    private String status;

    @Column(name = "reasonforcancellation")
    private String reasonForCancellation;

}
