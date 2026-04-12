package com.sksolutions.VaxiCore.dto.ResponseDto;

import com.sksolutions.VaxiCore.Enum.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Dose1ResponseDto {
    String name;
    String doseId;
    DoseType doseType;
    Date vaccinationDate;
}
