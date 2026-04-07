package by.nurbolat.smartinventoryapplication.dto;

import lombok.Data;

@Data
public class BarcodeRequest {
    private String barcode;
    private Long warehouseId;
    private Integer quantity;
    private String performedBy;
}