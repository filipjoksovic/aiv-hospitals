package com.hospital.hospital.dao.mysql;

import com.hospital.hospital.dao.interfaces.VisitDAO;
import com.hospital.hospital.vao.Visit;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Stateless
public class VisitDAOMySQLImpl implements VisitDAO {

    private static VisitDAOMySQLImpl instance;
    Logger logger = Logger.getLogger(VisitDAOMySQLImpl.class.toString());
    @PersistenceContext(unitName = "hospitals", type = PersistenceContextType.TRANSACTION)
    EntityManager em;
    EntityManagerFactory emf;

    public VisitDAOMySQLImpl() {
        emf = Persistence.createEntityManagerFactory("hospitals");
        em = emf.createEntityManager();
        em.setProperty("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
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
        Visit found = em.find(Visit.class, id);
        if (found == null) {
            throw new NoSuchElementException("Visit with id " + id + " not found");
        }
        em.refresh(found);
        return em.find(Visit.class, id);
    }

    @Override
    public Visit save(Visit entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return entity;
    }

    @Override
    public Visit update(Visit entity) {
        Visit found = em.find(Visit.class, entity.getId());
        if (found == null) {
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
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
        }
        return entity;
    }

    @Override
    public int delete(int id) {
        Visit found = em.find(Visit.class, id);
        if (found == null) {
            return -1;
        }
        try {
            em.getTransaction().begin();
            em.remove(found);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return -1;
        }
        return 0;
    }
}
