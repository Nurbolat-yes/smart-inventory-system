package by.nurbolat.smartinventoryapplication.controller;


import by.nurbolat.smartinventoryapplication.entity.audit.LowStockAlert;
import by.nurbolat.smartinventoryapplication.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @GetMapping
    public ResponseEntity<List<LowStockAlert>> getAll() {
        return ResponseEntity.ok(alertService.getAll());
    }

    @GetMapping("/unresolved")
    public ResponseEntity<List<LowStockAlert>> getUnresolved() {
        return ResponseEntity.ok(alertService.getUnresolvedAlerts());
    }

    @PutMapping("/{alertId}/resolve")
    public ResponseEntity<LowStockAlert> resolve(@PathVariable Long alertId) {
        return ResponseEntity.ok(alertService.resolveAlert(alertId));
    }
}