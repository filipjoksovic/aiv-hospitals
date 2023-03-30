package com.hospital.hospital.service;

import com.hospital.hospital.dao.inmem.PatientDAOInMemImpl;
import com.hospital.hospital.dto.PatientListNotification;
import com.hospital.hospital.enums.PatientListAction;
import com.hospital.hospital.interfaces.IPatientServiceLocal;
import com.hospital.hospital.interfaces.IPatientServiceRemote;
import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.List;

@Stateless
public class PatientService implements Serializable, IPatientServiceLocal, IPatientServiceRemote {


    private static final long serialVersionUID = 3278536426967908723L;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    private final EmailSenderService emailSenderService;

    public PatientService() {
        this.doctorRepository = new DoctorRepository();
        this.patientRepository = new PatientRepository();
        this.emailSenderService = new EmailSenderService();
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.getAll();
    }

    @Override
    public Patient find(int patientId) {
        return patientRepository.find(patientId);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.update(patient);
    }

    @Override
    public int delete(int patientId) {
        return PatientDAOInMemImpl.getInstance().delete(patientId);
    }

    @Override
    public boolean addDoctorToPatient(int patientId, int doctorId) throws Exception {
        if (doctorId <= 0 || patientId <= 0) {
            return false;
        }

        Doctor found = this.doctorRepository.find(doctorId);
        Patient foundPatient = this.patientRepository.find(patientId);


        if (found != null && foundPatient != null && found.getMaxPatients() > found.getPatients().size()) {
            PatientListNotification notification = new PatientListNotification(found, foundPatient, PatientListAction.SELECT);
            foundPatient.setDoctor(found);
            this.patientRepository.update(foundPatient);

            found.getPatients().add(foundPatient);
            doctorRepository.update(found);
            foundPatient.patientSubject.setState(notification);
            emailSenderService.notifyDoctor(foundPatient);
            return true;
        } else {
            emailSenderService.notifyPatient(foundPatient);
            return false;
        }
    }
}
