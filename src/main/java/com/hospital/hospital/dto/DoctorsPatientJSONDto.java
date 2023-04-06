package com.hospital.hospital.dto;

import com.hospital.hospital.vao.Patient;

public class DoctorsPatientJSONDto {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String dob;
    private String note;

    public DoctorsPatientJSONDto(int id, String fname, String lname, String email, String phone, String dob, String note) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.note = note;
    }

    public DoctorsPatientJSONDto() {
    }

    public static DoctorsPatientJSONDto to(Patient patient) {
        return new DoctorsPatientJSONDto(patient.getId(), patient.getFname(), patient.getLname(), patient.getEmail(), patient.getPhone(), patient.getDob(), patient.getNote());
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
