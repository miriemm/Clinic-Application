package com.assignment3.patient;

import com.assignment3.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByNameLike(String name);

    Optional<Patient> findPatientByNameLike(String name);
}
