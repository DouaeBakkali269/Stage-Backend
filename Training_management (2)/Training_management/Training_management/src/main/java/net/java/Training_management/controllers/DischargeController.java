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
@RequestMapping("/api/discharges")
@CrossOrigin("*")
public class DischargeController {

    @Autowired
    private DischargeService dischargeService;
    @Autowired
    private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<DischargeDTO>> getAllDischarges() {
        List<DischargeDTO> discharges = dischargeService.getAllDischarges();
        return new ResponseEntity<>(discharges, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DischargeDTO> getDischargeById(@PathVariable Integer id) {
        DischargeDTO dischargeDTO = dischargeService.getDischargeById(id);
        return new ResponseEntity<>(dischargeDTO, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<DischargeDTO>> getDischargesByUsername(@PathVariable String username){
        List<DischargeDTO> dtos = dischargeService.getDischargesByUsername(username);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<DischargeDTO>> getDischargesByStatus(@PathVariable String status){
        List<DischargeDTO> dtos = dischargeService.getDischargesByStatus(status);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
    @GetMapping("/material/{dischargeId}")
    public ResponseEntity<DischargeDTO> getDischargeWithMaterials(@PathVariable Integer dischargeId){
        DischargeDTO dto = dischargeService.getDischargeById(dischargeId);
        List<MaterialDTO> materialdtos = materialService.getMaterialsByDischargeId(dischargeId);
        dto.setMaterials(materialdtos);
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    public ResponseEntity<DischargeDTO> createDischarge(@RequestBody DischargeDTO dischargeDTO) {
        DischargeDTO createdDischarge = dischargeService.createDischarge(dischargeDTO);
        return new ResponseEntity<>(createdDischarge, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DischargeDTO> updateDischarge(@PathVariable Integer id, @RequestBody DischargeDTO dischargeDTO) {
        DischargeDTO updatedDischarge = dischargeService.updateDischarge(id, dischargeDTO);
        return new ResponseEntity<>(updatedDischarge, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateDischargeStatus(@PathVariable Integer id, @RequestParam String status) {
        dischargeService.updateDischargeStatus(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDischarge(@PathVariable Integer id) {
        dischargeService.deleteDischarge(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

