package com.hospital.hospital.servlets.config;

import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.service.PatientService;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();

        Patient p1 = (new Patient("Patient", "1", "patient1@email.com", "+38651789648", "21-10-2002", "No note"));
        Patient p2 = (new Patient("Patient", "2", "patient2@email.com", "+38651789648", "21-10-2002", "No note"));
        Patient p3 = (new Patient("Patient", "3", "patient3@email.com", "+38651789648", "21-10-2002", "No note"));
        Patient p4 = (new Patient("Patient", "4", "patient4@email.com", "+38651789648", "21-10-2002", "No note"));

        Doctor d1 = (new Doctor("Doctor", "1", "doctor1@email.com", "+38651789648", "21-10-2002", 10));
        Doctor d2 = (new Doctor("Doctor", "2", "doctor2@email.com", "+38651789648", "21-10-2002", 10));
        Doctor d3 = (new Doctor("Doctor", "3", "doctor3@email.com", "+38651789648", "21-10-2002", 10));
        Doctor d4 = (new Doctor("Doctor", "4", "doctor4@email.com", "+38651789648", "21-10-2002", 10));

        p1.setDoctor(d1);
        d1.getPatients().add(p1);

        p2.setDoctor(d1);
        d1.getPatients().add(p2);

        p3.setDoctor(d2);
        d2.getPatients().add(p3);

        patientService.save(p1);
        patientService.save(p2);
        patientService.save(p3);
        patientService.save(p4);

        doctorService.save(d1);
        doctorService.save(d2);
        doctorService.save(d3);
        doctorService.save(d4);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
