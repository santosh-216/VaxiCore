package com.sksolutions.VaxiCore.Controller;

import com.sksolutions.VaxiCore.Service.PersonService;
import com.sksolutions.VaxiCore.dto.RequestDto.AddPersonRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.AddPersonResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/person")
@RestController
public class PersonController {
    @Autowired
    PersonService personService;

    //** AddPerson **//
    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto){
        try{
            AddPersonResponseDto personResponse = personService.addPerson(addPersonRequestDto);
            return new ResponseEntity(personResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Email already Existed",HttpStatus.BAD_REQUEST);
        }
    }

    //** update email **//
    @PutMapping("/updateEmail")
    public ResponseEntity updateEmail(@RequestParam("oldEmail") String oldEmail,
                                      @RequestParam("newEmail") String newEmail){
        try{
            String updateResponse = personService.updateEMail(oldEmail,newEmail);
            return new ResponseEntity(updateResponse,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
