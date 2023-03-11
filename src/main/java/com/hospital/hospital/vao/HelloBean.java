package com.hospital.hospital.vao;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
