package com.hospital.hospital.jsf;

import com.hospital.hospital.service.interfaces.IDoctorServiceLocal;
import com.hospital.hospital.service.interfaces.IDoctorServiceRemote;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("doctorBean")
@SessionScoped
public class DoctorsJsfBean implements Serializable {
    private final Logger log = Logger.getLogger(DoctorsJsfBean.class.toString());

    private static final long serialVersionUID = 4659985386224665451L;
    @EJB
    IDoctorServiceLocal iDoctorServiceLocal;
    @EJB
    IDoctorServiceRemote iDoctorServiceRemote;

    private Doctor selectedDoctor = new Doctor();

    public void save() {
        log.info("Attempting to save new doctor");
        log.info("Making a new default doctor instance");

        iDoctorServiceLocal.save(selectedDoctor);
    }

    public String update() {
        if (selectedDoctor == null) {
            return "doctors.xhtml?faces-redirect=true";
        }
        iDoctorServiceLocal.update(selectedDoctor);

        return "doctors.xhtml?faces-redirect=true";
    }

    public void delete(int doctorId) {
        iDoctorServiceLocal.delete(doctorId);
    }

    public List<Doctor> getAll() {
        return iDoctorServiceRemote.getAll();
    }

    public void find(int doctorId) {
        iDoctorServiceRemote.find(doctorId);
    }

    public String goToDoctorDetails(int doctorId) {
        selectedDoctor = iDoctorServiceRemote.find(doctorId);
        return "doctorDetails.xhtml?id=" + doctorId + "faces-redirect=true";
    }

    public void resetSelectedDoctor() {
        this.selectedDoctor = new Doctor();
//        selectedDoctor.setId(-1);
    }

    public String removePatient(int patientId) {
        iDoctorServiceRemote.removePatient(selectedDoctor.getId(), patientId);
        iDoctorServiceRemote.getAll();
        return "doctors.xhtml?" + "faces-redirect=true";

    }

    public List<Patient> getDoctorPatients() {
        return this.selectedDoctor.getPatients();
    }

    public Doctor getSelectedDoctor() {
        return selectedDoctor;
    }
}
