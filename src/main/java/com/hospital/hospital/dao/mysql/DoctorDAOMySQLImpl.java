package com.hospital.hospital.dao.mysql;

import com.hospital.hospital.dao.DoctorDAO;
import com.hospital.hospital.vao.Doctor;
import jakarta.enterprise.inject.Model;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Model
public class DoctorDAOMySQLImpl implements DoctorDAO {

    private static DoctorDAOMySQLImpl instance;
    Logger logger = LoggerFactory.getLogger(DoctorDAOMySQLImpl.class);
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
//        em.clear();
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
                logger.error("Error when saving doctor with id {}", entity.getId());
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
        em.getTransaction().begin();
        em.merge(foundDoctor);
        em.getTransaction().commit();
        return entity;
    }

    @Override
    @Transactional
    public int delete(int entityId) {
        Doctor found = em.find(Doctor.class, entityId);
        logger.info("Finding doctor with id {}", entityId);
        if (found != null) {
            logger.info("Doctor with id {} found", entityId);
            em.getTransaction().begin();
            em.remove(found);
            em.getTransaction().commit();
            return found.getId();
        }
        logger.error("Doctor not found. Provided id {}", entityId);
        return -1;
    }

    @Override
    public int getNumberOfPatients(int doctorId) {
        logger.info("Finding doctor with id {}", doctorId);
        Doctor found = em.find(Doctor.class, doctorId);
        if (found == null) {
            logger.error("Doctor with id {} not found", doctorId);
            return 0;
        } else {
            logger.info("Doctor with id {} found", doctorId);
            return 1;
        }

    }
}
