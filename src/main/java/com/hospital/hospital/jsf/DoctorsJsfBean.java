package com.hospital.hospital.jsf;

import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.vao.Doctor;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("doctorBean")
@SessionScoped
public class DoctorsJsfBean implements Serializable {

    private static final long serialVersionUID = 4659985386224665451L;

    private final DoctorService doctorsService = new DoctorService();

    public void save(Doctor doctor) {
        doctorsService.save(doctor);
    }

    public void update(Doctor doctor) {
        doctorsService.update(doctor);
    }

    public void delete(int doctor_id) {
        doctorsService.delete(doctor_id);
    }

    public List<Doctor> getAll() {
        return doctorsService.getAll();
    }

    public void find(int doctor_id) {
        doctorsService.find(doctor_id);
    }
}
