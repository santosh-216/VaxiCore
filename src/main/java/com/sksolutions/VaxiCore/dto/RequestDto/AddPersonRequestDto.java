package com.sksolutions.VaxiCore.dto.RequestDto;

import com.sksolutions.VaxiCore.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddPersonRequestDto {
    String name;
    int age;
    String emailId;
    Gender gender;
}
