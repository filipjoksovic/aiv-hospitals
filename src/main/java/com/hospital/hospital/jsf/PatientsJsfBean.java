package com.hospital.hospital.jsf;

import com.hospital.hospital.service.interfaces.IDoctorServiceRemote;
import com.hospital.hospital.service.interfaces.IPatientServiceLocal;
import com.hospital.hospital.service.interfaces.IPatientServiceRemote;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("patientsBean")
@SessionScoped
public class PatientsJsfBean implements Serializable {

    Logger log = Logger.getLogger(PatientsJsfBean.class.toString());
    private static final long serialVersionUID = -3294547943335355918L;

    @EJB
    IPatientServiceLocal iPatientServiceLocal;
    @EJB
    IPatientServiceRemote iPatientServiceRemote;

    @EJB
    IDoctorServiceRemote iDoctorServiceRemote;

//    @EJB
//    EmailSenderService emailService;

    private Patient selectedPatient = new Patient();
    private Doctor selectedDoctor = new Doctor();
    private int doctor_id = -1;

    public String save() {
        log.info("Attempting to save a patient");
        if (doctor_id > 0) {
            log.info("No doctor selected for a new patient");
            selectedDoctor = iDoctorServiceRemote.find(doctor_id);
            selectedPatient.setDoctor(selectedDoctor);
        } else {
            selectedPatient.setDoctor(null);
        }
        iPatientServiceLocal.save(selectedPatient);
        return "patients?faces-redirect=true";
    }

    public String update() throws Exception {
        log.info("Attempting to update a patient");

        iPatientServiceLocal.update(selectedPatient);

        if (doctor_id > 0) {
            log.info("Doctor selected for patient");
            iPatientServiceRemote.addDoctorToPatient(selectedPatient.getId(), doctor_id);
        } else {
            selectedPatient.setDoctor(null);
        }

        return "patients?faces-redirect=true";

    }

    public void delete(int patient_id) {
        iPatientServiceLocal.delete(patient_id);
    }

    public List<Patient> getAll() {
        return iPatientServiceRemote.getAll();
    }

    public Patient find(int patient_id) {
        return this.iPatientServiceRemote.find(patient_id);
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

        this.selectedPatient = iPatientServiceRemote.find(patient_id);
        return "patientDetails?faces-redirect=true";

    }
}
