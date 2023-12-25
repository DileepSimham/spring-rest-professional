package com.infosys.infyride.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Fare")
public class FareEntity {

    @Id
    @Column(name = "fareid")
    private int fareId;

    @Column(name = "pickuplocation")
    private String pickupLocation;

    @Column(name = "droplocation")
    private String dropLocation;

    @Column(name = "fare")
    private Double fare;

}
