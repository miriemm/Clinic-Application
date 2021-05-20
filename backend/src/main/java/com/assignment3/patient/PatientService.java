package com.assignment3.patient;

import com.assignment3.patient.model.Patient;
import com.assignment3.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + id));
    }

    public Optional<Patient> findPatientByNameLike(String name){
        return patientRepository.findPatientByNameLike(name);
    }

    public List<PatientDTO> findAllByNameLike(String name){
        return patientRepository.findAllByNameLike(name).stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientDTO create(PatientDTO patient) {
        return patientMapper.toDto(patientRepository.save(
                patientMapper.fromDto(patient)
        ));
    }

    public PatientDTO edit(PatientDTO patient) {
        Patient actPatient = findById(patient.getId());
        actPatient.setName(patient.getName());
        actPatient.setIdentityCardNr(patient.getIdentityCardNr());
        actPatient.setPersonalNumericCode(patient.getPersonalNumericCode());
        actPatient.setDateOfBirth(patient.getDateOfBirth());
        actPatient.setAddress(patient.getAddress());

        return patientMapper.toDto(
                patientRepository.save(actPatient)
        );
    }


}
