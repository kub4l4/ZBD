package com.zbd.java.mongo;

import jakarta.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "houses")
public class HomeMongo {

    @Id
    private String id;

    private String ad_description;
    private String ad_last_update;
    private String house_id;
    private String price;

    @Embedded
    private HouseInformation house_information;

    @Embedded
    private Facilities facilities;

    @Embedded
    private Location location;

    @Embedded
    private Quantity quantity;
}
