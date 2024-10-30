

package net.java.Training_management.services;

import net.java.Training_management.dtos.MaterialTypeDTO;

import java.util.List;

public interface MaterialTypeService {

    MaterialTypeDTO createMaterialType(MaterialTypeDTO materialTypeDTO);

    MaterialTypeDTO updateMaterialType(Integer id, MaterialTypeDTO materialTypeDTO);

    void deleteMaterialType(Integer id);

    MaterialTypeDTO getMaterialTypeById(Integer id);

    List<MaterialTypeDTO> getAllMaterialTypes();
}

