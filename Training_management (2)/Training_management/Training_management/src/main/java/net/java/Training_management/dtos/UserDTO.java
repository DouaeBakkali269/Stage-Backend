package net.java.Training_management.dtos;

import lombok.Data;
import net.java.Training_management.entities.ERole;

import java.util.List;
@Data

public class UserDTO {
    private String nom;
    private String prenom;
    private String email;
    private String phoneNumber;
    private String address;
    private ERole role;
    private String nationality;
    private String position;
    private String cin;
    private List<DischargeDTO> discharges;

}
