package com.sksolutions.VaxiCore.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorUpdateRequest {
    int doctorId;
    Integer age;
    String emailId;
}
