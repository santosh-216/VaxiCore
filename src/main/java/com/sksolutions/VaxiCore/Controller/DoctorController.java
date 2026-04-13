package com.sksolutions.VaxiCore.Controller;

import com.sksolutions.VaxiCore.Service.DoctorService;
import com.sksolutions.VaxiCore.dto.RequestDto.DoctorRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.DoctorResponseDto;
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
}
