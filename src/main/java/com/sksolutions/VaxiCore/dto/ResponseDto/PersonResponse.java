package com.sksolutions.VaxiCore.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PersonResponse {
    String name;
    int age;
    String emailId;
}
