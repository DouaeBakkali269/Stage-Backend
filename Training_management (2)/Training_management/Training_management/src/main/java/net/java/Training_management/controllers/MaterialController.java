package net.java.Training_management.controllers;


import net.java.Training_management.dtos.DischargeDTO;
import net.java.Training_management.dtos.MaterialDTO;
import net.java.Training_management.services.DischargeService;
import net.java.Training_management.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@CrossOrigin("*")

public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<MaterialDTO> createMaterial(@RequestBody MaterialDTO materialDTO) {
        MaterialDTO createdMaterial = materialService.createMaterial(materialDTO);
        return new ResponseEntity<>(createdMaterial, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<List<MaterialDTO>> updateMaterials(@RequestBody List<MaterialDTO> dtos) {
        List<MaterialDTO> updatedMaterials = materialService.updateMaterials(dtos);
        return new ResponseEntity<>(updatedMaterials, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Integer id) {
        materialService.deleteMaterial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> getMaterialById(@PathVariable Integer id) {
        MaterialDTO materialDTO = materialService.getMaterialById(id);
        return new ResponseEntity<>(materialDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> getAllMaterials() {
        List<MaterialDTO> materials = materialService.getAllMaterials();
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<MaterialDTO>> getAvailableMaterials() {
        List<MaterialDTO> materials = materialService.getAvailableMaterials();
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable Integer id) {
        boolean isAvailable = materialService.checkAvailability(id);
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStock(@PathVariable Integer id, @RequestParam String status) {
        materialService.updateStock(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

         // Assign selected materials and change their status to unavailable
    @PostMapping("/assign")
    public ResponseEntity<?> assignMaterials(@RequestBody List<Integer> materialIds) {
        materialService.assignMaterials(materialIds);
        return ResponseEntity.ok().body("Materials assigned and status updated");
    }


}
