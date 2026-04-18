package com.sksolutions.VaxiCore.Controller;

import com.sksolutions.VaxiCore.Enum.CenterType;
import com.sksolutions.VaxiCore.Model.VaccinationCenter;
import com.sksolutions.VaxiCore.Service.VaccinationCenterService;
import com.sksolutions.VaxiCore.dto.RequestDto.CenterRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.CenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/add-center")
    public ResponseEntity addCenter(@RequestBody CenterRequestDto centerRequestDto){
        CenterResponseDto centerResponce = vaccinationCenterService.addCenter(centerRequestDto);
        return new ResponseEntity(centerResponce, HttpStatus.CREATED);
    }

    // get the center with the highest number of doctors
    @GetMapping("/highest-no-of-doctors")
    public ResponseEntity getCenterWithMaxDoctors(){
        try {
            CenterResponseDto centerResponseDto = vaccinationCenterService.getCenterWithMaxDoctors();
            return new ResponseEntity(centerResponseDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    // get the center with the highest number of doctors among a particular centerType
    @GetMapping("/top/{centerType}")
    public ResponseEntity getTopCenterByType(@PathVariable CenterType centerType){
        try {
            CenterResponseDto centerResponseDto = vaccinationCenterService.getTopCenterByType(centerType);
            return new ResponseEntity(centerResponseDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
