package com.sksolutions.VaxiCore.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorUpdateResponse {
    String name;
    int age;
    String email;
    String message;
}
