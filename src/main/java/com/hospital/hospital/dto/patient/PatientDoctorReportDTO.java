package com.hospital.hospital.dto.patient;

public class PatientDoctorReportDTO {
    public String doctorName;
    public int doctorCount;

    public PatientDoctorReportDTO(String doctorName, int doctorCount) {
        this.doctorName = doctorName;
        this.doctorCount = doctorCount;
    }
}
