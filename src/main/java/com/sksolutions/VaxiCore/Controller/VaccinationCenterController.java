package com.sksolutions.VaxiCore.Controller;

import com.sksolutions.VaxiCore.Service.VaccinationCenterService;
import com.sksolutions.VaxiCore.dto.RequestDto.CenterRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.CenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
