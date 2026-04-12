package com.sksolutions.VaxiCore.Service;

import com.sksolutions.VaxiCore.Model.Person;
import com.sksolutions.VaxiCore.Repository.PersonRepository;
import com.sksolutions.VaxiCore.dto.RequestDto.AddPersonRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.AddPersonResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        // Convert Request Dto -> Enttity
        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setAge(addPersonRequestDto.getAge());
        person.setGender(addPersonRequestDto.getGender());
        person.setEmailId(addPersonRequestDto.getEmailId());

        //by default these two are false but just for understanding
        person.setDose1Taken(false);
        person.setDose2Taken(false);
        person.setCertificate(null);

        Person personResponse = personRepository.save(person);

        // saved entity -> response dto

        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(person.getName());
        addPersonResponseDto.setMessage("Congrats! You have been registered");
        return addPersonResponseDto;
    }


}
