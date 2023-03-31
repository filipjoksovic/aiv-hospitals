package com.hospital.hospital.patterns.strategy.visit.notification.implementations;

import com.hospital.hospital.patterns.strategy.visit.notification.IVisitNotificationStrategy;

public class DefaultNotificationStrategy implements IVisitNotificationStrategy {
    @Override
    public void handleNotification() {
        System.out.println("Boohoo i do nothing");
    }
}
