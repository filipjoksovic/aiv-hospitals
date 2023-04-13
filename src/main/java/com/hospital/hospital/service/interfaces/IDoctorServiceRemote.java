package com.hospital.hospital.service.interfaces;

import com.hospital.hospital.vao.Doctor;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface IDoctorServiceRemote {
    List<Doctor> getAll();

    List<Doctor> getAllAvailable();

    Doctor find(int doctorId);

    boolean addPatient(int doctorId, int patientId);

    boolean removePatient(int doctorId, int patientId);

}
