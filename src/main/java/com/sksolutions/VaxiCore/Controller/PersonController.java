package com.sksolutions.VaxiCore.Controller;

import com.sksolutions.VaxiCore.Model.Person;
import com.sksolutions.VaxiCore.Service.PersonService;
import com.sksolutions.VaxiCore.dto.RequestDto.AddPersonRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.AddPersonResponseDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // get all males of age greater than certain age
    @GetMapping("/find-males-above-age")
    public ResponseEntity getAllMalesOfAgeGreaterThanCertainAge(@RequestParam("age") int age){
        try {
            List<PersonResponse> personList = personService.getAllMalesOfAgeGreaterThanCertainAge(age);
            return new ResponseEntity(personList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //get all females who have taken only dose1
    @GetMapping("/find-females-take-dose1")
    public ResponseEntity getAllFemalesWhoHaveTakenOnlyDose1(){
        try {
            List<PersonResponse> personList = personService.getAllFemalesWhoHaveTakenOnlyDose1();
            return new ResponseEntity(personList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //get all who fully vaccinated
    @GetMapping("/fully-vaccinated")
    public ResponseEntity getFullVaccinatedPersons(){
        try {
            List<PersonResponse> personList = personService.getFullVaccinatedPersons();
            return new ResponseEntity(personList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    //get all people who are not taken even single dose
    @GetMapping("/unvaccinated")
    public ResponseEntity getunvaccinatedPersons(){
        try {
            List<PersonResponse> personList = personService.getunvaccinatedPersons();
            return new ResponseEntity(personList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    // get all females whose age is grater than particular age and who take dose1 only
    @GetMapping("females-dose1-only")
    public ResponseEntity getFemalesWithOnlyDose1AboveAge(@RequestParam("age") int age){
        try {
            List<PersonResponse> personList = personService.getFemalesWithOnlyDose1AboveAge(age);
            return new ResponseEntity(personList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get all males whose age is greater than particular age and who have taken both doses
    @GetMapping("males/fully-vaccinated")
    public ResponseEntity getMalesFullyVaccinatedAboveAge(@RequestParam("age") int age){
        try {
            List<PersonResponse> personList = personService.getMalesFullyVaccinatedAboveAge(age);
            return new ResponseEntity(personList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
