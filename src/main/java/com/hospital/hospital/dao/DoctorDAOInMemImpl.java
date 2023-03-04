package com.hospital.hospital.dao;

import com.hospital.hospital.vao.Doctor;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

public class DoctorDAOInMemImpl implements DoctorDAO {


    private static DoctorDAOInMemImpl instance;
    private final List<Doctor> dataSource = new Vector<>();

    public static synchronized DoctorDAOInMemImpl getInstance() {
        if (instance == null) {
            instance = new DoctorDAOInMemImpl();
        }
        return instance;
    }

    @Override
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
    public Doctor delete(Doctor entity) {
        dataSource.removeIf(doctor -> entity.getId() == doctor.getId());
        return entity;
    }

    public int delete(int id) {
        Doctor foundDoctor = this.find(id);
        this.delete(foundDoctor);
        return id;
    }
}
