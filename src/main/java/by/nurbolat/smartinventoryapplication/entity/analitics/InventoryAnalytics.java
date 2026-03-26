package by.nurbolat.smartinventoryapplication.entity.analitics;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "InventoryAnalytics", schema = "analytics")
public class InventoryAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long analyticsId;

    private Long h3Index;
    private Integer totalProducts;
    private Integer totalQuantity;
    private LocalDateTime lastAggregated = LocalDateTime.now();
}
