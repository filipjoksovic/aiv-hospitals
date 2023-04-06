package com.hospital.hospital.dao.inmem;

import com.hospital.hospital.dao.interfaces.PatientDAO;
import com.hospital.hospital.vao.Patient;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PatientDAOInMemImpl implements PatientDAO {
    private static PatientDAOInMemImpl instance = new PatientDAOInMemImpl();
    private final List<Patient> dataSource = new Vector<>();
    Logger logger = Logger.getLogger(PatientDAOInMemImpl.class.toString());

    public static synchronized PatientDAOInMemImpl getInstance() {
        if (instance == null) {
            instance = new PatientDAOInMemImpl();
        }
        return instance;
    }

    public int generateId() {
        if (dataSource.size() == 0) {
            return 1;
        }
        return dataSource.stream().max(Comparator.comparing(Patient::getId)).orElseThrow(NoSuchElementException::new).getId() + 1;
    }

    @Override
    public List<Patient> getAll() {
        return dataSource;
    }

    @Override
    public Patient find(int id) {
        return dataSource.stream().filter(patient -> patient.getId() == id).findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Patient save(Patient entity) {
//        entity.setId(generateId());
        dataSource.add(entity);
        return entity;
    }

    @Override
    public Patient update(Patient entity) {
        Patient found = dataSource.stream().filter(patient -> patient.getId() == entity.getId()).findFirst().orElseThrow(NoSuchElementException::new);
        found = entity;
        return entity;
    }

    @Override
    public int delete(int id) {
        dataSource.removeIf(patient -> patient.getId() == id);
        return id;
    }

    @Override
    public List<Patient> findByDoctorId(String[] doctorIds) {
        return dataSource.stream().filter(patient -> Arrays.asList(doctorIds).contains(String.valueOf(patient.getDoctor().getId()))).collect(Collectors.toList());
    }
}
