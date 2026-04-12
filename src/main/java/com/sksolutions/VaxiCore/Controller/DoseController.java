package com.sksolutions.VaxiCore.Controller;

import com.sksolutions.VaxiCore.Enum.DoseType;
import com.sksolutions.VaxiCore.Model.Dose;
import com.sksolutions.VaxiCore.Service.DoseService;
import com.sksolutions.VaxiCore.dto.RequestDto.BookDose1RequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.Dose1ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

    @PostMapping("/get-dose1")
    public ResponseEntity getDose1(@RequestBody BookDose1RequestDto bookDose1RequestDto){
        try{
            Dose1ResponseDto doseTake = doseService.getDose1(bookDose1RequestDto);
            return new ResponseEntity(doseTake, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
