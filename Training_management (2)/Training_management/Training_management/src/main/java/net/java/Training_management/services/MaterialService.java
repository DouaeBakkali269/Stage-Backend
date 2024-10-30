package net.java.Training_management.services;

import net.java.Training_management.dtos.MaterialDTO;

import java.util.List;


public interface MaterialService {
    MaterialDTO createMaterial(MaterialDTO materialDTO);
    void deleteMaterial(Integer materialId);
    MaterialDTO getMaterialById(Integer materialId);
    List<MaterialDTO> getAllMaterials();
    boolean checkAvailability(Integer materialId);
    void updateStock(Integer materialId, String status);
    List<MaterialDTO> getAvailableMaterials( );

    List<MaterialDTO> getMaterialsByDischargeId(Integer dischargeId);

    void assignMaterials(List<Integer> materialIds);

    List<MaterialDTO> updateMaterials(List<MaterialDTO> dtos);
}
