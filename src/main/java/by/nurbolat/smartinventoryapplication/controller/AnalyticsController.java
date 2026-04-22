package by.nurbolat.smartinventoryapplication.controller;

import by.nurbolat.smartinventoryapplication.entity.analitics.InventoryAnalytics;
import by.nurbolat.smartinventoryapplication.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping
    public ResponseEntity<List<InventoryAnalytics>> getAll() {
        return ResponseEntity.ok(analyticsService.getAll());
    }

    @PostMapping("/aggregate")
    public ResponseEntity<List<InventoryAnalytics>> aggregate() {
        return ResponseEntity.ok(analyticsService.aggregateByH3());
    }
}