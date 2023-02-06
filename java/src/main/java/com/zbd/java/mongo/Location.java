package com.zbd.java.mongo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class Location {
    private String loc_city;
    private String loc_district;
    private String loc_full;
    private String loc_neigh;
    private String loc_street;
    private String loc_zone;
}
