package com.hospital.hospital.interfaces;

import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface IDoctorServiceLocal {

    Doctor save(Doctor doctor);

    Doctor update(Doctor doctor);

    int delete(int doctorId);

    List<Patient> getDoctorPatients(Doctor doctor);
}
