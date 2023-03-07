package com.hospital.hospital.servlets.patient;

import com.hospital.hospital.dto.doctor.DoctorIdFnameLnameDTO;
import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "PatientDoctor", urlPatterns = "/patientDoctor")
public class PatientDoctorServlet extends HttpServlet {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public PatientDoctorServlet() {
        this.doctorRepository = new DoctorRepository();
        this.patientRepository = new PatientRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<DoctorIdFnameLnameDTO> doctorsList = doctorRepository.getAll().stream().map(DoctorIdFnameLnameDTO::toDto).collect(Collectors.toList());

        req.setAttribute("doctorOptions", doctorsList);
        List<Doctor> doctors = doctorRepository.getAll();
        List<Patient> patients = patientRepository.getAll();

        Map<String, Integer> doctorPatientCount = new HashMap<>();

        for (Patient patient : patients) {
            Doctor chosenDoctor = patient.getDoctor();
            if (chosenDoctor == null) {
                doctorPatientCount.put("No doctor selected", doctorPatientCount.getOrDefault("No doctor selected", 0) + 1);
            } else {
                doctorPatientCount.put(chosenDoctor.getFname() + " " + chosenDoctor.getLname(), doctorPatientCount.getOrDefault(chosenDoctor.getFname() + " " + chosenDoctor.getLname(), 0) + 1);
            }
        }

        req.setAttribute("report", doctorPatientCount);

        req.getRequestDispatcher("/patients/patientDoctorData.jsp").forward(req, resp);
    }
}
