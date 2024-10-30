package net.java.Training_management.mappers;

import net.java.Training_management.dtos.UserDTO;
import net.java.Training_management.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {DischargeMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "discharges", target = "discharges")
    UserDTO toDto(Utilisateur utilisateur);

    @Mapping(source = "discharges", target = "discharges")
    Utilisateur toEntity(UserDTO userDTO);
}

