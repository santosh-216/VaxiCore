package com.sksolutions.VaxiCore.Service;

import com.sksolutions.VaxiCore.Exception.DoctorNotFoundException;
import com.sksolutions.VaxiCore.Exception.PersonNotFoundException;
import com.sksolutions.VaxiCore.Model.Appointment;
import com.sksolutions.VaxiCore.Model.Doctor;
import com.sksolutions.VaxiCore.Model.Person;
import com.sksolutions.VaxiCore.Model.VaccinationCenter;
import com.sksolutions.VaxiCore.Repository.AppointmentRepository;
import com.sksolutions.VaxiCore.Repository.DoctorRepository;
import com.sksolutions.VaxiCore.Repository.PersonRepository;
import com.sksolutions.VaxiCore.dto.RequestDto.BookAppointmentRequest;
import com.sksolutions.VaxiCore.dto.ResponseDto.AppointmentResponse;
import com.sksolutions.VaxiCore.dto.ResponseDto.CenterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public AppointmentResponse bookAppointment(BookAppointmentRequest bookAppointmentRequest) {
        Optional<Person> optionalPerson = personRepository.findById(bookAppointmentRequest.getPersonId());
        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException("Invalid person Id..!");
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(bookAppointmentRequest.getDoctorId());
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Invalid doctor Id..!");
        }

        Person person = optionalPerson.get();
        Doctor doctor = optionalDoctor.get();

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setPerson(person);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        person.getAppointmentList().add(savedAppointment);
        doctor.getAppointmentList().add(savedAppointment);

        Doctor savedDoctor = doctorRepository.save(doctor);
        Person savedPerson = personRepository.save(person);
        VaccinationCenter vaccinationCenter = savedDoctor.getCenter();

        // send an email
        String text = "Congrats!! "+savedPerson.getName()+" Your appointment has been booked with Doctor "+
                savedDoctor.getName() + ". Your vaccination center name is: " + savedDoctor.getCenter().getCenterName() + " Please reach at this address "+
                savedDoctor.getCenter().getAddress() + " at this time: " + savedAppointment.getAppointmentDate()+" Dhanyawad!!!";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("");
        simpleMailMessage.setTo(savedPerson.getEmailId());
        simpleMailMessage.setSubject("Congrats!! Appointment Done!!");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);


        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setAddress(vaccinationCenter.getAddress());
        centerResponseDto.setCenterType(vaccinationCenter.getCenterType());
        centerResponseDto.setCenterName(vaccinationCenter.getCenterName());

        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setAppointmentDate(savedAppointment.getAppointmentDate());
        appointmentResponse.setAppointmentId(savedAppointment.getAppointmentId());
        appointmentResponse.setDoctorName(savedDoctor.getName());
        appointmentResponse.setPersonName(savedPerson.getName());
        appointmentResponse.setCenterResponseDto(centerResponseDto);

        return appointmentResponse;

    }

    public  List<AppointmentResponse> getAppointments(int doctorId) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Invalid Doctor Id...!");
        }

        Doctor doctor = optionalDoctor.get();
        List<Appointment> appointmentList = doctor.getAppointmentList();
        List<AppointmentResponse> appointmentResponseList = new ArrayList<>();
        for(Appointment appointment : appointmentList){
            AppointmentResponse appointmentResponse = new AppointmentResponse();
            appointmentResponse.setAppointmentDate(appointment.getAppointmentDate());
            appointmentResponse.setAppointmentId(appointment.getAppointmentId());
            appointmentResponse.setPersonName(appointment.getPerson().getName());
            appointmentResponse.setDoctorName(appointment.getDoctor().getName());
            CenterResponseDto centerResponseDto = new CenterResponseDto();
            centerResponseDto.setCenterName(appointment.getDoctor().getCenter().getCenterName());
            centerResponseDto.setAddress(appointment.getDoctor().getCenter().getAddress());
            centerResponseDto.setCenterType(appointment.getDoctor().getCenter().getCenterType());
            appointmentResponse.setCenterResponseDto(centerResponseDto);

            appointmentResponseList.add(appointmentResponse);
        }
        return appointmentResponseList;
    }
}
