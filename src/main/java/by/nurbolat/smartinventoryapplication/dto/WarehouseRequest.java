package by.nurbolat.smartinventoryapplication.dto;

import lombok.Data;

@Data
public class WarehouseRequest {
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
}