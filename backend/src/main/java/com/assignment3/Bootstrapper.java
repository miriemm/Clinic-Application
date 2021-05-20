package com.assignment3;

import com.assignment3.consultation.ConsultationRepository;
import com.assignment3.patient.PatientRepository;
import com.assignment3.user.RoleRepository;
import com.assignment3.user.UserRepository;
import com.assignment3.security.AuthService;
import com.assignment3.security.dto.SignupRequest;
import com.assignment3.user.model.ERole;
import com.assignment3.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final PatientRepository patientRepository;

    private final ConsultationRepository consultationRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            consultationRepository.deleteAll();
            patientRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .firstName("FirstName1")
                    .lastName("LastName1")
                    .email("alex@email.com")
                    .username("alex")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .firstName("FirstName2")
                    .lastName("LastName2")
                    .email("alex1@email.com")
                    .username("alex1")
                    .password("WooHoo1!")
                    .roles(Set.of("SECRETARY"))
                    .build());

        }
    }
}
