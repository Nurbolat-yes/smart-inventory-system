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
@Table(name = "AuditLog", schema = "audit")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    private String action; //  ADD_STOCK, REMOVE_STOCK

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    private Integer quantityChange;
    private String performedBy;

    private LocalDateTime timestamp = LocalDateTime.now();
}