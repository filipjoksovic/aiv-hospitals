package com.hospital.hospital.service;

import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;
import com.hospital.hospital.vao.Doctor;

import java.util.List;

public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public DoctorService() {
        this.doctorRepository = new DoctorRepository();
        this.patientRepository = new PatientRepository();
    }

    public List<Doctor> getAll() {
        return doctorRepository.getAll();
    }

    public Doctor find(int doctor_id) {
        return doctorRepository.find(doctor_id);
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor update(Doctor doctor) {
        return doctorRepository.update(doctor);
    }

    public int delete(int doctor_id) {
        return doctorRepository.delete(doctor_id);
    }

}
