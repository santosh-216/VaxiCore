package com.sksolutions.VaxiCore.dto.RequestDto;


import com.sksolutions.VaxiCore.Enum.CenterType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CenterRequestDto {
    String centerName;
    @Enumerated(EnumType.STRING)
    CenterType centerType;
    String address;
}
