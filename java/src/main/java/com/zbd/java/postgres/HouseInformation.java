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
@Table(name = "houseInformation")
public class HouseInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String houseType;
    private String balcony;
    private String bathNum;
    private String builtInWardrobe;
    private String chimney;
    private String floor;
    private String garage;
    private String garden;
    private String groundSize;
    private String condition;
    private String constructDate;
    private String energeticCertif;
    private String roomNum;
    private String storageRoom;
    private String swimmingPool;
    private String terrace;
    private String orientation;
    private String reducedMobility;
    private String kitchen;
    private String lift;
    private String obtentionDate;

}
