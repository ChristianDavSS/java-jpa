package com.backend.domain.ports;

import com.backend.domain.entity.Subject;

public interface SubjectRepository {
    Subject getById(Long id);
    Subject save(Subject subject);
    void update(Subject subject);
    void deleteById(Long id);
    Boolean existsById(Long id);
}
