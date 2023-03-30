package com.hospital.hospital.repository;

import com.hospital.hospital.dao.inmem.PatientDAOInMemImpl;
import com.hospital.hospital.dao.mysql.PatientDaoMySQLImpl;
import com.hospital.hospital.vao.Patient;

import java.util.List;

public class PatientRepository {

    public List<Patient> getAll() {
        return PatientDaoMySQLImpl.getInstance().getAll();
    }

    public Patient find(int patient_id) {
        return PatientDaoMySQLImpl.getInstance().find(patient_id);
    }

    public Patient save(Patient patient) {
        return PatientDaoMySQLImpl.getInstance().save(patient);
    }

    public Patient update(Patient patient) {
        return PatientDaoMySQLImpl.getInstance().update(patient);
    }

    public int delete(int patient_id) {
        return PatientDaoMySQLImpl.getInstance().delete(PatientDAOInMemImpl.getInstance().find(patient_id).getId());
    }
}
