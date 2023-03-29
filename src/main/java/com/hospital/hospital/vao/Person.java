package com.hospital.hospital.vao;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person implements Serializable {

    private static final long serialVersionUID = 7447025625103498705L;
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String dob;

    public Person() {
    }

    public Person(String fname, String lname, String email, String phone, String dob) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public Person(int id, String fname, String lname, String email, String phone, String dob) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && Objects.equals(getFname(), person.getFname()) && Objects.equals(getLname(), person.getLname()) && Objects.equals(getEmail(), person.getEmail()) && Objects.equals(getPhone(), person.getPhone()) && Objects.equals(getDob(), person.getDob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFname(), getLname(), getEmail(), getPhone(), getDob());
    }
}
