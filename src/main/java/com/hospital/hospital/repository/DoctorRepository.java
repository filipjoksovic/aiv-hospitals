package com.hospital.hospital.repository;

import com.hospital.hospital.dao.DoctorDAOInMemImpl;
import com.hospital.hospital.vao.Doctor;

import java.util.List;

public class DoctorRepository {
    public DoctorRepository() {
    }

    public List<Doctor> getAll() {
        return DoctorDAOInMemImpl.getInstance().getAll();
    }

    public Doctor find(int doctor_id) {
        return DoctorDAOInMemImpl.getInstance().find(doctor_id);
    }

    public Doctor save(Doctor doctor) {
        return DoctorDAOInMemImpl.getInstance().save(doctor);
    }

    public Doctor update(Doctor doctor) {
        return DoctorDAOInMemImpl.getInstance().update(doctor);
    }

    public int delete(int doctor_id) {
        return DoctorDAOInMemImpl.getInstance().delete(doctor_id);
    }

}
