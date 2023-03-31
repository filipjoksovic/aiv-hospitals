package com.hospital.hospital.service;

import com.hospital.hospital.patterns.strategy.visit.notification.NotificationStrategyContext;
import com.hospital.hospital.repository.VisitRepository;
import com.hospital.hospital.service.interfaces.IVisitServiceLocal;
import com.hospital.hospital.service.interfaces.IVisitServiceRemote;
import com.hospital.hospital.vao.Visit;
import jakarta.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Stateless
public class VisitService implements IVisitServiceLocal, IVisitServiceRemote {

    private final VisitRepository visitRepository;
    Logger logger = LoggerFactory.getLogger(VisitService.class);
    NotificationStrategyContext notificationStrategyContext;


    public VisitService() {
        this.visitRepository = new VisitRepository();
    }

    @Override
    public Visit save(Visit visit) {
        try {
            Visit saved = visitRepository.save(visit);
            notificationStrategyContext = new NotificationStrategyContext(visit);
            return visitRepository.save(visit);
        } catch (Exception e) {
            logger.error("Error when saving visit with id {}", visit.getId());
        }
        return null;
    }

    @Override
    public Visit update(Visit visit) {
        try {
            Visit updated = visitRepository.update(visit);
            notificationStrategyContext = new NotificationStrategyContext(visit);
            return updated;
        } catch (Exception e) {
            logger.error("Error when updating visit with id {}", visit.getId());
        }
        return null;
    }

    @Override
    public int delete(int id) {
        return visitRepository.delete(id);
    }

    @Override
    public Visit find(int id) {
        return visitRepository.find(id);
    }

    @Override
    public List<Visit> getAll() {
        return visitRepository.getAll();
    }
}
