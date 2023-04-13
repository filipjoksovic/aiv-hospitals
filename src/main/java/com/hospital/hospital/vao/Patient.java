package com.hospital.hospital.vao;

import com.hospital.hospital.patterns.observer.DoctorChangeObserver;
import com.hospital.hospital.patterns.observer.PatientListActionSubject;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient implements Serializable {

    private static final long serialVersionUID = -2078065580068941509L;

    @Transient
    transient public PatientListActionSubject patientSubject;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String phone;

    private String note;
    private String dob;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Visit> visit;
    @Transient
    transient private DoctorChangeObserver patientObserver;


    public Patient() {
        this.patientSubject = new PatientListActionSubject();
        this.patientObserver = new DoctorChangeObserver(this.patientSubject);
    }

    public Patient(String note) {
        this();
        this.note = note;
    }


    public Patient(String fname, String lname, String email, String phone, String dob) {
        this();
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public Patient(int id, String fname, String lname, String email, String phone, String dob) {
        this();
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public Patient(String fname, String lname, String email, String phone, String dob, String note) {
        this(fname, lname, email, phone, dob);
        this.note = note;
    }

    public Patient(int id, String fname, String lname, String email, String phone, String dob, String note) {
        this(id, fname, lname, email, phone, dob);
        this.note = note;
    }

    public Patient(String fname, String lname, String email, String phone, String dob, String note, Doctor doctor) {
        this(fname, lname, email, phone, dob);
        this.note = note;
        this.doctor = doctor;
    }

    public Patient(int id, String fname, String lname, String email, String phone, String dob, String note, Doctor doctor) {
        this(id, fname, lname, email, phone, dob);
        this.note = note;
        this.doctor = doctor;
    }

    public Patient(String note, Doctor doctor) {
        this();
        this.note = note;
        this.doctor = doctor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public PatientListActionSubject getPatientSubject() {
        return patientSubject;
    }

    public void setPatientSubject(PatientListActionSubject patientSubject) {
        this.patientSubject = patientSubject;
    }

    public DoctorChangeObserver getPatientObserver() {
        return patientObserver;
    }

    public void setPatientObserver(DoctorChangeObserver patientObserver) {
        this.patientObserver = patientObserver;
    }

    public List<Visit> getVisit() {
        return visit;
    }

    public void setVisit(List<Visit> visit) {
        this.visit = visit;
    }
}
