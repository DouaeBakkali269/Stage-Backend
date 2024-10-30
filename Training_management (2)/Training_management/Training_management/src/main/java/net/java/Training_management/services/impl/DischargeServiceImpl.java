package net.java.Training_management.services.impl;

import net.java.Training_management.dtos.DischargeDTO;
import net.java.Training_management.entities.Discharge;
import net.java.Training_management.entities.OrganizationUnit;
import net.java.Training_management.mappers.DischargeMapper;
import net.java.Training_management.repositories.DischargeRepository;
import net.java.Training_management.repositories.OrganizationUnitRepository;
import net.java.Training_management.services.DischargeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DischargeServiceImpl implements DischargeService {

    private final DischargeRepository dischargeRepository;
    private final OrganizationUnitRepository organizationUnitRepository;
    private final DischargeMapper dischargeMapper;

    public DischargeServiceImpl(DischargeRepository dischargeRepository,
                                OrganizationUnitRepository organizationUnitRepository,
                                DischargeMapper dischargeMapper) {
        this.dischargeRepository = dischargeRepository;
        this.organizationUnitRepository = organizationUnitRepository;
        this.dischargeMapper = dischargeMapper;
    }

    @Override
    public DischargeDTO createDischarge(DischargeDTO dischargeDTO) {
        OrganizationUnit organizationUnit = organizationUnitRepository
                .findById(dischargeDTO.getOrganizationUnit().getUnitId())
                .orElseThrow(() -> new RuntimeException("Organization Unit not found"));

        Discharge discharge = dischargeMapper.toEntity(dischargeDTO, organizationUnit);
        discharge = dischargeRepository.save(discharge);
        return dischargeMapper.toDTO(discharge);
    }

    @Override
    public DischargeDTO updateDischarge(Integer id, DischargeDTO dischargeDTO) {
        Discharge existingDischarge = dischargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discharge not found"));

        OrganizationUnit organizationUnit = organizationUnitRepository
                .findById(dischargeDTO.getOrganizationUnit().getUnitId())
                .orElseThrow(() -> new RuntimeException("Organization Unit not found"));

        Discharge updatedDischarge = dischargeMapper.toEntity(dischargeDTO, organizationUnit);
        updatedDischarge.setDischargeId(existingDischarge.getDischargeId()); // Preserve the ID
        updatedDischarge = dischargeRepository.save(updatedDischarge);

        return dischargeMapper.toDTO(updatedDischarge);
    }

    @Override
    public void deleteDischarge(Integer id) {
        Discharge discharge = dischargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discharge not found"));

        dischargeRepository.delete(discharge);
    }

    @Override
    public DischargeDTO getDischargeById(Integer id) {
        Discharge discharge = dischargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discharge not found"));

        return dischargeMapper.toDTO(discharge);
    }

    @Override
    public List<DischargeDTO> getAllDischarges() {
        return dischargeRepository.findAll().stream()
                .map(dischargeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DischargeDTO> getDischargesByUsername(String username) {
        List<Discharge> discharges = dischargeRepository.findByUserUsername(username);

        return discharges.stream()
                .map(dischargeMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<DischargeDTO> getDischargesByStatus(String status) {
        List<Discharge> discharges = dischargeRepository.findByStatus(status);

        return discharges.stream()
                .map(dischargeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateDischargeStatus(Integer id, String status) {
        Discharge discharge= dischargeRepository.findById(id).orElseThrow(()-> new RuntimeException("discharge not found"));
        discharge.setStatus(status);
        dischargeRepository.save(discharge);
    }


}
