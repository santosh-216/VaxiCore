package com.sksolutions.VaxiCore.Controller;

import com.sksolutions.VaxiCore.Enum.CenterType;
import com.sksolutions.VaxiCore.Model.Doctor;
import com.sksolutions.VaxiCore.Service.DoctorService;
import com.sksolutions.VaxiCore.dto.RequestDto.DoctorRequestDto;
import com.sksolutions.VaxiCore.dto.RequestDto.DoctorUpdateRequest;
import com.sksolutions.VaxiCore.dto.ResponseDto.DoctorResponse;
import com.sksolutions.VaxiCore.dto.ResponseDto.DoctorResponseDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.DoctorUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @PostMapping("/add-doctor")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        try{
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctorResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-by-age-greater-than")
    public ResponseEntity getByAgeGreaterThan(@RequestParam("age") int age){
        try{
            List<String> doctorsList = doctorService.getByAgeGreaterThan(age);
            return new ResponseEntity(doctorsList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get the doctor with the highest number of appointments

    @GetMapping("/most-booked")
    public ResponseEntity getDoctorWithMostAppointments(){
        try{
            DoctorResponse doctor = doctorService.getDoctorWithMostAppointments();
            return new ResponseEntity(doctor,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // get the list of doctors who belong to a particular center
    @GetMapping("/by-center")
    public ResponseEntity getDoctorsByCenter(@RequestParam("centerId") int id ){
        try{
            List<DoctorResponse> doctorList = doctorService.getDoctorsByCenter(id);
            return new ResponseEntity(doctorList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    // api to update email and/or age of a doctor
    @PutMapping("/update")
    public ResponseEntity updateDoctor(@RequestBody DoctorUpdateRequest doctorUpdateRequest){
        try {
            DoctorUpdateResponse doctorUpdateResponse = doctorService.updateDoctor(doctorUpdateRequest);
            return new ResponseEntity(doctorUpdateResponse,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    // get all the doctors at centers of a particular centerType
    @GetMapping("/all-doctors/{centerType}")
    public ResponseEntity getDoctorsByCenterType(@PathVariable CenterType centerType){
        try{
            List<DoctorResponse> doctorList = doctorService.getDoctorsByCenterType(centerType);
            return new ResponseEntity(doctorList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
