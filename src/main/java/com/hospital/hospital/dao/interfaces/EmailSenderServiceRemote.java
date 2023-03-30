package com.hospital.hospital.dao.interfaces;

import com.hospital.hospital.vao.Patient;
import jakarta.ejb.Remote;

@Remote
public interface EmailSenderServiceRemote {

    void notifyDoctor(Patient patient) throws Exception;

    void notifyPatient(Patient patient) throws Exception;

    void send(String to, String from, String subject, String body) throws Exception;

}
