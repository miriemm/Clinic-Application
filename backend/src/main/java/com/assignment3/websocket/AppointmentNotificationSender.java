package com.assignment3.websocket;

import java.util.Date;

public class AppointmentNotificationSender {

    private String patientName;
    private String doctorName;
    private Date dateOfConsultation;

    public AppointmentNotificationSender(){

    }

    public AppointmentNotificationSender(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(Date dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }
}
