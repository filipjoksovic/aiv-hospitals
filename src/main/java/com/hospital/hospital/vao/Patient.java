package com.hospital.hospital.vao;

import java.io.Serializable;

public class Patient extends Person implements Serializable {

    private static final long serialVersionUID = -2078065580068941509L;
    private String note;
    private Doctor doctor;

    public Patient() {
        super();
        this.setId(-1);
    }

    public Patient(String note) {
        this.note = note;
    }

    public Patient(String fname, String lname, String email, String phone, String dob, String note) {
        super(fname, lname, email, phone, dob);
        this.note = note;
    }

    public Patient(int id, String fname, String lname, String email, String phone, String dob, String note) {
        super(id, fname, lname, email, phone, dob);
        this.note = note;
    }

    public Patient(String fname, String lname, String email, String phone, String dob, String note, Doctor doctor) {
        super(fname, lname, email, phone, dob);
        this.note = note;
        this.doctor = doctor;
    }

    public Patient(int id, String fname, String lname, String email, String phone, String dob, String note, Doctor doctor) {
        super(id, fname, lname, email, phone, dob);
        this.note = note;
        this.doctor = doctor;
    }

    public Patient(String note, Doctor doctor) {
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
}
