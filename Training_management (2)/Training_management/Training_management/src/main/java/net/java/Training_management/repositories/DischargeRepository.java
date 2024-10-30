package net.java.Training_management.repositories;

import net.java.Training_management.entities.Discharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DischargeRepository extends JpaRepository<Discharge,Integer> {
    Optional<Discharge> findById(Integer id);
    void deleteById(Integer id);
    List<Discharge> findByUserUsername(String username);
    List<Discharge> findByStatus(String status);

}
