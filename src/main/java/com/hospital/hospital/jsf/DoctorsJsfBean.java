package com.hospital.hospital.jsf;

import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@Named("doctorBean")
@SessionScoped
public class DoctorsJsfBean implements Serializable {
    Logger log = LoggerFactory.getLogger(DoctorsJsfBean.class);

    private static final long serialVersionUID = 4659985386224665451L;

    private final DoctorService doctorsService = new DoctorService();

    private Doctor selectedDoctor = new Doctor();

    public void save() {
        log.info("Attempting to save new doctor");
        log.info("Making a new default doctor instance");

        doctorsService.save(selectedDoctor);
    }

    public String update() {
        if (selectedDoctor == null) {
            log.error("Trying to update doctor while doctor is not set");
            return "doctors.xhtml?faces-redirect=true";
        }
        log.info("Updating doctor with id {}", selectedDoctor.getId());
        doctorsService.update(selectedDoctor);

        return "doctors.xhtml?faces-redirect=true";
    }

    public void delete(int doctor_id) {
        doctorsService.delete(doctor_id);
    }

    public List<Doctor> getAll() {
        return doctorsService.getAll();
    }

    public void find(int doctor_id) {
        doctorsService.find(doctor_id);
    }

    public String goToDoctorDetails(int doctor_id) {
        Doctor found = doctorsService.find(doctor_id);
        selectedDoctor = found;
        return "doctorDetails.xhtml?id=" + doctor_id + "faces-redirect=true";
    }

    public void resetSelectedDoctor() {
        this.selectedDoctor = new Doctor();
        selectedDoctor.setId(-1);
    }

    public String removePatient(int patient_id) {
        doctorsService.removePatient(selectedDoctor.getId(), patient_id);
        return "doctorDetails.xhtml?" + "faces-redirect=true";

    }

    public List<Patient> getDoctorPatients() {
        return this.selectedDoctor.getPatients();
    }

    public Doctor getSelectedDoctor() {
        return selectedDoctor;
    }
}
