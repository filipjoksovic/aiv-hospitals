package com.hospital.hospital.jsf;

import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.service.EmailSenderService;
import com.hospital.hospital.service.PatientService;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@Named("patientsBean")
@SessionScoped
public class PatientsJsfBean implements Serializable {

    Logger log = LoggerFactory.getLogger(PatientsJsfBean.class);
    private static final long serialVersionUID = -3294547943335355918L;

    @EJB
    PatientService patientService;
    @EJB
    DoctorService doctorService;

    @EJB
    EmailSenderService emailService;

    private Patient selectedPatient = new Patient();
    private Doctor selectedDoctor = new Doctor();
    private int doctor_id = -1;

    public String save() {
        log.info("Attempting to save a patient");
        if (doctor_id != -1) {
            log.info("No doctor selected for a new patient");
            selectedDoctor = doctorService.find(doctor_id);
            selectedPatient.setDoctor(selectedDoctor);
        } else {
            selectedPatient.setDoctor(null);
        }
        patientService.save(selectedPatient);
        return "patients?faces-redirect=true";
    }

    public String update() throws Exception {
        log.info("Attempting to update a patient");

        patientService.addDoctorToPatient(selectedPatient.getId(), doctor_id);

        return "patients?faces-redirect=true";

    }

    public void delete(int patient_id) {
        patientService.delete(patient_id);
    }

    public List<Patient> getAll() {
        return patientService.getAll();
    }

    public Patient find(int patient_id) {
        return this.patientService.find(patient_id);
    }

    public void resetSelectedPatient() {
        this.selectedPatient = new Patient();
    }

    public Patient getSelectedPatient() {
        return selectedPatient;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int id) {
        doctor_id = id;
    }

    public String goToPatientDetails(int patient_id) {
        log.info("Setting selected patient");

        this.selectedPatient = patientService.find(patient_id);
        return "patientDetails?faces-redirect=true";

    }
}
