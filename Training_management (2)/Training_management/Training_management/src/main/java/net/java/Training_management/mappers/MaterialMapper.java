package net.java.Training_management.mappers;

import net.java.Training_management.dtos.MaterialDTO;
import net.java.Training_management.entities.Material;
import net.java.Training_management.entities.MaterialType;
import net.java.Training_management.entities.Local;
import net.java.Training_management.entities.Discharge;
import net.java.Training_management.repositories.MaterialTypeRepository;
import net.java.Training_management.repositories.LocalRepository;
import net.java.Training_management.repositories.DischargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {

    @Autowired
    private MaterialTypeRepository materialTypeRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private DischargeRepository dischargeRepository;

    // Convert Material entity to MaterialDTO
    public MaterialDTO toDTO(Material material) {
        MaterialDTO dto = new MaterialDTO();
        dto.setMaterialId(material.getId());
        dto.setName(material.getName());
        dto.setDescription(material.getDescription());
        dto.setSerialNumber(material.getSerialNumber());
        dto.setManufacturer(material.getManufacturer());
        dto.setStatus(material.getStatus());

        // Map MaterialType name to typeName in DTO
        if (material.getType() != null) {
            dto.setTypeName(material.getType().getName());
        }

        // Map Local id
        if (material.getLocal() != null) {
            dto.setLocalId(material.getLocal().getLocalId());
        }

        // Map Discharge id
        if (material.getDischarge() != null) {
            dto.setDischargeId(material.getDischarge().getDischargeId());
        }

        return dto;
    }

    // Convert MaterialDTO to Material entity
    public Material toEntity(MaterialDTO dto) {
        Material material = new Material();
        material.setId(dto.getMaterialId()); // Set material ID (useful for updating)
        material.setName(dto.getName());
        material.setDescription(dto.getDescription());
        material.setSerialNumber(dto.getSerialNumber());
        material.setManufacturer(dto.getManufacturer());
        material.setStatus(dto.getStatus());

        // Map typeName to MaterialType entity
        if (dto.getTypeName() != null) {
            MaterialType materialType = materialTypeRepository.findByName(dto.getTypeName())
                    .orElseThrow(() -> new RuntimeException("MaterialType not found: " + dto.getTypeName()));
            material.setType(materialType);
        }

        // Map localId to Local entity
        if (dto.getLocalId() != null) {
            Local local = localRepository.findById(dto.getLocalId())
                    .orElseThrow(() -> new RuntimeException("Local not found with ID: " + dto.getLocalId()));
            material.setLocal(local);
        }

        // Map dischargeId to Discharge entity
        if (dto.getDischargeId() != null) {
            Discharge discharge = dischargeRepository.findById(dto.getDischargeId())
                    .orElseThrow(() -> new RuntimeException("Discharge not found with ID: " + dto.getDischargeId()));
            material.setDischarge(discharge);
        }

        return material;
    }
}
