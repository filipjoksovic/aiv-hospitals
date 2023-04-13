package com.hospital.hospital.repository;

import com.hospital.hospital.dao.mysql.DoctorDAOMySQLImpl;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;

import java.util.List;
import java.util.NoSuchElementException;

public class DoctorRepository {
    public DoctorRepository() {
    }

    public List<Doctor> getAll() {
        return DoctorDAOMySQLImpl.getInstance().getAll();
    }

    public Doctor find(int doctor_id) {
        return DoctorDAOMySQLImpl.getInstance().find(doctor_id);
    }

    public Doctor save(Doctor doctor) {
        return DoctorDAOMySQLImpl.getInstance().save(doctor);
    }

    public Doctor update(Doctor doctor) {
        return DoctorDAOMySQLImpl.getInstance().update(doctor);
    }

    public int delete(int doctor_id) {
        return DoctorDAOMySQLImpl.getInstance().delete(doctor_id);
    }

    public List<Patient> getAllDoctorPatients(Doctor doctor) throws NoSuchElementException {
        return DoctorDAOMySQLImpl.getInstance().find(doctor.getId()).getPatients();
    }

    public int getPatientCount(int id) throws NoSuchElementException {
        return DoctorDAOMySQLImpl.getInstance().getNumberOfPatients(id);
    }

    public List<Doctor> getAllAvailable() {
        return DoctorDAOMySQLImpl.getInstance().getAllAvailable();
    }
}
