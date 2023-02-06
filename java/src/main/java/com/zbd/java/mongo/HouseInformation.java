package com.zbd.java.mongo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class HouseInformation {
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
