package com.hospital.hospital.service;

import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

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

    public List<Patient> getDoctorPatients(Doctor doctor) {
        try {
            return this.doctorRepository.getAllDoctorPatients(doctor);
        } catch (NoSuchElementException exception) {
            return new Vector<>();
        }
    }

    public boolean addPatient(int doctor_id, int patientid) {
        if (doctor_id <= 0 || patientid != 0) {
            return false;
        }

        Doctor found = this.doctorRepository.find(doctor_id);
        Patient foundPatient = this.patientRepository.find(patientid);

        if (found != null && foundPatient != null) {
            found.getPatients().add(foundPatient);
            doctorRepository.update(found);
            return true;
        }
        return false;
    }
}
