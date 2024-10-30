package net.java.Training_management.mappers;

import net.java.Training_management.dtos.LocalDTO;
import net.java.Training_management.entities.Local;
import org.springframework.stereotype.Component;

@Component
public class LocalMapper {

    public LocalDTO toLocalDTO(Local local) {
        if (local == null) {
            return null;
        }

        LocalDTO localDTO = new LocalDTO();
        localDTO.setLocalId(local.getLocalId());
        localDTO.setName(local.getName());

        return localDTO;
    }

    public Local toLocalEntity(LocalDTO localDTO) {
        if (localDTO == null) {
            return null;
        }

        Local local = new Local();
        local.setLocalId(localDTO.getLocalId());
        local.setName(localDTO.getName());

        return local;
    }
}
