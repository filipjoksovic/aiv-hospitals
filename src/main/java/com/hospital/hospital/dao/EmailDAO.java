package com.hospital.hospital.dao;

public class EmailDAO {

    public static EmailDAO instance;

    public static synchronized EmailDAO getInstance() {
        if (instance == null) {
            instance = new EmailDAO();
        }
        return instance;
    }

    public void setupEmailSender(String from){
        

    }


}
