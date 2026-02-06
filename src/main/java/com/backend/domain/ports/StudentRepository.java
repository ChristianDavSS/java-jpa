package com.backend.domain.ports;

import com.backend.domain.entity.Student;

public interface StudentRepository {
    Student getById(Long id);
    Student save(Student student);
    void update(Student student);
    void deleteById(Long id);
    Boolean existsById(Long id);
}
