package com.zbd.java.mongo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class HouseInformation {
    private String house_type;
    private String balcony;
    private String bath_num;
    private String built_in_wardrobe;
    private String chimney;
    private String floor;
    private String garage;
    private String garden;
    private String ground_size;
    private String condition;
    private String construct_date;
    private String energetic_certif;
    private String room_num;
    private String storage_room;
    private String swimming_pool;
    private String terrace;
    private String orientation;
    private String reduced_mobility;
    private String kitchen;
    private String lift;
    private Date obtention_date;
}
