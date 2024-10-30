package net.java.Training_management.services.impl;

import net.java.Training_management.dtos.MaterialTypeDTO;
import net.java.Training_management.entities.MaterialType;
import net.java.Training_management.mappers.MaterialTypeMapper;
import net.java.Training_management.repositories.MaterialTypeRepository;
import net.java.Training_management.services.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialTypeServiceImpl implements MaterialTypeService {

    @Autowired
    private MaterialTypeRepository materialTypeRepository;

    @Autowired
    private MaterialTypeMapper materialTypeMapper;

    @Override
    public MaterialTypeDTO createMaterialType(MaterialTypeDTO materialTypeDTO) {
        MaterialType materialType = materialTypeMapper.toMaterialTypeEntity(materialTypeDTO);
        materialType = materialTypeRepository.save(materialType);

        return materialTypeMapper.toMaterialTypeDTO(materialType);
    }

    @Override
    public MaterialTypeDTO updateMaterialType(Integer id, MaterialTypeDTO materialTypeDTO) {
        MaterialType materialType = materialTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material Type not found"));

        materialType.setName(materialTypeDTO.getName());

        materialType = materialTypeRepository.save(materialType);

        return materialTypeMapper.toMaterialTypeDTO(materialType);
    }

    @Override
    public void deleteMaterialType(Integer id) {
        materialTypeRepository.deleteById(id);
    }

    @Override
    public MaterialTypeDTO getMaterialTypeById(Integer id) {
        MaterialType materialType = materialTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material Type not found"));
        return materialTypeMapper.toMaterialTypeDTO(materialType);
    }

    @Override
    public List<MaterialTypeDTO> getAllMaterialTypes() {
        List<MaterialType> materialTypes = materialTypeRepository.findAll();
        return materialTypes.stream().map(materialTypeMapper::toMaterialTypeDTO).collect(Collectors.toList());
    }
}
