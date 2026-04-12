package com.sksolutions.VaxiCore.dto.RequestDto;

import com.sksolutions.VaxiCore.Enum.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDose1RequestDto {
    int id;
    DoseType doseType;
}
