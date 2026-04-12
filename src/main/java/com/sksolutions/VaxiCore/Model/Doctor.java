package com.sksolutions.VaxiCore.Model;


import com.sksolutions.VaxiCore.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    int age;
    @Column(unique = true,nullable = false)
    String emailId;
    @Enumerated(EnumType.STRING)
    Gender gender;


}
