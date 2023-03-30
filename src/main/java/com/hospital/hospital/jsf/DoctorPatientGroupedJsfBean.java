package com.hospital.hospital.jsf;

import com.hospital.hospital.dto.doctor.DoctorPatientNumberDTO;
import com.hospital.hospital.service.interfaces.IPatientServiceLocal;
import jakarta.ejb.EJB;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("doctorPatientGroupedBean")
public class DoctorPatientGroupedJsfBean implements Serializable {


    private static final long serialVersionUID = 6052676653364581497L;

    @EJB
    IPatientServiceLocal iPatientServiceLocal;

    public List<DoctorPatientNumberDTO> getGroupedPatients() {
        return iPatientServiceLocal.getGroupedPatients();
    }


}
