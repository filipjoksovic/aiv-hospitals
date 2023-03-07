package com.hospital.hospital.servlets.patient;

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

@WebServlet(name = "AddDoctorServlet", urlPatterns = "/addPatient")
public class AddPatientServlet extends HttpServlet {

    private final DoctorService doctorService;
    private final PatientService patientService;

    public AddPatientServlet() {
        this.doctorService = new DoctorService();
        this.patientService = new PatientService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String dob = req.getParameter("dob");
        String note = req.getParameter("note");
        int doctor_id = req.getParameter("doctor_id") != null ? Integer.parseInt(req.getParameter("doctor_id")) : -1;

        Doctor chosenDoctor = null;

        if (doctor_id != -1) {
            chosenDoctor = doctorService.find(doctor_id);
        }

        Patient toSave = patientService.save(new Patient(fname, lname, email, phone, dob, note, chosenDoctor));
        patientService.save(toSave);
        if (chosenDoctor != null) {
            chosenDoctor.getPatients().add(toSave);
            doctorService.update(chosenDoctor);

        }

        resp.sendRedirect(req.getContextPath() + "/patients");
    }
}
