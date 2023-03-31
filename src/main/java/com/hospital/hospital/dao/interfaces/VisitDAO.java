package com.hospital.hospital.dao.interfaces;

import com.hospital.hospital.vao.Visit;

import java.util.List;

public interface VisitDAO extends CrudDAO<Visit> {
    @Override
    List<Visit> getAll();

    @Override
    Visit find(int id);

    @Override
    Visit save(Visit entity);

    @Override
    Visit update(Visit entity);

    @Override
    int delete(int id);
}
