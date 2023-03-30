package com.hospital.hospital.dao.mysql;

import com.hospital.hospital.dao.PatientDAO;
import com.hospital.hospital.vao.Patient;
import jakarta.persistence.*;

import java.util.List;


public class PatientDaoMySQLImpl implements PatientDAO {

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
        Query query = em.createQuery("SELECT p FROM Patient p");
        return (List<Patient>) query.getResultList();
    }

    @Override
    public Patient find(int id) {
        return null;
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
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public int delete(int entityId) {
        return -1;
    }

    @Override
    public List<Patient> findByDoctorId(String[] doctorIds) {
        return null;
    }
}
