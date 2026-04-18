package com.sksolutions.VaxiCore.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentResponse {
    String appointmentId;
    String personName;
    String doctorName;
    Date appointmentDate;
    CenterResponseDto centerResponseDto;

}
