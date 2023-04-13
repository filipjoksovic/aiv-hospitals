package com.hospital.hospital.repository;

import com.hospital.hospital.dao.inmem.PatientDAOInMemImpl;
import com.hospital.hospital.dao.mysql.PatientDaoMySQLImpl;
import com.hospital.hospital.vao.Patient;

import java.util.List;
import java.util.logging.Logger;

public class PatientRepository {

    Logger logger = Logger.getLogger(PatientRepository.class.toString());
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
        logger.info("Deleting patient with id: " + patient_id);
        return PatientDaoMySQLImpl.getInstance().delete(patient_id);
    }
}
