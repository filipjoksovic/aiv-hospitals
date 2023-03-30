package com.hospital.hospital.servlets.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextListener implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {


//        Patient p1 = (new Patient("Patient", "1", "patient1@email.com", "+38651789648", "21-10-2002", "No note"));
//        Patient p2 = (new Patient("Patient", "2", "patient2@email.com", "+38651789648", "21-10-2002", "No note"));
//        Patient p3 = (new Patient("Patient", "3", "patient3@email.com", "+38651789648", "21-10-2002", "No note"));
//        Patient p4 = (new Patient("Patient", "4", "patient4@email.com", "+38651789648", "21-10-2002", "No note"));
//        Doctor d1 = (new Doctor("Doctor", "1", "doctor1@email.com", "+38651789648", "21-10-2002", 10));
//        Doctor d2 = (new Doctor("Doctor", "2", "doctor2@email.com", "+38651789648", "21-10-2002", 10));
//        Doctor d3 = (new Doctor("Doctor", "3", "doctor3@email.com", "+38651789648", "21-10-2002", 10));
//        Doctor d4 = (new Doctor("Doctor", "4", "doctor4@email.com", "+38651789648", "21-10-2002", 10));
//
//
//        logger.info("Saving doctor with id {}", d1.getId());
//        DoctorDAOMySQLImpl.getInstance().save(d1);
//        logger.info("Saved doctor with id {}", d1.getId());
//
//
//        logger.info("Saving doctor with id {}", d2.getId());
//        DoctorDAOMySQLImpl.getInstance().save(d2);
//        logger.info("Saved doctor with id {}", d2.getId());
//
//
//        logger.info("Saving doctor with id {}", d3.getId());
//        DoctorDAOMySQLImpl.getInstance().save(d3);
//        logger.info("Saved doctor with id {}", d3.getId());
//
//
//        logger.info("Saving doctor with id {}", d4.getId());
//        DoctorDAOMySQLImpl.getInstance().save(d4);
//        logger.info("Saved doctor with id {}", d4.getId());

//        Doctor d1Found = DoctorDAOMySQLImpl.getInstance().find(1);
//
//        p1.setDoctor(d1Found);

//        PatientDaoMySQLImpl.getInstance().save(p1);
//        PatientDaoMySQLImpl.getInstance().save(p2);
//        PatientDaoMySQLImpl.getInstance().save(p3);
//        PatientDaoMySQLImpl.getInstance().save(p4);


        //
//        EntityManagerFactory doctorsFactory = Persistence.createEntityManagerFactory("Doctors");
//
//        EntityManagerFactory patientsFactory = Persistence.createEntityManagerFactory("hospitals");
//
//        EntityManager doctorsManager = doctorsFactory.createEntityManager();
//        EntityManager patientsManager = patientsFactory.createEntityManager();
//
//        patientsManager.getTransaction().begin();
//
////        DoctorService doctorService = new DoctorService();
////        PatientService patientService = new PatientService();
//
//        patientsManager.persist(p1);
//        patientsManager.persist(p2);
//        patientsManager.persist(p3);
//        patientsManager.persist(p4);
//        patientsManager.getTransaction().commit();
//        patientsManager.close();
//        patientsFactory.close();
//
//        doctor
//        p1.setDoctor(d1);
//        d1.getPatients().add(p1);
//
//        p2.setDoctor(d1);
//        d1.getPatients().add(p2);
//
//        p3.setDoctor(d2);
//        d2.getPatients().add(p3);
//
//        patientService.save(p1);
//        patientService.save(p2);
//        patientService.save(p3);
//        patientService.save(p4);
//
//        doctorService.save(d1);
//        doctorService.save(d2);
//        doctorService.save(d3);
//        doctorService.save(d4);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
