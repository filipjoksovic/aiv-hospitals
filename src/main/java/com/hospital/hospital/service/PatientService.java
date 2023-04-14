package com.hospital.hospital.service;

import com.hospital.hospital.dto.PatientListNotification;
import com.hospital.hospital.dto.doctor.DoctorPatientNumberDTO;
import com.hospital.hospital.enums.PatientListAction;
import com.hospital.hospital.patterns.observer.DoctorChangeObserver;
import com.hospital.hospital.patterns.observer.PatientListActionSubject;
import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;
import com.hospital.hospital.service.interfaces.IPatientServiceLocal;
import com.hospital.hospital.service.interfaces.IPatientServiceRemote;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Stateless
public class PatientService implements Serializable, IPatientServiceLocal, IPatientServiceRemote {
    String NO_DOCTOR_LABEL = "No doctor";

    Logger log = Logger.getLogger(PatientService.class.toString());
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
        if(patient.getDoctor() == null) {
            log.info("Doctor is null. Proceeding to save patient without doctor");
            return patientRepository.save(patient);
        }
        if (doctorRepository.isDoctorFull(patient.getDoctor().getId())) {
            log.info("Doctor is full. Proceeding to save patient without doctor");
            patient.setDoctor(null);
        }
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Patient patient) throws Exception {
        if (patient.getDoctor() == null) {
            log.info("Doctor is null. Proceeding to update patient without doctor");
            return patientRepository.update(patient);
        }
        if (doctorRepository.isDoctorFull(patient.getDoctor().getId())) {
            log.info("Doctor is full. Proceeding to update patient without doctor");
            patient.setDoctor(null);
        }
        return patientRepository.update(patient);
    }

    @Override
    public int delete(int patientId) {
        log.info("Deleting patient with id: " + patientId);
        return patientRepository.delete(patientId);
    }

    @Override
    public boolean addDoctorToPatient(int patientId, int doctorId) throws Exception {
        if (doctorId <= 0 || patientId <= 0) {
            return false;
        }
        Patient patient = patientRepository.find(patientId);
        Doctor doctor = doctorRepository.find(doctorId);

        if (patient == null || doctor == null) {
            return false;
        }
        if (doctorRepository.isDoctorFull(doctorId)) {
            log.info("Doctor is full. Proceeding to update patient without doctor");
            patient.setDoctor(null);
            patientRepository.update(patient);
            return false;
        }
        patient.setDoctor(doctor);
        patientRepository.update(patient);
        if(patient.patientSubject != null) {
            patient.patientSubject.setState(new PatientListNotification(doctor, patient, PatientListAction.SELECT));
        }
        return true;
    }

    @Override
    public List<DoctorPatientNumberDTO> getGroupedPatients() {
        List<Patient> patients = patientRepository.getAll();
        Map<String, DoctorPatientNumberDTO> groupedMap = new HashMap<>();
        patients.forEach(patient -> {
            String doctorName = patient.getDoctor() != null ? patient.getDoctor().getFullName() : NO_DOCTOR_LABEL;
            if (groupedMap.containsKey(doctorName)) {
                groupedMap.get(doctorName).increment();
            } else {
                DoctorPatientNumberDTO doctorPatientNumberDTO = new DoctorPatientNumberDTO(doctorName, 1, String.valueOf(patient.getDoctor() != null ? patient.getDoctor().getMaxPatients() : "-"));
                groupedMap.put(doctorName, doctorPatientNumberDTO);
            }
        });
        return List.copyOf(groupedMap.values());
    }
}

