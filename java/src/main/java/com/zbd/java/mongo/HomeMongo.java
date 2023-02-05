package com.zbd.java.mongo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeMongo {
    @Id
    private String id;
    private String ad_description;
    private String ad_last_update;
    private String air_conditioner;
    private String balcony;
    private String bath_num;
    private String built_in_wardrobe;
    private String chimney;
    private String condition ;
    private String construct_date ;
    private String energetic_certif;
    private String floor ;
    private String garage;
    private String garden;
    private String ground_size;
    private String heating ;
    private String house_id;
    private String house_type;
    private String kitchen;
    private String lift ;
    private String loc_city;
    private String loc_district ;
    private String loc_full;
    private String loc_neigh ;
    private String loc_street ;
    private String loc_zone;
    private String m2_real;
    private String m2_useful;
    private String obtention_date;
    private String orientation;
    private String price;
    private String reduced_mobility;
    private String room_num;
    private String storage_room;
    private String swimming_pool;
    private String terrace;
    private String unfurnished;

}