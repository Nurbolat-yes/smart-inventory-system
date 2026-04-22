package by.nurbolat.smartinventoryapplication.service;
import by.nurbolat.smartinventoryapplication.entity.analitics.InventoryAnalytics;
import by.nurbolat.smartinventoryapplication.entity.inventory.Stock;
import by.nurbolat.smartinventoryapplication.repository.InventoryAnalyticsRepository;
import by.nurbolat.smartinventoryapplication.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final StockRepository stockRepository;
    private final InventoryAnalyticsRepository analyticsRepository;

    public List<InventoryAnalytics> aggregateByH3() {
        List<Stock> all = stockRepository.findAll();

        Map<Long, List<Stock>> grouped = all.stream()
                .filter(s -> s.getH3Index() != null)
                .collect(Collectors.groupingBy(Stock::getH3Index));

        grouped.forEach((h3, stocks) -> {
            InventoryAnalytics analytics = analyticsRepository
                    .findByH3Index(h3)
                    .orElseGet(() -> {
                        InventoryAnalytics a = new InventoryAnalytics();
                        a.setH3Index(h3);
                        return a;
                    });
            analytics.setTotalProducts((int) stocks.stream()
                    .map(s -> s.getProduct().getProductId()).distinct().count());
            analytics.setTotalQuantity(stocks.stream()
                    .mapToInt(Stock::getQuantity).sum());
            analytics.setLastAggregated(LocalDateTime.now());
            analyticsRepository.save(analytics);
        });

        return analyticsRepository.findAll();
    }

    public List<InventoryAnalytics> getAll() {
        return analyticsRepository.findAll();
    }
}