package com.hospital.hospital.dto.doctor;

import com.hospital.hospital.vao.Doctor;

public class DoctorIdFnameLnameDTO {

    private int id;
    private String fname;
    private String lname;

    public DoctorIdFnameLnameDTO(int id, String fname, String lname) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    public static DoctorIdFnameLnameDTO toDto(Doctor doctor) {
        return new DoctorIdFnameLnameDTO(doctor.getId(), doctor.getFname(), doctor.getLname());
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
