package com.assignment3.consultation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private Long id;
    private String patientName;
    private String doctorName;
    private Date dateOfConsultation;
    private String description;
}
