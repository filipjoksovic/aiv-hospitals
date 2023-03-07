package com.hospital.hospital.servlets.doctor;

import com.hospital.hospital.dto.patient.PatientIdFnameLnameDTO;
import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.vao.Doctor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "DoctorDetailsServlet", urlPatterns = "/doctorDetails")
public class DoctorDetailsServlet extends HttpServlet {

    private final DoctorRepository doctorRepository;

    public DoctorDetailsServlet() {
        doctorRepository = new DoctorRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.sendRedirect(req.getContextPath() + "/doctors");
            return;
        }
        resp.setContentType("text/html");
        resp.setStatus(200);
        Doctor found = doctorRepository.find(Integer.parseInt(req.getParameter("id")));
        List<PatientIdFnameLnameDTO> mappedPatients = found.getPatients().stream().map(PatientIdFnameLnameDTO::toDTO).collect(Collectors.toList());

        req.setAttribute("patients", mappedPatients);
        req.setAttribute("doctor", found);
        req.getRequestDispatcher("doctors/doctorDetails.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.sendRedirect(req.getContextPath() + "/doctors");
        }

        int id = Integer.parseInt(req.getParameter("id"));
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String dob = req.getParameter("dob");
        int maxPatients = req.getParameter("patient_quota") != null ? Integer.parseInt(req.getParameter("patient_quota")) : 10;

        Doctor foundDoctor = doctorRepository.find(id);
        foundDoctor.setFname(fname);
        foundDoctor.setLname(lname);
        foundDoctor.setEmail(email);
        foundDoctor.setPhone(phone);
        foundDoctor.setDob(dob);
        foundDoctor.setMaxPatients(maxPatients);
        doctorRepository.update(foundDoctor);
        resp.sendRedirect(req.getContextPath() + "/doctors");

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.sendRedirect(req.getContextPath() + "/doctors");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));

        doctorRepository.delete(id);

        resp.setStatus(200);
        resp.getWriter().print("all good");
    }
}
