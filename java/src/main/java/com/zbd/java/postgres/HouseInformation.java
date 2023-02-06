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
    private String obtention_date;

}
