package net.java.Training_management.playloadRequest;

import lombok.Data;

@Data
public class passwordUpdateRequest {
    private String currentPassword;
    private String newPassword;
}
