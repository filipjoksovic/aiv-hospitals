package com.hospital.hospital.service;

import com.hospital.hospital.dao.EmailSenderServiceRemote;
import com.hospital.hospital.vao.Doctor;
import com.hospital.hospital.vao.Patient;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.naming.InitialContext;
import java.io.Serializable;

@Stateless
@Remote(EmailSenderServiceRemote.class)
public class EmailSenderService implements Serializable {


    public static EmailSenderService instance;

    public static synchronized EmailSenderService getInstance() {
        if (instance == null) {
            instance = new EmailSenderService();
        }
        return instance;
    }

    private static final long serialVersionUID = 7791113143224082345L;

    public void notifyDoctor(Patient foundPatient) throws Exception {
        this.send("", "", "A patient has chosen you", "The patient " + foundPatient.getFname() + " " + foundPatient.getLname() + " has chosen you as their doctor. To see their data, go to the application.");
    }

    public void notifyPatient(Patient foundPatient) throws Exception {
        this.send("", "", "Error when choosing doctor", "There has been an error when choosing your doctor. Perhaps the quota is full, or a server error was encountered.");
    }

    public void notifyPatientAboutSelection(Patient foundPatient, Doctor doctor) throws Exception {
        this.send("", "", "You have been assigned a doctor", "Doctor " + doctor.getFname() + " " + doctor.getLname() + " has added you to his list of selected patients");
    }

    public void notifyPatientAboutRemoval(Patient foundPatient, Doctor doctor) throws Exception {
        this.send("", "", "Removal from list of patients", "Doctor " + doctor.getFname() + " " + doctor.getLname() + " has removed you from his list of patients.");
    }

    public void send(String to, String from, String subject, String body) throws Exception {
        Session mySession;
        InitialContext ctx = new InitialContext();
        mySession = (Session) ctx.lookup("java:jboss/mail/hospitals");
        Message message = new MimeMessage(mySession);
        message.setFrom(new InternetAddress("filip.joksovic@student.um.si")); //irl should be replaced with from parameter
        Address toAddress = new InternetAddress("filipjoksovic1@gmail.com"); // irl shoud be raplaced with to parameter
        message.addRecipient(Message.RecipientType.TO, toAddress);
        message.setSubject(subject);
        message.setContent(body, "text/plain");
        Transport.send(message);

    }
}
