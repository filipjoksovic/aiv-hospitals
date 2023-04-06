package com.hospital.hospital.patterns.observer;

import com.hospital.hospital.dto.PatientListNotification;
import com.hospital.hospital.enums.PatientListAction;
import com.hospital.hospital.service.EmailSenderService;

import java.util.logging.Logger;

public class DoctorChangeObserver extends Observer<PatientListNotification> {

    private final Logger logger = Logger.getLogger(DoctorChangeObserver.class.toString());

    public DoctorChangeObserver(Subject<PatientListNotification> subject) {
        super(subject);
    }

    @Override
    public void update() {
        PatientListNotification state = this.subject.getState();
        if (state.getAction() == PatientListAction.SELECT) {
            try {
                EmailSenderService.getInstance().notifyPatientAboutSelection(state.getPatient(), state.getDoctor());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                EmailSenderService.getInstance().notifyPatientAboutRemoval(state.getPatient(), state.getDoctor());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
