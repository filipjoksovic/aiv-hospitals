package com.hospital.hospital.dao.mysql;

import com.hospital.hospital.dao.interfaces.VisitDAO;
import com.hospital.hospital.vao.Visit;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Stateless
public class VisitDAOMySQLImpl implements VisitDAO {

    private static VisitDAOMySQLImpl instance;
    Logger logger = LoggerFactory.getLogger(VisitDAOMySQLImpl.class);
    @PersistenceContext(unitName = "hospitals")
    EntityManager em;
    EntityManagerFactory emf;

    public VisitDAOMySQLImpl() {
        emf = Persistence.createEntityManagerFactory("hospitals");
        em = emf.createEntityManager();
    }

    public static synchronized VisitDAOMySQLImpl getInstance() {
        if (instance == null) {
            instance = new VisitDAOMySQLImpl();
        }
        return instance;
    }

    @Override
    public List<Visit> getAll() {
        TypedQuery<Visit> query = em.createQuery("SELECT v FROM Visit v", Visit.class);
        return query.getResultList();
    }

    @Override
    public Visit find(int id) {
        return em.find(Visit.class, id);
    }

    @Override
    public Visit save(Visit entity) {
        logger.info("Saving visit with id {}", entity.getId());
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            logger.info("Visit with id {} saved", entity.getId());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                logger.error("Error when saving visit with id {}", entity.getId());
            }
        }
        return entity;
    }

    @Override
    public Visit update(Visit entity) {
        logger.info("Updating visit with id {}", entity.getId());
        Visit found = em.find(Visit.class, entity.getId());
        if (found == null) {
            logger.error("Visit with id {} not found", entity.getId());
            return null;
        }
        found.setDate(entity.getDate());
        found.setDoctor(entity.getDoctor());
        found.setPatient(entity.getPatient());
        found.setMedications(entity.getMedications());
        found.setNotes(entity.getNotes());
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            logger.info("Visit with id {} updated", entity.getId());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                logger.error("Error when updating visit with id {}", entity.getId());
            }
        }
        return entity;
    }

    @Override
    public int delete(int id) {
        logger.info("Deleting visit with id {}", id);
        Visit found = em.find(Visit.class, id);
        if (found == null) {
            logger.error("Visit with id {} not found", id);
            return -1;
        }
        try {
            em.getTransaction().begin();
            em.remove(found);
            em.getTransaction().commit();
            logger.info("Visit with id {} deleted", id);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                logger.error("Error when deleting visit with id {}", id);
            }
            return -1;
        }
        return 0;
    }
}
