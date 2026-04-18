package com.sksolutions.VaxiCore.dto.ResponseDto;

import com.sksolutions.VaxiCore.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorResponse {
    String name;
    int age;
    Gender gender;
    String emailId;
}
