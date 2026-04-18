package com.sksolutions.VaxiCore.Repository;

import com.sksolutions.VaxiCore.Enum.CenterType;
import com.sksolutions.VaxiCore.Model.Doctor;
import com.sksolutions.VaxiCore.Model.VaccinationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter,Integer> {

    @Query("select vc from VaccinationCenter vc ORDER BY SIZE(vc.doctors) DESC")
    List<VaccinationCenter> getCenterWithMaxDoctors();

    @Query("select vc from VaccinationCenter vc where vc.centerType = :centerType ORDER BY SIZE(vc.doctors) DESC")
    List<VaccinationCenter> getTopCenterByType(@Param("centerType") CenterType centerType);

}
