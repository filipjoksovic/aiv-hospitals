package com.hospital.hospital.dao.mysql;

import com.hospital.hospital.dao.interfaces.PatientDAO;
import com.hospital.hospital.vao.Patient;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class PatientDaoMySQLImpl implements PatientDAO {

    Logger logger = LoggerFactory.getLogger(PatientDaoMySQLImpl.class);
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
        logger.info("Attempting to update patient with id {}", entity.getId());
        Patient found = em.find(Patient.class, entity.getId());
        if (found == null) {
            logger.error("Patient with id {} not found in db", entity.getId());
            return null;
        }
        try {
            found.setDob(entity.getDob());
            found.setPhone(entity.getPhone());
            found.setLname(entity.getLname());
            found.setFname(entity.getFname());
            found.setNote(entity.getNote());
            found.setEmail(entity.getEmail());
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            logger.info("Successfully updated patient with id {}", entity.getId());
        } catch (Exception e) {
            logger.error("Error while updating patient with id {}. Rolling back", entity.getId());
            return null;
        }
        return entity;
    }

    @Override
    public int delete(int entityId) {
        Patient found = em.find(Patient.class, entityId);
        if (found == null) {
            logger.error("Patient with id {} not found in db", entityId);
            return -1;
        }
        logger.info("Found patient with id {}", entityId);
        try {
            em.getTransaction().begin();
            em.remove(found);
            em.getTransaction().commit();
            logger.info("Successfully deleted patient with id {}", entityId);
            return entityId;
        } catch (Exception e) {
            logger.info("Error while deleting patient with id {}. Rolling back", entityId);
            return -1;
        }
    }

    @Override
    public List<Patient> findByDoctorId(String[] doctorIds) {
        return null;
    }
}
