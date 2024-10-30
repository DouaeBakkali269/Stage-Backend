package net.java.Training_management.controllers;

import net.java.Training_management.dtos.UserDTO;
import net.java.Training_management.entities.Utilisateur;
import net.java.Training_management.mappers.UserMapper;
import net.java.Training_management.playloadRequest.passwordUpdateRequest;
import net.java.Training_management.repositories.UserRepository;

import net.java.Training_management.services.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Utilisateur> getAllUsers() {
        return utilisateurService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUserById(@PathVariable Long id) {
        Utilisateur user = utilisateurService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody Utilisateur utilisateur) {
        String username = utilisateurService.generateUsername(utilisateur.getPrenom(), utilisateur.getNom());
        String rawPassword = utilisateurService.generateRandomPassword();
       // Utilisateur createdUser = new Utilisateur(username,rawPassword,utilisateur.getNom(),utilisateur.getPrenom(),utilisateur.getEmail(),utilisateur.getPhoneNumber(),utilisateur.getAddress()
        //,utilisateur.getRole());
        utilisateur.setUsername(username);
        utilisateurService.createUser(utilisateur,rawPassword);
        UserDTO userDTO = userMapper.toDto(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails) {
        Utilisateur updatedUser = utilisateurService.updateUser(id, utilisateurDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        utilisateurService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        userRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
    // Endpoint to request password reset
    @PostMapping("/reset-password-request")
    public ResponseEntity<?> requestPasswordReset(@RequestParam String email) {
        Utilisateur user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        utilisateurService.createPasswordResetToken(user);
        return ResponseEntity.ok("Password reset email sent.");
    }

    // Endpoint to handle password reset using the token
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        Utilisateur user = utilisateurService.validatePasswordResetToken(token);
        utilisateurService.updatePassword(user, newPassword);
        return ResponseEntity.ok("Password successfully reset.");
    }
    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(
            @RequestBody passwordUpdateRequest passwordUpdateRequest,
            Authentication authentication) {
        // Retrieve the authenticated user
       // Optional<Utilisateur> user = userRepository.findByUsername(authentication.getName());
        Utilisateur user = userRepository.findByUsername(authentication.getName()).orElse(null);



        if (!passwordEncoder.matches(passwordUpdateRequest.getCurrentPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current password is incorrect");
        }

        // Update to new password
        utilisateurService.updatePassword(user, passwordUpdateRequest.getNewPassword());
        return ResponseEntity.ok("Password successfully updated");
    }
}
