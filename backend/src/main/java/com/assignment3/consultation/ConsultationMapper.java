package com.assignment3.consultation;


import com.assignment3.consultation.model.Consultation;
import com.assignment3.consultation.model.dto.ConsultationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    @Mappings({
            @Mapping(target="patientName", source = "patient.name"),
            @Mapping(target="doctorName", source = "doctor.lastName"),
            @Mapping(target = "dateOfConsultation", source = "consultation.dateOfConsultation"),
            @Mapping(target = "description", source = "consultation.description")
    })
    ConsultationDTO toDto(Consultation consultation);

    Consultation fromDto(ConsultationDTO consultation);
}
