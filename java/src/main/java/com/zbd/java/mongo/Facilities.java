package com.zbd.java.mongo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class Facilities {
    private String airConditioner;
    private String heating;
    private String unfurnished;
}
