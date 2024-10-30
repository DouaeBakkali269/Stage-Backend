package net.java.Training_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationUnitDTO {

    private Integer unitId;
    private String name;
    private String description;
    private String type;  // Depending on how you want to map OrganizationUnitType (as String or enum)
    private Integer parentUnitId; // Recursive DTO for the parent unit
    private Set<OrganizationUnitDTO> childUnits;  // Set of child units (if needed)
}
