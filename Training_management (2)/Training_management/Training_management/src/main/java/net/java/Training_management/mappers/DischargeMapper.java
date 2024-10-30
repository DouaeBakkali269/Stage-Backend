package net.java.Training_management.mappers;

import net.java.Training_management.dtos.DischargeDTO;
import net.java.Training_management.entities.Discharge;
import net.java.Training_management.entities.OrganizationUnit;
import net.java.Training_management.dtos.MaterialDTO;
import net.java.Training_management.entities.Utilisateur;
import net.java.Training_management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.ClassUtils.isPresent;

@Component
public class DischargeMapper {

    private final MaterialMapper materialMapper;
    @Autowired
    UserRepository userRepository;

    public DischargeMapper(MaterialMapper materialMapper) {
        this.materialMapper = materialMapper;
    }

    public DischargeDTO toDTO(Discharge discharge) {
        if (discharge == null) {
            return null;
        }

        DischargeDTO dischargeDTO = new DischargeDTO();
        dischargeDTO.setDischargeId(discharge.getDischargeId());
        dischargeDTO.setDate(discharge.getDate());
        dischargeDTO.setSignature(discharge.getSignature());
        if (discharge.getUser() != null) {
            dischargeDTO.setUsername(discharge.getUser().getUsername());
        } else {
            dischargeDTO.setUsername("Utilisateur inconnu"); // ou une valeur par défaut
        }        dischargeDTO.setStatus(discharge.getStatus());

        dischargeDTO.setOrganizationUnit(
                OrganizationUnitMapper.INSTANCE.toDTO(discharge.getOrganizationUnit())
        );

        dischargeDTO.setSelectedChild(
                OrganizationUnitMapper.INSTANCE.toDTO(discharge.getSelectedChild())
      );

        dischargeDTO.setMaterials(
                discharge.getMaterials().stream()
                        .map(materialMapper::toDTO)
                        .collect(Collectors.toList())
        );

        return dischargeDTO;
    }

    public Discharge toEntity(DischargeDTO dto, OrganizationUnit organizationUnit) {
        if (dto == null) {
            return null;
        }

        Discharge discharge = new Discharge();
        discharge.setDischargeId(dto.getDischargeId());
        discharge.setDate(dto.getDate());
        discharge.setStatus(dto.getStatus());
        discharge.setSignature(dto.getSignature());
        Optional<Utilisateur> user = userRepository.findByUsername(dto.getUsername());
        user.ifPresent(discharge::setUser);
        discharge.setOrganizationUnit(organizationUnit);
        discharge.setSelectedChild(
                OrganizationUnitMapper.INSTANCE.toEntity(dto.getSelectedChild())
        );


        discharge.setMaterials(
                dto.getMaterials().stream()
                        .map(materialMapper::toEntity)
                        .collect(Collectors.toList())
        );

        return discharge;
    }
}
