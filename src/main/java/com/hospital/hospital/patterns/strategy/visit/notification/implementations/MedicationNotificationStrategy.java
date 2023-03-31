package com.hospital.hospital.patterns.strategy.visit.notification.implementations;

import com.hospital.hospital.patterns.strategy.visit.notification.IVisitNotificationStrategy;
import com.hospital.hospital.service.EmailSenderService;

public class MedicationNotificationStrategy implements IVisitNotificationStrategy {

    private final String content;
    private final String email;

    public MedicationNotificationStrategy(String content, String email) {
        this.content = content;
        this.email = email;
    }

    @Override
    public void handleNotification() throws Exception {
        System.out.println("Medication notification");
        EmailSenderService.getInstance().sendMedicationReport(content, email);
    }
}
