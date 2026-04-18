package com.sksolutions.VaxiCore.Service;

import com.sksolutions.VaxiCore.Exception.DoseAlreadyTakenException;
import com.sksolutions.VaxiCore.Exception.PersonNotFoundException;
import com.sksolutions.VaxiCore.Model.Dose;
import com.sksolutions.VaxiCore.Model.Person;
import com.sksolutions.VaxiCore.Repository.DoseRepository;
import com.sksolutions.VaxiCore.Repository.PersonRepository;
import com.sksolutions.VaxiCore.dto.RequestDto.BookDoseRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.DoseResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public DoseResponseDto getDose1(BookDoseRequestDto bookDoseRequestDto){
        Optional<Person> personResponse = personRepository.findById(bookDoseRequestDto.getId());
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
        dose.setDoseType(bookDoseRequestDto.getDoseType());
        dose.setPerson(person);
        Dose addedDose = doseRepository.save(dose);

        person.setDose1Taken(true);
        person.getDoseTaken().add(addedDose);

        Person savedPerson = personRepository.save(person);

        DoseResponseDto dose1ResponseDto = new DoseResponseDto();
        dose1ResponseDto.setName(savedPerson.getName());
        dose1ResponseDto.setDoseId(addedDose.getDoseId());
        dose1ResponseDto.setDoseType(addedDose.getDoseType());
        dose1ResponseDto.setVaccinationDate(addedDose.getVaccinationDate());

        return dose1ResponseDto;

    }


    public DoseResponseDto getDose2(BookDoseRequestDto bookDoseRequestDto){
        Optional<Person> personResponse = personRepository.findById(bookDoseRequestDto.getId());
//        check if person exists or not
        if(personResponse.isEmpty()){
            throw new PersonNotFoundException("Invalid PersonId");
        }
        Person person = personResponse.get();
//         check if dose 2 is already taken
        if(person.getDose2Taken()){
            throw new DoseAlreadyTakenException("Dose2 already taken");
        }

        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDoseRequestDto.getDoseType());
        dose.setPerson(person);
        Dose addedDose = doseRepository.save(dose);

        person.setDose2Taken(true);
        person.getDoseTaken().add(addedDose);

        Person savedPerson = personRepository.save(person);

        DoseResponseDto dose2ResponseDto = new DoseResponseDto();
        dose2ResponseDto.setName(savedPerson.getName());
        dose2ResponseDto.setDoseId(addedDose.getDoseId());
        dose2ResponseDto.setDoseType(addedDose.getDoseType());
        dose2ResponseDto.setVaccinationDate(addedDose.getVaccinationDate());

        return dose2ResponseDto;

    }

}
