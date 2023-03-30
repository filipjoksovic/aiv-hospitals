package com.hospital.hospital.patterns.observer;

import com.hospital.hospital.dto.PatientListNotification;
import com.hospital.hospital.enums.PatientListAction;
import com.hospital.hospital.service.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoctorChangeObserver extends Observer<PatientListNotification> {

    private final Logger logger = LoggerFactory.getLogger(DoctorChangeObserver.class);

    public DoctorChangeObserver(Subject<PatientListNotification> subject) {
        super(subject);
    }

    @Override
    public void update() {
        logger.info("Doctor for patient changed");
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
