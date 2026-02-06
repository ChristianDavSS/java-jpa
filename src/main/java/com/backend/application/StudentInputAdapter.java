package com.backend.application;

import com.backend.domain.entity.Student;
import com.backend.domain.ports.Repository;

/**
 * StudentInputAdapter is an adapter that executes the repository methods defined by an infrastructure
 * */
public class StudentInputAdapter {
    private final Repository<Student, Long> repository;
    public StudentInputAdapter(Repository<Student, Long> repository) {
        this.repository = repository;
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public Student getById(Long id) {
        return repository.getById(id);
    }
}
