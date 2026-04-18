package com.sksolutions.VaxiCore.Service;

import com.sksolutions.VaxiCore.Enum.CenterType;
import com.sksolutions.VaxiCore.Exception.CenterNotFoundException;
import com.sksolutions.VaxiCore.Model.VaccinationCenter;
import com.sksolutions.VaxiCore.Repository.VaccinationCenterRepository;
import com.sksolutions.VaxiCore.dto.RequestDto.CenterRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.CenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CenterResponseDto getCenterWithMaxDoctors() {
        List<VaccinationCenter> vaccinationCenters = vaccinationCenterRepository.getCenterWithMaxDoctors();
        if(vaccinationCenters.isEmpty()){
            throw  new CenterNotFoundException("No vaccination centers found");
        }
        VaccinationCenter vaccinationCenter = vaccinationCenters.getFirst();

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setAddress(vaccinationCenter.getAddress());
        centerResponseDto.setCenterName(vaccinationCenter.getCenterName());
        centerResponseDto.setCenterType(vaccinationCenter.getCenterType());
        return centerResponseDto;
    }

    public CenterResponseDto getTopCenterByType(CenterType centerType) {
        List<VaccinationCenter> vaccinationCenterList = vaccinationCenterRepository.getTopCenterByType(centerType);
        if(vaccinationCenterList.isEmpty()){
            throw new CenterNotFoundException("No centers found for given center type");
        }
        VaccinationCenter vaccinationCenter = vaccinationCenterList.getFirst();

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterType(vaccinationCenter.getCenterType());
        centerResponseDto.setCenterName(vaccinationCenter.getCenterName());
        centerResponseDto.setAddress(vaccinationCenter.getAddress());

        return centerResponseDto;
    }
}
