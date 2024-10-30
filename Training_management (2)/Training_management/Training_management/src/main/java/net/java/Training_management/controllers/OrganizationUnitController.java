package net.java.Training_management.controllers;

import net.java.Training_management.dtos.OrganizationUnitDTO;
import net.java.Training_management.services.OrganizationUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization-units")
@CrossOrigin("*")

public class OrganizationUnitController {

    @Autowired
    private OrganizationUnitService organizationUnitService;

    @GetMapping
    public List<OrganizationUnitDTO> getAllUnits() {
        return organizationUnitService.getAllUnits();
    }

    @PostMapping
    public OrganizationUnitDTO createUnit(@RequestBody OrganizationUnitDTO organizationUnitDTO) {
        return organizationUnitService.saveUnit(organizationUnitDTO);
    }

    @GetMapping("/{parentId}/children")
    public List<OrganizationUnitDTO> getChildUnits(@PathVariable Integer parentId) {
        return organizationUnitService.getChildUnits(parentId);
    }

    @GetMapping("/withchildren")
    public List<OrganizationUnitDTO> getAllUnitsWithChildren() {
        return organizationUnitService.getAllUnitsWithChildren();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        organizationUnitService.deleteOrganizationUnit(id);
    }
    @DeleteMapping("/all")
    public void deleteAll(){
        organizationUnitService.deleteAllOrganizationUnits();
    }
}
