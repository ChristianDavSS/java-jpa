package com.backend.application;

import com.backend.domain.entity.Takes;
import com.backend.domain.entity.TakesPK;
import com.backend.domain.ports.Repository;

import java.util.List;

public class TakesService {
    private final Repository<Takes, TakesPK> takesRepository;

    public TakesService(Repository<Takes, TakesPK> takesRepository) {
        this.takesRepository = takesRepository;
    }

    public List<Takes> getAll() {
        return this.takesRepository.getAll();
    }

    public Takes save(Takes takes) {
        return this.takesRepository.save(takes);
    }

    public void update(Takes takes) {
        this.takesRepository.update(takes);
    }

    public Takes getById(TakesPK id) {
        return this.takesRepository.getById(id);
    }

    public void deleteById(TakesPK id) {
        this.takesRepository.deleteById(id);
    }

    public Boolean existsById(TakesPK id) {
        return this.takesRepository.existsById(id);
    }
}
