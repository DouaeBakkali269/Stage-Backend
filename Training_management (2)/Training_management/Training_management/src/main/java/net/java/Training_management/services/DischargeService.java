package net.java.Training_management.services;

import net.java.Training_management.dtos.DischargeDTO;

import java.util.List;

public interface DischargeService {

    DischargeDTO createDischarge(DischargeDTO dischargeDTO);

    DischargeDTO updateDischarge(Integer id, DischargeDTO dischargeDTO);

    void deleteDischarge(Integer id);

    DischargeDTO getDischargeById(Integer id);

    List<DischargeDTO> getAllDischarges();

    List<DischargeDTO> getDischargesByUsername(String username);

    void updateDischargeStatus(Integer id, String status);

    List<DischargeDTO> getDischargesByStatus(String status);
}
