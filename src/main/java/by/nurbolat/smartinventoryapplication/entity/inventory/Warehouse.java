package by.nurbolat.smartinventoryapplication.entity.inventory;

import by.nurbolat.smartinventoryapplication.config.H3Helper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Warehouses", schema = "inventory")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;

    private String name;
    private String address;

    private Double latitude;
    private Double longitude;
    private Long h3Index;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    @PreUpdate
    public void computeH3() {
        if (latitude != null && longitude != null) {
            this.h3Index = H3Helper.latLngToH3(latitude, longitude, 8);
        }
        this.updatedAt = LocalDateTime.now();
    }
}