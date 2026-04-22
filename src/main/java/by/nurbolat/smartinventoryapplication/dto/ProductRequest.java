package by.nurbolat.smartinventoryapplication.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String barcode;
    private Integer restockThreshold;
    private Double price;
}