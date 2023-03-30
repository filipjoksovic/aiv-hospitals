package com.hospital.hospital.dto;

import com.hospital.hospital.enums.PatientListAction;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;

public class PatientListNotification {
    Doctor doctor;
    Patient patient;
    PatientListAction action;

    public PatientListNotification(Doctor doctor, Patient patient, PatientListAction action) {
        this.doctor = doctor;
        this.patient = patient;
        this.action = action;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientListAction getAction() {
        return action;
    }

    public void setAction(PatientListAction action) {
        this.action = action;
    }
}
