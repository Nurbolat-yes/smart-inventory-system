package by.nurbolat.smartinventoryapplication.controller;

import by.nurbolat.smartinventoryapplication.dto.BarcodeRequest;
import by.nurbolat.smartinventoryapplication.dto.StockUpdateRequest;
import by.nurbolat.smartinventoryapplication.entity.inventory.Stock;
import by.nurbolat.smartinventoryapplication.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("/update")
    public ResponseEntity<Stock> updateStock(@RequestBody StockUpdateRequest req) {
        return ResponseEntity.ok(stockService.updateStock(req));
    }

    @PostMapping("/barcode")
    public ResponseEntity<Stock> updateByBarcode(@RequestBody BarcodeRequest req) {
        return ResponseEntity.ok(stockService.updateStockByBarcode(req));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Stock>> getLowStock() {
        return ResponseEntity.ok(stockService.getLowStockItems());
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<Stock>> getByWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(stockService.getStockByWarehouse(warehouseId));
    }

    @GetMapping("/h3/{h3Index}")
    public ResponseEntity<List<Stock>> getByH3(@PathVariable Long h3Index) {
        return ResponseEntity.ok(stockService.getStockByH3(h3Index));
    }
}