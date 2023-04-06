package com.hospital.hospital.service;

import com.hospital.hospital.dto.PatientListNotification;
import com.hospital.hospital.dto.doctor.DoctorPatientNumberDTO;
import com.hospital.hospital.enums.PatientListAction;
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
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.update(patient);
    }

    @Override
    public int delete(int patientId) {
        return patientRepository.delete(patientId);
    }

    @Override
    public boolean addDoctorToPatient(int patientId, int doctorId) throws Exception {
        if (doctorId <= 0 || patientId <= 0) {
            return false;
        }

        Doctor foundDoctor = this.doctorRepository.find(doctorId);
        Patient foundPatient = this.patientRepository.find(patientId);


        if (foundPatient.getDoctor() == null || foundPatient.getDoctor().getId() != doctorId) {
            if (foundDoctor != null && foundPatient != null && foundDoctor.getMaxPatients() > foundDoctor.getPatients().size()) {
                PatientListNotification notification = new PatientListNotification(foundDoctor, foundPatient, PatientListAction.SELECT);
                foundPatient.setDoctor(foundDoctor);
                this.patientRepository.update(foundPatient);
                foundPatient.patientSubject.setState(notification);
                emailSenderService.notifyDoctor(foundPatient);
                return true;
            } else {
                emailSenderService.notifyPatient(foundPatient);
                return false;
            }
        } else {
            return false;
        }
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

