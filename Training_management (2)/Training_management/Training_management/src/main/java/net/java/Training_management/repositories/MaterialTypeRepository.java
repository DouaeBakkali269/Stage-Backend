package net.java.Training_management.repositories;


import net.java.Training_management.entities.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaterialTypeRepository extends JpaRepository<MaterialType,Integer>{
    Optional<MaterialType> findByName(String name);
}
