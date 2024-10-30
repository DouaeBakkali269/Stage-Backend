package net.java.Training_management.services.impl;

import net.java.Training_management.dtos.MaterialDTO;
import net.java.Training_management.entities.Material;
import net.java.Training_management.entities.MaterialType;
import net.java.Training_management.entities.Local;
import net.java.Training_management.entities.Discharge;
import net.java.Training_management.exception.UserNotFoundException;
import net.java.Training_management.repositories.MaterialRepository;
import net.java.Training_management.repositories.MaterialTypeRepository;
import net.java.Training_management.repositories.LocalRepository;
import net.java.Training_management.repositories.DischargeRepository;
import net.java.Training_management.mappers.MaterialMapper;
import net.java.Training_management.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialTypeRepository materialTypeRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private DischargeRepository dischargeRepository;
    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public MaterialDTO createMaterial(MaterialDTO dto) {
        Material material = materialMapper.toEntity(dto);
        setAssociations(dto, material);
        Material savedMaterial = materialRepository.save(material);
        return materialMapper.toDTO(savedMaterial);
    }

    @Override
    public List<MaterialDTO> updateMaterials(List<MaterialDTO> dtos) {
        List<Material> updatedMaterials = new ArrayList<>();

        for (MaterialDTO dto : dtos) {
            Material material = materialRepository.findById(dto.getMaterialId())
                    .orElseThrow(() -> new RuntimeException("Material not found"));
            material.setName(dto.getName());
            material.setDescription(dto.getDescription());
            material.setSerialNumber(dto.getSerialNumber());
            material.setManufacturer(dto.getManufacturer());
            material.setStatus(dto.getStatus());
            setAssociations(dto, material);
            updatedMaterials.add(material);
        }

        List<Material> savedMaterials = materialRepository.saveAll(updatedMaterials);
        return savedMaterials.stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMaterial(Integer materialId) {
        materialRepository.deleteById(materialId);
    }

    @Override
    public MaterialDTO getMaterialById(Integer materialId) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material not found"));
        return materialMapper.toDTO(material);
    }

    @Override
    public List<MaterialDTO> getAllMaterials() {
        List<Material> materials = materialRepository.findAll();
        return materials.stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkAvailability(Integer materialId) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material not found"));
        return "available".equalsIgnoreCase(material.getStatus());
    }

    @Override
    public void updateStock(Integer materialId, String status) {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material not found"));
        material.setStatus(status);
        materialRepository.save(material);
    }

    @Override
    public List<MaterialDTO> getAvailableMaterials() {
        List<Material> materials= materialRepository.findByStatus("available");
        return materials.stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialDTO> getMaterialsByDischargeId(Integer dischargeId) {
        List<Material> materials = materialRepository.findByDischarge_dischargeId(dischargeId);
             return materials.stream()
                     .map(materialMapper::toDTO)
                     .collect(Collectors.toList());
    }



    private void setAssociations(MaterialDTO dto, Material material) {
        if (dto.getTypeName() != null) {
            MaterialType type = materialTypeRepository.findByName(dto.getTypeName())
                    .orElseThrow(() -> new RuntimeException("MaterialType not found"));
            material.setType(type);
        }

        if (dto.getLocalId() != null) {
            Local local = localRepository.findById(dto.getLocalId())
                    .orElseThrow(() -> new RuntimeException("Local not found"));
            material.setLocal(local);
        }

        if (dto.getDischargeId() != null) {
            Discharge discharge = dischargeRepository.findById(dto.getDischargeId())
                    .orElseThrow(() -> new RuntimeException("Discharge not found"));
            material.setDischarge(discharge);
        }
    }
    @Override
    public void assignMaterials(List<Integer> materialIds) {
        for (Integer materialId : materialIds) {
            Material material = materialRepository.findById(materialId).orElseThrow(() -> new UserNotFoundException("Material not found"));
            material.setStatus("unavailable");
            materialRepository.save(material);
        }
    }

}
