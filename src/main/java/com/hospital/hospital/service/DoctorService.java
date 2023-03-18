package com.hospital.hospital.service;

import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.stream.Collectors;

@Stateless
public class DoctorService implements Serializable {


    private static final long serialVersionUID = 2332164455692294904L;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public DoctorService() {
        this.doctorRepository = new DoctorRepository();
        this.patientRepository = new PatientRepository();
    }

    public List<Doctor> getAll() {
        return doctorRepository.getAll();
    }

    public Doctor find(int doctorId) {
        return doctorRepository.find(doctorId);
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor update(Doctor doctor) {
        return doctorRepository.update(doctor);
    }

    public int delete(int doctorId) {
        return doctorRepository.delete(doctorId);
    }

    public List<Patient> getDoctorPatients(Doctor doctor) {
        try {
            return this.doctorRepository.getAllDoctorPatients(doctor);
        } catch (NoSuchElementException exception) {
            return new Vector<>();
        }
    }

    public boolean addPatient(int doctorId, int patientId) {
        if (doctorId <= 0 || patientId != 0) {
            return false;
        }

        Doctor found = this.doctorRepository.find(doctorId);
        Patient foundPatient = this.patientRepository.find(patientId);

        if (found != null && foundPatient != null) {
            found.getPatients().add(foundPatient);
            doctorRepository.update(found);
            return true;
        }
        return false;
    }

    public boolean removePatient(int doctorId, int patientId) {
        if (doctorId <= 0 || patientId <= 0) {
            return false;
        }

        Doctor found = this.doctorRepository.find(doctorId);
        Patient foundPatient = this.patientRepository.find(patientId);

        if (found != null && foundPatient != null) {
            found.setPatients(found.getPatients().stream().filter(patient -> patient.getId() != patientId).collect(Collectors.toList()));
            doctorRepository.update(found);
            foundPatient.setDoctor(null);
            patientRepository.update(foundPatient);
            return true;
        }
        return false;

    }
}
