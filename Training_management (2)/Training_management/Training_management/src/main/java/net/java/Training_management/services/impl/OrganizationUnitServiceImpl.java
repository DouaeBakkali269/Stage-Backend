package net.java.Training_management.services.impl;

import jakarta.transaction.Transactional;
import net.java.Training_management.dtos.OrganizationUnitDTO;
import net.java.Training_management.entities.OrganizationUnit;
import net.java.Training_management.mappers.OrganizationUnitMapper;
import net.java.Training_management.repositories.OrganizationUnitRepository;
import net.java.Training_management.services.OrganizationUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationUnitServiceImpl implements OrganizationUnitService {


    @Autowired
    private OrganizationUnitRepository organizationUnitRepository;

    @Override
    public List<OrganizationUnitDTO> getAllUnits() {
        return organizationUnitRepository.findAll().stream()
                .map(OrganizationUnitMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganizationUnitDTO> getChildUnits(Integer parentId) {
        return organizationUnitRepository.getChildUnits(parentId).stream()
                .map(OrganizationUnitMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganizationUnitDTO> getAllUnitsWithChildren() {
        List<OrganizationUnit> units = organizationUnitRepository.findAllWithChildren();
        return units.stream()
                .map(OrganizationUnitMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteOrganizationUnit(Integer unitId) {
        organizationUnitRepository.deleteById(unitId);
    }
    @Override
    public void deleteAllOrganizationUnits(){
        organizationUnitRepository.deleteAll();
    }

    @Override
    @Transactional
    public OrganizationUnitDTO saveUnit(OrganizationUnitDTO organizationUnitDTO) {
        // First, handle the parent unit (if any)
        OrganizationUnit parentUnit = null;

        // Check if there's a parent unit, and load it from the database
        if (organizationUnitDTO.getParentUnitId() != null) {
            parentUnit = organizationUnitRepository.findById(organizationUnitDTO.getParentUnitId())
                    .orElseThrow(() -> new RuntimeException("Parent unit not found"));
        }

        // Convert DTO to entity, without child units for now
        OrganizationUnit organizationUnit = OrganizationUnitMapper.INSTANCE.toEntity(organizationUnitDTO);
        organizationUnit.setParentUnit(parentUnit);

        // Save the parent unit without children first
        OrganizationUnit savedUnit = organizationUnitRepository.save(organizationUnit);

        // Now handle the child units (if any)
        if (organizationUnitDTO.getChildUnits() != null && !organizationUnitDTO.getChildUnits().isEmpty()) {
            for (OrganizationUnitDTO childUnitDTO : organizationUnitDTO.getChildUnits()) {
                OrganizationUnit childUnit = OrganizationUnitMapper.INSTANCE.toEntity(childUnitDTO);
                childUnit.setParentUnit(savedUnit);  // Link the child to the parent

                // Save each child unit
                organizationUnitRepository.save(childUnit);
            }
        }

        // Return the saved unit, including children if needed
        return OrganizationUnitMapper.INSTANCE.toDTO(savedUnit);
    }


}


