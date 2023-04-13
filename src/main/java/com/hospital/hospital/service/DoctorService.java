package com.hospital.hospital.service;

import com.hospital.hospital.dto.PatientListNotification;
import com.hospital.hospital.enums.PatientListAction;
import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;
import com.hospital.hospital.service.interfaces.IDoctorServiceLocal;
import com.hospital.hospital.service.interfaces.IDoctorServiceRemote;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.logging.Logger;

@Stateless
public class DoctorService implements Serializable, IDoctorServiceRemote, IDoctorServiceLocal {
    private static final long serialVersionUID = 2332164455692294904L;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    private final Logger logger = Logger.getLogger(DoctorService.class.toString());


    public DoctorService() {
        this.doctorRepository = new DoctorRepository();
        this.patientRepository = new PatientRepository();
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> allDoctors = doctorRepository.getAll();

        return allDoctors;
    }

    @Override
    public List<Doctor> getAllAvailable() {
        List<Doctor> allDoctors = doctorRepository.getAllAvailable();

        return allDoctors;
    }

    @Override
    public Doctor find(int doctorId) {
        return doctorRepository.find(doctorId);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.update(doctor);
    }

    @Override
    public int delete(int doctorId) {
        return doctorRepository.delete(doctorId);
    }

    @Override
    public List<Patient> getDoctorPatients(Doctor doctor) {
        try {
            return this.doctorRepository.getAllDoctorPatients(doctor);
        } catch (NoSuchElementException exception) {
            return new Vector<>();
        }
    }

    @Override
    public boolean addPatient(int doctorId, int patientId) {

        if (doctorId <= 0 || patientId <= 0) {
            return false;
        }

        Doctor found = this.doctorRepository.find(doctorId);
        Patient foundPatient = this.patientRepository.find(patientId);

        if (found != null && foundPatient != null) {
            PatientListNotification notification = new PatientListNotification(found, foundPatient, PatientListAction.SELECT);
//            found.getPatients().add(foundPatient);
//            doctorRepository.update(found);
            try {
                foundPatient.patientSubject.setState(notification);
                foundPatient.setDoctor(found);
                patientRepository.update(foundPatient);

            } catch (Exception e) {
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removePatient(int doctorId, int patientId) {
        if (doctorId <= 0 || patientId <= 0) {
            return false;
        }

        Doctor found = this.doctorRepository.find(doctorId);
        Patient foundPatient = this.patientRepository.find(patientId);

        if (found != null && foundPatient != null) {
            foundPatient.setDoctor(null);
            patientRepository.update(foundPatient);
            return true;
        }
        return false;

    }


}
