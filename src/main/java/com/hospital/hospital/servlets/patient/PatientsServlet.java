package com.hospital.hospital.servlets.patient;

import com.hospital.hospital.dao.DoctorDAOInMemImpl;
import com.hospital.hospital.dao.PatientDAOInMemImpl;
import com.hospital.hospital.dto.doctor.DoctorIdFnameLnameDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "PatientServlet", urlPatterns = "/patients")
public class PatientsServlet extends HttpServlet {

    public void init() {
    }

    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("patients", PatientDAOInMemImpl.getInstance().getAll());
        List<DoctorIdFnameLnameDTO> doctors = DoctorDAOInMemImpl.getInstance().getAll().stream().map(DoctorIdFnameLnameDTO::toDto).collect(Collectors.toList());
        req.setAttribute("doctors", doctors);
        req.getRequestDispatcher("patients/patients.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
