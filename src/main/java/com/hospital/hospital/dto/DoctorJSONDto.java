package com.hospital.hospital.dto;

import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;

import java.util.ArrayList;
import java.util.List;

public class DoctorJSONDto {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String dob;
    private int maxPatients;
    private int patientsCount;
    List<DoctorsPatientJSONDto> patients;

    public DoctorJSONDto(int id, String fname, String lname, String email, String phone, String dob, int maxPatients) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.maxPatients = maxPatients;
        this.patients = new ArrayList<>();
    }

    public DoctorJSONDto(int id, String fname, String lname, String email, String phone, String dob, int maxPatients, List<Patient> patients) {
        this(id, fname, lname, email, phone, dob, maxPatients);
        this.patients = patients.stream().map(DoctorsPatientJSONDto::to).toList();
    }

    public DoctorJSONDto() {
    }

    public static DoctorJSONDto to(Doctor doctor) {
        return new DoctorJSONDto(doctor.getId(), doctor.getFname(), doctor.getLname(), doctor.getEmail(), doctor.getPhone(), doctor.getDob(), doctor.getMaxPatients(), doctor.getPatients());
    }

    public static DoctorJSONDto toDetails(Doctor doctor){
        DoctorJSONDto details =  new DoctorJSONDto(doctor.getId(), doctor.getFname(), doctor.getLname(), doctor.getEmail(), doctor.getPhone(), doctor.getDob(), doctor.getMaxPatients());
        details.setPatientsCount(doctor.getPatients().size());
        return details;
    }

    public void setPatientsCount(int size) {
        this.patientsCount = size;
    }
    public int getPatientsCount() {
        return patientsCount;
    }


    public static Doctor from (DoctorJSONDto doctor) {
        return new Doctor(doctor.getFname(), doctor.getLname(), doctor.getEmail(), doctor.getPhone(), doctor.getDob(), doctor.getMaxPatients());
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


    public List<DoctorsPatientJSONDto> getPatients() {
        return patients;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setMaxPatients(int maxPatients) {
        this.maxPatients = maxPatients;
    }

    public void setPatients(List<DoctorsPatientJSONDto> patients) {
        this.patients = patients;
    }
}