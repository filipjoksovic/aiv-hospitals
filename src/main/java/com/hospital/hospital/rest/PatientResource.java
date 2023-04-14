package com.hospital.hospital.rest;

import com.hospital.hospital.dto.AddPatientReqDto;
import com.hospital.hospital.dto.PatientJSONDto;
import com.hospital.hospital.service.interfaces.IDoctorServiceRemote;
import com.hospital.hospital.service.interfaces.IPatientServiceLocal;
import com.hospital.hospital.service.interfaces.IPatientServiceRemote;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    @EJB
    IDoctorServiceRemote doctorService;
    @EJB
    IPatientServiceLocal patientServiceLocal;
    @EJB
    IPatientServiceRemote patientService;

    @GET
    @Path("/all")
    public List<PatientJSONDto> getAll(@QueryParam("available") boolean available) {
        List<PatientJSONDto> dtos = new ArrayList<>();
        for (Patient p : patientService.getAll()) {
            dtos.add(PatientJSONDto.to(p));
        }
        return dtos;
    }

    @GET
    @Path("/{id}")
    public PatientJSONDto find(@PathParam("id") int id) {
        return PatientJSONDto.to(patientService.find(id));
    }

    @POST
    public PatientJSONDto save(PatientJSONDto patientJSONDto) {
        Patient patient = patientJSONDto.from(patientJSONDto);

        return PatientJSONDto.to(patientServiceLocal.save(patient));
    }

    @POST
    @Path("/addDoctor")
    public PatientJSONDto addDoctor(AddPatientReqDto reqDto) throws Exception {
        Patient patient = patientService.find(reqDto.getPatientId());
        Doctor doctor = doctorService.find(reqDto.getDoctorId());
        if (patient == null || doctor == null) {
            return null;
        } else {
            patientService.addDoctorToPatient(patient.getId(), doctor.getId());
            Doctor found = doctorService.find(doctor.getId());
            return PatientJSONDto.to(patient);
        }
    }
}
