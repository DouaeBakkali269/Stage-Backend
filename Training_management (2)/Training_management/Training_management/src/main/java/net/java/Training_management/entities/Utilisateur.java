package net.java.Training_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String phoneNumber;
    private String address;
    private String nationality;
    private String position;

    @Enumerated(EnumType.STRING)
    private ERole role;

    private boolean passwordResetRequired = true;
    private String resetToken;
    private Date resetTokenExpiryDate;

    public Utilisateur(String username, String password , String nom,
    String prenom , String email, String phoneNumber, String address, ERole role){
        this.username=username;
        this.password=password;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.role=role;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Discharge> discharges= new ArrayList<>();

}
