package com.sksolutions.VaxiCore.Controller;

import com.sksolutions.VaxiCore.Service.AppointmentService;
import com.sksolutions.VaxiCore.dto.RequestDto.BookAppointmentRequest;
import com.sksolutions.VaxiCore.dto.ResponseDto.AppointmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody BookAppointmentRequest bookAppointmentRequest){
        try {
            AppointmentResponse appointmentResponse = appointmentService.bookAppointment(bookAppointmentRequest);
            return new ResponseEntity(appointmentResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/appointments-of-doctor")
    public ResponseEntity getAppointments(@RequestParam("doctorId") int doctorId){
        try{
            List<AppointmentResponse> appointmentResponseList = appointmentService.getAppointments(doctorId);
            return new ResponseEntity(appointmentResponseList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
