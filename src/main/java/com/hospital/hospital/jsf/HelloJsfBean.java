package com.hospital.hospital.jsf;

import com.hospital.hospital.vao.HelloBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("helloBean")
@SessionScoped
public class HelloJsfBean implements Serializable {

    private static final long serialVersionUID = -8979220536758073133L;

    private HelloBean helloBeanVal = new HelloBean();

    public HelloBean getHelloBeanVal() {
        return helloBeanVal;
    }

    public void setHelloBeanVal(HelloBean helloBeanVal) {
        this.helloBeanVal = helloBeanVal;
    }

}
