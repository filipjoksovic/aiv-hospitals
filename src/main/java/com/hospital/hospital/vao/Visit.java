package com.hospital.hospital.vao;

import com.hospital.hospital.enums.VisitStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "visits")
public class Visit implements Serializable {


    private static final long serialVersionUID = 121447310604196893L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @NotNull
    Patient patient;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @NotNull
    Doctor doctor;
    @NotNull
    String date;
    String notes;
    String medications;

    @Enumerated(EnumType.ORDINAL)
    private VisitStatus status = VisitStatus.ACTIVE;


    public Visit(int id, Patient patient, Doctor doctor, String date, String notes, String medications) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.notes = notes;
        this.medications = medications;
    }

    public Visit() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }
}
