package com.hospital.hospital.rest;

import com.hospital.hospital.dto.AddPatientReqDto;
import com.hospital.hospital.dto.PatientJSONDto;
import com.hospital.hospital.service.interfaces.IDoctorServiceRemote;
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
    @Path("/addDoctor")
    public PatientJSONDto addDoctor(AddPatientReqDto reqDto) throws Exception {
        Patient patient = patientService.find(reqDto.getPatientId());
        Doctor doctor = doctorService.find(reqDto.getDoctorId());
        if (patient == null || doctor == null) {
            return null;
        } else {
            patientService.addDoctorToPatient(patient.getId(), doctor.getId());
            return PatientJSONDto.to(patient);
        }
    }

//    @POST
//    @Path("/addPatient")
//    public DoctorJSONDto addPatient(AddPatientReqDto reqDto) {
//        Patient patient = patientService.find(reqDto.getPatientId());
//        Doctor doctor = doctorService.find(reqDto.getDoctorId());
//        if (patient == null || doctor == null) {
//            return null;
//        } else {
//            doctorService.addPatient(doctor.getId(), patient.getId());
//            return DoctorJSONDto.to(doctor);
//        }
//    }
//
//    @POST
//    @Path("/removePatient")
//    public DoctorJSONDto removePatient(AddPatientReqDto reqDto) {
//        Patient patient = patientService.find(reqDto.getPatientId());
//        Doctor doctor = doctorService.find(reqDto.getDoctorId());
//        if (patient == null || doctor == null) {
//            return null;
//        } else {
//            doctorService.removePatient(doctor.getId(), patient.getId());
//            return DoctorJSONDto.to(doctor);
//        }
//    }
//
//    @GET
//    @Path("{id}/patients")
//    public List<DoctorsPatientJSONDto> getPatients(@PathParam("id") int doctorId) {
//        Doctor doctor = doctorService.find(doctorId);
//        if (doctor == null) {
//            return null;
//        } else {
//            return DoctorJSONDto.to(doctor).getPatients();
//        }
//    }
}
