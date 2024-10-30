package net.java.Training_management.repositories;

import net.java.Training_management.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utilisateur,Long> {
    boolean existsByUsername(String username);

    Optional<Utilisateur> findByUsername(String username);
    Utilisateur findByEmail(String email);
    Utilisateur findByResetToken(String resetToken);
}
