package by.nurbolat.smartinventoryapplication.repository;

import by.nurbolat.smartinventoryapplication.entity.audit.LowStockAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface LowStockAlertRepository extends JpaRepository<LowStockAlert, Long> {
    List<LowStockAlert> findByIsResolvedFalse();
    List<LowStockAlert> findByProduct_ProductIdAndIsResolvedFalse(Long productId);
}