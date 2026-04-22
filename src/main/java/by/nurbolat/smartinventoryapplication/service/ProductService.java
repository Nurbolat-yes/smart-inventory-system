package by.nurbolat.smartinventoryapplication.service;

import by.nurbolat.smartinventoryapplication.dto.ProductRequest;
import by.nurbolat.smartinventoryapplication.entity.inventory.Product;
import by.nurbolat.smartinventoryapplication.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequest req) {
        Product p = Product.builder()
                .name(req.getName())
                .description(req.getDescription())
                .barcode(req.getBarcode())
                .restockThreshold(req.getRestockThreshold())
                .price(req.getPrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return productRepository.save(p);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public Product findByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("No product with barcode: " + barcode));
    }

    public Product updateProduct(Long id, ProductRequest req) {
        Product p = getById(id);
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setBarcode(req.getBarcode());
        p.setRestockThreshold(req.getRestockThreshold());
        p.setPrice(req.getPrice());
        p.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(p);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}