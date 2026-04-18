package com.sksolutions.VaxiCore.Repository;

import com.sksolutions.VaxiCore.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findByEmailId(String oldEmail);

    @Query(value = "select * from person where age > :age AND gender = 'MALE'",nativeQuery = true)
    List<Person> findMalesAboveAge(@Param("age") int age);

    @Query(value = "select * from person where gender = 'FEMALE' AND dose1_taken = true AND dose2_taken = false",nativeQuery = true)
    List<Person> findFemalesTakeOnlyDose1();

    @Query(value = "select * from person where dose1_taken = true AND dose2_taken = true",nativeQuery = true)
    List<Person> findFullVaccinatedPersons();

    @Query(value = "select * from person where dose1_taken = false AND dose2_taken = false",nativeQuery = true)
    List<Person> findUnvaccinatedPersons();

    @Query(value = "select * from person where gender = 'FEMALE' AND dose1_taken = true AND dose2_taken = false AND age > :age",nativeQuery = true)
    List<Person> findFemalesTakeOnlyDose1GreaterByAge(@Param("age") int age);

    @Query(value = "select * from person where gender = 'MALE' AND dose1_taken = true AND dose2_taken = true AND age > :age",nativeQuery = true)
    List<Person> findMalesFullyVaccinatedAboveAge(@Param("age") int age);

}
