package net.java.Training_management.controllers;

import net.java.Training_management.dtos.LocalDTO;
import net.java.Training_management.services.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locals")
public class LocalController {

    @Autowired
    private LocalService localService;

    @PostMapping
    public ResponseEntity<LocalDTO> createLocal(@RequestBody LocalDTO localDTO) {
        LocalDTO createdLocal = localService.createLocal(localDTO);
        return ResponseEntity.ok(createdLocal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> updateLocal(@PathVariable Integer id, @RequestBody LocalDTO localDTO) {
        LocalDTO updatedLocal = localService.updateLocal(id, localDTO);
        return ResponseEntity.ok(updatedLocal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocal(@PathVariable Integer id) {
        localService.deleteLocal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> getLocalById(@PathVariable Integer id) {
        LocalDTO localDTO = localService.getLocalById(id);
        return ResponseEntity.ok(localDTO);
    }

    @GetMapping
    public ResponseEntity<List<LocalDTO>> getAllLocals() {
        List<LocalDTO> localDTOs = localService.getAllLocals();
        return ResponseEntity.ok(localDTOs);
    }
}
