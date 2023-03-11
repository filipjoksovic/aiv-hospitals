package com.hospital.hospital.servlets.patient;

import com.hospital.hospital.dto.doctor.DoctorIdFnameLnameDTO;
import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.service.PatientService;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "PatientDetails", urlPatterns = "/patientDetails")
public class PatientDetailsServlet extends HttpServlet {

    private final DoctorService doctorService;
    private final PatientService patientService;

    public PatientDetailsServlet() {
        this.doctorService = new DoctorService();
        this.patientService = new PatientService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.sendRedirect(req.getContextPath() + "/patients");
        }

        int patientId = Integer.parseInt(req.getParameter("id"));

        Patient foundPatient = patientService.find(patientId);
        if (foundPatient == null) {
            resp.sendRedirect(req.getContextPath() + "/patients");
        } else {
            req.setAttribute("patient", foundPatient);
            List<DoctorIdFnameLnameDTO> doctors = doctorService.getAll().stream().map(DoctorIdFnameLnameDTO::toDto).collect(Collectors.toList());
            req.setAttribute("doctors", doctors);
            req.getRequestDispatcher("/patients/patientDetails.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.sendRedirect(req.getContextPath() + "/patients");
        }

        int id = Integer.parseInt(req.getParameter("id"));
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String dob = req.getParameter("dob");
        String note = req.getParameter("note");
        int doctor_id = req.getParameter("doctor_id") != null ? Integer.parseInt(req.getParameter("doctor_id")) : -1;

        Doctor foundDoctor = doctorService.find(doctor_id);
        Patient foundPatient = patientService.find(id);
        foundPatient.setFname(fname);
        foundPatient.setLname(lname);
        foundPatient.setEmail(email);
        foundPatient.setPhone(phone);
        foundPatient.setDob(dob);
        foundPatient.setNote(note);

        foundPatient.setDoctor(foundDoctor);

        patientService.update(foundPatient);

        if (foundDoctor != null) {
            foundDoctor.getPatients().add(foundPatient);
            doctorService.update(foundDoctor);

        }
        resp.sendRedirect(req.getContextPath() + "/patients");

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.sendRedirect(req.getContextPath() + "/patients");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));

        patientService.delete(id);

        resp.sendRedirect(req.getContextPath() + "/patients");
    }
}
