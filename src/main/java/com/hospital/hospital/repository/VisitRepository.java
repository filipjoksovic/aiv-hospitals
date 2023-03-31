package com.hospital.hospital.repository;

import com.hospital.hospital.dao.mysql.VisitDAOMySQLImpl;
import com.hospital.hospital.vao.Visit;

import java.util.List;

public class VisitRepository {

    public Visit save(Visit visit) {
        return VisitDAOMySQLImpl.getInstance().save(visit);
    }

    public Visit update(Visit visit) {
        return VisitDAOMySQLImpl.getInstance().update(visit);
    }

    public int delete(int id) {
        return VisitDAOMySQLImpl.getInstance().delete(id);
    }

    public Visit find(int id) {
        return VisitDAOMySQLImpl.getInstance().find(id);
    }

    public List<Visit> getAll() {
        return VisitDAOMySQLImpl.getInstance().getAll();
    }


}
