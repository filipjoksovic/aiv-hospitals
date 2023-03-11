package com.hospital.hospital.vao;

import java.util.List;
import java.util.Vector;

public class Doctor extends Person {

    private int maxPatients;
    private List<Patient> patients = new Vector<>();

    public Doctor() {
        super();
        this.setId(-1);
    }

    public Doctor(int maxPatients, List<Patient> patients) {
        this.maxPatients = maxPatients;
        this.patients = patients;
    }

    public Doctor(String fname, String lname, String email, String phone, String dob, int maxPatients, List<Patient> patients) {
        super(fname, lname, email, phone, dob);
        this.maxPatients = maxPatients;
        this.patients = patients;
    }

    public Doctor(String fname, String lname, String email, String phone, String dob, int maxPatients) {
        super(fname, lname, email, phone, dob);
        this.maxPatients = maxPatients;
    }

    public Doctor(int id, String fname, String lname, String email, String phone, String dob, int maxPatients, List<Patient> patients) {
        super(id, fname, lname, email, phone, dob);
        this.maxPatients = maxPatients;
        this.patients = patients;
    }

    public int getMaxPatients() {
        return maxPatients;
    }

    public void setMaxPatients(int maxPatients) {
        this.maxPatients = maxPatients;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
