package com.sksolutions.VaxiCore.Model;

import com.sksolutions.VaxiCore.Enum.CenterType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL)
    List<Doctor> doctors = new ArrayList<>();


}
