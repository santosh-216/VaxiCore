package com.sksolutions.VaxiCore.Repository;

import com.sksolutions.VaxiCore.Enum.CenterType;
import com.sksolutions.VaxiCore.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    @Query(value = "select * from doctor where age > :age",nativeQuery = true)
    List<Doctor> getByAge(int age);

    @Query("select d from Doctor d ORDER BY SIZE(d.appointmentList) DESC")
    List<Doctor> findDoctorWithMaxAppointments();

    @Query(value = "select * from doctor where center_id = :id",nativeQuery = true)
    List<Doctor> findDoctorsByCenterId(@Param("id") int id);

    @Query("SELECT d FROM Doctor d WHERE d.center.centerType = :centerType")
    List<Doctor> getDoctorsByCenterType(@Param("centerType") CenterType centerType);
}
