package net.java.Training_management.services;

import net.java.Training_management.dtos.OrganizationUnitDTO;

import java.util.List;

public interface OrganizationUnitService {
 /*   OrganizationUnitDTO createOrganizationUnit(OrganizationUnitDTO organizationUnitDTO);

    OrganizationUnitDTO updateOrganizationUnit(Integer unitId, OrganizationUnitDTO organizationUnitDTO);

    OrganizationUnitDTO getOrganizationUnitById(Integer unitId);

    List<OrganizationUnitDTO> getAllOrganizationUnits();

    List<OrganizationUnitDTO> getChildUnits(Integer parentUnitId);  */// Get child units by parent ID

    List<OrganizationUnitDTO> getAllUnits();

    OrganizationUnitDTO saveUnit(OrganizationUnitDTO organizationUnitDTO);

    List<OrganizationUnitDTO> getChildUnits(Integer parentId);

    List<OrganizationUnitDTO> getAllUnitsWithChildren();

    void deleteOrganizationUnit(Integer unitId);
    void deleteAllOrganizationUnits();
}
