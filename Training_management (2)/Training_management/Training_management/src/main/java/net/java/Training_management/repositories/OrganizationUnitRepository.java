package net.java.Training_management.repositories;

import net.java.Training_management.entities.OrganizationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrganizationUnitRepository extends JpaRepository<OrganizationUnit, Integer> {
    Optional<OrganizationUnit> findByName(String name);

    @Query("SELECT ou FROM OrganizationUnit ou WHERE ou.parentUnit.unitId = :parentUnitId")
    List<OrganizationUnit> getChildUnits(@Param("parentUnitId") Integer parentUnitId);

    @Query("SELECT ou FROM OrganizationUnit ou LEFT JOIN FETCH ou.childUnits WHERE ou.parentUnit IS NULL")
    List<OrganizationUnit> findAllWithChildren();

}
