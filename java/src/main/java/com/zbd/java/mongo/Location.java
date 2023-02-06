package com.zbd.java.mongo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class Location {
    private String locCity;
    private String locDistrict;
    private String locFull;
    private String locNeigh;
    private String locStreet;
    private String locZone;
}
