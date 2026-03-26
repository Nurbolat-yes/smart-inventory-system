package by.nurbolat.smartinventoryapplication.entity.inventory;

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
@Table(name = "Stock", schema = "inventory",
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "warehouse_id"}))
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    private Integer quantity;
    private Long h3Index; // redundant, for analytics

    private LocalDateTime lastUpdated = LocalDateTime.now();

}
