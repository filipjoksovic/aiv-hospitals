package com.hospital.hospital.repository;

import com.hospital.hospital.dao.PatientDAOInMemImpl;
import com.hospital.hospital.vao.Patient;

import java.util.List;

public class PatientRepository {

    public List<Patient> getAll() {
        return PatientDAOInMemImpl.getInstance().getAll();
    }

    public Patient find(int patient_id) {
        return PatientDAOInMemImpl.getInstance().find(patient_id);
    }

    public Patient save(Patient patient) {
        return PatientDAOInMemImpl.getInstance().save(patient);
    }

    public Patient update(Patient patient) {
        return PatientDAOInMemImpl.getInstance().update(patient);
    }

    public int delete(int patient_id) {
        return PatientDAOInMemImpl.getInstance().delete(patient_id);
    }
}
