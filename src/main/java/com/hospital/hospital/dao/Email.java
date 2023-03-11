package com.hospital.hospital.dao;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.naming.InitialContext;
import java.io.Serializable;

@Named
@SessionScoped
public class Email implements Serializable {

    private static final long serialVersionUID = 1544680932114626710L;

    private Session mySession;

    private String to;

    private String from;

    private String subject;

    private String body;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void send() throws Exception {
        InitialContext ctx = new InitialContext();
        mySession = (Session) ctx.lookup("java:jboss/mail/hospitals");
        Message message = new MimeMessage(mySession);
        message.setFrom(new InternetAddress("filip.joksovic@student.um.si"));
        Address toAddress = new InternetAddress("filipjoksovic1@gmail.com");
        message.addRecipient(Message.RecipientType.TO, toAddress);
        message.setSubject("aaa");
        message.setContent("Biiiitch", "text/plain");
        Transport.send(message);
    }
}