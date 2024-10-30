package net.java.Training_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MaterialDTO {
    private Integer materialId;
    private String name;
    private String description;
    private String serialNumber;
    private String manufacturer;
    private String status;
    private String typeName;
    private Integer localId;
    private Integer dischargeId;
}
