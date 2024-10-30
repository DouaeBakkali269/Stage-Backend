package net.java.Training_management.services.impl;

import net.java.Training_management.dtos.LocalDTO;
import net.java.Training_management.entities.Local;
import net.java.Training_management.mappers.LocalMapper;
import net.java.Training_management.repositories.LocalRepository;
import net.java.Training_management.services.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocalServiceImpl implements LocalService {

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LocalMapper localMapper;

    @Override
    public LocalDTO createLocal(LocalDTO localDTO) {
        Local local = localMapper.toLocalEntity(localDTO);
        local = localRepository.save(local);

        return localMapper.toLocalDTO(local);
    }

    @Override
    public LocalDTO updateLocal(Integer id, LocalDTO localDTO) {
        Local local = localRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Local not found"));

        local.setName(localDTO.getName());

        local = localRepository.save(local);

        return localMapper.toLocalDTO(local);
    }

    @Override
    public void deleteLocal(Integer id) {
        localRepository.deleteById(id);
    }

    @Override
    public LocalDTO getLocalById(Integer id) {
        Local local = localRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Local not found"));
        return localMapper.toLocalDTO(local);
    }

    @Override
    public List<LocalDTO> getAllLocals() {
        List<Local> locals = localRepository.findAll();
        return locals.stream().map(localMapper::toLocalDTO).collect(Collectors.toList());
    }
}
