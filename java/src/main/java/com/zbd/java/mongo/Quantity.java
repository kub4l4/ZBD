package com.zbd.java.mongo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class Quantity {
    private String m2_real;
    private String m2_useful;
}

