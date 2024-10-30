package net.java.Training_management.mappers;

import net.java.Training_management.dtos.OrganizationUnitDTO;
import net.java.Training_management.entities.OrganizationUnit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationUnitMapper {
    OrganizationUnitMapper INSTANCE = Mappers.getMapper(OrganizationUnitMapper.class);

    @Mapping(source = "parentUnit.unitId", target = "parentUnitId")
    OrganizationUnitDTO toDTO(OrganizationUnit organizationUnit);

    @Mapping(source = "parentUnitId", target = "parentUnit.unitId")
    OrganizationUnit toEntity(OrganizationUnitDTO organizationUnitDTO);

    // MapStruct should be able to map the childUnits recursively automatically
}
