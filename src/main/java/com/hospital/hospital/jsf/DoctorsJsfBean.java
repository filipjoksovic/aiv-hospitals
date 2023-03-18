package com.hospital.hospital.jsf;

import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@Named("doctorBean")
@SessionScoped
public class DoctorsJsfBean implements Serializable {
    private final Logger log = LoggerFactory.getLogger(DoctorsJsfBean.class);

    private static final long serialVersionUID = 4659985386224665451L;
    @EJB
    DoctorService doctorsService;

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

    public void delete(int doctorId) {
        doctorsService.delete(doctorId);
    }

    public List<Doctor> getAll() {
        return doctorsService.getAll();
    }

    public void find(int doctorId) {
        doctorsService.find(doctorId);
    }

    public String goToDoctorDetails(int doctorId) {
        Doctor found = doctorsService.find(doctorId);
        selectedDoctor = found;
        return "doctorDetails.xhtml?id=" + doctorId + "faces-redirect=true";
    }

    public void resetSelectedDoctor() {
        this.selectedDoctor = new Doctor();
        selectedDoctor.setId(-1);
    }

    public String removePatient(int patientId) {
        doctorsService.removePatient(selectedDoctor.getId(), patientId);
        return "doctorDetails.xhtml?" + "faces-redirect=true";

    }

    public List<Patient> getDoctorPatients() {
        return this.selectedDoctor.getPatients();
    }

    public Doctor getSelectedDoctor() {
        return selectedDoctor;
    }
}
