package com.assignment3.patient;

import com.assignment3.UrlMapping;
import com.assignment3.consultation.model.dto.ConsultationDTO;
import com.assignment3.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.PATIENTS)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> allPatients() {
        return patientService.findAll();
    }

    @PostMapping
    public PatientDTO create(@RequestBody PatientDTO patient) {
        return patientService.create(patient);
    }

    @PatchMapping
    public PatientDTO edit(@RequestBody PatientDTO patient) {
        return patientService.edit(patient);
    }

}
