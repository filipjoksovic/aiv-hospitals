package com.hospital.hospital.repository;

import com.hospital.hospital.dao.DoctorDAOInMemImpl;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;

import java.util.List;
import java.util.NoSuchElementException;

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

    public List<Patient> getAllDoctorPatients(Doctor doctor) throws NoSuchElementException {
        return DoctorDAOInMemImpl.getInstance().find(doctor.getId()).getPatients();
    }

    public int getPatientCount(int id) throws NoSuchElementException {
        return DoctorDAOInMemImpl.getInstance().find(id).getPatients().size();
    }
}
