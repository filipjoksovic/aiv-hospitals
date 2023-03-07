package com.hospital.hospital.servlets.doctor;

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

@WebServlet(name = "RemovePatient", urlPatterns = "/removePatient")
public class RemoveDoctorsPatientServlet extends HttpServlet {

    private final DoctorService doctorService;
    private final PatientService patientService;

    public RemoveDoctorsPatientServlet() {
        this.doctorService = new DoctorService();
        this.patientService = new PatientService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("patientId") == null || req.getParameter("doctorId") == null) {
            resp.sendRedirect(req.getHeader("referer"));
        } else {
            int patientid = Integer.parseInt(req.getParameter("patientId"));
            int doctorid = Integer.parseInt(req.getParameter("doctorId"));
            Doctor foundDoctor = doctorService.find(doctorid);

            if (foundDoctor != null) {
                foundDoctor.getPatients().removeIf(p -> p.getId() == patientid);
                Patient foundPatient = patientService.find(patientid);
                if (foundPatient != null) {
                    foundPatient.setDoctor(null);
                }
            }
            resp.sendRedirect(req.getHeader("referer"));
        }

    }
}
