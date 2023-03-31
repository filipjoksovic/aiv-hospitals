package com.hospital.hospital.service.interfaces;

import com.hospital.hospital.vao.Visit;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface IVisitServiceLocal {

    Visit save(Visit visit);

    Visit update(Visit visit);

    int delete(int id);

    Visit find(int id);

    List<Visit> getAll();

}
