package com.sksolutions.VaxiCore.Service;

import com.sksolutions.VaxiCore.Exception.CenterNotFoundException;
import com.sksolutions.VaxiCore.Exception.DoctorsNotFoundException;
import com.sksolutions.VaxiCore.Model.Doctor;
import com.sksolutions.VaxiCore.Model.VaccinationCenter;
import com.sksolutions.VaxiCore.Repository.DoctorRepository;
import com.sksolutions.VaxiCore.Repository.VaccinationCenterRepository;
import com.sksolutions.VaxiCore.dto.RequestDto.DoctorRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.CenterResponseDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.DoctorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/doctor")
@Service
public class DoctorService {

    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    @Autowired
    DoctorRepository doctorRepository;

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto){
        Optional<VaccinationCenter> center = vaccinationCenterRepository.findById(doctorRequestDto.getCenterId());
        if(center.isEmpty())
             throw new CenterNotFoundException("center id is invalid..!");
        Doctor doctor = new Doctor();
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setName(doctorRequestDto.getName());
        doctor.setEmailId(doctorRequestDto.getEmailId());
        doctor.setCenter(center.get());

        center.get().getDoctors().add(doctor);
        doctorRepository.save(doctor);
        vaccinationCenterRepository.save(center.get());

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterType(center.get().getCenterType());
        centerResponseDto.setCenterName(center.get().getCenterName());
        centerResponseDto.setAddress(center.get().getAddress());

        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setCenterResponseDto(centerResponseDto);
        doctorResponseDto.setName(doctor.getName());
        doctorResponseDto.setMessage("successfully added the doctor");
        return doctorResponseDto;
    }

    public List<String> getByAgeGreaterThan(int age){
        List<Doctor> doctorList = doctorRepository.getByAge(age);
        if(doctorList.isEmpty())
            throw new DoctorsNotFoundException("sorry there is no one age older than "+age);
        List<String> doctorNames = new ArrayList<>();

        for(Doctor doctor : doctorList){
            doctorNames.add(doctor.getName());
        }
        return doctorNames;
    }
}
