package com.hospital.hospital.dao.interfaces;

import com.hospital.hospital.vao.Doctor;

import java.util.List;

public interface DoctorDAO extends CrudDAO<Doctor> {

    @Override
    List<Doctor> getAll();

    @Override
    Doctor find(int id);

    @Override
    Doctor save(Doctor entity);

    @Override
    Doctor update(Doctor entity);

    int delete(int entityId);

    int getNumberOfPatients(int doctorId);
}
