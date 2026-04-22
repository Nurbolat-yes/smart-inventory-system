package by.nurbolat.smartinventoryapplication.service;

import by.nurbolat.smartinventoryapplication.dto.WarehouseRequest;
import by.nurbolat.smartinventoryapplication.entity.inventory.Warehouse;
import by.nurbolat.smartinventoryapplication.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public Warehouse createWarehouse(WarehouseRequest req) {
        Warehouse w = Warehouse.builder()
                .name(req.getName())
                .address(req.getAddress())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .createdAt(LocalDateTime.now())
                .build();
        return warehouseRepository.save(w);
    }

    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    public Warehouse getById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found: " + id));
    }

    public List<Warehouse> getByH3Index(Long h3Index) {
        return warehouseRepository.findByH3Index(h3Index);
    }
}
