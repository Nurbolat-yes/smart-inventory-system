package by.nurbolat.smartinventoryapplication.repository;
import by.nurbolat.smartinventoryapplication.entity.analitics.InventoryAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryAnalyticsRepository extends JpaRepository<InventoryAnalytics, Long> {
    Optional<InventoryAnalytics> findByH3Index(Long h3Index);
}