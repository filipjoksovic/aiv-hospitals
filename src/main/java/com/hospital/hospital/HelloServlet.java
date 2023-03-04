package com.hospital.hospital;

import com.hospital.hospital.dao.PatientDAOInMemImpl;
import com.hospital.hospital.vao.Patient;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PatientDAOInMemImpl.getInstance().save(new Patient("Patient", "1", "patient1@email.com", "+38651789648", "21-10-2002", "No note"));
        PatientDAOInMemImpl.getInstance().save(new Patient("Patient", "2", "patient2@email.com", "+38651789648", "21-10-2002", "No note"));
        PatientDAOInMemImpl.getInstance().save(new Patient("Patient", "3", "patient3@email.com", "+38651789648", "21-10-2002", "No note"));
        PatientDAOInMemImpl.getInstance().save(new Patient("Patient", "4", "patient4@email.com", "+38651789648", "21-10-2002", "No note"));
        PatientDAOInMemImpl.getInstance().save(new Patient("Patient", "5", "patient5@email.com", "+38651789648", "21-10-2002", "No note"));


        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        response.sendRedirect(request.getContextPath() + "/patients");
    }

    public void destroy() {
    }
}