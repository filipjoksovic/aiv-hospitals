package com.hospital.hospital.dao.mysql;

import com.hospital.hospital.dao.interfaces.PatientDAO;
import com.hospital.hospital.vao.Patient;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.logging.Logger;


public class PatientDaoMySQLImpl implements PatientDAO {

    Logger logger = Logger.getLogger(PatientDaoMySQLImpl.class.toString());
    private static PatientDaoMySQLImpl instance;
    @PersistenceContext(unitName = "hospitals")
    EntityManager em;
    EntityManagerFactory emf;

    public PatientDaoMySQLImpl() {
        emf = Persistence.createEntityManagerFactory("hospitals");
        em = emf.createEntityManager();
    }

    public static synchronized PatientDaoMySQLImpl getInstance() {
        if (instance == null) {
            instance = new PatientDaoMySQLImpl();
        }
        return instance;
    }

    @Override
    public List<Patient> getAll() {
        TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient p", Patient.class);
        return query.getResultList();
    }

    @Override
    public Patient find(int id) {
        return em.find(Patient.class, id);
    }

    @Override
    public Patient save(Patient entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public Patient update(Patient entity) {
        Patient found = em.find(Patient.class, entity.getId());
        if (found == null) {
            return null;
        }
        try {
            found.setDob(entity.getDob());
            found.setPhone(entity.getPhone());
            found.setLname(entity.getLname());
            found.setFname(entity.getFname());
            found.setNote(entity.getNote());
            found.setEmail(entity.getEmail());
            found.setDoctor(entity.getDoctor());
            em.getTransaction().begin();
            em.merge(found);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.severe("Failed to update patient with id: " + entity.getId());
            e.printStackTrace();
            return null;
        }
        return entity;
    }

    @Override
    @Transactional
    public int delete(int entityId) {
        logger.info("Attempting to delete patient with id: " + entityId);
        Patient found = em.find(Patient.class, entityId);
        if (found == null) {
            logger.info("Patient with id: " + entityId + " not found");
            return -1;
        }
        try {
            em.getTransaction().begin();
            em.remove(found);
            em.getTransaction().commit();
            logger.info("Should be deleted now");
            return entityId;
        } catch (Exception e) {
            logger.severe("Failed to delete patient with id: " + entityId);
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Patient> findByDoctorId(String[] doctorIds) {
        return null;
    }
}
