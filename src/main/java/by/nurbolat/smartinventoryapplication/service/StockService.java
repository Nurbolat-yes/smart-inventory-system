package by.nurbolat.smartinventoryapplication.service;

import by.nurbolat.smartinventoryapplication.dto.BarcodeRequest;
import by.nurbolat.smartinventoryapplication.dto.StockUpdateRequest;
import by.nurbolat.smartinventoryapplication.entity.audit.AuditLog;
import by.nurbolat.smartinventoryapplication.entity.inventory.Product;
import by.nurbolat.smartinventoryapplication.entity.inventory.Stock;
import by.nurbolat.smartinventoryapplication.entity.inventory.Warehouse;
import by.nurbolat.smartinventoryapplication.event.LowStockEvent;
import by.nurbolat.smartinventoryapplication.repository.AuditLogRepository;
import by.nurbolat.smartinventoryapplication.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final AuditLogRepository auditLogRepository;
    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Stock updateStock(StockUpdateRequest req) {
        Product product = productService.getById(req.getProductId());
        Warehouse warehouse = warehouseService.getById(req.getWarehouseId());

        Stock stock = stockRepository
                .findByProduct_ProductIdAndWarehouse_WarehouseId(req.getProductId(), req.getWarehouseId())
                .orElseGet(() -> {
                    Stock s = new Stock();
                    s.setProduct(product);
                    s.setWarehouse(warehouse);
                    s.setQuantity(0);
                    s.setH3Index(warehouse.getH3Index());
                    return s;
                });

        int oldQty = stock.getQuantity();
        int newQty = oldQty + req.getQuantity();
        if (newQty < 0) throw new RuntimeException("Stock cannot go negative");

        stock.setQuantity(newQty);
        stock.setLastUpdated(LocalDateTime.now());
        stockRepository.save(stock);

        // Audit
        String action = req.getQuantity() >= 0 ? "ADD_STOCK" : "REMOVE_STOCK";
        AuditLog log = AuditLog.builder()
                .action(action)
                .product(product)
                .warehouse(warehouse)
                .quantityChange(req.getQuantity())
                .performedBy(req.getPerformedBy())
                .timestamp(LocalDateTime.now())
                .build();
        auditLogRepository.save(log);

        // Fire event if low stock
        if (newQty <= product.getRestockThreshold()) {
            eventPublisher.publishEvent(new LowStockEvent(this, stock));
        }

        return stock;
    }

    @Transactional
    public Stock updateStockByBarcode(BarcodeRequest req) {
        Product product = productService.findByBarcode(req.getBarcode());
        StockUpdateRequest sur = new StockUpdateRequest();
        sur.setProductId(product.getProductId());
        sur.setWarehouseId(req.getWarehouseId());
        sur.setQuantity(req.getQuantity());
        sur.setPerformedBy(req.getPerformedBy());
        return updateStock(sur);
    }

    public List<Stock> getLowStockItems() {
        return stockRepository.findLowStockItems();
    }

    public List<Stock> getStockByWarehouse(Long warehouseId) {
        return stockRepository.findByWarehouse_WarehouseId(warehouseId);
    }

    public List<Stock> getStockByH3(Long h3Index) {
        return stockRepository.findByH3Index(h3Index);
    }
}