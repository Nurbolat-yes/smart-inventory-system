package by.nurbolat.smartinventoryapplication.repository;

import by.nurbolat.smartinventoryapplication.entity.inventory.Product;
import by.nurbolat.smartinventoryapplication.entity.inventory.Stock;
import by.nurbolat.smartinventoryapplication.entity.inventory.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProduct_ProductIdAndWarehouse_WarehouseId(Long productId, Long warehouseId);

    List<Stock> findByWarehouse_WarehouseId(Long warehouseId);

    List<Stock> findByH3Index(Long h3Index);

    @Query("SELECT s FROM Stock s WHERE s.quantity <= s.product.restockThreshold")
    List<Stock> findLowStockItems();
}