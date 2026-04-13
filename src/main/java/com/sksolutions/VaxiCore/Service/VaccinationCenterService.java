package com.sksolutions.VaxiCore.Service;

import com.sksolutions.VaxiCore.Model.VaccinationCenter;
import com.sksolutions.VaxiCore.Repository.VaccinationCenterRepository;
import com.sksolutions.VaxiCore.dto.RequestDto.CenterRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.CenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {
        VaccinationCenter vaccinationCenter = new VaccinationCenter();
        vaccinationCenter.setCenterName(centerRequestDto.getCenterName());
        vaccinationCenter.setCenterType(centerRequestDto.getCenterType());
        vaccinationCenter.setAddress(centerRequestDto.getAddress());
        VaccinationCenter vaccinationCenterResponse =  vaccinationCenterRepository.save(vaccinationCenter);

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setAddress(vaccinationCenterResponse.getAddress());
        centerResponseDto.setCenterType(vaccinationCenterResponse.getCenterType());
        centerResponseDto.setCenterName(vaccinationCenterResponse.getCenterName());
        return centerResponseDto;

    }
}
