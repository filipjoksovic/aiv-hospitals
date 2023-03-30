package com.hospital.hospital.dao.inmem;

import com.hospital.hospital.dao.interfaces.DoctorDAO;
import com.hospital.hospital.vao.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

public class DoctorDAOInMemImpl implements DoctorDAO {


    private static DoctorDAOInMemImpl instance;
    private final List<Doctor> dataSource = new Vector<>();
    Logger logger = LoggerFactory.getLogger(DoctorDAOInMemImpl.class);


    public static synchronized DoctorDAOInMemImpl getInstance() {
        if (instance == null) {
            instance = new DoctorDAOInMemImpl();
        }
        return instance;
    }

    public int generateId() {
        return this.dataSource.size() > 0 ? this.dataSource.stream().max(Comparator.comparing(Doctor::getId)).orElseThrow(NoSuchElementException::new).getId() + 1 : 1;
    }

    @Override
    public List<Doctor> getAll() {
        return this.dataSource;
    }

    @Override
    public Doctor find(int id) {
        return this.dataSource.stream().filter(doctor -> doctor.getId() == id).findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Doctor save(Doctor entity) {
        entity.setId(this.generateId());
        logger.info("Saving entity with id: {} ", entity.getId());
        this.dataSource.add(entity);
        return entity;
    }

    @Override
    public Doctor update(Doctor entity) {
        Doctor found = dataSource.stream().filter(doctor -> doctor.getId() == entity.getId()).findFirst().orElseThrow(NoSuchElementException::new);
        found = entity;
        return entity;
    }

    @Override
    public int delete(int entityId) {
        dataSource.removeIf(doctor -> entityId == doctor.getId());
        return 1;
    }


    @Deprecated
    @Override
    /**
     * Will never be implemented, since there is no more use for inMemDAO. Cry about it
     * @author Filip Joksovic
     * @deprecated
     */
    public int getNumberOfPatients(int doctorId) {
        return 0;
    }

}
