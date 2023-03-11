//package com.hospital.hospital.servlets.email;
//
//import com.hospital.hospital.dao.Email;
//import com.hospital.hospital.service.EmailSenderService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet(name = "EmailServlet", urlPatterns = "/email")
//public class EmailServlet extends HttpServlet {
//    EmailSenderService emailSenderService = new EmailSenderService();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Email email = new Email();
//
//        try {
//            email.send();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        resp.getWriter().println("Done");
//    }
//}
