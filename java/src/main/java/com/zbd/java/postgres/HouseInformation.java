package com.zbd.java.postgres;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "house_information")
public class HouseInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String houseType;
    private int balcony;
    private String bathNum;
    private int builtInWardrobe;
    private int chimney;
    private String floor;
    private String garage;
    private int garden;
    private int groundSize;
    private String condition;
    private int constructDate;
    private String energeticCertif;
    private String roomNum;
    private int storageRoom;
    private int swimmingPool;
    private int terrace;
    private String orientation;
    private String reducedMobility;
    private int kitchen;
    private int lift;
    private String obtentionDate;

}
