package com.hospital.hospital.dto.doctor;

import java.io.Serializable;

public class DoctorPatientNumberDTO implements Serializable {

    private static final long serialVersionUID = 6647942271740771878L;
    private String doctorName;
    private int numberOfPatients;
    private String maxQuota;

    public DoctorPatientNumberDTO(String doctorName, int numberOfPatients, String maxQuota) {
        this.doctorName = doctorName;
        this.numberOfPatients = numberOfPatients;
        this.maxQuota = maxQuota;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public String getMaxQuota() {
        return maxQuota;
    }

    public void setMaxQuota(String maxQuota) {
        this.maxQuota = maxQuota;
    }

    public void increment() {
        this.numberOfPatients++;
    }
}
