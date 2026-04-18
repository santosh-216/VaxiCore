package com.sksolutions.VaxiCore.Service;

import com.sksolutions.VaxiCore.Enum.CenterType;
import com.sksolutions.VaxiCore.Exception.CenterNotFoundException;
import com.sksolutions.VaxiCore.Exception.DoctorNotFoundException;
import com.sksolutions.VaxiCore.Model.Doctor;
import com.sksolutions.VaxiCore.Model.VaccinationCenter;
import com.sksolutions.VaxiCore.Repository.DoctorRepository;
import com.sksolutions.VaxiCore.Repository.VaccinationCenterRepository;
import com.sksolutions.VaxiCore.dto.RequestDto.DoctorRequestDto;
import com.sksolutions.VaxiCore.dto.RequestDto.DoctorUpdateRequest;
import com.sksolutions.VaxiCore.dto.ResponseDto.CenterResponseDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.DoctorResponse;
import com.sksolutions.VaxiCore.dto.ResponseDto.DoctorResponseDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.DoctorUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
            throw new DoctorNotFoundException("sorry there is no one age older than "+age);
        List<String> doctorNames = new ArrayList<>();

        for(Doctor doctor : doctorList){
            doctorNames.add(doctor.getName());
        }
        return doctorNames;
    }

    public DoctorResponse getDoctorWithMostAppointments() {
        List<Doctor> doctorResponse = doctorRepository.findDoctorWithMaxAppointments();
        if(doctorResponse.isEmpty()){
            throw new DoctorNotFoundException("No doctors found with appointment data");
        }
        Doctor doctor = doctorResponse.getFirst();
        DoctorResponse doctorResponse1 = new DoctorResponse();
        doctorResponse1.setAge(doctor.getAge());
        doctorResponse1.setName(doctor.getName());
        doctorResponse1.setGender(doctor.getGender());
        doctorResponse1.setEmailId(doctor.getEmailId());

        return doctorResponse1;
    }

    public List<DoctorResponse> getDoctorsByCenter(int id) {
        List<Doctor> doctorList = doctorRepository.findDoctorsByCenterId(id);
        if(doctorList.isEmpty()){
            throw new DoctorNotFoundException("No doctors found for the given center");
        }

        List<DoctorResponse> doctors = new ArrayList<>();
        for(Doctor doctor : doctorList) {
            DoctorResponse doctorResponse = new DoctorResponse();
            doctorResponse.setAge(doctor.getAge());
            doctorResponse.setName(doctor.getName());
            doctorResponse.setGender(doctor.getGender());
            doctorResponse.setEmailId(doctor.getEmailId());

            doctors.add(doctorResponse);
        }
        return doctors;
    }


    public List<DoctorResponse> getDoctorsByCenterType(CenterType centerType) {

        List<Doctor> doctorList = doctorRepository.getDoctorsByCenterType(centerType);
        if(doctorList.isEmpty()){
            throw new DoctorNotFoundException("No doctors found for given center type");
        }
        List<DoctorResponse> doctors = new ArrayList<>();
        for(Doctor doctor : doctorList) {
            DoctorResponse doctorResponse = new DoctorResponse();
            doctorResponse.setAge(doctor.getAge());
            doctorResponse.setName(doctor.getName());
            doctorResponse.setGender(doctor.getGender());
            doctorResponse.setEmailId(doctor.getEmailId());

            doctors.add(doctorResponse);
        }
        return doctors;
    }

    public DoctorUpdateResponse updateDoctor(DoctorUpdateRequest doctorUpdateRequest) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorUpdateRequest.getDoctorId());
        if (optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Invalid Doctor Id..!");
        }

        Doctor doctor = optionalDoctor.get();
        if(doctorUpdateRequest.getAge()!=null){
            doctor.setAge(doctorUpdateRequest.getAge());
        }
        if(doctorUpdateRequest.getEmailId()!=null){
            doctor.setEmailId(doctorUpdateRequest.getEmailId());
        }

        Doctor savedDoctor = doctorRepository.save(doctor);
        DoctorUpdateResponse doctorUpdateResponse = new DoctorUpdateResponse();
        doctorUpdateResponse.setAge(savedDoctor.getAge());
        doctorUpdateResponse.setName(savedDoctor.getName());
        doctorUpdateResponse.setEmail(savedDoctor.getEmailId());
        doctorUpdateResponse.setMessage("successfully updated...!");

        return doctorUpdateResponse;
    }
}
