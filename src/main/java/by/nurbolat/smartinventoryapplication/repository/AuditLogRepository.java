package by.nurbolat.smartinventoryapplication.repository;

import by.nurbolat.smartinventoryapplication.entity.audit.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByProduct_ProductId(Long productId);
    List<AuditLog> findByWarehouse_WarehouseId(Long warehouseId);
}