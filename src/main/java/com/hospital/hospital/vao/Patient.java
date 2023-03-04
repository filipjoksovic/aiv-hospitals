package com.hospital.hospital.vao;

public class Patient extends Person {

    private String note;
    private Doctor doctor;

    public Patient() {
        super();
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
