package net.java.Training_management.services;

import net.java.Training_management.entities.Utilisateur;
import net.java.Training_management.exception.UserNotFoundException;
import net.java.Training_management.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UserRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    public String generateUsername(String firstName, String lastName) {
        return firstName.toLowerCase() + " " + lastName.toLowerCase();
    }

    public String generateRandomPassword() {
        // Generate a random 8-character alphanumeric password
        return RandomStringUtils.randomAlphanumeric(8);
    }

    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUserById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    // Save the user and send an email later
    public void createUser(Utilisateur user, String rawPassword) {
        // Encode password
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        // Save user to the database
        utilisateurRepository.save(user);

        // Send email with raw password
        sendEmailWithPassword(user.getEmail(), rawPassword);
    }

    private void sendEmailWithPassword(String email, String rawPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Account Password");
        message.setText("Your account has been created. Your password is: " + rawPassword);
        mailSender.send(message);
    }

    public Utilisateur updateUser(Long id, Utilisateur utilisateurDetails) {
        Utilisateur user = utilisateurRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));


        user.setNom(utilisateurDetails.getNom());
        user.setPrenom(utilisateurDetails.getPrenom());
        user.setUsername(utilisateurDetails.getNom()+' '+utilisateurDetails.getPrenom());
        user.setEmail(utilisateurDetails.getEmail());
        user.setRole(utilisateurDetails.getRole());
        user.setPhoneNumber(utilisateurDetails.getPhoneNumber());
        user.setAddress(utilisateurDetails.getAddress());
        user.setCin(utilisateurDetails.getCin());
        user.setNationality(utilisateurDetails.getNationality());
        user.setPosition(utilisateurDetails.getPosition());
        user.setDischarges(utilisateurDetails.getDischarges());

        return utilisateurRepository.save(user);
    }

    public void deleteUser(Long id) {
        Utilisateur user = utilisateurRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        utilisateurRepository.delete(user);
    }

    public void deleteAllUsers() {
        utilisateurRepository.deleteAll();
    }

    public String generateResetToken() {
        // Generate a random 30-character alphanumeric token
        return RandomStringUtils.randomAlphanumeric(30);
    }

    private void sendResetTokenEmail(String email, String token, String username, String fullname) {
        String resetUrl = "http://localhost:5173/reset-password/" + token;
        String emailContent = "Hello " + fullname + ",\n\n" +
                "To reset your password, use the username: "+ username +" and please click the link below to choose a new password:\n" +
                resetUrl + "\n\n" +
                "This link is valid for 6 hours.\n\n" +
                "Best regards,\nYour Support Team";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reset Password Request");
        message.setText("Click the link to reset your password: " + resetUrl);
        message.setText(emailContent);

        mailSender.send(message);
    }

    public void createPasswordResetToken(Utilisateur user) {
        String token = generateResetToken();
        user.setResetToken(token);
        // Set token expiry to 2 hours (120 minutes)
        user.setResetTokenExpiryDate(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000)); // 2 hours expiry
        utilisateurRepository.save(user);

        // Send email to the user with the token
        sendResetTokenEmail(user.getEmail(), token, user.getUsername(),user.getNom()+' '+user.getPrenom());
    }

    public Utilisateur validatePasswordResetToken(String token) {
        Utilisateur user = utilisateurRepository.findByResetToken(token);

        if (user == null || user.getResetTokenExpiryDate().before(new Date())) {
            throw new RuntimeException("Invalid or expired token");
        }
        return user;
    }

    public void updatePassword(Utilisateur user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null); // Clear the reset token after successful password reset
        user.setResetTokenExpiryDate(null);
        utilisateurRepository.save(user);
    }
}
