package com.sksolutions.VaxiCore.Service;

import com.sksolutions.VaxiCore.Enum.DoseType;
import com.sksolutions.VaxiCore.Exception.DoseAlreadyTakenException;
import com.sksolutions.VaxiCore.Exception.PersonNotFoundException;
import com.sksolutions.VaxiCore.Model.Dose;
import com.sksolutions.VaxiCore.Model.Person;
import com.sksolutions.VaxiCore.Repository.DoseRepository;
import com.sksolutions.VaxiCore.Repository.PersonRepository;
import com.sksolutions.VaxiCore.dto.RequestDto.BookDose1RequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.Dose1ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {
    @Autowired
    DoseRepository doseRepository;

    @Autowired
    PersonRepository personRepository;

//    public Dose getDose1(int personId, DoseType doseType) {
//
//        Optional<Person> personResponse = personRepository.findById(personId);
//
//        // check if person exists or not
//        if(!personResponse.isPresent()){
//            throw new PersonNotFoundException("Invalid PersonId");
//        }
//
//        Person person = personResponse.get();
//
//        // check if dose 1 is already taken
//        if(person.getDose1Taken()){
//            throw new DoseAlreadyTakenException("Dose1 already taken");
//        }
//
//        Dose dose = new Dose();
//        dose.setDoseId(String.valueOf(UUID.randomUUID()));
//        dose.setDoseType(doseType);
//        dose.setPerson(person);
//
//        person.setDose1Taken(true);
//        personRepository.save(person);
//
//        return doseRepository.save(dose);
//
//    }

    public Dose1ResponseDto getDose1(BookDose1RequestDto bookDose1RequestDto){
        Optional<Person> personResponse = personRepository.findById(bookDose1RequestDto.getId());
//        check if person exists or not
        if(!personResponse.isPresent()){
            throw new PersonNotFoundException("Invalid PersonId");
        }
        Person person = personResponse.get();
//         check if dose 1 is already taken
        if(person.getDose1Taken()){
            throw new DoseAlreadyTakenException("Dose1 already taken");
        }

        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose1RequestDto.getDoseType());
        dose.setPerson(person);

        person.setDose1Taken(true);
        person.getDoseTaken().add(dose);

        Person savedPerson = personRepository.save(person);

        Dose1ResponseDto dose1ResponseDto = new Dose1ResponseDto();
        dose1ResponseDto.setName(savedPerson.getName());
        dose1ResponseDto.setDoseId(dose.getDoseId());
        dose1ResponseDto.setDoseType(dose.getDoseType());
//        the below line prints null instead of date we can use new Date() but not good practice
        dose1ResponseDto.setVaccinationDate(dose.getVaccinationDate());

        return dose1ResponseDto;

    }

}
