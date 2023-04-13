package com.hospital.hospital.service.interfaces;

import com.hospital.hospital.dto.doctor.DoctorPatientNumberDTO;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface IPatientServiceLocal {
    Patient save(Patient patient);

    Patient update(Patient patient) throws Exception;

    int delete(int patientId);

    List<DoctorPatientNumberDTO> getGroupedPatients();
}
