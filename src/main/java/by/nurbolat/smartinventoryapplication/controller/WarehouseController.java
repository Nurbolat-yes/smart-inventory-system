package by.nurbolat.smartinventoryapplication.controller;
import by.nurbolat.smartinventoryapplication.dto.WarehouseRequest;
import by.nurbolat.smartinventoryapplication.entity.inventory.Warehouse;
import by.nurbolat.smartinventoryapplication.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Warehouse> create(@RequestBody WarehouseRequest req) {
        return ResponseEntity.ok(warehouseService.createWarehouse(req));
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAll() {
        return ResponseEntity.ok(warehouseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getById(id));
    }

    @GetMapping("/h3/{h3Index}")
    public ResponseEntity<List<Warehouse>> getByH3(@PathVariable Long h3Index) {
        return ResponseEntity.ok(warehouseService.getByH3Index(h3Index));
    }
}