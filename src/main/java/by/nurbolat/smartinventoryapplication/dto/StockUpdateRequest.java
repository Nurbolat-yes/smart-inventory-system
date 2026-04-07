package by.nurbolat.smartinventoryapplication.dto;


import lombok.Data;

@Data
public class StockUpdateRequest {
    private Long productId;
    private Long warehouseId;
    private Integer quantity; // positive = add, negative = remove
    private String performedBy;
}