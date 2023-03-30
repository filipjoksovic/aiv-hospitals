package com.hospital.hospital.dao.interfaces;

import com.hospital.hospital.vao.Patient;

import java.util.List;

public interface PatientDAO extends CrudDAO<Patient> {
    @Override
    List<Patient> getAll();

    @Override
    Patient find(int id);

    @Override
    Patient save(Patient entity);

    @Override
    Patient update(Patient entity);

    @Override
    int delete(int entityId);

    List<Patient> findByDoctorId(String[] doctorIds);
}
