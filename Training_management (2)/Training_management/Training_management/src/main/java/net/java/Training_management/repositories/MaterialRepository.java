package net.java.Training_management.repositories;


import net.java.Training_management.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findByStatus(String status);

    List<Material> findByDischarge_dischargeId(Integer dischargeId);
}
