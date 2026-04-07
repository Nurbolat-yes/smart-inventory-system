package by.nurbolat.smartinventoryapplication.service;

import by.nurbolat.smartinventoryapplication.entity.audit.LowStockAlert;
import by.nurbolat.smartinventoryapplication.repository.LowStockAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final LowStockAlertRepository alertRepository;

    public List<LowStockAlert> getUnresolvedAlerts() {
        return alertRepository.findByIsResolvedFalse();
    }

    public LowStockAlert resolveAlert(Long alertId) {
        LowStockAlert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new RuntimeException("Alert not found: " + alertId));
        alert.setIsResolved(true);
        return alertRepository.save(alert);
    }

    public List<LowStockAlert> getAll() {
        return alertRepository.findAll();
    }
}