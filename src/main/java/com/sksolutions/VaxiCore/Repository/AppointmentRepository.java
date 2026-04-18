package com.sksolutions.VaxiCore.Repository;

import com.sksolutions.VaxiCore.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
