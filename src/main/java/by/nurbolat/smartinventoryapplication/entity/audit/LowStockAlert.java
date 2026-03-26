package by.nurbolat.smartinventoryapplication.entity.audit;

import by.nurbolat.smartinventoryapplication.entity.inventory.Product;
import by.nurbolat.smartinventoryapplication.entity.inventory.Warehouse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "LowStockAlert", schema = "audit")
public class LowStockAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    private LocalDateTime triggeredAt = LocalDateTime.now();
    private Boolean isResolved = false;
    private Integer currentQuantity;

}
