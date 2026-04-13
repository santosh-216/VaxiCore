package com.sksolutions.VaxiCore.Controller;

import com.sksolutions.VaxiCore.Service.DoseService;
import com.sksolutions.VaxiCore.dto.RequestDto.BookDoseRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.DoseResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

    //** Get Dose1 **//
    @PostMapping("/get-dose1")
    public ResponseEntity getDose1(@RequestBody BookDoseRequestDto bookDoseRequestDto){
        try{
            DoseResponseDto doseTake = doseService.getDose1(bookDoseRequestDto);
            return new ResponseEntity(doseTake, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //** Get Dose2 **//
    @PostMapping("/get-dose2")
    public ResponseEntity getDose2(@RequestBody BookDoseRequestDto bookDoseRequestDto){
        try{
            DoseResponseDto doseTake = doseService.getDose2(bookDoseRequestDto);
            return new ResponseEntity(doseTake, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }




}
