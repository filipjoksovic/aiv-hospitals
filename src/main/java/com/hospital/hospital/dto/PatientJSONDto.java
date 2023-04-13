package com.hospital.hospital.dto;

import com.hospital.hospital.vao.Patient;

public class PatientJSONDto {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String dob;
    private String note;

    private PatientsDoctorJSONDto doctor;

    public PatientJSONDto(int id, String fname, String lname, String email, String phone, String dob, String note) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.note = note;
    }

    public PatientJSONDto(int id, String fname, String lname, String email, String phone, String dob, String note, PatientsDoctorJSONDto doctor) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.note = note;
        this.doctor = doctor;
    }

    public static PatientJSONDto to(Patient p) {
        return new PatientJSONDto(p.getId(), p.getFname(), p.getLname(), p.getEmail(), p.getPhone(), p.getDob(), p.getNote(), PatientsDoctorJSONDto.to(p.getDoctor()));
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getNote() {
        return note;
    }

    public PatientsDoctorJSONDto getDoctor() {
        return doctor;
    }
}
