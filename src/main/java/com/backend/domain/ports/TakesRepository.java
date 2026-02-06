package com.backend.domain.ports;

import com.backend.domain.entity.Takes;
import com.backend.domain.entity.TakesPK;

public interface TakesRepository {
    Takes getById(TakesPK id);
    Takes save(Takes takes);
    void update(Takes takes);
    void deleteById(Long id);
    Boolean existsById(Long id);
}
