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
    private String ad_last_update;
    private String house_id;
    private String price;
    @OneToOne(cascade = CascadeType.ALL)
    private HouseInformation house_information;
    @OneToOne(cascade = CascadeType.ALL)
    private Facilities facilities;
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;
    @OneToOne(cascade = CascadeType.ALL)
    private Quantity quantity;

}
