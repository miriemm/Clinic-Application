package com.assignment3.consultation;

import com.assignment3.UrlMapping;
import com.assignment3.consultation.model.dto.ConsultationDTO;
import com.assignment3.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.CONSULTATIONS)
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationDTO> allConsultations() {
        return consultationService.findAll();
    }

    @GetMapping("/findAllPastConsultations/")
    public List<ConsultationDTO> allPastConsultations() {
        return consultationService.findAllPastConsultations();
    }

    @PostMapping
    public void create(@RequestBody ConsultationDTO consultation) {
         consultationService.create(consultation);
    }

    @PatchMapping
    public ConsultationDTO edit(@RequestBody ConsultationDTO consultation) {
        return consultationService.edit(consultation);
    }

    @DeleteMapping
    public void delete(@RequestBody ConsultationDTO consultation){
        consultationService.delete(consultation.getId());
    }

    @PatchMapping("/addDetails")
    public ConsultationDTO addDetails(@RequestBody ConsultationDTO consultation) {
        return consultationService.addDetails(consultation);
    }
}
