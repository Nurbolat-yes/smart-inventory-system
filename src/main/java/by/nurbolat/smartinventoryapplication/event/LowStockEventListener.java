package by.nurbolat.smartinventoryapplication.event;
import by.nurbolat.smartinventoryapplication.entity.audit.LowStockAlert;
import by.nurbolat.smartinventoryapplication.entity.inventory.Stock;
import by.nurbolat.smartinventoryapplication.repository.LowStockAlertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class LowStockEventListener {

    private final LowStockAlertRepository alertRepository;

    @EventListener
    public void handleLowStockEvent(LowStockEvent event) {
        Stock stock = event.getStock();

        // avoid duplicate unresolved alerts
        boolean alreadyOpen = !alertRepository
                .findByProduct_ProductIdAndIsResolvedFalse(stock.getProduct().getProductId())
                .isEmpty();

        if (!alreadyOpen) {
            LowStockAlert alert = new LowStockAlert();
            alert.setProduct(stock.getProduct());
            alert.setWarehouse(stock.getWarehouse());
            alert.setCurrentQuantity(stock.getQuantity());
            alert.setTriggeredAt(LocalDateTime.now());
            alert.setIsResolved(false);
            alertRepository.save(alert);
            log.warn("LOW STOCK ALERT: Product '{}' in Warehouse '{}' has only {} units.",
                    stock.getProduct().getName(),
                    stock.getWarehouse().getName(),
                    stock.getQuantity());
        }
    }
}