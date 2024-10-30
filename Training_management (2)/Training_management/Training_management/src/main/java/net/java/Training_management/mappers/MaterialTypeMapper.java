package net.java.Training_management.mappers;

import net.java.Training_management.dtos.MaterialTypeDTO;
import net.java.Training_management.entities.MaterialType;
import org.springframework.stereotype.Component;

@Component
public class MaterialTypeMapper {

    public MaterialTypeDTO toMaterialTypeDTO(MaterialType materialType) {
        if (materialType == null) {
            return null;
        }

        MaterialTypeDTO materialTypeDTO = new MaterialTypeDTO();
        materialTypeDTO.setTypeId(materialType.getId());
        materialTypeDTO.setName(materialType.getName());

        return materialTypeDTO;
    }

    public MaterialType toMaterialTypeEntity(MaterialTypeDTO materialTypeDTO) {
        if (materialTypeDTO == null) {
            return null;
        }

        MaterialType materialType = new MaterialType();
        materialType.setId(materialTypeDTO.getTypeId());
        materialType.setName(materialTypeDTO.getName());

        return materialType;
    }
}
