package com.hospital.hospital.vao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor implements Serializable {


    private static final long serialVersionUID = 4408855420657862980L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String dob;


    private int maxPatients;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Visit> visits;

    public Doctor() {
        super();
    }

    public Doctor(int maxPatients, List<Patient> patients) {
        this.maxPatients = maxPatients;
        this.patients = patients;
    }

    public Doctor(String fname, String lname, String email, String phone, String dob, int maxPatients, List<Patient> patients) {
        this(fname, lname, email, phone, dob);
        this.maxPatients = maxPatients;
        this.patients = patients;
    }

    public Doctor(String fname, String lname, String email, String phone, String dob, int maxPatients) {
        this(fname, lname, email, phone, dob);
        this.maxPatients = maxPatients;
    }

    public Doctor(int id, String fname, String lname, String email, String phone, String dob, int maxPatients, List<Patient> patients) {
        this(id, fname, lname, email, phone, dob);
        this.maxPatients = maxPatients;
        this.patients = patients;
    }

    public Doctor(String fname, String lname, String email, String phone, String dob) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public Doctor(int id, String fname, String lname, String email, String phone, String dob) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getNumberOfPatients() {
        return this.patients != null ? this.patients.size() : 0;
    }

    public String getFullName() {
        return this.fname + " " + this.lname;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
