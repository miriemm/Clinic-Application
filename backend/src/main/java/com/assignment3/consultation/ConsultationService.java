package com.assignment3.consultation;

import com.assignment3.consultation.model.Consultation;
import com.assignment3.consultation.model.dto.ConsultationDTO;
import com.assignment3.patient.PatientService;
import com.assignment3.patient.model.Patient;
import com.assignment3.user.UserService;
import com.assignment3.user.dto.UserDTO;
import com.assignment3.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final PatientService patientService;
    private final UserService userService;

    private Consultation findById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found: " + id));
    }

    public List<ConsultationDTO> findAll() {
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    // for the doctor page (in frontend)
    public List<ConsultationDTO> findAllPastConsultations() {
        Date currentDate = new Date();

        return consultationRepository.findAllByDateOfConsultationBefore(currentDate).stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }


    public ConsultationDTO create(ConsultationDTO consultation) {

        Patient patient = patientService.findPatientByNameLike(consultation.getPatientName()).get();
        User doctor = userService.findByLastName(consultation.getDoctorName()).get();

        Consultation newConsultation =  Consultation.builder()
                .patient(patient)
                .doctor(doctor)
                .dateOfConsultation(consultation.getDateOfConsultation())
                .description(consultation.getDescription())
                .build();


        return consultationMapper.toDto(consultationRepository.save(newConsultation));

    }

    public ConsultationDTO edit(ConsultationDTO consultation) {


        Consultation actConsultation = findById(consultation.getId());

        Patient actPatient = patientService.findPatientByNameLike(consultation.getPatientName()).get();

        User actDoctor = userService.findByLastName(consultation.getDoctorName()).get();


        actConsultation.setPatient(actPatient);
        actConsultation.setDoctor(actDoctor);
        actConsultation.setDateOfConsultation(consultation.getDateOfConsultation());
        actConsultation.setDescription(consultation.getDescription());


        return consultationMapper.toDto(
                consultationRepository.save(actConsultation)
        );

    }

    public void delete(Long id){
        Consultation consultationToDelete = findById(id);
        consultationRepository.delete(consultationToDelete);

    }

    // doctor page - he can add Details
    // this method works like an edit, except we can add only new details
    public ConsultationDTO addDetails(ConsultationDTO consultation){
        Consultation pastConsultation = findById(consultation.getId());

        String pastDescription = pastConsultation.getDescription();
        String newDetails = consultation.getDescription();

        String finalDescription = pastDescription + "\nNew Details:\n" + newDetails;

        pastConsultation.setDescription(finalDescription);

        return consultationMapper.toDto(
                consultationRepository.save(pastConsultation)
        );

    }

}
