package com.hospital.hospital.jsf;

import com.hospital.hospital.enums.VisitStatus;
import com.hospital.hospital.service.interfaces.IDoctorServiceRemote;
import com.hospital.hospital.service.interfaces.IPatientServiceRemote;
import com.hospital.hospital.service.interfaces.IVisitServiceLocal;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import com.hospital.hospital.vao.Visit;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@Named("visitBean")
@SessionScoped
public class VisitJsfBean implements Serializable {


    private static final long serialVersionUID = 4896830600207254789L;
    Logger log = LoggerFactory.getLogger(VisitJsfBean.class);

    @EJB
    IVisitServiceLocal iVisitServiceLocal;
    @EJB
    IPatientServiceRemote iPatientServiceRemote;

    @EJB
    IDoctorServiceRemote iDoctorServiceRemote;
    private Visit selectedVisit = new Visit();
    private int patientId = -1;
    private int doctorId = -1;

    private VisitStatus visitStatus;

    public String save() {
        log.info("Attempting to save a visit");
        Patient foundPatient = null;
        Doctor foundDoctor = null;
        if (patientId != -1) {
            foundPatient = iPatientServiceRemote.find(patientId);
            selectedVisit.setPatient(foundPatient);
        }
        if (doctorId != -1) {
            foundDoctor = iDoctorServiceRemote.find(doctorId);
            selectedVisit.setDoctor(foundDoctor);
        }
        iVisitServiceLocal.save(selectedVisit);
        selectedVisit = new Visit();
        return "visits?faces-redirect=true";
    }

    public String update() {
        log.info("Attempting to update a visit");
        iVisitServiceLocal.update(selectedVisit);
        return "visits?faces-redirect=true";
    }

    public Visit finalizeVisit() {
        this.selectedVisit.setStatus(VisitStatus.FINALIZED);
        return iVisitServiceLocal.update(selectedVisit);
    }

    public String delete(int visitId) {
        log.info("Attempting to delete a visit");
        iVisitServiceLocal.delete(visitId);
        return "visits?faces-redirect=true";
    }

    public List<Visit> getAllVisits() {
        log.info("Getting all visits");
        return iVisitServiceLocal.getAll();
    }

    public String goToVisitDetails(int visitId) {
        log.info("Going to visit details");
        selectedVisit = iVisitServiceLocal.find(visitId);
        return "visitDetails?faces-redirect=true";
    }

    public Visit getSelectedVisit() {
        return selectedVisit;
    }

    public void setSelectedVisit(Visit selectedVisit) {
        this.selectedVisit = selectedVisit;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public VisitStatus getVisitStatus() {
        return visitStatus;
    }
}
