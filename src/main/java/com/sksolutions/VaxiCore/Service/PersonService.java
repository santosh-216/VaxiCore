package com.sksolutions.VaxiCore.Service;

import com.sksolutions.VaxiCore.Enum.Gender;
import com.sksolutions.VaxiCore.Exception.PersonNotFoundException;
import com.sksolutions.VaxiCore.Model.Person;
import com.sksolutions.VaxiCore.Repository.PersonRepository;
import com.sksolutions.VaxiCore.dto.RequestDto.AddPersonRequestDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.AddPersonResponseDto;
import com.sksolutions.VaxiCore.dto.ResponseDto.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public String updateEMail(String oldEmail, String newEmail) {
        Person person = personRepository.findByEmailId(oldEmail);
        if(person == null){
            throw new PersonNotFoundException("Sorry email doesn't exist");
        }
        person.setEmailId(newEmail);
        personRepository.save(person);
        return "Congrats!! Your email has been updated successfully";
    }

    public List<PersonResponse> getAllMalesOfAgeGreaterThanCertainAge(int age){
        List<Person> personList = personRepository.findMalesAboveAge(age);
        if(personList.isEmpty()){
            throw new PersonNotFoundException("No one older than "+age);
        }
        List<PersonResponse> personResponseList = new ArrayList<>();
        for(Person person: personList){
            PersonResponse personResponse = new PersonResponse();
            personResponse.setAge(person.getAge());
            personResponse.setName(person.getName());
            personResponse.setEmailId(person.getEmailId());

            personResponseList.add(personResponse);
        }
        return personResponseList;
    }

    public List<PersonResponse> getAllFemalesWhoHaveTakenOnlyDose1(){
        List<Person> personList = personRepository.findFemalesTakeOnlyDose1();
        if(personList.isEmpty()){
            throw new PersonNotFoundException("No females have received at least one dose yet");
        }
        List<PersonResponse> personResponseList = new ArrayList<>();
        for(Person person: personList){
            PersonResponse personResponse = new PersonResponse();
            personResponse.setAge(person.getAge());
            personResponse.setName(person.getName());
            personResponse.setEmailId(person.getEmailId());

            personResponseList.add(personResponse);
        }
        return personResponseList;
    }

    public List<PersonResponse> getFullVaccinatedPersons() {
        List<Person> personList = personRepository.findFullVaccinatedPersons();
        if(personList.isEmpty()){
            throw new PersonNotFoundException("No one have received at least one dose yet");
        }
        List<PersonResponse> personResponseList = new ArrayList<>();
        for(Person person: personList){
            PersonResponse personResponse = new PersonResponse();
            personResponse.setAge(person.getAge());
            personResponse.setName(person.getName());
            personResponse.setEmailId(person.getEmailId());

            personResponseList.add(personResponse);
        }
        return personResponseList;
    }

    public List<PersonResponse> getunvaccinatedPersons(){
        List<Person> personList = personRepository.findUnvaccinatedPersons();
        if(personList.isEmpty()){
            throw new PersonNotFoundException("No unvaccinated individuals found");
        }
        List<PersonResponse> personResponseList = new ArrayList<>();
        for(Person person: personList){
            PersonResponse personResponse = new PersonResponse();
            personResponse.setAge(person.getAge());
            personResponse.setName(person.getName());
            personResponse.setEmailId(person.getEmailId());

            personResponseList.add(personResponse);
        }
        return personResponseList;
    }

    public List<PersonResponse> getFemalesWithOnlyDose1AboveAge(int age) {
        List<Person> personList = personRepository.findFemalesTakeOnlyDose1GreaterByAge(age);
        if(personList.isEmpty()){
            throw new PersonNotFoundException("No female individuals found above the specified age who have received only the first dose");
        }
        List<PersonResponse> personResponseList = new ArrayList<>();
        for(Person person: personList){
            PersonResponse personResponse = new PersonResponse();
            personResponse.setAge(person.getAge());
            personResponse.setName(person.getName());
            personResponse.setEmailId(person.getEmailId());

            personResponseList.add(personResponse);
        }
        return personResponseList;
    }


    public List<PersonResponse> getMalesFullyVaccinatedAboveAge(int age) {
        List<Person> personList = personRepository.findMalesFullyVaccinatedAboveAge(age);
        if(personList.isEmpty()){
            throw new PersonNotFoundException("No male individuals found above the specified age who are fully vaccinated");
        }
        List<PersonResponse> personResponseList = new ArrayList<>();
        for(Person person: personList){
            PersonResponse personResponse = new PersonResponse();
            personResponse.setAge(person.getAge());
            personResponse.setName(person.getName());
            personResponse.setEmailId(person.getEmailId());

            personResponseList.add(personResponse);
        }
        return personResponseList;
    }
}
