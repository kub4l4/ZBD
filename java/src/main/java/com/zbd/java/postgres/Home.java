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
@Table(name = "houses")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition="TEXT")
    private String ad_description;
    @Column(columnDefinition="TEXT")
    private String ad_last_update;
    @Column(columnDefinition="TEXT")
    private String air_conditioner;
    @Column(columnDefinition="TEXT")
    private String balcony;
    @Column(columnDefinition="TEXT")
    private String bath_num;
    @Column(columnDefinition="TEXT")
    private String built_in_wardrobe;
    @Column(columnDefinition="TEXT")
    private String chimney;
    @Column(columnDefinition="TEXT")
    private String condition ;
    @Column(columnDefinition="TEXT")
    private String construct_date ;
    @Column(columnDefinition="TEXT")
    private String energetic_certif;
    @Column(columnDefinition="TEXT")
    private String floor ;
    @Column(columnDefinition="TEXT")
    private String garage;
    @Column(columnDefinition="TEXT")
    private String garden;
    @Column(columnDefinition="TEXT")
    private String ground_size;
    @Column(columnDefinition="TEXT")
    private String heating ;
    @Column(columnDefinition="TEXT")
    private String house_id;
    @Column(columnDefinition="TEXT")
    private String house_type;
    @Column(columnDefinition="TEXT")
    private String kitchen;
    @Column(columnDefinition="TEXT")
    private String lift ;
    @Column(columnDefinition="TEXT")
    private String loc_city;
    @Column(columnDefinition="TEXT")
    private String loc_district ;
    @Column(columnDefinition="TEXT")
    private String loc_full;
    @Column(columnDefinition="TEXT")
    private String loc_neigh ;
    @Column(columnDefinition="TEXT")
    private String loc_street ;
    @Column(columnDefinition="TEXT")
    private String loc_zone;
    @Column(columnDefinition="TEXT")
    private String m2_real;
    @Column(columnDefinition="TEXT")
    private String m2_useful;
    @Column(columnDefinition="TEXT")
    private String obtention_date;
    @Column(columnDefinition="TEXT")
    private String orientation;
    @Column(columnDefinition="TEXT")
    private String price;
    @Column(columnDefinition="TEXT")
    private String reduced_mobility;
    @Column(columnDefinition="TEXT")
    private String room_num;
    @Column(columnDefinition="TEXT")
    private String storage_room;
    @Column(columnDefinition="TEXT")
    private String swimming_pool;
    @Column(columnDefinition="TEXT")
    private String terrace;
    @Column(columnDefinition="TEXT")
    private String unfurnished;

}