package com.hospital.hospital.service;

import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.naming.InitialContext;

public class EmailSenderService {
    private Session mySession;

    public void send(String to, String from, String subject, String body) throws Exception {
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
