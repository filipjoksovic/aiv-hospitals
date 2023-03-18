package com.hospital.hospital.service;

import com.hospital.hospital.vao.Patient;
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
public class EmailSenderService implements Serializable {


    private static final long serialVersionUID = 7791113143224082345L;

    public void notifyDoctor(Patient foundPatient) throws Exception {
        this.send("", "", "A patient has chosen you", "The patient " + foundPatient.getFname() + " " + foundPatient.getLname() + " has chosen you as their doctor. To see their data, go to the application.");
    }

    public void notifyPatient(Patient foundPatient) throws Exception {
        this.send("", "", "Error when choosing doctor", "There has been an error when choosing your doctor. Perhaps the quota is full, or a server error was encountered.");
    }

    private void send(String to, String from, String subject, String body) throws Exception {
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
