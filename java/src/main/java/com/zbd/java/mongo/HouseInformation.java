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
