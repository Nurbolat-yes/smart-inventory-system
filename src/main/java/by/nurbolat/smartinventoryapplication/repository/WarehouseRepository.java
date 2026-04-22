package by.nurbolat.smartinventoryapplication.repository;

import by.nurbolat.smartinventoryapplication.entity.inventory.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByH3Index(Long h3Index);
}