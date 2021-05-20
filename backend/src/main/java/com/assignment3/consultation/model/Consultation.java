package com.assignment3.consultation.model;

import com.assignment3.patient.model.Patient;
import com.assignment3.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Consultation {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name="doctor_id")
    private User doctor;

    @Temporal(TemporalType.DATE)
    @Column
    private Date dateOfConsultation;

    @Column(length = 512, nullable = false)
    private String description;

    private String getPatientName(){
        return patient.getName();

    }

    private String getDoctorName(){
        return doctor.getLastName();
    }

}
