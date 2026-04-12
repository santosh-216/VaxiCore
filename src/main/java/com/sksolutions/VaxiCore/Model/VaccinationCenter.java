package com.sksolutions.VaxiCore.Model;

import com.sksolutions.VaxiCore.Enum.CenterType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String centerName;
    @Enumerated(EnumType.STRING)
    CenterType centerType;
    String address;
}
