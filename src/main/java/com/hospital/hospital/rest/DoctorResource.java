package com.hospital.hospital.rest;

import com.hospital.hospital.dto.AddPatientReqDto;
import com.hospital.hospital.dto.DoctorJSONDto;
import com.hospital.hospital.dto.DoctorsPatientJSONDto;
import com.hospital.hospital.service.interfaces.IDoctorServiceLocal;
import com.hospital.hospital.service.interfaces.IDoctorServiceRemote;
import com.hospital.hospital.service.interfaces.IPatientServiceRemote;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/doctors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorResource {

    @EJB
    IDoctorServiceRemote doctorService;
    @EJB
    IDoctorServiceLocal doctorServiceLocal;
    @EJB
    IPatientServiceRemote patientService;

    @GET
    @Path("/all")
    public List<DoctorJSONDto> getAll(@QueryParam("available") boolean available) {
        List<DoctorJSONDto> dtos = new ArrayList<>();
        for (Doctor d : available ? doctorService.getAll() : doctorService.getAllAvailable()) {
            dtos.add(DoctorJSONDto.to(d));
        }
        return dtos;
    }

    @GET
    @Path("/{id}")
    public DoctorJSONDto find(@PathParam("id") int id) {
        return DoctorJSONDto.to(doctorService.find(id));
    }

    @POST
    public DoctorJSONDto save(DoctorJSONDto doctorJSONDto) {
        Doctor doctor = doctorJSONDto.from(doctorJSONDto);
        return DoctorJSONDto.to(doctorServiceLocal.save(doctor));
    }

    @POST
    @Path("/addPatient")
    public DoctorJSONDto addPatient(AddPatientReqDto reqDto) {
        Patient patient = patientService.find(reqDto.getPatientId());
        Doctor doctor = doctorService.find(reqDto.getDoctorId());
        if (patient == null || doctor == null) {
            return null;
        } else {
            doctorService.addPatient(doctor.getId(), patient.getId());
            return DoctorJSONDto.to(doctor);
        }
    }

    @POST
    @Path("/removePatient")
    public DoctorJSONDto removePatient(AddPatientReqDto reqDto) {
        Patient patient = patientService.find(reqDto.getPatientId());
        Doctor doctor = doctorService.find(reqDto.getDoctorId());
        if (patient == null || doctor == null) {
            return null;
        } else {
            doctorService.removePatient(doctor.getId(), patient.getId());
            return DoctorJSONDto.to(doctor);
        }
    }

    @GET
    @Path("{id}/patients")
    public List<DoctorsPatientJSONDto> getPatients(@PathParam("id") int doctorId) {
        Doctor doctor = doctorService.find(doctorId);
        if (doctor == null) {
            return null;
        } else {
            return DoctorJSONDto.to(doctor).getPatients();
        }
    }
}
