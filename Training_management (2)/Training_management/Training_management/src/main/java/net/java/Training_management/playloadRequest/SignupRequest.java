package net.java.Training_management.playloadRequest;
import lombok.Data;
import net.java.Training_management.entities.ERole;

@Data

public class SignupRequest {

    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private String phoneNumber;
    private String address;
    private ERole role;
}