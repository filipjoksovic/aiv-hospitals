package com.hospital.hospital.dao.mysql;

import com.hospital.hospital.dao.interfaces.DoctorDAO;
import com.hospital.hospital.vao.Doctor;
import jakarta.enterprise.inject.Model;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Model
public class DoctorDAOMySQLImpl implements DoctorDAO {

    Logger logger = Logger.getLogger(DoctorDAOMySQLImpl.class.toString());
    private static DoctorDAOMySQLImpl instance;
    @PersistenceContext(unitName = "hospitals")
    EntityManager em;
    EntityManagerFactory emf;

    public DoctorDAOMySQLImpl() {
        emf = Persistence.createEntityManagerFactory("hospitals");
        em = emf.createEntityManager();
    }

    public static synchronized DoctorDAOMySQLImpl getInstance() {
        if (instance == null) {
            instance = new DoctorDAOMySQLImpl();
        }
        return instance;
    }

    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Doctor> getAll() {
        em.clear();
        TypedQuery<Doctor> query = em.createQuery("SELECT d FROM Doctor d", Doctor.class);
        List<Doctor> doctors = query.getResultList();
        return doctors;
    }

    @Override
    public Doctor find(int id) {
        return em.find(Doctor.class, id);
    }

    @Override
    @Transactional
    public Doctor save(Doctor entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return entity;
    }

    @Override
    @Transactional
    public Doctor update(Doctor entity) {
        Doctor foundDoctor = em.find(Doctor.class, entity.getId());
        foundDoctor.setFname(entity.getFname());
        foundDoctor.setLname(entity.getLname());
        foundDoctor.setMaxPatients(entity.getMaxPatients());
        foundDoctor.setDob(entity.getDob());
        foundDoctor.setPhone(entity.getPhone());
        foundDoctor.setPatients(entity.getPatients());
        em.getTransaction().begin();
        em.merge(foundDoctor);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    @Transactional
    public int delete(int entityId) {
        Doctor found = em.find(Doctor.class, entityId);
        if (found != null) {
            em.getTransaction().begin();
            em.remove(found);
            em.getTransaction().commit();
            return found.getId();
        }
        return -1;
    }

    @Override
    public int getNumberOfPatients(int doctorId) {
        Doctor found = em.find(Doctor.class, doctorId);
        if (found == null) {
            return 0;
        } else {
            return 1;
        }

    }

    public List<Doctor> getAllAvailable() {
        em.clear();
        TypedQuery<Doctor> query = em.createQuery("SELECT d FROM Doctor d WHERE d.patients.size < d.maxPatients", Doctor.class);
        List<Doctor> doctors = query.getResultList();
        return doctors;
    }
}
