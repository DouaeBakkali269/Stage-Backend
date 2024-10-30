package net.java.Training_management.services;

import net.java.Training_management.dtos.LocalDTO;

import java.util.List;

public interface LocalService {

    LocalDTO createLocal(LocalDTO localDTO);

    LocalDTO updateLocal(Integer id, LocalDTO localDTO);

    void deleteLocal(Integer id);

    LocalDTO getLocalById(Integer id);

    List<LocalDTO> getAllLocals();
}
