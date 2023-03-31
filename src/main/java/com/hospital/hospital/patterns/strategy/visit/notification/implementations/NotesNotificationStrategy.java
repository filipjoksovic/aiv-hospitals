package com.hospital.hospital.patterns.strategy.visit.notification.implementations;

import com.hospital.hospital.patterns.strategy.visit.notification.IVisitNotificationStrategy;
import com.hospital.hospital.service.EmailSenderService;

public class NotesNotificationStrategy implements IVisitNotificationStrategy {
    private final String content;
    private final String email;

    public NotesNotificationStrategy(String content, String email) {
        this.content = content;
        this.email = email;
    }

    @Override
    public void handleNotification() throws Exception {
        System.out.println("Notes notification");
        EmailSenderService.getInstance().sendNotesReport(content, email);
    }
}
