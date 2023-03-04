package com.hospital.hospital.dto.patient;

import com.hospital.hospital.vao.Patient;

public class PatientIdFnameLnameDTO {
    private int id;
    private String fname;
    private String lname;

    public PatientIdFnameLnameDTO(int id, String fname, String lname) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    public PatientIdFnameLnameDTO() {
    }

    public static PatientIdFnameLnameDTO toDTO(Patient patient) {
        return new PatientIdFnameLnameDTO(patient.getId(), patient.getFname(), patient.getLname());
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
}
