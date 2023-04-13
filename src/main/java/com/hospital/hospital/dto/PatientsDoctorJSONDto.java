package com.hospital.hospital.dto;

import com.hospital.hospital.vao.Doctor;

public class PatientsDoctorJSONDto {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String dob;
    private int maxPatients;

    public PatientsDoctorJSONDto(int id, String fname, String lname, String email, String phone, String dob, int maxPatients) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.maxPatients = maxPatients;
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

    public int getMaxPatients() {
        return maxPatients;
    }

    public static PatientsDoctorJSONDto to(Doctor doctor) {
        if (doctor == null) return null;
        return new PatientsDoctorJSONDto(doctor.getId(), doctor.getFname(), doctor.getLname(), doctor.getEmail(), doctor.getPhone(), doctor.getDob(), doctor.getMaxPatients());
    }
}
