package com.hospital.hospital.service;

import com.hospital.hospital.dto.PatientListNotification;
import com.hospital.hospital.enums.PatientListAction;
import com.hospital.hospital.interfaces.IDoctorServiceLocal;
import com.hospital.hospital.interfaces.IDoctorServiceRemote;
import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.stream.Collectors;

@Stateless
public class DoctorService implements Serializable, IDoctorServiceRemote, IDoctorServiceLocal {
    private static final long serialVersionUID = 2332164455692294904L;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    private final Logger logger = LoggerFactory.getLogger(DoctorService.class);


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
        logger.info("Adding patient with id {} to doctor with id {}", patientId, doctorId);

        if (doctorId <= 0 || patientId <= 0) {
            logger.error("Invalid ids provided. DoctorId: {}, PatientId: {}", doctorId, patientId);
            return false;
        }

        Doctor found = this.doctorRepository.find(doctorId);
        Patient foundPatient = this.patientRepository.find(patientId);

        if (found != null && foundPatient != null) {
            PatientListNotification notification = new PatientListNotification(found, foundPatient, PatientListAction.SELECT);
            found.getPatients().add(foundPatient);
            doctorRepository.update(found);
            logger.info("Added patient with id {} to doctor with id {}", patientId, doctorId);
            try {
                logger.info("Attempting to notify doctor {} about patient selection", doctorId);
                foundPatient.patientSubject.setState(notification);
                logger.info("Doctor {} notified", doctorId);

            } catch (Exception e) {
                logger.error("Error while sending the email {}", e.getMessage());
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
            found.setPatients(found.getPatients().stream().filter(patient -> patient.getId() != patientId).collect(Collectors.toList()));
            doctorRepository.update(found);
            foundPatient.setDoctor(null);
            patientRepository.update(foundPatient);
            return true;
        }
        return false;

    }

    @Override
    public String greet() {
        return "Hello";
    }
}
