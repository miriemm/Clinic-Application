package com.assignment3.consultation;

import com.assignment3.consultation.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findAllByDateOfConsultationBefore(Date dateOfConsultation);

    Optional<Consultation> findConsultationByDateOfConsultationBefore(Date dateOfConsultation);
}
