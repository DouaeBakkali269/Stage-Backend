package net.java.Training_management.controllers;

import net.java.Training_management.dtos.MaterialTypeDTO;
import net.java.Training_management.services.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materialTypes")
public class MaterialTypeController {


    @Autowired
    private MaterialTypeService materialTypeService;


    @PostMapping
    public ResponseEntity<MaterialTypeDTO> createMaterialType(@RequestBody MaterialTypeDTO materialTypeDTO) {
        MaterialTypeDTO createdMaterialType = materialTypeService.createMaterialType(materialTypeDTO);
        return ResponseEntity.ok(createdMaterialType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialTypeDTO> updateMaterialType(@PathVariable Integer id, @RequestBody MaterialTypeDTO materialTypeDTO) {
        MaterialTypeDTO updatedMaterialType = materialTypeService.updateMaterialType(id, materialTypeDTO);
        return ResponseEntity.ok(updatedMaterialType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterialType(@PathVariable Integer id) {
        materialTypeService.deleteMaterialType(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialTypeDTO> getMaterialTypeById(@PathVariable Integer id) {
        MaterialTypeDTO materialTypeDTO = materialTypeService.getMaterialTypeById(id);
        return ResponseEntity.ok(materialTypeDTO);
    }

    @GetMapping
    public ResponseEntity<List<MaterialTypeDTO>> getAllMaterialTypes() {
        List<MaterialTypeDTO> materialTypeDTOs = materialTypeService.getAllMaterialTypes();
        return ResponseEntity.ok(materialTypeDTOs);
    }

}

