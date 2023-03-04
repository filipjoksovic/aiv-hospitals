package com.hospital.hospital.servlets.doctor;

import com.hospital.hospital.dao.DoctorDAOInMemImpl;
import com.hospital.hospital.vao.Doctor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DoctorServlet", urlPatterns = "/doctors")
public class DoctorsServlet extends HttpServlet {

    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("doctors", DoctorDAOInMemImpl.getInstance().getAll());
        req.getRequestDispatcher("/doctors/doctors.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String dob = req.getParameter("dob");
        int maxPatients = req.getParameter("patient_quota") != null ? Integer.parseInt(req.getParameter("patient_quota")) : 10;

        Doctor toSave = new Doctor(fname, lname, email, phone, dob, maxPatients);
        DoctorDAOInMemImpl.getInstance().save(toSave);

        resp.sendRedirect(req.getContextPath() + "/doctors");
    }
}
