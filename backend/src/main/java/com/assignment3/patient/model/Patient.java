package com.assignment3.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String name;

    @Column
    private Long identityCardNr;

    @Column
    private Long personalNumericCode;

    @Temporal(TemporalType.DATE)
    @Column
    private Date dateOfBirth;

    @Column(length = 512, nullable = false)
    private String address;

}
