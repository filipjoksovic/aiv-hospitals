package com.hospital.hospital.service;

import com.hospital.hospital.dao.PatientDAOInMemImpl;
import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;

import java.util.List;

public class PatientService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public PatientService() {
        this.doctorRepository = new DoctorRepository();
        this.patientRepository = new PatientRepository();
    }

    public List<Patient> getAll() {
        return patientRepository.getAll();
    }

    public Patient find(int patient_id) {
        return patientRepository.find(patient_id);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient update(Patient patient) {
        return patientRepository.update(patient);
    }

    public int delete(int patient_id) {
        return PatientDAOInMemImpl.getInstance().delete(patient_id);
    }


    public boolean addDoctorToPatient(int patientid, int doctorid) {
        if (doctorid <= 0 || patientid <= 0) {
            return false;
        }

        Doctor found = this.doctorRepository.find(doctorid);
        Patient foundPatient = this.patientRepository.find(patientid);

        if (found != null && foundPatient != null) {
            foundPatient.setDoctor(found);
            this.patientRepository.update(foundPatient);

            found.getPatients().add(foundPatient);
            doctorRepository.update(found);
            return true;

        }
        return false;
    }
}
