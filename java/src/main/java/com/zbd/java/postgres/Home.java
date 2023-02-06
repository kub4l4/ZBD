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
    @Column(columnDefinition = "TEXT")
    private String adDescription;
    private String adLastUpdate;
    private String houseId;
    private String price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "house_information_id", referencedColumnName = "id")
    private HouseInformation houseInformation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facilities_id", referencedColumnName = "id")
    private Facilities facilities;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quantity_id", referencedColumnName = "id")
    private Quantity quantity;

}
